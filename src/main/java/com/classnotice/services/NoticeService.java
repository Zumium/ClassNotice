package com.classnotice.services;

import com.classnotice.db.entities.Notice;
import com.classnotice.db.entities.NoticeStatus;
import com.classnotice.db.NoticeStatusDAO;
import com.classnotice.db.NoticeDAO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@Service
public class NoticeService {

	@Autowired
	private NoticeStatusDAO noticeStatusDao;
	@Autowired
	private NoticeDAO noticeDao;

	public int CountUnreadNotice(String uid){
		return noticeStatusDao.QueryNoticeCountByRead(uid,false);
	}

	public List<Notice> GetUnreadNotice(String uid){
		List<NoticeStatus> statusUnread=noticeStatusDao.QueryBySidAndReadStatus(uid,false);
		Iterator<NoticeStatus> statusIterator=statusUnread.iterator();
		List<Notice> unreadNotice=new ArrayList<Notice>();
		while(statusIterator.hasNext()){
			NoticeStatus noticeStatus=statusIterator.next();
			Notice notice=noticeDao.Query(noticeStatus.getNid());
			unreadNotice.add(notice);
		}
		return unreadNotice;
	}

}
