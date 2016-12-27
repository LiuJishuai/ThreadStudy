package com.jeyson.thread.thread2.safe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @Message:  created by Jeyson on 2016年12月26日
 * 
 * @Description:
 *  Lock锁跟synchronized最大的不同，是执行完毕后，要关闭锁（unlock()),如果不关闭，其他线程就永远锁在外面。synchronized是JVM层面实现，系统可以监控锁的释放与否，而ReentrantLock
 *  是使用代码实现的，系统无法自动释放锁，所以必须有finally代码中显示的写出来释放代码：lock.unlock();
 */
public class SimpleThreadLock {
     public static void main(String[] args) {
		new SimpleThreadLock().init();
	}
     private void init(){
    	final Outputer outputer=new Outputer();
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("teemo");
					
				}
				
			}
		}).start();
    	
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("aishe");
					
				}
				
			}
		}
    			).start();
     }
     //带锁的输出类
     //没有锁的话，会把teemo和aishe单词有些会混合
     static class Outputer{
    	 Lock lock=new ReentrantLock();
    	 public void output(String name){
    		 int len=name.length();
    		 lock.lock();
    		 try {
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				lock.unlock();
			}
    	 }
     }
}

