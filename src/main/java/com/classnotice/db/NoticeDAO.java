package com.classnotice.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.core.PreparedStatementCreator;

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
	
	public void insert(Notice notice){
		String sql="INSERT INTO Notice(Title,Content,Sender,PublishTime) VALUES(?,?,?,?) ;";
		KeyHolder primaryKeyHolder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException{
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,notice.getTitle());
				ps.setString(2,notice.getContent());
				ps.setString(3,notice.getSender());
				ps.setTimestamp(4,notice.getPublishTime());
				return ps;
			}
		},primaryKeyHolder);
		notice.setID(primaryKeyHolder.getKey().intValue());
	}

}
