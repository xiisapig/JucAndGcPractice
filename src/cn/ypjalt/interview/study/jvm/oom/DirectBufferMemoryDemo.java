package cn.ypjalt.interview.study.jvm.oom;

import java.nio.ByteBuffer;

//-XX:MaxDirectMemorySize=5m
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory：" + (sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024) + "MB");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
