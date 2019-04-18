package org.vincent.lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/**
 * @author PengRong
 * @package org.vincent.lock
 * @date 2019/2/23 - 9:33
 * @ProjectName Multthread-in-action
 * @Description: 互斥锁不可重入实现，state为0 表示未锁定，state为1 表示锁定。
 * 尝试获取锁时候只能设置state=1，
 * 尝试取消锁时候只能设置state=0
 */
public class MutexLock implements Serializable, Lock {


    /**
     * 自定义同步器,互斥不可重入锁
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        // 判断是否锁定状态
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 尝试获取资源，立即返回。成功则返回true，否则false。
         *
         * @param acquires 申请的资源量 ，为1
         * @return
         */
        public boolean tryAcquire(int acquires) {
            // Assert.state(acquires == 1, "acquires must equal  1"); // 这里限定只能为1个量
            if (compareAndSetState(0, 1)) {//state为0才设置为1，不可重入！
                setExclusiveOwnerThread(Thread.currentThread());//设置为当前线程独占资源
                return true;
            }
            return false;
        }

        /**
         * 尝试释放资源，立即返回。成功则为true，否则false。
         *
         * @param releases 释放的资源量，只能为1
         * @return
         */
        protected boolean tryRelease(int releases) {
            //Assert.state(releases == 1, "releases must equal 1"); // 限定为1个量
            if (getState() == 0)//既然来释放，那肯定就是已占有状态了。只是为了保险，多层判断！
                throw new IllegalMonitorStateException("state == 0");
            if (getState() != 1) {
                throw new IllegalMonitorStateException("state != 1");
            }
            if (getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalMonitorStateException("getExclusiveOwnerThread() != Thread.currentThread()");
            }
            int c = getState() - releases;
            if (c==0){
                setExclusiveOwnerThread(null);
                setState(c);//释放资源，放弃占有状态
                return true;
            }
            return false;
        }

        /**
         * 返回一个条件，必须在同步类中才可以创建成功
         */
        final Condition newCondition() {
            return new AbstractQueuedSynchronizer.ConditionObject();
        }
    }

    // 真正同步类的实现都依赖继承于AQS的自定义同步器！
    private final Sync sync = new Sync();

    //lock<-->acquire。两者语义一样：获取资源，即便等待，直到成功才返回。
    @Override
    public void lock() {
        sync.acquire(1);
    }

    //tryLock<-->tryAcquire。两者语义一样：尝试获取资源，要求立即返回。成功则为true，失败则为false。
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    /**
     * tryLock<-->tryAcquire。两者语义一样：尝试获取资源，可以延迟等待。成功则为true，失败则为false。(最多等待时间：unit.toNanos(time))
     *
     * @param time 最大等待时间数量
     * @param unit 时间单位
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    /**
     * lockInterruptibly <--> acquireInterruptibly;
     * 支持响应中断申请锁资源
     *
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    //unlock<-->release。两者语文一样：释放资源。
    @Override
    public void unlock() {
        sync.release(1);
    }

    //锁是否占有状态
    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
