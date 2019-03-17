package org.vincent.blockqueue.arrayblockingqueue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Package: org.vincent.multthread.BlockingQueue.ArrayBlockingQueue <br/>
 * @Description： 基于 BlockingQueue 的 ArrayBlockingQueue 案例 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/9/3 0:50 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/9/3. <br/>
 */

public class BlockingQueueExample {
		public static void main(String[] args) throws InterruptedException {
				BlockingQueue queue = new ArrayBlockingQueue(1024);
				System.out.println("java.io.tmpdir property: " + System.getProperty("java.io.tmpdir"));
				Producer producer = new Producer(queue);
				Consumer consumer = new Consumer(queue);
				new Thread(producer).start();
				new Thread(consumer).start();
				Thread.sleep(4000);
		}
}
