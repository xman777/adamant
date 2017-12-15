package com.adamant.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 自动任务线程类
 * TODO: 在系统初始化（StartInit）时调用该类，在类里面设置某个时间点，按照某个时间间隔重复执行线程任务
 * @author xman
 * @time 2017年10月23日 下午4:27:00
 * @version v1.0
 */
public class TimeTask {
	//线程任务开始时间点，需根据情况设置这个时间点
	private static final String TASK_TIME = "23:00:00";
	
	//定义一个静态私有变量（不初始化，不适用final关键字，使用volatile保证了多线程访问时timeTask的可见性，避免了timeTask初始化时其他变量属性还没赋值完时，被另外线程调用）
	private static volatile TimeTask timeTask;
	
	/**
	 * 创建任务执行线程
	 */
	private Runnable taskThread = new Runnable() {
		private int num=1;
		
		//synchronized
		public synchronized void testSynchronized(int num){
			//线程任务执行相应地业务逻辑操作
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(num+"线程任务，开始时间："+df.format(new Date()));
		}
		
		public void run() {
			testSynchronized(num);
			num++;
		}
	};
	
	/**
	 * 获取当天某个时间点的时间毫秒值
	 * @author xman
	 * @time 2017-3-16 上午9:35:26
	 * @param time 日期下的某个时间点，如17:00:00
	 * @return
	 */
	private static long getTimeMillis(String time) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
			Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
			return curDate.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 私有构造方法
	 * @author xman
	 * @time 2017年3月16日 上午11:05:18
	 */
	private TimeTask(){
		long initialDelay = 0; //初始化延时
		long period = 1; //两次执行最小单位时间间隔值
		TimeUnit timeUnit = TimeUnit.MINUTES; //计时单位
		
		//时间点与当前时间相差的时间毫秒值
		initialDelay = getTimeMillis(TASK_TIME) - System.currentTimeMillis();
		initialDelay = initialDelay > 0? initialDelay : 0;
		
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(taskThread, initialDelay, period, timeUnit);
	}
	
	/**
	 * 定义一个共有的静态方法，返回该类型实例
	 * @author xman
	 * @time 2017年3月16日 上午11:09:53
	 * @return
	 */
	public static TimeTask runTask(){
		// 对象实例化时与否判断（timeTask不等于null时，直接返回对象，提高运行效率）
		if (timeTask==null) {
			// 同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时，对象在第一次创建之后，就不再重复被创建）
			synchronized(TimeTask.class) {
				// 未初始化，则初始timeTask变量
				if (timeTask == null) {
					timeTask = new TimeTask();
				}
			}
		}
		
		return timeTask;
	}
	
	
	
}
