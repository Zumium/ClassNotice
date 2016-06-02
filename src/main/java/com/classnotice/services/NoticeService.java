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
import java.util.Comparator;
import java.util.function.ToLongFunction;

@Service
public class NoticeService {

	@Autowired
	private NoticeStatusDAO noticeStatusDao;
	@Autowired
	private NoticeDAO noticeDao;

	public int CountUnreadNotice(String uid){
		return noticeStatusDao.queryNoticeCount(uid,false,false,NoticeStatusDAO.READ);
	}

	public List<Notice> GetUnreadNotice(String uid){
		List<NoticeStatus> statusUnread=noticeStatusDao.query(uid,false,false,NoticeStatusDAO.READ);
		Iterator<NoticeStatus> statusIterator=statusUnread.iterator();
		List<Notice> unreadNotice=new ArrayList<Notice>();
		while(statusIterator.hasNext()){
			NoticeStatus noticeStatus=statusIterator.next();
			Notice notice=noticeDao.query(noticeStatus.getNid());
			unreadNotice.add(notice);
		}
		unreadNotice.sort(Comparator.comparingLong(new ToLongFunction<Notice>(){
			public long applyAsLong(Notice notice){
				return notice.getPublishTime().getTime();
			}
		}).reversed());

		return unreadNotice;
	}

}
