package org.vincent.multthread.juc.thread;

/**
 * @author PengRong
 * @package org.vincent.multthread.juc.atomic
 * @date 2019/3/16 - 23:34
 * @ProjectName Multthread-in-action
 * @Description: 继承 Thread 启动线程
 */
public class Thread01 extends  Thread {
    @Override
    public void run() {
        System.out.println("sdfasdff");
    }

    public static void main(String[] args) {
        /** 方法调用 不是启动线程*/
        new  Thread01().run();;
        /** 线程启动*/
        new  Thread01().start();
        System.out.println("");
    }
}
