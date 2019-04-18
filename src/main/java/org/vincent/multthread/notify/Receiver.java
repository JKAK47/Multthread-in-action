package org.vincent.multthread.notify;

/**
 * @author PengRong
 * @package org.vincent.multthread.notify
 * @ClassName Receiver.java
 * @date 2019/3/23 - 14:09
 * @ProjectName Multthread-in-action
 * @Description: 消费者消费数据
 */
public class Receiver extends Thread {
    private Object lock;

    public Receiver(Object lock) {
        this.lock = lock;
    }

    /**
     * 消费者线程提供 获取任务 消费的接口
     */
    public void getTask() {
        if (isAlive() && DateSource.getDateSize() >= 0) {
            System.out.println(this.getClass().getSimpleName() + " getData " + DateSource.getDate());
        }
    }

    /**
     * 消费者线程run方法只是负责同步方法事宜。
     */
    @Override
    public void run() {
        try {
            while (true) {
                synchronized (lock) {
                    if (DateSource.getDateSize() == 0) {
                        System.out.println(this.getClass().getSimpleName() + "无消息消费将休息 begin " + System.currentTimeMillis());
                        lock.wait();
                        System.out.println(this.getClass().getSimpleName() + "有消息消费将退出休息 stop " + System.currentTimeMillis());
                    }
                    if (DateSource.getDateSize() < DateSource.CAPACITY) {
                        System.out.println(this.getClass().getSimpleName() + " notify producer ");
                        lock.notifyAll();
                    }
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
