package com.classnotice.db;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.BoundSetOperations;

import java.util.Set;

@Repository
public class GroupDAO{
	private static final String prefix="classnotice_group_";
	private static final String[] STRING_ARRAY={"A"};

	@Autowired
	@Qualifier("redisTemplate")
	private RedisOperations<String,String> redis;

	private String key(String groupName){
		return prefix+groupName;
	}

	public String[] queryMembers(String groupName){
		String groupKey=key(groupName);
		BoundSetOperations<String,String> group=redis.boundSetOps(groupKey);
		Set<String> groupMembers=group.members();
		return groupMembers.toArray(STRING_ARRAY);
	}

	public void setMembers(String groupName,String[] members){
		String groupKey=key(groupName);
		BoundSetOperations<String,String> group=redis.boundSetOps(groupKey);
		long groupSize=group.size();
		for(int i=0;i<groupSize;i++) group.pop();

		this.addMembers(groupName,members);
	}

	public void addMembers(String groupName,String[] members){
		String groupKey=key(groupName);
		BoundSetOperations<String,String> group=redis.boundSetOps(groupKey);
		for(String member: members) group.add(member);
	}

	public void removeMembers(String groupName,String[] members){
		String groupKey=key(groupName);
		BoundSetOperations<String,String> group=redis.boundSetOps(groupKey);
		for(String member: members) group.remove(member);
	}
}
