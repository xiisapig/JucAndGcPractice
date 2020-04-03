package cn.ypjalt.interview.study.jvm.oom;

public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        byte[] bytes = new byte[1024 * 1024 * 80];
    }
}
