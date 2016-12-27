package com.jeyson.thread.thread4.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.jeyson.thread.thread4.blockingQueue.vo.Consumer;
import com.jeyson.thread.thread4.blockingQueue.vo.Message;
import com.jeyson.thread.thread4.blockingQueue.vo.Producer;

/**
 *
 * @Message:  created by Jeyson on 2016年12月22日
 * 
 * @Description:BlockingQueue实现的生产者和消费者解决方案
 */
public class ProducerConsumerService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       BlockingQueue<Message> queue=new ArrayBlockingQueue<>(10);
       Producer producer=new Producer(queue);
       Consumer consumer=new Consumer(queue);
       new Thread(producer).start();
       new Thread(consumer).start();
       System.out.println("生产者和消费者大战开始了");
	}

}

