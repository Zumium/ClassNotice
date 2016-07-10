package com.classnotice.db;

public interface GroupDAO{
	public String[] queryMembers(String sid,String groupName);
	public void setMembers(String sid,String groupName,String[] members);
	public void addMembers(String sid,String groupName,String[] members);
	public void removeMembers(String sid,String groupName,String[] members);
}
