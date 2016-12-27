package com.jeyson.thread.thread5.threadPool.vo;
/**
 *
 * @Message:  created by Jeyson on 2016年12月22日
 * 
 * @Description:
 */
public class SimpleWorkThread implements Runnable{
    
	private String msg;
	public SimpleWorkThread(String msg) {
		this.msg=msg;
	}
	
	public String getMsg() {
		return msg;
	}

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName()+" start msg="+msg);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println(Thread.currentThread().getName()+" 休眠被打断了");
		}
		System.out.println(Thread.currentThread().getName()+" end");
	}
   
	
}

