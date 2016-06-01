package com.classnotice.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.classnotice.db.entities.NoticeStatus;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

@Repository
public class NoticeStatusDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NoticeStatus Query(long id){
		String sql="SELECT * FROM NoticeStatus WHERE ID=? ;";
		return jdbcTemplate.queryForObject(sql,new RowMapper<NoticeStatus>(){
			public NoticeStatus mapRow(ResultSet rs,int rowNum) throws SQLException{
				NoticeStatus status=new NoticeStatus();
				status.setStatusID(rs.getLong(1));
				status.setSid(rs.getString(2));
				status.setNid(rs.getInt(3));
				status.setStar(rs.getBoolean(4));
				status.setRead(rs.getBoolean(5));
				return status;
			}
		},id);
	}

	public List<NoticeStatus> QueryBySidAndStatus(String sid,boolean star,boolean read){
		String sql="SELECT * FROM NoticeStatus WHERE Sid=? AND StarStatus=? AND ReadStatus=? ;";
		return jdbcTemplate.query(sql,new RowMapper<NoticeStatus>(){
			public NoticeStatus mapRow(ResultSet rs,int rowNum) throws SQLException {
				NoticeStatus status=new NoticeStatus();
				status.setStatusID(rs.getLong(1));
				status.setSid(rs.getString(2));
				status.setNid(rs.getInt(3));
				status.setStar(rs.getBoolean(4));
				status.setRead(rs.getBoolean(5));
				return status;
			}
		},sid,star,read);
	}

	public List<NoticeStatus> QueryBySidAndReadStatus(String sid,boolean read){
		String sql="SELECT * FROM NoticeStatus WHERE Sid=? AND ReadStatus=? ;";
		return jdbcTemplate.query(sql,new RowMapper<NoticeStatus>(){
			public NoticeStatus mapRow(ResultSet rs,int rowNum) throws SQLException {
				NoticeStatus status=new NoticeStatus();
				status.setStatusID(rs.getLong(1));
				status.setSid(rs.getString(2));
				status.setNid(rs.getInt(3));
				status.setStar(rs.getBoolean(4));
				status.setRead(rs.getBoolean(5));
				return status;
			}
		},sid,read);
	}

	public int QueryNoticeCountByRead(String uid,boolean read){
		String sql="SELECT COUNT(*) FROM NoticeStatus WHERE Sid=? AND ReadStatus=? ;";
		return jdbcTemplate.queryForObject(sql,new Object[]{uid,read},Integer.class);
	}
}
