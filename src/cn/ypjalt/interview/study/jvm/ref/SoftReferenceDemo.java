package cn.ypjalt.interview.study.jvm.ref;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
    /**
     * 内存够用就保留，不够用就回收
     */
    public static void softRef_Memory_Enough() {
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());

        obj1 = null;
        System.gc();

        System.out.println(obj1);
        System.out.println(softReference.get());


    }

    public static void softRef_Memory_NotEnough() {
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());

        obj1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception w) {

        } finally {
            System.out.println(obj1);
            System.out.println(softReference.get());
        }


    }

    public static void main(String[] args) {
        softRef_Memory_NotEnough();
    }
}
