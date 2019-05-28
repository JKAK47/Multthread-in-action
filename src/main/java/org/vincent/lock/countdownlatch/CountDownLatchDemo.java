package org.vincent.lock.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author PengRong
 * @package org.vincent.lock.countdownlatch
 * @ClassName CountDownLatchDemo.java
 * @date 2019/4/15 - 7:09
 * @ProjectName Multthread-in-action
 * @Description: CountDownLatch 示例
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        /** 定义了 16 个任务 .*/
        int N = 16;
        /** 所有子线程结束的信号*/
        CountDownLatch doneSignal = new CountDownLatch(N);
        /** 所有子线程开始运行的信号，调用方给的一个启动信号 。*/
        CountDownLatch startSignal = new CountDownLatch(1);
        ExecutorService e = Executors.newFixedThreadPool(8);

        // 创建 N 个任务，提交给线程池来执行
        for (int i = 0; i < N; ++i) // create and start threads
            e.execute(new WorkerRunnable(startSignal, doneSignal, i));

        System.out.println("begin startSignal");
        /** // 因为这里 count == 1，所以，只要调用一次，那么所有的 await 方法都会唤醒 */
        startSignal.countDown();/** 启动所有子线程*/
        System.out.println("stop startSignal");
        /**
         *  等待所有的子线程任务完成，这个方法才会返回
         */
        doneSignal.await();           // wait for all to finish
        System.out.println("main end.");

        /** 关闭线程继续提交任务 */
        e.shutdown();
        /** 等待线程池执行已存在的任务*/
        boolean flag = e.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println(flag);
    }


}
