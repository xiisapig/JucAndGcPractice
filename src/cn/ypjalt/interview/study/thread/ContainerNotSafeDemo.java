package cn.ypjalt.interview.study.thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 聚合类不安全的问题
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        mapNotSafe();


    }

    public static void mapNotSafe() {
//        Map<String, String> map = new HashMap<>();
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
//        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {
//        Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
        new HashSet<>().add("a");
    }

    /**
     * 1 故障现象
     * java.util.ConcurrentModificationException
     * 2 导致原因
     * <p>
     * <p>
     * 3 解决方案
     * new Vector<>();
     * Collections.synchronizedList(new ArrayList<>());
     * new CopyOnWriteArrayList<>();
     * 4 优化建议
     */
    public static void listNotSafe() {
        //        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

    }
}
