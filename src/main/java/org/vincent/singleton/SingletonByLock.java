package org.vincent.singleton;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author PengRong
 * @package org.vincent.lock
 * @ClassName SingletonByLock.java
 * @date 2019/4/27 - 10:21
 * @ProjectName Multthread-in-action
 * @Description: 基于Lock 实现单例
 */
public class SingletonByLock {
    private static volatile SingletonByLock instance;
    private static ReentrantLock lock = new ReentrantLock();

    private SingletonByLock() {
    }

    public static SingletonByLock getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                /** 如果 instance 未初始化 ，就初始化一下，volatile可以保证可见性 */
                if (instance == null) {
                    instance = new SingletonByLock();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        SingletonByLock singletonByLock =SingletonByLock.getInstance();
        SingletonByLock singletonByLock1 =SingletonByLock.getInstance();
        SingletonByLock singletonByLock2 =SingletonByLock.getInstance();
        SingletonByLock singletonByLock3 =SingletonByLock.getInstance();
        SingletonByLock singletonByLock4 =SingletonByLock.getInstance();
        if (singletonByLock == singletonByLock1 && singletonByLock1 == singletonByLock2 && singletonByLock2 == singletonByLock3
                && singletonByLock3 == singletonByLock4){
            System.out.println(" 实现单例模式");
        }
    }
}
