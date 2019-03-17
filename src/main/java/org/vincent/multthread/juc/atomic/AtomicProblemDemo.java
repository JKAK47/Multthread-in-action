package org.vincent.multthread.juc.atomic;

/**
 * @author PengRong
 * @package org.vincent.lock
 * @date 2019/2/23 - 9:34
 * @ProjectName Multthread-in-action
 * @Description: 多线程原子性问题暴露， 使用 volatile 关键字也不能解决问题
 */

public class AtomicProblemDemo {
    volatile  int i=0;
    public static void main(String[] args) throws InterruptedException {

       AtomicProblemDemo demo =new AtomicProblemDemo();
       for (int i=0; i<5;i++){
           new  Thread(()->{
               for (int j=0;j<10000;j++){
                   demo.add();
               }
           }).start();
       }
       Thread.yield();
       Thread.sleep(2000L);
        System.out.println(demo.i);

    }

    private void add() {
        i++;
    }
}
