package org.vincent.blockqueue.arrayblockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @Package: org.vincent.multthread.BlockingQueue.ArrayBlockingQueue <br/>
 * @Description： 阻塞消费者 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/9/3 0:51 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/9/3. <br/>
 */

public class Consumer implements Runnable{

		protected BlockingQueue queue = null;

		public Consumer(BlockingQueue queue) {
				this.queue = queue;
		}

		@Override
		public void run() {
				try {
						System.out.println(queue.take());
						System.out.println(queue.take());
						System.out.println(queue.take());
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
		}
}
