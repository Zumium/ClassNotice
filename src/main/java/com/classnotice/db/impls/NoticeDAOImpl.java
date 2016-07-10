package com.classnotice.db.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.classnotice.db.entities.Notice;
import com.classnotice.db.NoticeDAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Repository("noticeDao")
public class NoticeDAOImpl implements NoticeDAO{
	
	private static final String TABLENAME_NOTICE="Notice";
	private static final String TABLENAME_NOTICESTATUS="NoticeStatus";
	private static final int PARTLY=4;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* public Notice query(int id){
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
	} */
	@Override
	public long count(){
		String sql=String.format("SELECT COUNT(*) FROM %s;",TABLENAME_NOTICE);
		return jdbcTemplate.queryForObject(sql,Long.class);
	}

	@Override
	public void delete(Integer id){
		String sql=String.format("DELETE FROM %s WHERE ID=?;",TABLENAME_NOTICE);
		jdbcTemplate.update(sql,id);
	}

	@Override
	public void delete(Notice notice){
		String sql=String.format("DELETE FROM %s WHERE ID=?;",TABLENAME_NOTICE);
		jdbcTemplate.update(sql,notice.getID());
	}

	@Override
	public void delete(Iterable<? extends Notice> notices){
		Iterator iter=notices.iterator();
		Notice eachNotice=null;
		while(iter.hasNext()){
			eachNotice=(Notice)iter.next();
			this.delete(eachNotice);
		}
	}

	@Override
	public void deleteAll(){
		String sql=String.format("DELETE FROM %s;",TABLENAME_NOTICE);
		jdbcTemplate.update(sql);
	}

	@Override
	public boolean exists(Integer id){
		String sql=String.format("SELECT COUNT(*) FROM %s WHERE ID=?;",TABLENAME_NOTICE);
		int rowNum=jdbcTemplate.queryForObject(sql,Integer.class,id);
		return rowNum!=0;
	}

	@Override
	public Iterable<Notice> findAll(){
		String sql=String.format("SELECT * FROM %s;",TABLENAME_NOTICE);
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
		});
	}

	@Override
	public Iterable<Notice> findAll(Iterable<Integer> ids){
		Iterator<Integer> iter=ids.iterator();
		ArrayList<Notice> founded=new ArrayList<Notice>();
		int eachId=0;
		Notice eachFound=null;
		while(iter.hasNext()){
			eachId=iter.next();
			eachFound=this.findOne(eachId);
			if(eachFound==null) continue;
			founded.add(eachFound);
		}
		return founded;
	}

	@Override
	public Notice findOne(Integer id){
		String sql=String.format("SELECT * FROM %s WHERE ID=?;",TABLENAME_NOTICE);
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

	@Override
	public <N extends Notice> Iterable<N> save(Iterable<N> notices){
		Iterator iter=notices.iterator();
		Notice eachNotice=null;
		ArrayList<N> savedInstances=new ArrayList<N>();
		while(iter.hasNext()){
			eachNotice=(Notice)iter.next();
			eachNotice=this.save(eachNotice);
			if(eachNotice==null) continue;
			savedInstances.add((N)eachNotice);
		}
		return savedInstances;
	}

	@Override
	public <N extends Notice> N save(N notice){
		if(this.exists(notice.getID())){
			String sql=String.format("UPDATE %s SET Title=?,Content=?,Sender=?,PublishTime=? WHERE ID=?;",TABLENAME_NOTICE);
			int ret=jdbcTemplate.update(sql,notice.getTitle(),notice.getContent(),notice.getSender(),notice.getPublishTime(),notice.getID());
			if(ret!=0) return notice;
		}
		else{
			//String sql="INSERT INTO Notice(Title,Content,Sender,PublishTime) VALUES(?,?,?,?) ;";
			String sql=String.format("INSERT INTO %s(Title,Content,Sender,PublishTime) VALUES(?,?,?,?);",TABLENAME_NOTICE);
			KeyHolder primaryKeyHolder=new GeneratedKeyHolder();
			int ret=jdbcTemplate.update(new PreparedStatementCreator(){
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
			if(ret!=0) return notice;
		}
		return null;
	}

	@Override
	public int countSent(String senderId){
		//String sql="SELECT COUNT(*) FROM Notice WHERE Sender=? ;";
		String sql=String.format("SELECT COUNT(*) FROM %s WHERE Sender=?;",TABLENAME_NOTICE);
		return jdbcTemplate.queryForObject(sql,Integer.class,senderId);
	}

	@Override
	public List<Notice> findSent(String senderId){
		//String sql="SELECT * FROM Notice WHERE Sender=? ORDER BY PublishTime DESC ;";
		String sql=String.format("SELECT * FROM %s WHERE Sender=? ORDER BY PublishTime DESC;",TABLENAME_NOTICE);
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
		},senderId);
	}

	@Override
	public List<Notice> findSent(String senderId,int limit,int offset){
		String sql=String.format("SELECT * FROM %s WHERE Sender=? ORDER BY PublishTime DESC LIMIT %d OFFSET %d;",TABLENAME_NOTICE,limit,offset);
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
		},senderId);
	}

	@Override
	public List<Notice> findRecv(String sid){
		return query(sid,false,false,0,0,0);
	}

	@Override
	public List<Notice> findRecv(String sid,int limit,int offset){
		return query(sid,false,false,limit,offset,this.PARTLY);
	}

	@Override
	public List<Notice> findRecv(String sid,boolean star,boolean read,int flags){
		return query(sid,star,read,0,0,flags);
	}

	@Override
	public List<Notice> findRecv(String sid,boolean star,boolean read,int flags,int limit,int offset){
		int actualFlags=flags|this.PARTLY;
		return query(sid,star,read,limit,offset,actualFlags);
	}

	private List<Notice> query(String sid,boolean star,boolean read,int limit,int offset,int flags){
		//String sql="SELECT N.* FROM NoticeStatus AS NS JOIN Notice AS N ON NS.Nid=N.ID WHERE NS.Sid=? ";
		String sql=String.format("SELECT N.* FROM %s AS NS JOIN %s AS N ON NS.Nid=N.ID WHERE NS.Sid=? ",TABLENAME_NOTICESTATUS,TABLENAME_NOTICE);
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
		sql+="ORDER BY N.PublishTime DESC ";
		if((flags & this.PARTLY) != 0){
			sql+=String.format("LIMIT %d OFFSET %d ",limit,offset);
		}
		//sql+="ORDER BY N.PublishTime DESC;";
		sql+=";";

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
}
