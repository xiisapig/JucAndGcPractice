package cn.ypjalt.interview.study.jvm.ref;

/**
 * 强引用
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        // 这样定义就是强引用
        Object obj1 = new Object();
        Object obj2 = obj1;
        obj1 = null;
        System.gc();
        System.out.println(obj2);
    }
}
