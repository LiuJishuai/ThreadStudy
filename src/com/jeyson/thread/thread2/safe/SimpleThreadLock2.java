package com.jeyson.thread.thread2.safe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @Message:  created by Jeyson on 2016年12月26日
 * 
 * @Description: 使用condition
 *   condition中用await代替wait，用signal替代notify，用signalAll代替notifyAll，传统线程的通信方式，Condition都可以实现。
 *   
 *   注意，在等待condition时，可能出现虚假唤醒（spuriouswakeup），所以这里用while循环避免虚假唤醒而不是if条件判断
 */
public class SimpleThreadLock2 {

	public static void main(String[] args) {
		final Business bu = new Business();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<=5;i++){
					bu.sub(i);
				}
				
			}
		}).start();
		//main本身就是一个线程，这样就可以省略new 一个
		for(int i=1;i<=5;i++){
			bu.main(i);
		}

	}
   
	static class Business{
		Lock lock=new ReentrantLock();
		Condition condition=lock.newCondition();
		//创建一个变量控制执行main还是sub
		private boolean isSub=true;
		public void sub(int i){
			lock.lock();
			try {
				//当不是sub时，等待
				while (!isSub) {
					try {
						condition.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				for(int j=1;j<=10;j++){
					System.out.println("sub Thread sequece of "+ j+",loop of "+i);
				}
				isSub=false;
				condition.signal();//相当于
			} finally {
				lock.unlock();
			}
		}
		
		public void main(int i){
			lock.lock();
			try {
				//当isSub==true等待
				while (isSub) {
					try {
						condition.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				for(int j=1;j<=20;j++){
					System.out.println("main Thread sequece of "+ j+",loop of "+i);
				}
				isSub=true;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}

