package com.feng.deepintojvm.gc;

public class BigObjectAllocation {

    private static final int _1MB = 1024 * 1024;
    /**
     * VM参数： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[6 * _1MB]; //直接分配在老年代中
    }


    /**
     * Heap
     *  PSYoungGen      total 9216K, used 2350K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 8192K, 28% used [0x00000000ff600000,0x00000000ff84b990,0x00000000ffe00000)
     *   from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     *   to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
     *  ParOldGen       total 10240K, used 6144K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     *   object space 10240K, 60% used [0x00000000fec00000,0x00000000ff200010,0x00000000ff600000)
     *  Metaspace       used 3440K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 374K, capacity 388K, committed 512K, reserved 1048576K
     *
     * 我们看到Eden空间几乎没有被使用， 而老年代的10MB空间被使用了40%，
     * 4MB的allocation对象直接就分配在老年代中， 这是因为XX： PretenureSizeThreshold被设置为3MB（就是3145728， 这个参数不能与
     * -Xmx之类的参数一样直接写3MB） ， 因此超过3MB的对象都会直接在老年代进行分配。
     * @param args
     */
    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }
}
