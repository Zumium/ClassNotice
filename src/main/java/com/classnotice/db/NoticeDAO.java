package com.classnotice.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.classnotice.db.entities.Notice;

import java.sql.*;

@Repository
public class NoticeDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Notice query(int id){
		String sql="SELECT * FROM Notice WHERE ID=? ;";
		return jdbcTemplate.queryForObject(sql,new RowMapper<Notice>(){
			public Notice mapRow(ResultSet rs,int rowNum) throws SQLException {
				Notice notice=new Notice();
				notice.setID(rs.getInt(1));
				notice.setTitle(rs.getString(2));
				notice.setContent(rs.getString(3));
				notice.setSender(rs.getString(4));
				notice.setPublishTime(rs.getTimestamp(5));
				return notice;
			}
		},id);
	}
}
