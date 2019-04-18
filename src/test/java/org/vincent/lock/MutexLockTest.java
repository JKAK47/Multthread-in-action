package org.vincent.lock;

/**
 * @author PengRong
 * @package org.vincent.lock
 * @ClassName MutexLockTest.java
 * @date 2019/4/13 - 19:39
 * @ProjectName Multthread-in-action
 * @Description: 自定义互斥不可重入锁测试类
 */
public class MutexLockTest {

    public static void main(String[] args) throws InterruptedException {
        MutexLock lock = new MutexLock();
        /*测试 互斥锁*/

        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(() -> {
                try {
                    lock.lock();/*获取锁，阻塞性*/
                    System.out.println("threadName: " + Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();/*释放锁*/
                }
            });
            thread.start();
            thread.join();
        }
        /*测试锁是不可重入的 */
        /*第一次尝试获取锁 返回true，第二次获取锁返回false */
        boolean flag = lock.tryLock();
        System.out.println(flag);
        flag = lock.tryLock();
        System.out.println(flag);

    }
}
