package com.jeyson.thread.thread5.threadPool.vo;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @Message:  created by Jeyson on 2016年12月22日
 * 
 * @Description:定制实现处理无法加入工作队列的任务
 */
public class RejectedExecutionHanderImpl implements RejectedExecutionHandler{

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println(r.toString()+" is rejected");
		
	}

}

