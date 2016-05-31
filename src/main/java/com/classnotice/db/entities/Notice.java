package com.classnotice.db.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;

public class Notice {
	
	@Autowired
	private static JdbcTemplate jdbcTemplate;
//============================================
	private int id;
	private String title;
	private String content;
	private String sender;
	private Timestamp publishTime;

	public Notice(){}

	public void setID(int id){
		this.id=id;
	}
	public int getID(){
		return this.id;
	}

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

	public void setPublishTime(Timestamp publishTime){
		this.publishTime=publishTime;
	}
	public Timestamp getPublishTime(){
		return this.publishTime;
	}
}
