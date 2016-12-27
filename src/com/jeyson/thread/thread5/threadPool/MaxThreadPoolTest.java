package com.jeyson.thread.thread5.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @Message:  created by Jeyson on 2016年12月23日
 * 
 * @Description:
 */
public class MaxThreadPoolTest {
	
   public static void main(String[] args) {
	  //单线程的线程池
	   //ExecutorService threadPool=Executors.newSingleThreadExecutor();
	   //固定大小的线程池
	   //ExecutorService  threadPool=Executors.newFixedThreadPool(3);
	   //可缓存的线程池
	   ExecutorService threadPool=Executors.newCachedThreadPool();
	   //往线程池里放10个任务
	   for(int i=1;i<=10;i++){
		   final int task=i;
		   //执行
		   threadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				for(int j=0;j<10;j++){
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" is working of "+ j+" for task "+task);
				}
				
			}
		});
	   }
	   System.out.println("All task finish");
}
}

