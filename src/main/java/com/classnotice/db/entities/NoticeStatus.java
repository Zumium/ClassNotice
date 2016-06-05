package com.classnotice.db.entities;

public class NoticeStatus {
	private long statusId;
	private String sid;
	private int nid;
	private boolean read;
	private boolean star;

	public NoticeStatus(){}
	public NoticeStatus(String sid,int nid,boolean star,boolean read){
		this.sid=sid;
		this.nid=nid;
		this.star=star;
		this.read=read;
	}

	public void setStatusID(long statusId){
		this.statusId=statusId;
	}
	public long getStatusID(){
		return this.statusId;
	}

	public void setSid(String sid){
		this.sid=sid;
	}
	public String getSid(){
		return this.sid;
	}

	public void setNid(int nid){
		this.nid=nid;
	}
	public int getNid(){
		return this.nid;
	}

	public void setStar(boolean star){
		this.star=star;
	}
	public boolean getStar(){
		return this.star;
	}

	public void setRead(boolean read){
		this.read=read;
	}
	public boolean getRead(){
		return this.read;
	}
}
