package org.vincent.multthread.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author PengRong
 * @package org.vincent.multthread.juc.atomic
 * @date 2019/2/23 - 11:34
 * @ProjectName Multthread-in-action
 * @Description: AtomicProblemDemo.java案例已经说明了volatile不能保证原子性  juc 提供的原子操作类保证对变量操作的线程安全
 */
public class AtomicOperation {
    AtomicInteger i =new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {

        AtomicOperation demo =new AtomicOperation();
        for (int i=0; i<5;i++){
            new  Thread(()->{
                for (int j=0;j<10000;j++){
                    demo.add();
                }
            }).start();
        }
        Thread.yield();
        Thread.sleep(2000L);
        System.out.println(demo.i);

    }

    private void add() {
        /* 线程安全自增 1 到当前值 并返回自增后的值 */
        i.incrementAndGet();
        /* 线程安全自增 指定的值到当前值 并返回原来的旧值 */
        //i.getAndAdd(1);
    }
}
