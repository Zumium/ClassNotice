package com.classnotice.db;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.BoundValueOperations;

@Repository
public class PortraitDAO {

	private static final String hasPortraitPrefix="has_portrait_";
	private static final String HAS_PORTRAIT="yes";
	private static final String NOT_HAVE_PORTRAIT="no";

	@Autowired
	@Qualifier("redisTemplate")
	private RedisOperations<String,String> redis;

	private String key(String sid){
		return hasPortraitPrefix+sid;
	}

	public boolean queryPortraitExists(String sid){
		String keyBoundToSid=key(sid);
		if(!redis.hasKey(keyBoundToSid)){
			redis.boundValueOps(keyBoundToSid).set(NOT_HAVE_PORTRAIT);
			return false;
		}
		String queryResultString=redis.boundValueOps(keyBoundToSid).get();
		return queryResultString.equals(HAS_PORTRAIT);
	}

	public void setPortraitExists(String sid,boolean isExists){
		if(isExists){
			redis.boundValueOps(key(sid)).set(HAS_PORTRAIT);
		}
		else{
			redis.boundValueOps(key(sid)).set(NOT_HAVE_PORTRAIT);
		}
	}
}
