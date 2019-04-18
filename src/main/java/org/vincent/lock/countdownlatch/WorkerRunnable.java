package org.vincent.lock.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author PengRong
 * @package org.vincent.lock.countdownlatch
 * @ClassName WorkerRunnable.java
 * @date 2019/4/15 - 7:11
 * @ProjectName Multthread-in-action
 * @Description: TODO
 */
public class WorkerRunnable implements Runnable {
    /**
     * 子任务开始执行标识
     */
    private final CountDownLatch startSignal;
    /**
     * 子任务完成标识
     */
    private final CountDownLatch doneSignal;

    private final int i;

    public WorkerRunnable(CountDownLatch startSignal, CountDownLatch doneSignal, int i) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            // 为了让所有线程同时开始任务，我们让所有线程先阻塞在这里
            // 等大家都准备好了，再打开这个门栓；startSignal 是调用方控制的
            /** 阻塞，等待调用方给信号什么时候所有子线程 可以启动执行任务。*/
            startSignal.await();
            System.out.println("output data WorkerRunnable . i = " + i);
            /** 标记该线程任务完成，调用 countDown 方法*/
            doneSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
