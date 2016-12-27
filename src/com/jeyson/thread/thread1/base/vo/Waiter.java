package com.jeyson.thread.thread1.base.vo;

/**
 *
 * @Message: created by Jeyson on 2016年12月21日
 * 
 * @Description:
 */
public class Waiter implements Runnable {
	private Ticket ticket;

	public Waiter(Ticket tick) {
		this.ticket = tick;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		synchronized (ticket) {
			System.out.println(name +" start but wait at time="+System.currentTimeMillis());
			try {
				ticket.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name+" notified at time="+System.currentTimeMillis());
		}
		
	}

}
