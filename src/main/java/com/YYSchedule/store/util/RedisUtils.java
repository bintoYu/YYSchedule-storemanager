/**
 * 
 */
package com.YYSchedule.store.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ybt
 * 
 * @date 2018年9月3日
 * @version 1.0
 */
public class RedisUtils
{
	/**
	 * 普通缓存放入
	 * 
	 * @param key
	 * @param value
	 * @return true成功 false失败
	 */
	public static boolean set(RedisTemplate<String,String> redisTemplate,String key, String value)
	{
		try
		{
			redisTemplate.opsForList().leftPush(key, value);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 普通缓存取出
	 * 
	 * @param key
	 * @param value
	 * @return true成功 false失败
	 */
	public static String get(RedisTemplate<String,String> redisTemplate,String key)
	{
		String leftPop = null;
		try
		{
			leftPop = redisTemplate.opsForList().rightPop(key);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return leftPop;
	}
	
	
	public static int size(RedisTemplate<String,String> redisTemplate,String key)
	{
		return redisTemplate.opsForList().size(key).intValue();
	}
}
