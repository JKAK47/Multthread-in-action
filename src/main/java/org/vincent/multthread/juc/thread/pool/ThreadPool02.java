package org.vincent.multthread.juc.thread.pool;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author PengRong
 * @package org.vincent.multthread.juc.thread.pool
 * @date 2019/3/17 - 11:00
 * @ProjectName Multthread-in-action
 * @Description: 自己配置线程池参数
 */
public class ThreadPool02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = null;

        /**
         * 自定义一个自定义的 线程池类 (ThreadPoolExecutor),定义一个 ThreadPoolExecutor的匿名内部类
         * 并重写钩子方法 beforeExecute， afterExecute
         * 主要改造点：
             * 创建工作线程的线程工厂
             * 阻塞队列 ：SynchronousQueue， ArrayBlockingQueue
             * 自定义拒绝策略
             * 重写ThreadPoolExecutor类中两个钩子方法 beforeExecute， afterExecute
         *
         * */
        executorService = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1),
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
                System.out.println("线程 name ="+t.getName() + " 开始执行任务..."+r.getClass().getSimpleName());
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(r.getClass().getSimpleName() + "线程执行后...");
                super.afterExecute(r, t);
            }
        };

        /**
         * 使用自己修改后的的线程池来实现线程池功能
         * */
        /**
         * ExecutorService.submit
         * 提交runnable类型任务,第二个参数是该任务成功执行将返回的 结果实例result,
         * 该实例的类型将是返回的Future 类型的泛型参数类型
         * submit方法将一个runnable接口实例适配成callable接口类型。
         *
         * */
        Future<String> future = executorService.submit(() -> {
            System.out.println("runnable-submit");
            System.out.println("2");
        }, "submit-runnable-task-result");
        System.out.println(future.get());

        /**
         * ExecutorService.submit
         * 提交callable接口类型任务，任务直接有返回值。
         * Future的泛型参数必须是callable接口call方法返回值类型
         *
         * */
        Future<String> future1 = executorService.submit(() -> {
            System.out.println("callable-submit");
            return "submit-Callable-task";
        });
        System.out.println(future1.get());
        /**
         * ExecutorService.execute 并没有返回值，submit提交runnable 接口类型任务可以具有返回值
         * execute 方法只能提交 runnable接口类型的任务
         * */
        executorService.execute(() -> {
            System.out.println("execute-only-submit-runnableTask");
            System.out.println("3");
        });

        for (int i=0;i<1000;i++){
            executorService.execute(()-> System.out.println("i ="+100));
        }
        executorService.shutdown();
    }


}
