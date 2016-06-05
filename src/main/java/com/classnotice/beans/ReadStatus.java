package com.classnotice.beans;

import com.classnotice.db.entities.Notice;

public class ReadStatus {
	private Notice notice;
	private int readCount;
	private String noticePath;

	public ReadStatus(){}

	public void setNotice(Notice notice){
		this.notice=notice;
	}
	public Notice getNotice(){
		return this.notice;
	}

	public void setReadCount(int readCount){
		this.readCount=readCount;
	}
	public int getReadCount(){
		return this.readCount;
	}

	public void setNoticePath(String noticePath){
		this.noticePath=noticePath;
	}
	public String getNoticePath(){
		return this.noticePath;
	}
}
