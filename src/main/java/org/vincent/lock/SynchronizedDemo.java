package org.vincent.lock;


/**
 * @author PengRong
 * @package org.vincent.lock
 * @ClassName SynchronizedDemo.java
 * @date 2019/4/9 - 7:23
 * @ProjectName Multthread-in-action
 * @Description: 验证 synchronized 关键字是否是可重入锁 ，验证后：确认 synchronized 是互斥，可重入锁。
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    demo.do1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public synchronized void do1() throws InterruptedException {
        System.out.println("thread " + Thread.currentThread().getName() + "\t\tdo1");
        Thread.sleep(10);
        do2();
        Thread.sleep(10);
    }

    public synchronized void do2() {
        System.out.println("thread " + Thread.currentThread().getName() + "\t\tdo2");
    }

}
