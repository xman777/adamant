package com.adamant.utils;

/**
 * 加密工具类
 * TODO: 对数据信息进行加密，加密算法可以不一样
 * @author xman
 * @time 2018年1月10日 下午3:44:49
 * @version v1.0
 */
public class EncryptUtil {
	
	/**
	 * ASCII加密算法
	 * @author xman
	 * @time 2018年1月10日 下午3:54:54
	 * @param sourceStr 加密前原数据
	 * @return
	 */
	public static String encryptASCII(String sourceStr) {
		// 加密后的数据
		String targetStr = "";
		
		// 开始加密
		try {
			// 一 将明文拆分成单个字符
			byte[] strBytes = sourceStr.getBytes("gbk");
			// 二 将每个字符 都做 - 5 操作
			for (int i = 0; i < strBytes.length; i++) {
				// 对每个字符都做 - 5 操作
				strBytes[i] -= 5;
			}
			// 加密后的数据
			targetStr = new String(strBytes, "gbk");

			/*
			System.out.println("加密后的数据："+targetStr);
			
			//解密
			byte[] oldBytes = targetStr.getBytes("gbk");
			for (int i = 0; i < oldBytes.length; i++) {
				oldBytes[i] += 5;
			}
			
			//解密后得到的数据
			String oldStr = new String(oldBytes, "gbk");
			System.out.println("解密后数据："+oldStr);
			*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return targetStr;
	}
	
	
	public static void main(String[] args) throws Exception {
		String targetStr = encryptASCII("水电费撒旦法撒旦的sdfasdfa，。‘’、法师的范德萨发生大发东方大厦是打发斯蒂芬sad范德萨发生喜爱发送的");
		System.out.println(targetStr);
	}

}
