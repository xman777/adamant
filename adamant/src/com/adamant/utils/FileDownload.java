package com.adamant.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * 文件下载工具类
 * TODO: 从服务器端下载文件到客户端
 * @author xman
 * @time 2017年5月31日 上午11:00:16
 * @version v1.0
 */
public class FileDownload {
	
	/**
	 * 根据文件路径下载文件到客户端
	 * @author xman
	 * @time 2017年5月31日 上午11:13:31
	 * @param request 请求对象
	 * @param response 请求对应的响应对象
	 * @param fileUrl 源文件路径
	 * @param newFileName 下载到客户端的文件名称
	 */
	public void downloadFile(HttpServletRequest request ,HttpServletResponse response, String fileUrl, String newFileName) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			// 根据条件得到文件路径
			// String fileurl="/01/demand/upload/20160316/下载.docx";
			System.out.println("===========文件路径===========" + fileUrl);
			// 将文件读入文件流
			inputStream = new FileInputStream(fileUrl);
			//获取响应的输出流
			outputStream = response.getOutputStream();
			// 获得浏览器代理信息
			final String userAgent = request.getHeader("USER-AGENT");
			String finalFileName = null;
			// 判断浏览器代理并分别设置响应给浏览器的编码格式
			if (StringUtils.contains(userAgent, "MSIE") || StringUtils.contains(userAgent, "Trident")) {// IE浏览器
				finalFileName = URLEncoder.encode(newFileName, "UTF8");
				System.out.println("IE浏览器");
			} else if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
				finalFileName = new String(newFileName.getBytes(), "ISO8859-1");
			} else {
				finalFileName = URLEncoder.encode(newFileName, "UTF8");// 其他浏览器
			}
			
			/*重要*/
			// 设置HTTP响应头
			response.reset();// 重置 响应头
			response.setContentType("application/x-download");// 告知浏览器下载文件，而不是直接打开，浏览器默认为打开
			response.addHeader("Content-Disposition", "attachment;filename=\"" + finalFileName + "\"");// 下载文件的名称

			// 循环取出流中的数据
			byte[] b = new byte[1024];
			int len;

			while ((len = inputStream.read(b)) > 0) {
				outputStream.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("========== downloadFile 下载文件出错了！！===========");
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("============ downloadFile 下载文件成功了！！===========");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String fileUrl = request.getSession().getServletContext().getRealPath("/WEB-INF/x.csb");
		
	}

}
