package com.feng.deepintojvm.oom;

import java.util.HashSet;
import java.util.Set;

/**
 * VM args: -XX:PermSize=6M -XX:MaxPermSize=6M
 */
public class RuntimeConstantPoolOOM {

    /**
     * 1. (jdk-1.6): Exception in thread "main" java.lang.OutOfMemoryError: PermGen space，说明运行时常量池的确是属于方法区
     *
     * 2. (jdk-1.7+): Exception in thread "main" java.lang.OutOfMemoryError: Java heap space, 出现这种变化，
     * 是因为自JDK 7起， 原本存放在永久代的字符串常量池被移至Java堆之中， 所以在JDK 7及以上版本， 限制方法区的容
     * 量对该测试用例来说是毫无意义的。
     * @param args
     */
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        short i=0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
