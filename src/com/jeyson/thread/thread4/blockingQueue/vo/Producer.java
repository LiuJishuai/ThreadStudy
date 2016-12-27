package com.jeyson.thread.thread4.blockingQueue.vo;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @Message:  created by Jeyson on 2016年12月22日
 * 
 * @Description:
 */
public class Producer implements Runnable {
	
	private BlockingQueue<Message> queue;
    
	public Producer(BlockingQueue<Message> queue) {
		this.queue=queue;
	}
	@Override
	public void run() {
		//不断的生产
		for(int i=1;i<=50;i++){
			Message message=new Message();
			message.setMsg("msg:"+i);
			try {
				Thread.sleep(i);
				queue.put(message);
				System.out.println("produce："+message.getMsg());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Message message1=new Message();
		message1.setMsg("exit");
		try {
			queue.put(message1);
			System.out.println("produce："+message1.getMsg());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
   
}

