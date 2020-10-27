package com.feng.deepintojvm.gc;

public class MinorGC {
    private static final int _1MB = 1024 * 1024;
    /**
     * VM参数： -XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        System.out.println(1);
        allocation1 = new byte[2 * _1MB];
        System.out.println(2);
        allocation2 = new byte[2 * _1MB];
        System.out.println(3);
        allocation3 = new byte[2 * _1MB];
        System.out.println(4);
        allocation4 = new byte[4 * _1MB];// 出现一次Minor GC
        System.out.println(5);
    }

    /**
     * 1
     * 2
     * 3
     * [GC (Allocation Failure) [PSYoungGen: 6282K->808K(9216K)] 6282K->4904K(19456K), 0.0140960 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
     * 4
     * 5
     * Heap
     *  PSYoungGen      total 9216K, used 7191K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 8192K, 77% used [0x00000000ff600000,0x00000000ffc3beb8,0x00000000ffe00000)
     *   from space 1024K, 78% used [0x00000000ffe00000,0x00000000ffeca020,0x00000000fff00000)
     *   to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     *  ParOldGen       total 10240K, used 4096K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     *   object space 10240K, 40% used [0x00000000fec00000,0x00000000ff000020,0x00000000ff600000)
     *  Metaspace       used 3443K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 374K, capacity 388K, committed 512K, reserved 1048576K
     *
     *   allocation4分配内存时， 发现Eden已经被占用了6MB， 剩余空间已不足以分配allocation4所需的4MB内存， 因此发生Minor GC。
     *   垃圾收集期间虚拟机又发现已有的三个2MB大小的对象全部无法放入Survivor空间（Survivor空间只有1MB大小） ， 所以只好通过分
     *   配担保机制提前转移到老年代去。
     * @param args
     */
    public static void main(String[] args) {
        testAllocation();
    }
}
