package com.company.web.controller.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Service("redisServiceImpl")
@SuppressWarnings({"unchecked","rawtypes"})
public class RedisServiceImpl implements RedisService{
	
	private static final Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	private ValueOperations getOpsForValue(){
		return redisTemplate.opsForValue();
	}
	
	private ListOperations getOpsForList(){
		return redisTemplate.opsForList();		
	}
	
	private HashOperations getOpsForHash(){
		return redisTemplate.opsForHash();
	}
	
	private RedisOperations getOperations(){
		return getOpsForValue().getOperations();
	}
	@Override
	public RedisConnection getConnection() {
		try {
			return redisTemplate.getConnectionFactory().getConnection();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	
	@Override
	public Object get(String key) {
		try {
			return getOpsForValue().get(key);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void set(String key, Object value, long timeout) {
		try {
			getOpsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void set(String key, Object value) {
		try {
			getOpsForValue().set(key, value);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void del(String key) {
		try {
			getOperations().delete(key);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@Override
	public void del(Collection<String> key) {
		try {
			getOperations().delete(key);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/*@Override
	public void leftPush(String key, Object value) {
		try {
			getOpsForList().leftPush(key,value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Override
	public void rightPush(String key, Object value) {
		try {
			getOpsForList().rightPush(key,value);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@Override
	public long llen(String key) {
		try {
			return getOpsForList().size(key);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	/*@Override
	public Object rightPop(String key) {
		try {
			return getOpsForList().rightPop(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	@Override
	public Object leftPop(String key) {
		try {
			return getOpsForList().leftPop(key);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}


	//和rightPush()配合使用，队列
	@Override
	public Object range(String key, long start, long end) {
		try {
			return getOpsForList().range(key, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public void trim(String key, int start, int end) {
		try {
			getOpsForList().trim(key, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/*@Override
	public String getCode(String key) {
		try {
			String code = (String) this.get(key);
			if (code == null) {
				code = RandomStringUtils.random(6, "0123456789").toUpperCase();
				this.set(key, code, 30 * 60);
			}
			return code;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
*/
	
	@Override
	public void hset(String key, String hashKey, Object value) {
		try {
			getOpsForHash().put(key, hashKey, value);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public Object hget(String key, String hashKey) {
		try {
			return getOpsForHash().get(key, hashKey);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}


}
