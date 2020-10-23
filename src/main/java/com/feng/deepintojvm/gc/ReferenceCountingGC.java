package com.feng.deepintojvm.gc;

/**
 * VM args: -XX:+PrintGCDetails
 */
public class ReferenceCountingGC {

    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    /**
     * 这个成员属性的唯一意义就是占点内存， 以便能在GC日志中看清楚是否有回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];
    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        // 假设在这行发生GC， objA和objB是否能被回收？
        System.gc();
    }

    /**
     * 输出信息：[GC (System.gc()) [PSYoungGen: 7014K->792K(56320K)] 7014K->800K(184832K), 0.0084800 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
     * 表明jvm对objA和objB进行了回收， 说明jvm不是使用引用计数算法进行GC的
     * @param args
     */
    public static void main(String[] args) {
        testGC();
    }
}
