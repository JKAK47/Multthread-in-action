package org.vincent.multthread.notify.wait;

/**
 * @author PengRong
 * @package org.vincent.multthread.notify.wait
 * @date 2019/3/23 - 11:41
 * @ProjectName Multthread-in-action
 * @Description: 用于测试wait方法和notify方法 等待和通知机制
 */
public class MyThread1Notify extends Thread {
    /**
     * 设置一个对象，作为两个线程之间同步，协调的一把对象锁
     */
    private Object lock;

    public MyThread1Notify(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        /**
         * 同步代码块中或者同步方法中才可以执行 notify 方法
         * 这里使用同步代码块的方式，lock 被两个线程共享作为一把对象锁。
         * 为什么需要这个lock实例，是因为wait方法和notify 方法两个方法执行必须获取一个对象锁，这个对象锁作为一个通知标识，协调调用wait方法的线程和调用notify方法的线程。
         * 而且他们的   等待/通知  机制都是通过这个对象锁达到的
         * */
        synchronized (lock) {
            System.out.println("notify begin time " + System.currentTimeMillis());
            /**
             * 执行wait方法，先获取lock对象锁，执行wait方法 导致当前线程(Mythread1Wait)阻塞，
             * 释放对象锁lock。代码执行至此，等待其他线程调用 lock.notify方法通知当前线程 (Mythread1Wait)唤醒。
             * 如果执行notify方法时候不能获取对象锁，抛运行时异常 IllegalMonitorStateException，
             * */
            lock.notify();// 执行完这个方法后，当前线程就执行到这里，然后线程切换执行其他线程了。
            System.out.println("notify stop time " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        MyThread1Notify notify = new MyThread1Notify(new Object());
        notify.start();
    }
}
