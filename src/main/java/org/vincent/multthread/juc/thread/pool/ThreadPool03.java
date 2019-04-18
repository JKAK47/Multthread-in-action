package org.vincent.multthread.juc.thread.pool;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author PengRong
 * @package org.vincent.multthread.juc.thread.pool
 * @date 2019/3/17 - 11:00
 * @ProjectName Multthread-in-action
 * @Description: 自己配置线程池参数
 */
public class ThreadPool03 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, ClassNotFoundException {
        ThreadPoolExecutor executorService = null;

        /**
         * 自定义一个自定义的 线程池类 (ThreadPoolExecutor),定义一个 ThreadPoolExecutor的匿名内部类
         * 并重写钩子方法 beforeExecute， afterExecute
         * 主要改造点：
         * 创建工作线程的线程工厂
         * 阻塞队列 ：SynchronousQueue， ArrayBlockingQueue 对于大量任务很容易抛异常拒绝执行任务；LinkedBlockingQueue 队列很好，不会抛异常
         * 自定义拒绝策略
         * 重写ThreadPoolExecutor类中两个钩子方法 beforeExecute， afterExecute
         *
         * */
        executorService = new ThreadPoolExecutor(1, 10, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                /** 实现线程池创建线程工厂*/
                r -> {
                    Thread t = new Thread(null, r, "Vincent-pool-" + new Random().nextInt());
                    if (t.isDaemon())
                        t.setDaemon(false);
                    if (t.getPriority() != Thread.NORM_PRIORITY)
                        t.setPriority(Thread.NORM_PRIORITY);
                    return t;
                },
                /** 继承现有的拒绝策略 */
                new ThreadPoolExecutor.AbortPolicy() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                        System.out.println("oo. abort policy");
                        super.rejectedExecution(r, e);
                    }
                })
                /**
                 * 创建 ThreadPoolExecutor 的匿名内部类并重写其中的两个钩子方法
                 * */
        {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("线程 name =" + t.getName() + " 开始执行任务..." + r.getClass().getSimpleName());
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(r.getClass().getSimpleName() + "线程执行后...");
                super.afterExecute(r, t);
            }
        };

        executorService.execute(() -> {
            System.out.println("sdfsd");
            throw new IllegalArgumentException("sdfdf");
        });
        executorService.shutdown();
    }


}
