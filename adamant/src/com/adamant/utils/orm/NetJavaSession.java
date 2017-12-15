package com.adamant.utils.orm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.adamant.entity.UserEntity;

/**
 * Java对象反射机制
 * TODO: 基本原理：保存数据时，把需要保存的对象的属性值全部取出来再拼凑sql语句 ； 查询时，将查询到的数据全部包装成一个java对象。 
 * 步骤：
 * 		1、首先数据库的有一个表，假设数据库名称为：xtest,里面的一个表名user。
 * 		2、创建对应的pojo类：com.adamant.entity.UserEntity。
 * 		3、编写获得数据库连接的工厂类：com.adamant.utils.orm.Connect2DBFactory
 * 		4、操作数据库的dao类（实现反射）:com.adamant.utils.orm.NetJavaSession
 * @author xman
 * @time 2017年12月15日 上午9:59:50
 * @version v1.0
 */
public class NetJavaSession {
	/**
	 * 解析出保存对象的sql语句(没有将数据新增入数据库，仅获得插入数据的sql)
	 * 
	 * @param object：需要保存的对象
	 * @return：保存对象的sql语句
	 */
	public static String getSaveObjectSql(Object object) {
		// 定义一个sql字符串
		String sql = "insert into ";
		// 得到对象的类
		Class c = object.getClass();
		// 得到对象中所有的方法
		Method[] methods = c.getMethods();
		// 得到对象中所有公有的属性  getFields：只返回公共字段，即有public修饰的字段
		Field[] fields = c.getFields();
		// 得到对象中所有的属性 getDeclaredFields()返回Class中所有的字段，包括私有字段
		Field[] allFields = c.getDeclaredFields();
//		for (Field field : allFields) {
//			System.out.println("对象中所有的属性---->"+field.getName()+",  "+field.getType());
//		}
		// 得到对象类的名字
		String cName = c.getName();
		// 从类的名字中解析出表名
		String tableName = cName.substring(cName.lastIndexOf(".") + 1, cName.length());
		tableName = getTableName(tableName);
		System.out.println("tableName:"+tableName);
		
		sql += tableName + "(";
		List<String> mList = new ArrayList<String>(); // 字段名字
		List vList = new ArrayList(); //字段值
		for (Method method : methods) {
			String mName = method.getName();
//			System.out.println("mName:"+mName);
			
			if (mName.startsWith("get") && !mName.startsWith("getClass")) {
				String fieldName = mName.substring(3, mName.length());
				fieldName = getColumnName(fieldName);
				mList.add(fieldName);
				System.out.println("字段名字----->" + fieldName);
				try {
					Object value = method.invoke(object, null);
					System.out.println("执行方法返回的值：" + value);
					if (value instanceof String) {
						vList.add("\"" + value + "\"");
						System.out.println("字段值------>" + value);
					} else {
						vList.add(value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		for (int i = 0; i < mList.size(); i++) {
			if (i < mList.size() - 1) {
				sql += mList.get(i) + ",";
			} else {
				sql += mList.get(i) + ") values(";
			}
		}
		for (int i = 0; i < vList.size(); i++) {
			if (i < vList.size() - 1) {
				sql += vList.get(i) + ",";
			} else {
				sql += vList.get(i) + ")";
			}
		}

		return sql;
	}

	public static List getDatasFromDB(String tableName, int Id) {

		return null;

	}

	/**
	 * 将对象保存到数据库中
	 *
	 * @param object ：需要保存的对象
	 * @return：方法执行的结果;1:表示成功，0:表示失败
	 */
	public int saveObject(Object object) {
		Connection con = Connect2DBFactory.getDBConnection();
		String sql = getSaveObjectSql(object);
		try {
			// Statement
			Statement statement = (Statement) con.createStatement();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 从数据库中取得对象
	 *
	 * @param arg0：对象所属的类
	 * @param id：对象的id
	 * @return:需要查找的对象
	 */
	public Object getObjectById(String className, Object id) {
		// 得到表名字
		String tableName = className.substring(className.lastIndexOf(".") + 1, className.length());
		tableName = getTableName(tableName);
		System.out.println("tableName:"+tableName);
		// 根据类名来创建Class对象
		Class c = null;
		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		// 拼凑查询sql语句
		String sql = "select * from " + tableName + " where id=" + "\""+id+"\"";
		System.out.println("查找sql语句：" + sql);
		// 获得数据库链接
		Connection con = Connect2DBFactory.getDBConnection();
		// 创建类的实例
		Object obj = null;
		try {
			Statement stm = con.createStatement();
			// 得到执行查寻语句返回的结果集
			ResultSet set = stm.executeQuery(sql);
			// 得到对象的方法数组
			Method[] methods = c.getMethods();
			// 遍历结果集
			while (set.next()) {
				obj = c.newInstance();
				// 遍历对象的方法
				for (Method method : methods) {
					String methodName = method.getName();
					// 如果对象的方法以set开头
					if (methodName.startsWith("set")) {
						// 根据方法名字得到数据表格中字段的名字
						String columnName = methodName.substring(3, methodName.length());
						columnName = getColumnName(columnName);
						// 得到方法的参数类型
						Class[] parmts = method.getParameterTypes();
						if (parmts[0] == String.class) {
							// 如果参数为String类型，则从结果集中按照列名取得对应的值，并且执行改set方法
							method.invoke(obj, set.getString(columnName));
						}
						if (parmts[0] == int.class) {
							method.invoke(obj, set.getInt(columnName));
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 根据entity名获取表名（根据具体项目格式规范而定）
	 * @author xman
	 * @time 2017年12月15日 下午2:38:01
	 * @param entityName entity对象名称
	 * @return
	 */
	public static String getTableName(String entityName){
		entityName = entityName.replace("Entity", "");
		String resultName = entityName;
		for (int i = 0; i < entityName.length(); i++) {
			if ((byte) entityName.charAt(i) > 64 && (byte) entityName.charAt(i) < 91) { //大写字母转换
				if (i==0) {
					resultName = resultName.replaceFirst(""+entityName.charAt(i), (""+entityName.charAt(i)).toLowerCase());
				} else {
					resultName = resultName.replaceFirst(""+entityName.charAt(i), ("_"+entityName.charAt(i)).toLowerCase());
				}
			}
		}
		
		return resultName;
	}
	
	/**
	 * 根据entity中字段名转为数据库字段名（根据具体项目格式规范而定）
	 * @author xman
	 * @time 2017年12月15日 下午3:21:20
	 * @param entityColumnName entity对象中的字段名
	 * @return
	 */
	public static String getColumnName(String entityColumnName){
		String resultName = entityColumnName;
		for (int i = 0; i < entityColumnName.length(); i++) {
			if ((byte) entityColumnName.charAt(i) > 64 && (byte) entityColumnName.charAt(i) < 91) { //大写字母转换
				if (i==0) {
					resultName = resultName.replaceFirst(""+entityColumnName.charAt(i), (""+entityColumnName.charAt(i)).toLowerCase());
				} else {
					resultName = resultName.replaceFirst(""+entityColumnName.charAt(i), ("_"+entityColumnName.charAt(i)).toLowerCase());
				}
			}
		}
		
		return resultName;
	}
	
	public static void main(String args[]) {
		
		// 获得NetJavaSession对象
		NetJavaSession session = new NetJavaSession();
		// 创建一个UserInfo对象
		UserEntity user = new UserEntity();
		// 设置对象的属性
		user.setId("123");
		user.setName("xman");
		user.setPassword("123456");
		user.setMobile("13500000001");
		user.setStatus(1);
		user.setRoleId("xxx");
		// 将对象保存到数据库中所执行的sql
		String sql = session.getSaveObjectSql(user);
		System.out.println("保存对象的sql语句：" + sql);
		
		// 将对象保存到数据库中
		int result = session.saveObject(user);
		System.out.println("对象保存到数据库的结果:"+result);
		
		// 查找对象
		UserEntity userInfo = (UserEntity) session.getObjectById("com.adamant.entity.UserEntity", "xxxxxxxx");
		System.out.println("获取到的信息：" + userInfo.getMobile());
	}

}
