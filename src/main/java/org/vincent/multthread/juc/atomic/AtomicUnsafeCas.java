package org.vincent.multthread.juc.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;


/**
 * @author PengRong
 * @package org.vincent.multthread.juc.atomic
 * @date 2019/2/23 - 11:34
 * @ProjectName Multthread-in-action
 * @Description: AtomicUnsafeCas 自己使用Unsafe 类实现 i变量 的 Cas 操作。达到同步的目的
 *
 */
public class AtomicUnsafeCas {
    /* 使用Unsafe 提供的Cas 操作使用线程安全 */
    volatile int i = 0;

    // Unsafe unsafe =Unsafe.getUnsafe();
    //java.lang.SecurityException: Unsafe  非JDK类不能直接通过静态方法 获取Unsafe 类实例，检查我们的代码是否由主要的类加载器加载。
    private static Unsafe unsafe = null;
    private static long valueOffset;

    /* 静态加载unsafe 实例 */
    static {
        try {
            /* 反射获取 unsafe */
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);// theUnsafe 属性是static ，不用穿对象
            /* 获取变量 i 在对象内存中的相对对象基地址的偏移量 */
            valueOffset = unsafe.objectFieldOffset(AtomicUnsafeCas.class.getDeclaredField("i"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        AtomicUnsafeCas demo = new AtomicUnsafeCas();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    demo.add();
                }
            }).start();
        }
        Thread.yield();
        Thread.sleep(2000L);
        System.out.println(demo.i);

    }

    /**
     * unsafe 类提供的cas 操作能力实现原子性
     */
    private void add() {
        /* 依据unsafe 类提供的  compareAndSwapInt 方法实现CAS 操作 */
        int currentValue = 0;
        do{
            /* 获取当前变量值 */
            currentValue = unsafe.getIntVolatile(this, valueOffset);
        }while (!unsafe.compareAndSwapInt(this, valueOffset, currentValue, currentValue + 1));
    }
}
