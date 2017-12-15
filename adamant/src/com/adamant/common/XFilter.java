package com.adamant.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 过滤器demo
 * TODO: 
 * 1、Filter简介
 * 		Filter也称之为过滤器，它是Servlet技术中最实用的技术，Web开发人员通过Filter技术，对web服务器管理的所有web资源：例如Jsp, Servlet, 静态图片文件或静态 html 文件等进行拦截，
 *		从而实现一些特殊的功能。例如实现URL级别的权限访问控制、过滤敏感词汇、压缩响应信息等一些高级功能。
 *		它主要用于对用户请求进行预处理，也可以对HttpServletResponse进行后处理。使用Filter的完整流程：Filter对用户请求进行预处理，接着将请求交给Servlet进行处理并生成响应，最后Filter再对服务器响应进行后处理。
 * 2、Filter功能
 *		在HttpServletRequest到达 Servlet 之前，拦截客户的HttpServletRequest 。根据需要检查HttpServletRequest，也可以修改HttpServletRequest头和数据。
 *		在HttpServletResponse到达客户端之前，拦截HttpServletResponse 。根据需要检查HttpServletResponse，也可以修改HttpServletResponse头和数据。
 * 3、如何借助Filter实现拦截功能
 * 		Filter接口中有一个doFilter方法，当开发人员编写好Filter，并配置对哪个web资源进行拦截后，Web服务器每次在调用web资源的service方法之前，都会先调用一下filter的doFilter方法，因此，在该方法内编写代码可达到如下目的：
 *		调用目标资源之前，让一段代码执行。是否调用目标资源（即是否让用户访问web资源）。
 *		web服务器在调用doFilter方法时，会传递一个filterChain对象进来，filterChain对象是filter接口中最重要的一个对象，它也提供了一个doFilter方法，开发人员可以根据需求决定是否调用此方法，调用该方法，则web服务器就会调用web资源的service方法，即web资源就会被访问，否则web资源不会被访问。
 * @author xman
 * @time 2017年10月1日 上午10:02:33
 * @version v1.0
 */
public class XFilter implements Filter {
	private FilterConfig config; //FilterConfig可以获取部署描述符文件（web.xml）中分配的过滤器初始化参数
	
	private String param1; //web.xml文件中设置的参数
	private String param2; //web.xml文件中设置的参数
	
	@Override
	public void destroy() { //销毁
		this.config = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException { //过滤
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		
		System.out.println("xxxxxxxxxxxxparam1:"+param1);
		System.out.println("xxxxxxxxxxxxparam2:"+param2);
		
		/*
			getRequestURL 方法返回客户端发出请求时的完整URL。
			getRequestURI 方法返回请求行中的资源名部分。
			getQueryString 方法返回请求行中的参数部分。
			getPathInfo 方法返回请求URL中的额外路径信息。额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以“/”开头。
			getRemoteAddr 方法返回发出请求的客户机的IP地址。
			getRemoteHost 方法返回发出请求的客户机的完整主机名。
			getRemotePort 方法返回客户机所使用的网络端口号。
			getLocalAddr 方法返回WEB服务器的IP地址。
			getLocalName 方法返回WEB服务器的主机名。
		 */
		//获取用户请求URL
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);
		
		/*
		//创建类Constants.java，里面写的是无需过滤的页面
        for (int i = 0; i < Constants.NoFilter_Pages.length; i++) {

            if (url.indexOf(Constants.NoFilter_Pages[i]) > -1) {
            	filterChain.doFilter(request, response);
                return;
            }
        }*/
        
		
		//从session中获取过滤所需信息。例如：用户id等
		String userId = (String) session.getAttribute("userId");
		
		// 登陆页面无需过滤
        if(uri.indexOf("/index.jsp") > -1) {
        	System.out.println("index.jsp页面 无需过滤");
        	filterChain.doFilter(request, response);
            return;
        }
		
        if (userId==null || "".equals(userId)) {//未登陆处理
        	// 跳转到登陆页面
//        	String indexUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/index.jsp";
//        	response.sendRedirect(indexUrl);
        	System.out.println("======================XFilter：判断为未登陆");
        	
        	filterChain.doFilter(request, response);//xxxxxxx未登陆则不可继续此次请求，实际需注释掉
		} else {//登陆的处理：继续此次请求
			//执行下一个过滤器
			filterChain.doFilter(request, response);
		}
		
//		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException { //初始化
		this.config = filterConfig;
		
		param1 = config.getInitParameter("param1");
		param2 = config.getInitParameter("param2");
		ServletContext servletContext = config.getServletContext();
		System.out.println("ServletContext:"+servletContext);
		
		System.out.println("param1:"+param1);
		System.out.println("param2:"+param2);
	}

}
