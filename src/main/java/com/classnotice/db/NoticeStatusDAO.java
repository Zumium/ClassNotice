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
	public static final int STAR=1;
	public static final int READ=2;
	public static final int SID=4;
	public static final int NID=8;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NoticeStatus query(long id){
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

	public NoticeStatus query(String sid,int nid){
		String sql="SELECT * FROM NoticeStatus WHERE Sid=? AND Nid=? ;";
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
		},sid,nid);
	}

	public List<NoticeStatus> query(String sid,boolean star,boolean read,int flags){
		String sql="SELECT * FROM NoticeStatus WHERE Sid=? ";
		List<Object> queryArgs=new ArrayList<Object>();

		queryArgs.add(sid);
		if((flags & this.STAR) != 0){
			sql+="AND StarStatus=? ";
			queryArgs.add(star);
		}
		if((flags & this.READ) != 0){
			sql+="AND ReadStatus=? ";
			queryArgs.add(read);
		}
		sql+=";";
		//String sql="SELECT * FROM NoticeStatus WHERE Sid=? AND StarStatus=? AND ReadStatus=? ;";
		return jdbcTemplate.query(sql,queryArgs.toArray(),new RowMapper<NoticeStatus>(){
			public NoticeStatus mapRow(ResultSet rs,int rowNum) throws SQLException {
				NoticeStatus status=new NoticeStatus();
				status.setStatusID(rs.getLong(1));
				status.setSid(rs.getString(2));
				status.setNid(rs.getInt(3));
				status.setStar(rs.getBoolean(4));
				status.setRead(rs.getBoolean(5));
				return status;
			}
		});
	}

	public int queryCount(String sid,int nid,boolean star,boolean read,int flags){
		String sql="SELECT COUNT(*) FROM NoticeStatus WHERE ";
		List<Object> queryArgs=new ArrayList<Object>();

		if((flags & this.SID) != 0){
			sql+="Sid=? ";
			queryArgs.add(sid);
		}
		if(((flags & this.NID)!=0) && ((flags & this.SID)!=0)){
			sql+="AND ";
		}
		if((flags & this.NID) != 0){
			sql+="Nid=? ";
			queryArgs.add(nid);
		}
		if((flags & this.STAR) != 0){
			sql+="AND StarStatus=? ";
			queryArgs.add(star);
		}
		if((flags & this.READ) != 0){
			sql+="AND ReadStatus=? ";
			queryArgs.add(read);
		}
		sql+=";";
		//String sql="SELECT COUNT(*) FROM NoticeStatus WHERE Sid=? AND ReadStatus=? ;";
		return jdbcTemplate.queryForObject(sql,queryArgs.toArray(),Integer.class);
	}

	public void update(NoticeStatus noticeStatus){
		String sql="UPDATE NoticeStatus SET StarStatus=?,ReadStatus=? WHERE ID=? ;";
		jdbcTemplate.update(sql,noticeStatus.getStar(),noticeStatus.getRead(),noticeStatus.getStatusID());
	}

	public void insert(NoticeStatus noticeStatus){
		String sql="INSERT INTO NoticeStatus(Sid,Nid,StarStatus,ReadStatus) VALUES(?,?,?,?) ;";
		jdbcTemplate.update(sql,noticeStatus.getSid(),noticeStatus.getNid(),noticeStatus.getStar(),noticeStatus.getRead());
	}

}
