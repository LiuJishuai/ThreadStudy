package com.jeyson.thread.thread3.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @Message: created by Jeyson on 2016年12月23日
 * 
 * @Description:  每一个被调度的任务都由线程池中一个线程去执行，任务是并发执行，相互不受干扰
 *   只有到了任务的执行时间，ScheduedExecutor才会真正启动一个线程，其余时间ScheduledExecutor 都是在轮询任务的状态
 *   
 *   scheduleAtFixedRate和scheduleWithFixedDelay的区别在于，每次执行开始的时间一个是上次任务开始时间计算一个时间间隔，一个是上个任务结束时间计算一个时间间隔。后一个取决于任务执行时间的长短
 */
public class MyScheduledExecutor implements Runnable {
	private String name;

	public MyScheduledExecutor(String name) {
		this.name = name;
	}

	@Override
	public void run() {
	
     System.out.println(" now execute : "+name);
     try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public static void main(String[] args) {
		//创建一个可以允许10个线程的调度线程池
		ScheduledExecutorService service=Executors.newScheduledThreadPool(10);
		long delay=1L;
		long perod=1L;
		//现在开始1s以后，每1s执行一次scheduleAtFixedRate
		//每次执行时间为上一次任务开始起向后推一个时间间隔
		//已固定的频率来执行某项任务，固定的频率来执行，某项计划，不受计划执行时间的影响
		service.scheduleAtFixedRate(new MyScheduledExecutor("teemo"), delay, perod, TimeUnit.SECONDS);
		
		long delay2=1L;
		long perod2=1L;
		//从现在开始1s以后，每1s执行一次scheduleWithFixedDelay
		//每次执行时间为上一次任务开始起向后推一个时间间隔
		//相对固定的延迟后，无论某个任务执行多久，等执行完了，再延迟指定时间。受计划执行时间的影响
		service.scheduleWithFixedDelay(new MyScheduledExecutor("aishe"), delay2, perod2, TimeUnit.SECONDS);
	}

}
