package cn.ypjalt.interview.study.thread.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 1 队列
 * 2 阻塞队列
 * 2.1 阻塞队列有没有好的一面
 * 2.2 不得不阻塞，你如何管理
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
//        List list = null;
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a", 2L, TimeUnit.SECONDS);
        blockingQueue.offer("b", 2L, TimeUnit.SECONDS);
        blockingQueue.offer("v", 2L, TimeUnit.SECONDS);
        blockingQueue.offer("d", 2L, TimeUnit.SECONDS);

    }
}
