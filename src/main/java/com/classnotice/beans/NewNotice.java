package com.classnotice.beans;

import java.sql.Timestamp;

public class NewNotice {
	private String title;
	private String content;
	private String sender;
	private String[] receivers;
	private Timestamp publishTime;

	public NewNotice(){}

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return this.title;
	}

	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return this.content;
	}

	public void setSender(String sender){
		this.sender=sender;
	}
	public String getSender(){
		return this.sender;
	}

	public void setReceivers(String[] receivers){
		this.receivers=receivers;
	}
	public String[] getReceivers(){
		return this.receivers;
	}

	public void setPublishTime(Timestamp publishTime){
		this.publishTime=publishTime;
	}
	public Timestamp getPublishTime(){
		return this.publishTime;
	}
}
