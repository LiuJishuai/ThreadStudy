package com.jeyson.thread.thread3.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @Message:  created by Jeyson on 2016年12月22日
 * 
 * @Description: Timer 按照时间安排线程执行
 * Timer的优点在于简单易用，
 * 但由于所有任务都是由同一个线程来调度，因此所有任务都是串行执行的，
 * 同一时间只能有一个任务在执行，
 * 前一个任务的延迟或异常都将会影响到之后的任务。
 * 
 */
public class MyTimerTask extends TimerTask {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TimerTask task=new MyTimerTask();
		//Timer，也是一种线程
		Timer timer=new Timer("Teemo", true);
		//将任务交给时间调度，在日期new Date()开始执行第一次，每5*1000ms执行一次
		timer.scheduleAtFixedRate(task, new Date(), 5*1000);
		
		try {
			Thread.sleep(30*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//30秒后中断执行
		timer.cancel();
		System.out.println("tasker cancel");

	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" start");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" end");
	}

}

