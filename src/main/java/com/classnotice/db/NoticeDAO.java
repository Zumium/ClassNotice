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
import java.util.List;
import java.util.ArrayList;

@Repository
public class NoticeDAO {
	
	public static final int STAR=1;
	public static final int READ=2;

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

	public List<Notice> query(String sid,boolean star,boolean read,int flags){
		String sql="SELECT N.* FROM NoticeStatus AS NS JOIN Notice AS N ON NS.Nid=N.ID WHERE NS.Sid=? ";
		List<Object> args=new ArrayList<Object>();

		args.add(sid);
		if((flags & this.STAR) != 0){
			sql+="AND StarStatus=? ";
			args.add(star);
		}
		if((flags & this.READ) != 0){
			sql+="AND ReadStatus=? ";
			args.add(read);
		}
		sql+="ORDER BY N.PublishTime DESC;";

		return jdbcTemplate.query(sql,args.toArray(),new RowMapper<Notice>(){
			public Notice mapRow(ResultSet rs,int rowNum) throws SQLException{
				Notice notice=new Notice();
				notice.setID(rs.getInt(1));
				notice.setTitle(rs.getString(2));
				notice.setContent(rs.getString(3));
				notice.setSender(rs.getString(4));
				notice.setPublishTime(rs.getTimestamp(5));
				return notice;
			}
		});
	}

	public List<Notice> queryBySender(String sender){
		String sql="SELECT * FROM Notice WHERE Sender=? ORDER BY PublishTime DESC ;";
		return jdbcTemplate.query(sql,new RowMapper<Notice>(){
			public Notice mapRow(ResultSet rs,int rowNum) throws SQLException{
				Notice notice=new Notice();
				notice.setID(rs.getInt(1));
				notice.setTitle(rs.getString(2));
				notice.setContent(rs.getString(3));
				notice.setSender(rs.getString(4));
				notice.setPublishTime(rs.getTimestamp(5));
				return notice;
			}
		},sender);
	}
	public int queryCountBySender(String sender){
		String sql="SELECT COUNT(*) FROM Notice WHERE Sender=? ;";
		return jdbcTemplate.queryForObject(sql,Integer.class,sender);
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
