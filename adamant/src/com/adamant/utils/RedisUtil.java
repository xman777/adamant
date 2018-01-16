package com.adamant.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * Redis缓存工具
 * TODO: （注意：先在本地安装并Redis缓存服务，并通过cmd找到安装目录路径，运行命令启动服务命令：redis-server redis.windows.conf）对Redis缓存进行操作的工具
 * 注：https://www.cnblogs.com/liuling/p/2014-4-19-04.html
 * @author xman
 * @time 2018年1月10日 下午3:57:49
 * @version v1.0
 */
public class RedisUtil {
	public static Jedis jedis = new Jedis("127.0.0.1"); //必须运行redis服务才有效
	
	/**
	 * 字符串测试
	 * @author xman
	 * @time 2018年1月11日 下午4:25:59
	 */
	public void testString() {
		// -----添加数据----------
		jedis.set("name", "xman");// 向key-->name中放入了value
		System.out.println(jedis.get("name"));// 执行结果

		jedis.append("name", " test"); // 拼接
		System.out.println(jedis.get("name"));

		jedis.del("name"); // 删除某个键
		System.out.println(jedis.get("name"));
		// 设置多个键值对
		jedis.mset("name", "liuling", "age", "23", "qq", "123456XXX");
		jedis.incr("age"); // 进行加1操作
		System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
	}
	
	/**
	 * redis操作Map
	 * @author xman
	 * @time 2018年1月11日 下午5:08:49
	 */
	public void testMap() {
        //-----添加数据----------  
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user",map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List  
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数  
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);  
  
        //删除map中的某个键值  
        jedis.hdel("user","age");
        System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null  
        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2 
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true  
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key  
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value 
  
        Iterator<String> iter=jedis.hkeys("user").iterator();  
        while (iter.hasNext()){  
            String key = iter.next();  
            System.out.println(key+":"+jedis.hmget("user",key));  
        }  
    }
	
	/**
	 * jedis操作List
	 * @author xman
	 * @time 2018年1月11日 下午5:09:44
	 */
	public void testList(){  
        //开始前，先移除所有的内容  
        jedis.del("java framework");  
        System.out.println(jedis.lrange("java framework",0,-1));  
        //先向key java framework中存放三条数据  
        jedis.lpush("java framework","spring");  
        jedis.lpush("java framework","struts");  
        jedis.lpush("java framework","hibernate");  
        //再取出所有数据jedis.lrange是按范围取出，  
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
        System.out.println(jedis.lrange("java framework",0,-1));  
        
        jedis.del("java framework");
        jedis.rpush("java framework","spring");  
        jedis.rpush("java framework","struts");  
        jedis.rpush("java framework","hibernate"); 
        System.out.println(jedis.lrange("java framework",0,-1));
    }
	
	/**
	 * jedis操作Set
	 * @author xman
	 * @time 2018年1月11日 下午5:10:36
	 */
	public void testSet(){  
        //添加  
        jedis.sadd("user","liuling");  
        jedis.sadd("user","xinxin");  
        jedis.sadd("user","ling");  
        jedis.sadd("user","zhangxinxin");
        jedis.sadd("user","who");  
        //移除noname  
        jedis.srem("user","who");  
        System.out.println(jedis.smembers("user"));//获取所有加入的value  
        System.out.println(jedis.sismember("user", "who"));//判断 who 是否是user集合的元素  
        System.out.println(jedis.srandmember("user"));  
        System.out.println(jedis.scard("user"));//返回集合的元素个数  
    }
	
	/**
	 * jedis 排序
	 * @author xman
	 * @time 2018年1月11日 下午5:12:20
	 * @throws Exception
	 */
	public void testSort() throws Exception {  
        //jedis 排序  
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）  
        jedis.del("a");//先清除数据，再加入数据进行测试  
        jedis.rpush("a", "1");  
        jedis.lpush("a","6");  
        jedis.lpush("a","3");  
        jedis.lpush("a","9");  
        System.out.println(jedis.lrange("a",0,-1));// [9, 3, 6, 1]  
        System.out.println(jedis.sort("a")); //[1, 3, 6, 9]  //输入排序后结果  
        System.out.println(jedis.lrange("a",0,-1));  
    }
	
	/**
	 * 根据key和value缓存信息
	 * 
	 * @author xman
	 * @time 2018年1月11日 下午4:58:52
	 * @param key 键名
	 * @param value 键值
	 */
	public static void saveString(String key, String value){
		jedis.set(key, value);
		System.out.println("获取存入缓存中的信息："+jedis.get(key));
	}
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
        //查看服务是否运行
//        System.out.println("服务正在运行: "+jedis.ping());
//        saveString("test", "xman test");
        
        
	}
}
