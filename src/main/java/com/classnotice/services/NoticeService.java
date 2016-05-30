package com.classnotice.services;

import com.classnotice.db.entities.Notice;
import com.classnotice.db.entities.NoticeStatus;
import com.classnotice.db.NoticeStatusDAO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class NoticeService {

	@Autowired
	private NoticeStatusDAO noticeStatusDao;

	public int CountUnreadNotice(String uid){
		List<NoticeStatus> results=noticeStatusDao.QueryBySidAndReadStatus(uid,false);
		return results.size();
	}

}
