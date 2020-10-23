package com.feng.deepintojvm.oom;

/**
 * VM args: -Xss128k
 */
public class JVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    /**
     * stackoverflow异常， 自己调用自己， 导致虚拟机栈溢出
     * 异常信息：Exception in thread "main" java.lang.StackOverflowError
     *          stack length:993
     *  	    at com.feng.deepintojvm.oom.JVMStackSOF.stackLeak(JVMStackSOF.java:11)
     * @param args
     */
    public static void main(String[] args) {
        JVMStackSOF oom = new JVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:"+oom.stackLength);
            throw e;
        }
    }
}
