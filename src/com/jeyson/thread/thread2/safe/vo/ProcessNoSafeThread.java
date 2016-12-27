package com.jeyson.thread.thread2.safe.vo;
/**
 *
 * @Message:  created by Jeyson on 2016年12月21日
 * 
 * @Description: 不安全的多线程实例
 */
public class ProcessNoSafeThread implements Runnable {
	private int count;
    
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void run() {
		for(int i=1;i<5;i++){
			doSomething(i);
			count++;
		}
		
	}
	
	private void doSomething(int i){
		try {
			Thread.sleep(i*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
	}
}

