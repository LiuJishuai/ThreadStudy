package com.jeyson.thread.thread2.safe;

import java.util.Arrays;

import org.junit.Test;

import com.jeyson.thread.thread2.safe.vo.HashMapProcess;
import com.jeyson.thread.thread2.safe.vo.ProcessNoSafeThread;
import com.jeyson.thread.thread2.safe.vo.ProcessSafeThread;

/**
 *
 * @Message:  created by Jeyson on 2016年12月21日
 * 
 * @Description: 不安全的多线程，正常应该count=8
 */
public class ThreadSafety {
	/**
	 * 不安全的多线程实例
	 * @throws InterruptedException
	 */
	@Test
	public void testUnSafe() throws InterruptedException{
		ProcessNoSafeThread process=new ProcessNoSafeThread();
		Thread thread1=new Thread(process,"thread1");
		thread1.start();
		Thread thread2=new Thread(process,"thread2");
		thread2.start();
		thread1.join();
		thread2.join();
		System.out.println("now the count:"+process.getCount());
		System.out.println("main Thread end");
	}
	/**
	 *  安全的多线程实例,synchronized
	 * @throws InterruptedException
	 */
	@Test
	public void testSafeSyn() throws InterruptedException{
		ProcessSafeThread process=new ProcessSafeThread();
		Thread thread1=new Thread(process,"thread1");
		thread1.start();
		Thread thread2=new Thread(process,"thread2");
		thread2.start();
		thread1.join();
		thread2.join();
		System.out.println("now the count:"+process.getCount());
		System.out.println("main Thread end");
	}
	
	@Test
	public void testSynSafe() throws InterruptedException{
		String[] str={"1","2","3","4"};
		HashMapProcess process=new HashMapProcess(str);
		Thread thread1=new Thread(process,"Thread1");
		Thread thread2=new Thread(process,"Thread2");
		Thread thread3=new Thread(process,"Thread3");
		thread1.start();thread2.start();thread3.start();
		thread1.join();thread2.join();thread3.join();
		System.err.println(Arrays.asList(process.getStr()));
	}
		
}

	
  

