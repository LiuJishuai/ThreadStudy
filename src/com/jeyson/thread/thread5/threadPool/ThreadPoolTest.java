package com.jeyson.thread.thread5.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.jeyson.thread.thread5.threadPool.vo.MonitorThread;
import com.jeyson.thread.thread5.threadPool.vo.RejectedExecutionHanderImpl;
import com.jeyson.thread.thread5.threadPool.vo.SimpleWorkThread;

import sun.util.logging.resources.logging;

/**
 *
 * @Message:  created by Jeyson on 2016年12月22日
 * 
 * @Description:
 */
public class ThreadPoolTest {
	/**
	 * 简单线程池的应用测试
	 */
   @Test
	public void simpleThreadPoolTest(){
	 //规定只有5个线程的线程池，其他进入的线程只能在这5个线程结束后才能进入
		ExecutorService executor=Executors.newFixedThreadPool(5);
		for(int i=0;i<10;i++){
			Runnable worker=new SimpleWorkThread("teemo:"+i);
			executor.execute(worker);
		}
		//executor.shutdown();//有序关闭，不再接收新线程
       
        executor.shutdownNow();//打断所有线程休眠等状态，立即运行完毕结束
		
		while (!executor.isTerminated()) {
			//只有执行了shutdown or shutdownNow这个isTerminated才能为true
			//System.out.println("线程没有全部关闭");
		}
		
		System.out.println("Finish");
	}
   @Test
   public void threadPoolExecutorTest() throws InterruptedException{
	   //处理拒绝的线程
	  RejectedExecutionHandler handler=new RejectedExecutionHanderImpl() ;
	  //线程工厂
	  ThreadFactory factory=Executors.defaultThreadFactory();
	  //创建一个线程池
	  ThreadPoolExecutor pool=new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), factory, handler);
	  //开启监视线程
	  MonitorThread monitor=new MonitorThread(pool, 3);
	  Thread monitorThread=new Thread(monitor);
	  monitorThread.start();
	  
	  for(int i=0;i<10;i++){
		  pool.execute(new SimpleWorkThread("teemo:"+i));
	  }
	  
	  Thread.sleep(30000);
	  pool.shutdown();
	  Thread.sleep(5000);
	  monitor.exit();
   }
}

