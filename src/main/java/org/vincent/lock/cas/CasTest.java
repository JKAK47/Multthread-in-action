package org.vincent.lock.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author PengRong
 * @package org.vincent.lock.cas
 * @ClassName CasTest.java
 * @date 2019/4/24 - 7:40
 * @ProjectName Multthread-in-action
 * @Description: 通过一个例子可以可以看到AtomicStampedReference和AtomicInteger的区别。我们定义两个线程，线程1负责将100 —> 110 —> 100，线程2执行 100 —>120，看两者之间的区别
 */
public class CasTest {
    static Integer integer100 = new Integer(100);
    static Integer integer110 = new Integer(110);
    static Integer integer120 = new Integer(120);
    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference(integer100, 1);

    public static void main(String[] args) throws InterruptedException {


        //AtomicInteger
        Thread at1 = new Thread(() -> {
            /** CAS A -> B -> A*/
            atomicInteger.compareAndSet(integer100.intValue(), integer110.intValue());
            atomicInteger.compareAndSet(integer110.intValue(), integer100.intValue());
        });

        Thread at2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);      // at1,执行完
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /** 能检测到上面的做的 A->B->A 操作吗？ 可以操作成功，说明不能避免ABA问题*/
            System.out.println("AtomicInteger:" + atomicInteger.compareAndSet(integer100.intValue(), integer120.intValue()));
        });

        at1.start();
        at2.start();

        at1.join();
        at2.join();

        //AtomicStampedReference

        Thread tsf1 = new Thread(() -> {
            try {
                //让 tsf2先获取stamp，导致预期时间戳不一致
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 预期引用：100，更新后的引用：110，预期标识getStamp() 更新后的标识getStamp() + 1
            boolean compareAndSet = atomicStampedReference.compareAndSet(integer100, integer110, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("first cas "+compareAndSet);
            compareAndSet = atomicStampedReference.compareAndSet(integer110, integer100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("seconds cas "+compareAndSet);
        });

        Thread tsf2 = new Thread(() -> {
            /** tsf2先获取stamp 然后睡眠一段时间产生事件间隔 ，让tsf1 先做 A->B->A; 当再次回到A的时候 标识 stamp 不一致 了。*/
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2);      //线程tsf1执行完
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("AtomicStampedReference:" + atomicStampedReference.compareAndSet(integer100, integer120, stamp, stamp + 1));
        });

        tsf1.start();
        tsf2.start();
    }
}
