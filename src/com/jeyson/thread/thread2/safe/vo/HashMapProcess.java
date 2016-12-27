package com.jeyson.thread.thread2.safe.vo;
/**
 *
 * @Message:  created by Jeyson on 2016年12月21日
 * 
 * @Description:
 */
public class HashMapProcess implements Runnable {
	
	private String[] str;

	public String[] getStr() {
		return str;
	}

	public void setStr(String[] str) {
		this.str = str;
	}

	public HashMapProcess(String[] str) {
		super();
		this.str = str;
	}
	//定义一个锁啊
	private Object obj=new Object();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		strAdd(Thread.currentThread().getName());
	}
	
	private void strAdd(String name){
		for(int i=0;i<str.length;i++){
			synchronized (obj) {
				//使用最低级别的锁，尽量少的锁代码
				str[i]=str[i]+":"+name;
			}
			
		}
	}

}

