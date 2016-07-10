package com.classnotice.db;

public interface PortraitDAO {
	public boolean queryPortraitExists(String sid);
	public void setPortraitExists(String sid,boolean isExists);
}
