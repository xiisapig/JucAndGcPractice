package cn.ypjalt.interview.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    // 1判断
    public void print5() {
        lock.lock();
        try {
            // 1 判断
            while (num != 1) {
                c1.await();
            }
            // 2 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + num);
            }
            // 3 通知
            num = 2;
            c2.signal();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print10() {
        lock.lock();
        try {
            // 1 判断
            while (num != 2) {
                c2.await();
            }
            // 2 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + num);
            }
            // 3 通知
            num = 3;
            c3.signal();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print15() {
        lock.lock();
        try {
            // 1 判断
            while (num != 3) {
                c3.await();
            }
            // 2 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + num);
            }
            // 3 通知
            num = 1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}
