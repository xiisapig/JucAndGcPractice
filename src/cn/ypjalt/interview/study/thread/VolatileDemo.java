package cn.ypjalt.interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// MyData.java ==> MyData.class ==> JVM字节码
class MyData {
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    /**
     * 此时number前面加了关键字修饰
     */
    public  void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addMyAtomic() {
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1.验证volatile的可见性
 * 1.1 假如int number = 0, number变量之前根本没有添加volatile关键字修饰
 * 1.2 添加volatile 可以解决可见性问题
 * <p>
 * 2.验证volatile不保证原子性
 * 2.1 原子性指的是什么
 * 不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割，需要整体完整
 * 要么同时成功，要么同时失败
 * 2.2 不保证原子性案例演示
 * 2.3 why
 * 2.4 如何解决原子性？
 * *加sync
 * *使用juc下的AtomicInteger
 */
public class VolatileDemo {
    public static void main(String[] args) {
        atomVollatile();
//        seeOkVolatile();
    }

    public static void atomVollatile() {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            }, String.valueOf(i)).start();
        }
        // 需要等待上面20个线程都全部计算完成，在用main进程取得最终的结果是看多少
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t int finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t atomicInteger finally number value:" + myData.atomicInteger);
    }

    // 测试可见性
    public static void seeOkVolatile() {
        // 资源类
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t coming in");
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updated number value:" + myData.number);
        }, "AAA").start();
        // 第2个线程就是我们的mian线程
        while (myData.number == 0) {
            // 如果没有加volatile，main线程就一直在这里循环等待，直到number不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t missin is over,main get munber value:" + myData.number);
    }
}
