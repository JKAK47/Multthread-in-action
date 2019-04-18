package org.vincent.multthread.juc.atomic;


/**
 * @author PengRong
 * @package org.vincent.multthread.juc.atomic
 * @date 2019/2/23 - 11:34
 * @ProjectName Multthread-in-action
 * @Description: SynchronizedDemo 使用 锁 同步技术，达到原子性的目的
 *
 */
public class SynchronizedDemo {
    /* volatile 达到可见性，synchronized 达到操作原子性 */
    volatile int i = 0;
    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo demo = new SynchronizedDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    demo.add();
                }
            }).start();
        }
        Thread.yield();
        Thread.sleep(2000L);
        System.out.println(demo.i);
    }
    /**
     * synchronized 锁实现线程同步，达到原子性
     */
    private void add() {
        synchronized (this){
            i++;
        }
    }
}
