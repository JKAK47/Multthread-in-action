package org.vincent.multthread.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author PengRong
 * @package org.vincent.multthread.juc.thread
 * @date 2019/3/17 - 9:23
 * @ProjectName Multthread-in-action
 * @Description: Callable 接口异步获取子线程执行结果，泛型是指定子线程执行后返回值结果类型
 */
public class Callable01 implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("asdawasdlfkjasdfkl");
        return "call";
    }

    public static void main(String[] args) throws Exception {
        /** 普通方法调用*/
        new Callable01().call();
        /** 创建继承Callable接口 方式创建子线程
         * 需要通过FutureTask 类将一个Callable 接口类适配成Runnable 接口实现
         * FutureTask 类run方法调用Callable接口call方法，run方法中调用call方法执行完了之后
         * 调用FutureTask.set方法设置call方法结果对象 outcome，并更新 FutureTask.state状态( NEW -> COMPLETING -> NORMAL )
         * 调用方线程通过调用get方法异步获取结果对象outcome，(异步机制 是通过 FutureTask 类 的状态流转实现的，当任务流转到 NORMAL 状态将获取到结果集，之前都是阻塞等待结果)
         * */
        FutureTask task = new FutureTask(new Callable01());
        new Thread(task).start();
        /** Callable接口线程执行结果，通过异步方式获取 */
        System.out.println(task.get());
    }
}
