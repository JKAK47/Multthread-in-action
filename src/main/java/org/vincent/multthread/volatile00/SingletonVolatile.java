package org.vincent.multthread.volatile00;

/**
 * Multthread-in-action.org.vincent.multthread.volatile00 <br/>
 * Created by PengRong on 2018/7/11. <br/>
 *
 * @author PengRong <br/>
 * @Description Volatatile 单例模式中测试原子性 (${END})
 * @ClassName: ${CLASS}
 * @since 2018-07-11 10:29 <br/>
 */

public class SingletonVolatile {
    /**volatile 放置单例模式下指令重排*/
    private static volatile SingletonVolatile _instance = null;

    private SingletonVolatile() {}

    /**
     * 二次检查锁定/Double Checked Locking的写法
     * @return
     */
    public static SingletonVolatile getInstance() {

        if (_instance == null) {
            synchronized (SingletonVolatile.class){
                if (_instance == null) {

                    _instance = new SingletonVolatile();
                    System.out.println("--initialized once. threadName = "+Thread.currentThread().getName());
                }
            }
        }
        return _instance;
    }

    public static void main(String[] args){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
            SingletonVolatile.getInstance();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                SingletonVolatile.getInstance();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                SingletonVolatile.getInstance();
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                SingletonVolatile.getInstance();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        while (t1.isAlive() || t2.isAlive() || t3.isAlive()|| t4.isAlive()) {

        }
    }
}
