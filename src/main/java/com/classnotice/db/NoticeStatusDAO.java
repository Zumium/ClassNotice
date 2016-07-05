package com.classnotice.db;

import org.springframework.data.repository.CrudRepository;

import com.classnotice.db.entities.NoticeStatus;

import java.util.List;

public interface NoticeStatusDAO extends CrudRepository<NoticeStatus,Long>{
	public static final int STAR=1;
	public static final int READ=2;

	boolean exists(String sid,int nid);

	NoticeStatus findOne(String sid,int nid);

	List<NoticeStatus> findAll(String sid);
	List<NoticeStatus> findAll(String sid,boolean star,boolean read,int flags);
	List<NoticeStatus> findAll(int nid);
	List<NoticeStatus> findAll(int nid,boolean star,boolean read,int flags);

	int count(String sid);
	int count(String sid,boolean star,boolean read,int flags);
	int count(int nid);
	int count(int nid,boolean star,boolean read,int flags);
}
