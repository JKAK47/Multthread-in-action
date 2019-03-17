package org.vincent.multthread.volatile00;

/**
 * Multthread-in-action.org.vincent.multthread.volatile00 <br/>
 * Created by PengRong on 2018/7/11. <br/>
 *
 * @author PengRong <br/>
 * @Description Volatile 原子性测试 ,两个线程对同一个变量进行自增1000万次，预期是2000万,但是实际没有 ,volatile 不能保证原子性
 * @ClassName: ${CLASS}
 * @since 2018-07-11 10:24 <br/>
 */
public class VolatileAtom {
    private static volatile long _longVal = 0;

    private static class LoopVolatile implements Runnable {
        @Override
        public void run() {
            long val = 0;
            while (val < 10000000L) {
                _longVal++;
                val++;
            }
        }
    }

    private static class LoopVolatile2 implements Runnable {
        @Override
        public void run() {
            long val = 0;
            while (val < 10000000L) {
                _longVal++;
                val++;
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new LoopVolatile());
        t1.start();

        Thread t2 = new Thread(new LoopVolatile2());
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {
        }
        /*预期是2000万， final val is: 13403859 volatile 不能保证原子性*/
        System.out.println("final val is: " + _longVal);
    }

}
