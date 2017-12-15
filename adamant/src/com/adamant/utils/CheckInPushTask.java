package com.adamant.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务执行工具类
 * @author xman
 * @time 2017-3-13 下午3:43:19
 * @version 1.3.x
 */
public class CheckInPushTask {
	private long initialDelay = 0; //初始化延时
	private long period = 1; //两次开始执行最小单位时间间隔值
	private TimeUnit timeUnit = TimeUnit.MINUTES; //计时单位
	
	private Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx 执行任务:"+sf.format(new Date()));
		}
	};
	
	public Runnable getRunnable() {
		return runnable;
	}

	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}

	public long getInitialDelay() {
		return initialDelay;
	}

	public void setInitialDelay(long initialDelay) {
		this.initialDelay = initialDelay;
	}

	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
	
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

	// 定义一个私有构造方法
	private CheckInPushTask() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		long oneDay = 60 * 1000;
		long initDelay = getTimeMillis("16:00:00") - System.currentTimeMillis();
		initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;

		executor.scheduleAtFixedRate(runnable, initDelay, 10000, TimeUnit.MILLISECONDS);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("zzzzzzzzzzzzzzzzzzzz 执行任务:"+sf.format(new Date()));
	}

	// 定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时checkInPushTask变量的可见性，避免了checkInPushTask初始化时其他变量属性还没赋值完时，被另外线程调用)
	private static volatile CheckInPushTask checkInPushTask;

	// 定义一个共有的静态方法，返回该类型实例
	public static CheckInPushTask runTask() {
		// 对象实例化时与否判断（不使用同步代码块，checkInPushTask不等于null时，直接返回对象，提高运行效率）
		if (checkInPushTask == null) {
			System.out.println("CheckInPushTask is null");
			// 同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
			synchronized (CheckInPushTask.class) {
				// 未初始化，则初始checkInPushTask变量
				if (checkInPushTask == null) {
					checkInPushTask = new CheckInPushTask();
				}
			}
		}
		return checkInPushTask;
	}
	
	public void doTask() {
		
		System.out.println("hasDone");
//		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//		service.scheduleAtFixedRate(runnable, initialDelay, period, timeUnit);
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		long oneDay = 24 * 60 * 60 * 1000;
		long initDelay = getTimeMillis("15:26:00") - System.currentTimeMillis();
		initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;

		executor.scheduleAtFixedRate(runnable, initDelay, oneDay, TimeUnit.MILLISECONDS);
	}

}
