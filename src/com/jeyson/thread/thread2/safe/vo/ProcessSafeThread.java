package com.jeyson.thread.thread2.safe.vo;
/**
 *
 * @Message:  created by Jeyson on 2016年12月21日
 * 
 * @Description: 安全的多线程实例,synchronized加一个锁即可
 * 
 * synchronized不能用于变量和构造函数
 * 尽量锁定比较小的范围
 * 方法同步的时候，锁定的是对象，如果同步的方法是静态方法，锁定的是类
 * 不应该锁定常量池对象，比如String对象
 *    建议使用同步代码块，而不是同步方法
 *    建议额外创建一个私有对象来锁定
 *       该对象不可共有，因为共有可以修改，就无法确保唯一锁定
 *       该对象不应该对外提供set方法，这样也可以修改引用对象，可以任意的秉性执行同步代码块中的内容
 */
public class ProcessSafeThread implements Runnable {
	private int count;
	private Object obj=new Object();
    
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
			synchronized (obj) {
				count++;
			}
			
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

