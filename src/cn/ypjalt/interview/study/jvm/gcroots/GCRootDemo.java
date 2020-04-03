package cn.ypjalt.interview.study.jvm.gcroots;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 */
public class GCRootDemo {
    private byte[] byteArray = new byte[1024 * 1024 * 1024];

    public static void m1() {
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次gc完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
