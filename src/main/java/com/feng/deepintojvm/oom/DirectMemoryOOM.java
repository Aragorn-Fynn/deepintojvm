package com.feng.deepintojvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM Args： -Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * @author zzm
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    /**
     * 直接内存溢出
     * 异常：Exception in thread "main" java.lang.OutOfMemoryError
     * 	at sun.misc.Unsafe.allocateMemory(Native Method)
     * 	at com.feng.deepintojvm.oom.DirectMemoryOOM.main(DirectMemoryOOM.java:21)
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
