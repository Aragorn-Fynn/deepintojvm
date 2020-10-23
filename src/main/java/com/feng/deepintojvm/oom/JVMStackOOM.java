package com.feng.deepintojvm.oom;

/**
 * VM args: -Xss2M
 */
public class JVMStackOOM {

    private void dontStop() {
        while (true) {}
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    /**
     * 结果：系统假死
     * 异常信息：Exception in thread "main" java.lang.OutOfMemoryError: unable to create native thread
     * @param args
     */
    public static void main(String[] args) {
        JVMStackOOM oom = new JVMStackOOM();
        oom.stackLeakByThread();
    }
}
