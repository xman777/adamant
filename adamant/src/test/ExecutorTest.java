package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

public class ExecutorTest {

	private static Executor executor = Executors.newFixedThreadPool(10); 
	
	static class Task implements Runnable{
		private static int num=1;
		
		//synchronized
		public synchronized void testSynchronized(int num){
			//线程任务执行相应地业务逻辑操作
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(num+"线程任务，开始时间："+df.format(new Date()));
		}
		
		public void run() {
//			System.out.println(Thread.currentThread().getId()+"    "+Thread.currentThread().getName());
			
			testSynchronized(num);
			num++;
		}
	};
	
	public static ExecutorService newFixedThreadPool(int nThreads){
		/*
		 * corePoolSize
		 * 线程池中的核心线程数，当提交一个任务时，线程池创建一个新线程执行任务，直到当前线程数等于corePoolSize；如果当前线程数为corePoolSize，
		 * 继续提交的任务被保存到阻塞队列中，等待被执行；如果执行了线程池的prestartAllCoreThreads()方法，线程池会提前创建并启动所有核心线程。
		 * 
		 * maximumPoolSize
		 * 线程池中允许的最大线程数。如果当前阻塞队列满了，且继续提交任务，则创建新的线程执行任务，前提是当前线程数小于maximumPoolSize；
		 * 
		 * keepAliveTime
		 * 线程空闲时的存活时间，即当线程没有任务执行时，继续存活的时间；默认情况下，该参数只在线程数大于corePoolSize时才有用；
		 * 
		 * unit
		 * keepAliveTime的单位；
		 * 
		 * workQueue
		 * 用来保存等待被执行的任务的阻塞队列，且任务必须实现Runable接口，在JDK中提供了如下阻塞队列：
		 * 	1、ArrayBlockingQueue：基于数组结构的有界阻塞队列，按FIFO排序任务；
		 * 	2、LinkedBlockingQuene：基于链表结构的阻塞队列，按FIFO排序任务，吞吐量通常要高于ArrayBlockingQuene；
		 * 	3、SynchronousQuene：一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQuene；
		 * 	4、priorityBlockingQuene：具有优先级的无界阻塞队列；
		 */
		
//		return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			executor.execute(new Task());
		}
		
//		for (int i = 0; i < 20; i++) {
//			newFixedThreadPool(10).execute(new Task());
//		}
		
		
	}

}
