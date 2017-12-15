package com.adamant.common;

public class XTest {

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
		System.out.println("utfStr:"+utfStr+"    ,encode:"+encode);
	}

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

}
