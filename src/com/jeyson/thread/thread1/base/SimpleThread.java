package com.jeyson.thread.thread1.base;

import org.junit.Test;

import com.jeyson.thread.thread1.base.vo.Notified;
import com.jeyson.thread.thread1.base.vo.Ticket;
import com.jeyson.thread.thread1.base.vo.Waiter;


/**
 *
 * @Message:  created by Jeyson on 2016年12月21日
 * 
 * @Description:
 */
public class SimpleThread {
    
	/**
	 * 简单线程实现
	 */
	@Test
	public void testSimpleImpl(){
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("线程："+Thread.currentThread().getName()+"running");
				
			}
		},"测试线程");
		thread.start();
	}
	/**
	 * 简单线程休眠
	 * @throws InterruptedException 如果时间是负数则抛出该异常
	 * 如果不写时间，会暂停该线程，等待调度
	 */
	@Test
	public void simpleThreadSleep() throws InterruptedException{
		long startTime=System.currentTimeMillis();//开始时间
		Thread.sleep(2000);//休眠2000ms
		System.out.println("Thread sleep time="+(System.currentTimeMillis()-startTime)+"ms");
	}
	/**
	 * 简单的强占线程
	 *  要求 Thread1先执行2000ms后，Thread2再执行，Thread1死后Thread3执行，Thread2最后死亡
	 */
	@Test
	public void testThreadJoin(){
		Thread thread1=new Thread(new MyRunnable(),"Thread1");
		Thread thread2=new Thread(new MyRunnable(),"Thread2");
		Thread thread3=new Thread(new MyRunnable(),"Thread3");
		thread1.start();
		//Thread1先独占2000ms
		try {
			thread1.join(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Thread2开始执行
		thread2.start();
		thread3.start();
		//Thread1执行完再执行Thread3
		try {
			thread1.join();
			//执行完Thread1，让Thread3占领执行
			thread3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	class MyRunnable implements Runnable{

		@Override
		public void run() {
			System.out.println("Thread start :"+Thread.currentThread().getName());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread end :"+Thread.currentThread().getName());
			
		}
		
	}
	
	/**
	 * 简单线程的wait和notify
	 * notify和notifyAll 一个是唤醒一个，一个是唤醒全部
	 * wait可以加时间，时间过后自己唤醒
	 */
	@Test
	public void testWaitAndNotify(){
		//实例用到Ticket类和Waiter类和Notified类，主要涉及生产者消费者问题
		Ticket ticket=new Ticket("机票");
		Waiter waiter1=new Waiter(ticket);
		Thread thread1=new Thread(waiter1,"waiter1");
		thread1.start();
		
		Waiter waiter2=new Waiter(ticket);
		Thread thread2=new Thread(waiter2,"waiter2");
		thread2.start();
		
		Notified notified=new Notified(ticket);
		Thread thread3=new Thread(notified,"notify");
		thread3.start();
		
		
	}
	 
}

