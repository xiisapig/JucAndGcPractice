package cn.ypjalt.interview.study.thread;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread2 implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "====come in callable");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}

class MyThread3 extends Thread {
    @Override
    public void run() {
        System.out.println("sss");
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask, "AA");
        t1.start();
        new Thread(futureTask2, "BB").start();
        System.out.println(Thread.currentThread().getName() + "====");// 建议放在最后，如果没有计算完成就要去要求，会导致阻塞，直到计算完成
        int re2 = 10;
        while (!futureTask.isDone()) {

        }
        int re1 = futureTask.get();
        System.out.println(re1 + re2);
    }
}
