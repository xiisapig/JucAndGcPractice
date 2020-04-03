package cn.ypjalt.interview.study.jvm.gc;

public class HelloGc {
    public static void main(String[] args) {
        /*
        // 返回java虚拟机的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        // 返回java虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("Total_MEMORY(-Xms) = " + totalMemory + "(字节)" + (totalMemory / (double) 1024 / 1024) + "MB");
        System.out.println("Max_MEMORY(-Xmx) = " + maxMemory + "(字节)" + (maxMemory / (double) 1024 / 1024) + "MB");
        */
        System.out.println("*******gc******");
        byte[] bytes = new byte[50 * 1024 * 1024];
    }
}
