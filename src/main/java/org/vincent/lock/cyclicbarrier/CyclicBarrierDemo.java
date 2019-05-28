package org.vincent.lock.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author PengRong
 * @package org.vincent.lock.cyclicbarrier
 * @ClassName CyclicBarrierDemo.java
 * @date 2019/4/18 - 8:13
 * @ProjectName Multthread-in-action
 * @Description: CyclicBarrier使用实例
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        /** 定义一次栅栏会拦截十个线程*/
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10,()->{
            System.out.println("barrier broken.");
        });
        /** 上面定义了十个线程为一个栅栏； 这里必须提交 10 整数倍的任务，多了，少了程序都不能正常退出
         * 90 次，每次 10 个是一个栅栏。
         * */
        for (int i=0;i<90;i++){
            new WorkThread(cyclicBarrier,i+1).start();
        }


    }

    public static class WorkThread extends Thread {
        private final CyclicBarrier barrier;
        private int index;
        public WorkThread(CyclicBarrier barrier,int index) {
            this.barrier = barrier;
            this.index=index;
        }

        @Override
        public void run() {
            try {
                System.out.println("will enter barrien, index="+index);
                System.out.println("will quit barrien, index="+index);
                Thread.sleep(1000);
                barrier.await();//子线程中这个语句一般放在最后，说明子线程工作完成了
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
