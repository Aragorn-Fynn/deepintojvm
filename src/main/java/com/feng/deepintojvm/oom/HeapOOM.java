package com.feng.deepintojvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 异常信息：Dumping heap to java_pid10952.hprof ...
 *          Heap dump file created [28352892 bytes in 0.195 secs]
 *          Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class HeapOOM {
    static class OOMObject {

    }

    /**
     * 堆内存OOM， 由于创建对象太多。
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
