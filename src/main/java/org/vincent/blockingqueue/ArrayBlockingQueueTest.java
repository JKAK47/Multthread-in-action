package org.vincent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author PengRong
 * @package org.vincent.blockingqueue
 * @ClassName ArrayBlockingQueueTest.java
 * @date 2019/4/28 - 23:55
 * @ProjectName Multthread-in-action
 * @Description: 基于数组的阻塞队列
 */
public class ArrayBlockingQueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue =new ArrayBlockingQueue<>(16);
        arrayBlockingQueue.remove();

    }
}
