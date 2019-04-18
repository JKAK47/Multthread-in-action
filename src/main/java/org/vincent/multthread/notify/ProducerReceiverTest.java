package org.vincent.multthread.notify;

/**
 * @author PengRong
 * @package org.vincent.multthread.notify
 * @ClassName ProducerReceiverTest.java
 * @date 2019/3/23 - 14:31
 * @ProjectName Multthread-in-action
 * @Description: TODO
 */
public class ProducerReceiverTest {
    public static void main(String[] args) {
        Object lock = new Object();
        Producer producer = new Producer(lock);
        Receiver receiver = new Receiver(lock);
        producer.start();
        receiver.start();

        /**
         *
         * 根据生产者提供的提交任务接口提交任务
         *
         * */
        new Thread(() -> {
            while (true) {
                producer.addTask(" from " + Thread.currentThread().getName());
            }
        }).start();
        /**
         *
         * 根据消费者线程提供的去任务接口获取任务消费
         *
         * */
        new Thread(() -> {
            while (true) {
                receiver.getTask();
            }
        }).start();
    }
}
