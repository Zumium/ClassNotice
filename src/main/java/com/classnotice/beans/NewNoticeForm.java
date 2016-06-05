package com.classnotice.beans;

public class NewNoticeForm {
	private String title;
	private String content;
	private String receiversGroup;

	public NewNoticeForm(){}

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

	public void setReceiversGroup(String receiversGroup){
		this.receiversGroup=receiversGroup;
	}
	public String getReceiversGroup(){
		return this.receiversGroup;
	}

}
