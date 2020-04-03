package cn.ypjalt.interview.study.jvm.oom;

public class MetaspaceOOMDemo {
    static class OOMTest {
    }

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            try {
                i++;
                OOMTest oomTest = new OOMTest();

            } catch (Throwable e) {
                System.out.println("多少次发生异常+" + i);
                e.printStackTrace();
            }
        }

    }
}
