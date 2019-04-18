package org.vincent.multthread.notify;

import java.util.Random;

/**
 * @author PengRong
 * @package org.vincent.multthread.notify.wait
 * @ClassName Producer.java
 * @date 2019/3/23 - 14:04
 * @ProjectName Multthread-in-action
 * @Description: 生产者 提交数据
 */
public class Producer extends Thread {

    private Object lock;

    public Producer(Object lock) {
        this.lock = lock;
    }

    /**
     * 生产者线程 提供 提交任务接口
     *
     * @param task
     */
    public void addTask(String task) {
        if (isAlive() && DateSource.getDateSize() < DateSource.CAPACITY) {
            String content = this.getClass().getSimpleName() + " : " + new Random().nextInt() + task;
            System.out.println(content);
            DateSource.setDate(content);
        }
    }

    /**
     * 生产者线程run方法只是负责同步事宜
     */
    @Override
    public void run() {
        try {
            while (true) {
                synchronized (lock) {
                    /** 只在 容器超过容器设置容量后才需要同步协调 */
                    if (DateSource.getDateSize() >= DateSource.CAPACITY) {
                        System.out.println(this.getClass().getSimpleName() + " 超过容量了生产者休息下，否则继续生产消息，现在消息数量 :" + DateSource.getDateSize());
                        lock.wait();/** 超过容量了生产者休息下，否则继续生产消息*/
                        System.out.println(this.getClass().getSimpleName() + " 退出休息区，继续生产消息");
                    } else if (DateSource.getDateSize() > 0) {
                        lock.notifyAll();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
