package com.feng.deepintojvm.load;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

    /**
     * 从第一行可以看到这个对象确实是类org.fenixsoft.classloading.ClassLoaderTest实例化出来的， 但在第二行的输出中却发现这个对象
     * 与类org.fenixsoft.classloading.ClassLoaderTest做所属类型检查的时候返回了false。 这是因为Java虚拟机中同时存在了两个
     * ClassLoaderTest类， 一个是由虚拟机的应用程序类加载器所加载的， 另外一个是由我们自定义的类加载器加载的， 虽然它们都来自同一
     * 个Class文件， 但在Java虚拟机中仍然是两个互相独立的类， 做对象所属类型检查时的结果自然为false。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1)+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("com.feng.deepintojvm.load.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.feng.deepintojvm.load.ClassLoaderTest);
    }
}
