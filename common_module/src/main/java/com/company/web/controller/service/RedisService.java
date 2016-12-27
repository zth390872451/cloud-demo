package com.company.web.controller.service;

import org.springframework.data.redis.connection.RedisConnection;

import java.util.Collection;

public interface RedisService {
	public RedisConnection getConnection();
	
	public Object get(final String key);
	
	void set(String key, Object value, final long timeout);

	void set(String key, Object value);
	
	void del(String key);
	
	void del(Collection<String> key);
	
	//void leftPush(String key,Object value);
	
	void rightPush(String key, Object value);
	
	long llen(String key);
	
	//Object rightPop(String key);
	
	Object leftPop(String key);
	
	Object range(String key, long start, long end);
	
	void trim(String key, int start, int end);
	/**
	 * 生成验证码
	 * @param key
	 * @return
	 */
//	String getCode(String key);
	
	//---------------------hash----------------------------
	void hset(String key, String hashKey, Object value);
	
	Object hget(String key, String hashKey);
}
