package org.vincent.lock.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author PengRong
 * @package org.vincent.lock.semaphore
 * @ClassName Parking.java
 * @date 2019/4/27 - 23:30
 * @ProjectName Multthread-in-action
 * @Description: TODO
 */
public class Parking {
    /**
     * 信号量，同步对资源使用 ，代表可以同时有多少个资源被使用
     */
    private Semaphore semaphore;

    /**
     *
     * @param count count 表示同时最多可以有count 个线程使用Parking 实例
     */
    Parking(int count) {
        semaphore = new Semaphore(count);
    }

    /**
     * 进入停车场*/
    public void park() {
        try {
            //获取信号量
            semaphore.acquire();
            long time = (long) (Math.random() * 10);
            System.out.println(Thread.currentThread().getName() + " 进入停车场，停车" + time + "秒...");
            Thread.sleep(time);
            System.out.println(Thread.currentThread().getName() + " 开出停车场...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
