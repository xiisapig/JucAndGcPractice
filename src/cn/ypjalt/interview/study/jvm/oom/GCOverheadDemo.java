package cn.ypjalt.interview.study.jvm.oom;

import java.util.ArrayList;
import java.util.List;

public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(i++).intern());
            }

        } catch (Throwable e) {
            System.out.println("**i" + i);
            e.printStackTrace();
            throw e;
        }
    }
}
