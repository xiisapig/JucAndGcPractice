package cn.ypjalt.interview.study.thread;
import java.util.concurrent.*;

/**
 * 第4种使用java多线程的方式，线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        try {
            for (int i = 0; i < 100; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }

        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    public static void threadPoolInit() {
        ExecutorService threadPoll = Executors.newFixedThreadPool(5);// 一池5个线程
//        ExecutorService threadPoll = Executors.newSingleThreadExecutor();// 一池1个线程
//        ExecutorService threadPoll = Executors.newCachedThreadPool();// 一池n个线程
        // 模拟10个用户来办理业务，每个用户就是一个来自外部的处理线程
        try {
            for (int i = 0; i < 10; i++) {
                threadPoll.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }

        } finally {
            threadPoll.shutdown();
        }
    }
}
