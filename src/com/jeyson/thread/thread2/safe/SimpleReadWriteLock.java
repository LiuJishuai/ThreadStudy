package com.jeyson.thread.thread2.safe;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @Message: created by Jeyson on 2016年12月26日
 * 
 * @Description: 简单的读写锁demo
 */
public class SimpleReadWriteLock {

	public static void main(String[] args) {
		final MyRWClass rwClass = new MyRWClass();
		for (int i = 0; i < 3; i++) {
			// 创建读线程
			new Thread() {
				public void run() {
					while (true) {
						rwClass.getObj();
					}
				}
			}.start();

			// 创建写线程
			new Thread() {
				public void run() {
					while (true) {
						rwClass.putObj("teemo" + new Random().nextInt(100));
					}
				}
			}.start();

		}

	}

}

class MyRWClass {
	// 共享的数据，只能一个线程写该数据，但可以多个线程读该数据
	private Object obj = null;
	// 读写锁
	ReadWriteLock lock = new ReentrantReadWriteLock();

	// 读数据
	public void getObj() {
		// 为了防止读出来写的时候的不完整数据，也要加锁
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " is ready to read data");
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " get data:" + obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.readLock().unlock();
		}

	}

	// 写数据
	public void putObj(Object obj) {
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " is ready to write data");
			Thread.sleep(1000);
			this.obj = obj;
			System.out.println(Thread.currentThread().getName() + " get data:" + obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
	}
}
