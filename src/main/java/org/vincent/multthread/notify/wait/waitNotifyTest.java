package org.vincent.multthread.notify.wait;

/**
 * @author PengRong
 * @package org.vincent.multthread.notify.wait
 * @date 2019/3/23 - 11:54
 * @ProjectName Multthread-in-action
 * @Description: TODO
 */
public class waitNotifyTest {
    public static void main(String[] args) throws InterruptedException {
        Object lock =new Object();
        Mythread1Wait wait =new Mythread1Wait(lock);
        wait.start();
        Thread.sleep(1000);
        MyThread1Notify notify =new MyThread1Notify(lock);
        notify.start();
    }
}
