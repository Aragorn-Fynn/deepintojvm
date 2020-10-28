package com.feng.deepintojvm.load;

class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}


class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLOWORLD = "hello world";
}

/**
 * -XX：+TraceClassLoading
 */
public class NotInitialization {
    public static void main(String[] args) {
        /**
         * 被动使用类字段演示一：
         * 通过子类引用父类的静态字段， 不会导致子类初始化
         * 对于静态字段，只有直接定义这个字段的类才会被初始化， 因此通过其子类来引用父类中定义的静态字段， 只会触发
         * 父类的初始化而不会触发子类的初始化。 至于是否要触发子类的加载和验证阶段， 在《Java虚拟机规范》 中并未明确规定，
         * 所以这点取决于虚拟机的具体实现。 对于HotSpot虚拟机来说， 可通过-XX：+TraceClassLoading参数观察到此操作是会
         * 导致子类加载的。
         **/
        System.out.println(SubClass.value);
        /**
         * 被动使用类字段演示二：
         * 通过数组定义来引用类， 不会触发此类的初始化
         **/
        SuperClass[] sca = new SuperClass[10];
        /**
         * 被动使用类字段演示三：
         * 常量在编译阶段会存入调用类的常量池中， 本质上没有直接引用到定义常量的类， 因此不会触发定义常量的类的初始化
         **/
        System.out.println(ConstClass.HELLOWORLD);

    }
}
