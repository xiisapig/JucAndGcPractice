package cn.ypjalt.interview.study.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {
    public synchronized void sendSms() {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail()");
    }

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked get()");
            set();
        } finally {
            lock.unlock();
            lock.unlock();
        }

    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked set()");

        } finally {
            lock.unlock();
        }
    }
}

/**
 * 可重用锁（递归锁）
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSms();
        }, "t1").start();
        new Thread(() -> {
            phone.sendSms();
        }, "t2").start();
        System.out.println();
        System.out.println();
        System.out.println();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t3 = new Thread(phone, "t3");
        Thread t4 = new Thread(phone, "t4");
        t3.start();
        t4.start();

    }
}
