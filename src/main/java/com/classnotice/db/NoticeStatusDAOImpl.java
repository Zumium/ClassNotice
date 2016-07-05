package com.classnotice.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.classnotice.db.entities.NoticeStatus;
import com.classnotice.db.NoticeStatusDAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Repository("noticeStatusDao")
public class NoticeStatusDAOImpl implements NoticeStatusDAO{
	private static final int SID=4;
	private static final int NID=8;
	private static final String TABLENAME_NOTICESTATUS="NoticeStatus";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* public NoticeStatus query(long id){
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

	public List<NoticeStatus> query(String sid,int nid,boolean star,boolean read,int flags){
		//String sql="SELECT * FROM NoticeStatus WHERE Sid=? ";
		String sql="SELECT * FROM NoticeStatus WHERE ";
		List<Object> queryArgs=new ArrayList<Object>();

		if((flags & this.SID) !=0) {
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
	} */
	@Override
	public long count(){
		String sql=String.format("SELECT COUNT(*) FROM %s;",TABLENAME_NOTICESTATUS);
		return jdbcTemplate.queryForObject(sql,Long.class);
	}

	@Override
	public void delete(Long id){
		String sql=String.format("DELETE FROM %s WHERE ID=?;",TABLENAME_NOTICESTATUS);
		jdbcTemplate.update(sql,id);
	}

	@Override
	public void delete(NoticeStatus noticeStatus){
		String sql=String.format("DELETE FROM %s WHERE ID=?;",TABLENAME_NOTICESTATUS);
		jdbcTemplate.update(sql,noticeStatus.getStatusID());
	}

	@Override
	public void delete(Iterable<? extends NoticeStatus> noticeStatuses){
		Iterator iter=noticeStatuses.iterator();
		NoticeStatus each=null;
		while(iter.hasNext()){
			each=(NoticeStatus)iter.next();
			this.delete(each);
		}
	}

	@Override
	public void deleteAll(){
		String sql=String.format("DELETE FROM %s;",TABLENAME_NOTICESTATUS);
		jdbcTemplate.update(sql);
	}

	@Override
	public boolean exists(Long id){
		String sql=String.format("SELECT COUNT(*) FROM %s WHERE ID=?;",TABLENAME_NOTICESTATUS);
		int ret=jdbcTemplate.queryForObject(sql,Integer.class,id);
		return ret!=0;
	}

	@Override
	public boolean exists(String sid,int nid){
		String sql=String.format("SELECT COUNT(*) FROM %s WHERE Sid=? AND Nid=?;",TABLENAME_NOTICESTATUS);
		int ret=jdbcTemplate.queryForObject(sql,Integer.class,sid,nid);
		return ret!=0;
	}

	@Override
	public Iterable<NoticeStatus> findAll(){
		String sql=String.format("SELECT * FROM %s;",TABLENAME_NOTICESTATUS);
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
		});
	}

	@Override
	public Iterable<NoticeStatus> findAll(Iterable<Long> ids){
		ArrayList<NoticeStatus> findResults=new ArrayList<NoticeStatus>();
		Iterator<Long> iter=ids.iterator();
		long eachId=0;
		NoticeStatus eachResult=null;
		while(iter.hasNext()){
			eachId=iter.next();
			eachResult=this.findOne(eachId);
			if(eachResult==null) continue;
			findResults.add(eachResult);
		}
		return findResults;
	}

	@Override
	public NoticeStatus findOne(Long id){
		String sql=String.format("SELECT * FROM %s WHERE ID=?;",TABLENAME_NOTICESTATUS);
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

	@Override
	public <NS extends NoticeStatus> Iterable<NS> save(Iterable<NS> noticeStatuses){
		Iterator<NS> iter=noticeStatuses.iterator();
		ArrayList<NS> savedInstances=new ArrayList<NS>();
		NS each=null;
		while(iter.hasNext()){
			each=iter.next();
			each=this.save(each);
			if(each==null) continue;
			savedInstances.add(each);
		}
		return savedInstances;
	}

	@Override
	public <NS extends NoticeStatus> NS save(NS noticeStatus){
		NoticeStatus noticeStatusPtr=(NoticeStatus)noticeStatus;
		if(this.exists(noticeStatusPtr.getStatusID())){
			String sql=String.format("UPDATE %s SET StarStatus=?,ReadStatus=?,Sid=?,Nid=? WHERE ID=?;",TABLENAME_NOTICESTATUS);
			int ret=jdbcTemplate.update(sql,noticeStatusPtr.getStar(),noticeStatusPtr.getRead(),noticeStatusPtr.getSid(),noticeStatusPtr.getNid(),noticeStatusPtr.getStatusID());
			if(ret!=0) return noticeStatus;
		}
		else{
			String sql=String.format("INSERT INTO %s(Sid,Nid,StarStatus,ReadStatus) VALUES(?,?,?,?);",TABLENAME_NOTICESTATUS);
			KeyHolder primaryKeyHolder=new GeneratedKeyHolder();
			int ret=jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException{
					PreparedStatement ps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1,noticeStatusPtr.getSid());
					ps.setInt(2,noticeStatusPtr.getNid());
					ps.setBoolean(3,noticeStatusPtr.getStar());
					ps.setBoolean(4,noticeStatusPtr.getRead());
					return ps;
				}
			},primaryKeyHolder);
			noticeStatusPtr.setStatusID(primaryKeyHolder.getKey().longValue());
			if(ret!=0) return (NS)noticeStatusPtr;
		}
		return null;
	}

	@Override
	public NoticeStatus findOne(String sid,int nid){
		String sql=String.format("SELECT * FROM %s WHERE Sid=? AND Nid=?;",TABLENAME_NOTICESTATUS);
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

	@Override
	public List<NoticeStatus> findAll(String sid){
		return query(sid,0,false,false,this.SID);
	}

	@Override
	public List<NoticeStatus> findAll(String sid,boolean star,boolean read,int flags){
		return query(sid,0,star,read,flags|this.SID);
	}

	@Override
	public List<NoticeStatus> findAll(int nid){
		return query(null,nid,false,false,this.NID);
	}

	@Override
	public List<NoticeStatus> findAll(int nid,boolean star,boolean read,int flags){
		return query(null,nid,star,read,flags|this.NID);
	}

	@Override
	public int count(String sid){
		return queryCount(sid,0,false,false,this.SID);
	}

	@Override
	public int count(String sid,boolean star,boolean read,int flags){
		return queryCount(sid,0,star,read,flags|this.SID);
	}

	@Override
	public int count(int nid){
		return queryCount(null,nid,false,false,this.NID);
	}

	@Override
	public int count(int nid,boolean star,boolean read,int flags){
		return queryCount(null,nid,star,read,flags|this.NID);
	}

	private List<NoticeStatus> query(String sid,int nid,boolean star,boolean read,int flags){
		String sql=String.format("SELECT * FROM %s WHERE ",TABLENAME_NOTICESTATUS);
		List<Object> queryArgs=new ArrayList<Object>();

		if((flags & this.SID) !=0) {
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

	private int queryCount(String sid,int nid,boolean star,boolean read,int flags){
		String sql=String.format("SELECT COUNT(*) FROM %s WHERE ",TABLENAME_NOTICESTATUS);
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
}
