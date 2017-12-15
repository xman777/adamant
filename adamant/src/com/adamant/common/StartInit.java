package com.adamant.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.adamant.utils.TimeTask;

/**
 * 系统启动时初始化的参数信息
 * TODO: 启动系统的时候就进行一些参数的初始化，使得可以在页面和后台中直接获取到这些参数信息
 * 步骤：
 * 		1、在初始化启动类  需要实现javax.servlet.ServletContextListener
 * 		2、在web.xml文件中建立一个监听器，使得该监听器可以起作用 <listener><listener-class>com.adamant.common.StartInit</listener-class></listener>
 * @author xman
 * @time 2016年10月11日 上午10:05:26
 * @version v1.0
 */
public class StartInit implements ServletContextListener {
	
	/**
	 * ServletContextListener接口，能够监听 ServletContext对象的生命周期，实际上就是监听 Web应用的生命周期。
	 * 当Servlet容器启动或终止Web应用时，会触发ServletContextEvent事件，该事件由 ServletContextListener来处理。在 ServletContextListener接口中定义了处理ServletContextEvent事件的两个方法。
	 */
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 * contextDestroyed(ServletContextEvent sce) ：当Servlet容器终止Web应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet和Filter过滤器。
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) { //销毁
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 * contextInitialized(ServletContextEvent sce) ：当Servlet容器启动Web应用时调用该方法。在调用完该方法之后，容器再对Filter初始化，并且对那些在Web应用启动时就需要被初始化的Servlet进行初始化。
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) { //初始化
		//初始化系统所需的相关信息
		System.out.println("************ StartInit begin ************");
		servletContextEvent.getServletContext().setAttribute("xInitParam", "yyyyyyyyyyyyyyyyyyyyyyy");
		System.out.println("************ StartInit end ************");
		
		
		TimeTask.runTask();
		
		
	}

}
