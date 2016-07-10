package com.classnotice.db;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.BoundSetOperations;

import java.util.Set;

import com.classnotice.db.GroupDAO;

@Repository("groupDao")
public class GroupDAOImpl implements GroupDAO{
	private static final String prefix="classnotice_group_";
	private static final String UNDERSCORE="_";
	private static final String[] STRING_ARRAY={"A"};

	@Autowired
	@Qualifier("redisTemplate")
	private RedisOperations<String,String> redis;

	private String key(String groupName){
		return prefix+groupName;
	}

	private String key(String sid,String groupName){
		return prefix+sid+UNDERSCORE+groupName;
	}

	@Override
	public String[] queryMembers(String sid,String groupName){
		String groupKey=null;
		if(sid==null) groupKey=key(groupName);
		else groupKey=key(sid,groupName);

		BoundSetOperations<String,String> group=redis.boundSetOps(groupKey);
		Set<String> groupMembers=group.members();
		return groupMembers.toArray(STRING_ARRAY);
	}

	@Override
	public void setMembers(String sid,String groupName,String[] members){
		String groupKey=null;
		if(sid==null) groupKey=key(groupName);
		else groupKey=key(sid,groupName);

		BoundSetOperations<String,String> group=redis.boundSetOps(groupKey);
		long groupSize=group.size();
		for(int i=0;i<groupSize;i++) group.pop();

		this.addMembers(sid,groupName,members);
	}

	@Override
	public void addMembers(String sid,String groupName,String[] members){
		String groupKey=null;
		if(sid==null) groupKey=key(groupName);
		else groupKey=key(sid,groupName);

		BoundSetOperations<String,String> group=redis.boundSetOps(groupKey);
		for(String member: members) group.add(member);
	}

	@Override
	public void removeMembers(String sid,String groupName,String[] members){
		String groupKey=null;
		if(sid==null) groupKey=key(groupName);
		else groupKey=key(sid,groupName);

		BoundSetOperations<String,String> group=redis.boundSetOps(groupKey);
		for(String member: members) group.remove(member);
	}
}
