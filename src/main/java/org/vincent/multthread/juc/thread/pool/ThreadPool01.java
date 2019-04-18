package org.vincent.multthread.juc.thread.pool;

import org.vincent.multthread.juc.thread.Callable01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author PengRong
 * @package org.vincent.multthread.juc.thread.pool
 * @date 2019/3/17 - 11:00
 * @ProjectName Multthread-in-action
 * @Description: Executors 提供工具类实现的Cache线程池实现
 */
public class ThreadPool01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
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
        Future<String> future1 =executorService.submit(() -> {
            System.out.println("callable-submit");
            return "submit-Callable-task";
        });
        System.out.println(future1.get());
        /**
         * ExecutorService.execute 并没有返回值，submit提交runnable 接口类型任务可以具有返回值
         * execute 方法只能提交 runnable接口类型的任务
         * */
        executorService.execute(()->{
            System.out.println("execute-only-submit-runnableTask");
            System.out.println("3");
        });

        executorService.shutdown();
    }


}
