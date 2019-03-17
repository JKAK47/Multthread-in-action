package org.vincent.multthread.juc.thread;

/**
 * @author PengRong
 * @package org.vincent.multthread.juc.thread
 * @date 2019/3/17 - 9:11
 * @ProjectName Multthread-in-action
 * @Description: 实现 Runnable 接口启动线程
 */
public class Runnable01 implements Runnable{
    @Override
    public void run() {
        System.out.println("asdf");
    }

    public static void main(String[] args) {
        /** 方法调用不是启动线程*/
        new Runnable01().run();
        /** 启动线程*/
        new Thread(new Runnable01()).start();
    }
}
