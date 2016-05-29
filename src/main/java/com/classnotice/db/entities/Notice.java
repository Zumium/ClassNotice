package com.classnotice.db.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.*;

public class Notice {
	
	@Autowired
	private static SimpleJdbcTemplate jdbcTemplate;
//============================================
	private int id;
	private String title;
	private String content;
	private String sender;

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
//=================================================
	public static Notice Query(int id){
		String sql="SELECT * FROM Notice WHERE ID=? ;";
		return Notice.jdbcTemplate.queryForObject(sql,new ParameterizedRowMapper<Notice>(){
			public Notice mapRow(ResultSet rs,int rowNum) throws SQLException {
				Notice notice=new Notice();
				notice.setID(rs.getInt(1));
				notice.setTitle(rs.getString(2));
				notice.setContent(rs.getString(3));
				notice.setSender(rs.getString(4));
				return notice;
			}
		},id);
	}
}
