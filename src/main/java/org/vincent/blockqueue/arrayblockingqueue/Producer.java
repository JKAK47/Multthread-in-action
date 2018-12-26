package org.vincent.blockqueue.arrayblockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @Package: org.vincent.multthread.BlockingQueue.ArrayBlockingQueue <br/>
 * @Description： 阻塞生产者 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/9/3 0:51 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/9/3. <br/>
 */

public class Producer implements Runnable{

		protected BlockingQueue queue = null;

		public Producer(BlockingQueue queue) {
				this.queue = queue;
		}

		@Override
		public void run() {
				try {
						queue.put("1");
						Thread.sleep(1000);
						queue.put("2");
						Thread.sleep(1000);
						queue.put("3");
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
		}
}
