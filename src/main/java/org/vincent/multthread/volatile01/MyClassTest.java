package org.vincent.multthread.volatile01;

/**
 * @author PengRong
 * @package org.vincent.multthread.volatile01
 * @date 2019/2/23 - 11:01
 * @ProjectName Multthread-in-action
 * @Description: 测试 类中一个变量是 volatile 修饰的类，造成该类所有对线程可见的属性都是 volatile 的
 */
public class MyClassTest {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.update(2019, 2, 23);
        int count = 2;

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"\t"+myClass.toString());
                myClass.setDays(26);
                System.out.println(Thread.currentThread().getName()+"\t"+myClass.toString());
                myClass.setMonths(3);
                System.out.println(Thread.currentThread().getName()+"\t"+myClass.toString());

            }
        },"thread1");
        thread1.start();
        Thread thread2 =new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"\t"+myClass.toString());
            }
        },"thread2");
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()){
            System.out.println(Thread.currentThread().getName()+" waiting...");
        }
    }
}
