package com.classnotice.db;

import org.springframework.data.repository.CrudRepository;

import com.classnotice.db.entities.Notice;

import java.util.List;

public interface NoticeDAO extends CrudRepository<Notice,Integer>{

	public static final int STAR=1;
	public static final int READ=2;

	int countSent(String senderId);
	List<Notice> findSent(String senderId);
	List<Notice> findSent(String senderId,int limit,int offset);
	List<Notice> findRecv(String sid);
	List<Notice> findRecv(String sid,int limit,int offset);
	List<Notice> findRecv(String sid,boolean star,boolean read,int flags);
	List<Notice> findRecv(String sid,boolean star,boolean read,int flags,int limit,int offset);
}
