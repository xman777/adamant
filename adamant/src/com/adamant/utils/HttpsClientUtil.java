package com.adamant.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

/**
 * 利用HttpClient进行post请求的工具类 
 * TODO: get和post方式的http请求获取参数
 * @author xman
 * @time 2017年2月7日 下午1:43:38
 * @version v1.0
 */
public class HttpsClientUtil {
	public static String doPost(String url, String jsonstr, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			httpPost.addHeader("Content-Type", "application/json");
			StringEntity se = new StringEntity(jsonstr);
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
			httpPost.setEntity(se);
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		String url = "http://localhost/api/ask/ask_index1";
		String jsonStr = "categoryId=120&uid=30886";
		String httpOrgCreateTestRtn = HttpsClientUtil.doPost(url, jsonStr, "utf-8");
		System.out.println(httpOrgCreateTestRtn);
	}

}
