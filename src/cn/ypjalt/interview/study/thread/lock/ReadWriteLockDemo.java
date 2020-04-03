package cn.ypjalt.interview.study.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 资源类
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    // 只能一个线程写入
    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成：");

        } finally {
            rwLock.writeLock().unlock();
        }

    }

    // 读取可以共享
    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取：" );
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + o);

        } finally {
            rwLock.readLock().unlock();
        }

    }


}

/**
 * 写操作：原子+独占,整个过程必须是一个完整的统一体，中间不许被分割，被打断
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int tempInt = 1;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int tempInt = 1;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}
