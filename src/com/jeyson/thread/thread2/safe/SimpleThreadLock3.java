package com.jeyson.thread.thread2.safe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @Message: created by Jeyson on 2016年12月26日
 * 
 * @Description: 多个Condition来控制线程间的轮转运行，1-2-3-1
 */
public class SimpleThreadLock3 {

	public static void main(String[] args) {
		final Business business = new Business();
		// 线程1
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 4; i++) {
					business.sub1(i);
				}

			}
		}).start();
		// 线程2
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 4; i++) {
					business.sub2(i);
				}

			}
		}).start();

		// 线程3
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 4; i++) {
					business.sub3(i);
				}

			}
		}).start();

	}

	static class Business {
		Lock lock = new ReentrantLock();
		Condition condi1 = lock.newCondition();
		Condition condi2 = lock.newCondition();
		Condition condi3 = lock.newCondition();
		private int subTurn = 1;

		public void sub1(int i) {
			lock.lock();
			try {
				while (subTurn != 1) {
					try {
						condi1.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				for (int j = 1; j <= 5; j++) {
					System.out.println("sub1 is runing at " + j + " of " + i);
				}
				subTurn = 2;
				condi2.signal();
			} finally {
				lock.unlock();
			}
		}

		public void sub2(int i) {
			lock.lock();
			try {
				while (subTurn != 2) {
					try {
						condi2.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				for (int j = 1; j <= 5; j++) {
					System.out.println("sub2 is runing at " + j + " of " + i);
				}
				subTurn = 3;
				condi3.signal();
			} finally {
				lock.unlock();
			}
		}

		public void sub3(int i) {
			lock.lock();
			try {
				while (subTurn != 3) {
					try {
						condi3.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				for (int j = 1; j <= 5; j++) {
					System.out.println("sub3 is runing at " + j + " of " + i);
				}
				subTurn = 1;
				condi1.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}
