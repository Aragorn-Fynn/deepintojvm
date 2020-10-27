package com.feng.deepintojvm.gc;

public class TestTenuringThreshold {

    private static final int _1MB = 1024 * 1024;
    /**
     * VM参数： -XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=0
     * -XX:+PrintTenuringDistribution
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4]; // 什么时候进入老年代决定于XX:MaxTenuringThreshold设置
        allocation2 = new byte[_1MB / 10];
        allocation3 = new byte[7 * _1MB];
        allocation3 = null;
        allocation3 = new byte[7 * _1MB];
    }

    /**
     * 此方法中allocation1对象需要256KB内存， Survivor空间可以容纳。
     * 当-XX： MaxTenuringThreshold=1时， allocation1对象在第二次GC发生时进入老年代，新生代已使用的内存在垃圾收集以后非常干净地变成0KB。
     *
     * 而当-XX： MaxTenuringThreshold=15时，第二次GC发生后， allocation1对象则还留在新生代Survivor空间， 这时候新生代仍然有404KB被占用。
     * @param args
     */
    public static void main(String[] args) {
        testTenuringThreshold();
    }
}
