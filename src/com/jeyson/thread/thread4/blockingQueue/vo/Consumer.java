package com.jeyson.thread.thread4.blockingQueue.vo;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @Message: created by Jeyson on 2016年12月22日
 * 
 * @Description:消费者
 */
public class Consumer  implements Runnable{
	private BlockingQueue<Message> queue;

	public Consumer(BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		Message message;
		try {
			while ((message=queue.take()).getMsg()!="exit") {
				Thread.sleep(10);
				System.out.println("consumed:"+message.getMsg());
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
