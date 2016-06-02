package com.classnotice.beans;

import com.classnotice.db.entities.Notice;
import com.classnotice.db.entities.Student;

public class ListItem {
	private static final String bannerDash="-";

	private Notice notice;
	private String senderBanner;
	private String senderPortrait;

	public ListItem(){}

	public ListItem(Notice notice,String senderBanner,String senderPortrait){
		this.notice=notice;
		this.senderBanner=senderBanner;
		this.senderPortrait=senderPortrait;
	}

	public void setNotice(Notice notice){
		this.notice=notice;
	}
	public Notice getNotice(){
		return this.notice;
	}

	public void setSenderBanner(String senderBanner){
		this.senderBanner=senderBanner;
	}
	public void setSenderBanner(Student sender){
		this.senderBanner=sender.getRole()+bannerDash+sender.getName();
	}
	public String getSenderBanner(){
		return this.senderBanner;
	}

	public void setSenderPortrait(String senderPortrait){
		this.senderPortrait=senderPortrait;
	}
	public String getSenderPortrait(){
		return this.senderPortrait;
	}
}
