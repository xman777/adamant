package com.adamant.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 文件上传下载demo 
 * TODO:
 * @author xman
 * @time 2016年9月29日 下午1:56:32
 * @version v1.0
 */
@Controller
@RequestMapping("/fileController")
public class FileController {

	/**
	 * 文件上传
	 * 前端通过form表单进行提交 而且表单中需加入属性：method="post" enctype="multipart/form-data"
	 * 在xml配置文件中需：做上传文件的配置 其中maxUploadSize为上传文件大小（1m=1048576=1024*1024）
	 * @author xman
	 * @time 2016年10月15日 上午9:24:11
	 * @param request
	 * @param response
	 */
	@RequestMapping("/fileUpload")
	public void fileUpload(HttpServletRequest request, HttpServletResponse response) {
		int success = 0;
		String msg = "";
		
//		String testParam = request.getParameter("testParam");
//		System.out.println("$$$$$$$$$$$$$$$$$:"+testParam);
		
		Map<String, Object> resultMap = new HashMap<>();
		long startTime = System.currentTimeMillis(); // 获取开始时间

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) { // 判断request是否有文件上传
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {
				MultipartFile file = multiRequest.getFile(ite.next());
				if (file != null) {
					File localFile = new File("F:/upload/" + file.getOriginalFilename());
					try {
						file.transferTo(localFile); // 将上传文件写到服务器上指定的文件
						success=1;
						msg="上传成功";
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}else {
			success=0;
			msg="无上传文件";
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("上传文件共使用时间：" + (endTime - startTime));
		
		resultMap.put("success", success);
		resultMap.put("msg", msg);
//		return resultMap;
	}

	/**
	 * 文件下载
	 * @author xman
	 * @time 2016年10月15日 上午9:24:11
	 * @param request
	 * @param response
	 */
	@RequestMapping("/fileDownload")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) {
		/*ServletContext servletContext = request.getServletContext();
		
		// 获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载
		String path = servletContext.getRealPath("/");*/

		String param1 = request.getParameter("param1");
		System.out.println("*********************param1:"+param1);
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		// 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
		response.setHeader("Content-Disposition", "attachment;fileName=" + "FileController.java");
		ServletOutputStream out=null;
		// 通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
		File file = new File("F:/" + "FileController.java");

		try {
			FileInputStream inputStream = new FileInputStream(file);

			// 3.通过response获取ServletOutputStream对象(out)
			out = response.getOutputStream();

			int b = 1024;
			byte[] buffer = new byte[1024];
			while (b > -1) {
				b = inputStream.read(buffer);
				System.out.println("b:"+b);
				// 4.写到输出流(out)中
				out.write(buffer, 0, b);
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				try {
					out.close();
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

}
