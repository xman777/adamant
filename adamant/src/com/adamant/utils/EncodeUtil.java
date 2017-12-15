package com.adamant.utils;

/**
 * 编码工具类
 * TODO: 获取字符串字符编码；处理字符串的字符编码
 * @author xman
 * @time 2016年9月28日 上午9:49:24
 * @version v1.0
 */
public class EncodeUtil {
	
	/**
	 * 获取字符串所属编码类型信息
	 * @author xman
	 * @time 2016年9月28日 上午9:34:50
	 * @param str 被查询编码类型的字符串
	 * @return
	 */
	public static String getEncoding(String str) {
		String encode = "";
		
		try {
			encode = "GB2312";
			if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
			}
			
			encode = "ISO-8859-1";
			if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
			}
			
			encode = "UTF-8";
			if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
			}
			
			encode = "GBK";
			if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return encode;
	}
	
	/**
	 * 设置字符串为对应字符编码
	 * @author xman
	 * @time 2016年9月28日 上午9:39:48
	 * @param str 输入字符串信息
	 * @param needEncode 设置为的字符编码，如：UTF-8
	 * @return
	 */
	public static String setEncoding(String str,String needEncode) {
		String returnStr = "";
		try {
			String encode = "GB2312";
			if (str.equals(new String(str.getBytes(encode), encode))) {
				returnStr = new String(str.getBytes(encode), needEncode);
				return returnStr;
			}
			
			encode = "ISO-8859-1";
			if (str.equals(new String(str.getBytes(encode), encode))) {
				returnStr = new String(str.getBytes(encode), needEncode);
				return returnStr;
			}
			
			encode = "UTF-8";
			if (str.equals(new String(str.getBytes(encode), encode))) {
				returnStr = new String(str.getBytes(encode), needEncode);
				return returnStr;
			}
			
			encode = "GBK";
			if (str.equals(new String(str.getBytes(encode), encode))) {
				returnStr = new String(str.getBytes(encode), needEncode);
				return returnStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return returnStr;
	}
	
	public static void main(String[] args) throws Exception {
		/*System.out.println("中文");

		System.out.println("中文".getBytes());

		System.out.println("中文".getBytes("GB2312"));

		System.out.println("中文".getBytes("ISO8859_1"));

		System.out.println(new String("中文".getBytes()));

		System.out.println(new String("中文".getBytes(), "GB2312"));

		System.out.println(new String("中文".getBytes(), "ISO8859_1"));

		System.out.println(new String("中文".getBytes("GB2312")));

		System.out.println(new String("中文".getBytes("GB2312"), "GB2312"));

		System.out.println(new String("中文".getBytes("GB2312"), "ISO8859_1"));

		System.out.println(new String("中文".getBytes("ISO8859_1")));

		System.out.println(new String("中文".getBytes("ISO8859_1"), "GB2312"));

		System.out.println(new String("中文".getBytes("ISO8859_1"), "ISO8859_1"));*/
		
		String encode="";
		byte[] bytes = "都是佛我胡搜的发挥就是咯都快放假快乐为".getBytes();
		String isoStr = new String(bytes, "ISO-8859-1");
		encode=getEncoding(isoStr);
		System.out.println("isoStr:"+isoStr+"   ,encode:"+encode);
//		String newIsoStr =  new String(isoStr.getBytes("ISO-8859-1"),"UTF-8");
//		System.out.println("newIsoStr:"+newIsoStr);
		
		String utfStr = new String(isoStr.getBytes("ISO-8859-1"), "UTF-8");
		encode=getEncoding(utfStr);
		System.out.println("utfStr:"+utfStr+"   ,encode:"+encode);
		
	}

}
