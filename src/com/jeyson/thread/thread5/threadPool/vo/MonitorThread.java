package com.jeyson.thread.thread5.threadPool.vo;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @Message:  created by Jeyson on 2016年12月22日
 * 
 * @Description:自己定义的监视线程
 */
public class MonitorThread implements Runnable {
	private ThreadPoolExecutor executor;
	private int seconds;
	private boolean run=true;
    
	public MonitorThread(ThreadPoolExecutor executor,int delay) {
		this.executor=executor;
		this.seconds=delay;
	}
	public void exit(){
		this.run=false;
	}
	@Override
	public void run() {
	 while (run) {
		System.out.println(
				String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
						this.executor.getPoolSize(),
						this.executor.getCorePoolSize(),
						this.executor.getActiveCount(),
						this.executor.getCompletedTaskCount(),
						this.executor.getTaskCount(),
						this.executor.isShutdown(),
						this.executor.isTerminated()
						));
		     try {
				Thread.sleep(seconds*1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		
	  }
		
	}

}

