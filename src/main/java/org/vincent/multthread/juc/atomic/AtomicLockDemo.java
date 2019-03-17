package org.vincent.multthread.juc.atomic;


/**
 * @author PengRong
 * @package org.vincent.multthread.juc.atomic
 * @date 2019/2/23 - 11:34
 * @ProjectName Multthread-in-action
 * @Description: AtomicLockDemo 使用 锁 同步技术，达到原子性的目的
 *
 */
public class AtomicLockDemo {
    /* 使用Unsafe 提供的Cas 操作使用线程安全 */
    volatile int i = 0;


    public static void main(String[] args) throws InterruptedException {

        AtomicLockDemo demo = new AtomicLockDemo();

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
     * 锁实现线程同步，达到原子性
     */
    private void add() {

        synchronized (this){
            i++;
        }
    }
}
