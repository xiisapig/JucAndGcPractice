package cn.ypjalt.interview.study.thread;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.CAS是什么 ? ==> compareAndSet
 * 比较和交换？
 */
public class CSADemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // main do things
        //
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current data:" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current data:" + atomicInteger.get());
    }

}
