package com.jeyson.thread.thread1.base.vo;

/**
 *
 * @Message: created by Jeyson on 2016年12月21日
 * 
 * @Description:
 */
public class Notified implements Runnable {
	private Ticket ticket;

	public Notified(Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public void run() {
		String name=Thread.currentThread().getName();
		synchronized (ticket) {
			System.out.println(name+" is working");
			//ticket.notify();
			ticket.notifyAll();
		}
		

	}

}
