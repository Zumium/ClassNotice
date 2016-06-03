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

	public int countUnreadNotice(String uid){
		return noticeStatusDao.queryNoticeCount(uid,false,false,NoticeStatusDAO.READ);
	}

	public int countReadNotice(String uid){
		return noticeStatusDao.queryNoticeCount(uid,false,true,NoticeStatusDAO.READ);
	}

	public int countStarNotice(String uid){
		return noticeStatusDao.queryNoticeCount(uid,true,false,NoticeStatusDAO.STAR);
	}

	public int countTotalNotice(String uid){
		return noticeStatusDao.queryNoticeCount(uid,false,false,0);
	}

	public List<Notice> getUnreadNotice(String uid){
		List<NoticeStatus> statusUnread=noticeStatusDao.query(uid,false,false,NoticeStatusDAO.READ);
		return convertNoticeStatusToNotice(statusUnread);
	}

	public List<Notice> getReadNotice(String uid){
		List<NoticeStatus> statusRead=noticeStatusDao.query(uid,false,true,NoticeStatusDAO.READ);
		return convertNoticeStatusToNotice(statusRead);
	}

	public List<Notice> getStarNotice(String uid){
		List<NoticeStatus> statusStar=noticeStatusDao.query(uid,true,false,NoticeStatusDAO.STAR);
		return convertNoticeStatusToNotice(statusStar);
	}

	public Notice getNotice(int nid){
		return noticeDao.query(nid);
	}

	public void setStar(String sid,int nid,boolean star){
		NoticeStatus status=noticeStatusDao.query(sid,nid);
		if(status.getStar()==star) return; //avoid useless i/o
		status.setStar(star);
		noticeStatusDao.update(status);
	}

	public void setRead(String sid,int nid,boolean read){
		NoticeStatus status=noticeStatusDao.query(sid,nid);
		if(status.getRead()==read) return; //avoid useless i/o
		status.setRead(read);
		noticeStatusDao.update(status);
	}
	
	public boolean getStar(String sid,int nid){
		return noticeStatusDao.query(sid,nid).getStar();
	}

	//Helper function
	private List<Notice> convertNoticeStatusToNotice(List<NoticeStatus> noticeStatuses){
		Iterator<NoticeStatus> statusIterator=noticeStatuses.iterator();
		List<Notice> notices=new ArrayList<Notice>();
		while(statusIterator.hasNext()){
			NoticeStatus noticeStatus=statusIterator.next();
			Notice notice=noticeDao.query(noticeStatus.getNid());
			notices.add(notice);
		}
		notices.sort(Comparator.comparingLong(new ToLongFunction<Notice>(){
			public long applyAsLong(Notice notice){
				return notice.getPublishTime().getTime();
			}
		}).reversed());

		return notices;
	}
}
