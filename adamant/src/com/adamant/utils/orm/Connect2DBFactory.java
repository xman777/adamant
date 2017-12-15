package com.adamant.utils.orm;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库连接的工厂类 
 * TODO: 链接数据库进行操作
 * @author xman
 * @time 2017年12月15日 上午9:59:50
 * @version v1.0
 */
public class Connect2DBFactory {

	public static Connection getDBConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/xtest";
			String user = "root";
			String password = "";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}


}
