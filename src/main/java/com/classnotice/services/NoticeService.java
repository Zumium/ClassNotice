package com.classnotice.services;

import com.classnotice.db.entities.Notice;
import com.classnotice.db.entities.NoticeStatus;
import com.classnotice.db.NoticeStatusDAO;
import com.classnotice.db.NoticeDAO;
import com.classnotice.beans.NewNotice;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.ToLongFunction;

@Service
public class NoticeService {

	private static final String NOTICE_PATH_BASE="/notice/";

	@Autowired
	private NoticeStatusDAO noticeStatusDao;
	@Autowired
	private NoticeDAO noticeDao;

	public int countUnreadNotice(String uid){
		return noticeStatusDao.queryCount(uid,0,false,false,NoticeStatusDAO.READ|NoticeStatusDAO.SID);
	}

	public int countReadNotice(String uid){
		return noticeStatusDao.queryCount(uid,0,false,true,NoticeStatusDAO.READ|NoticeStatusDAO.SID);
	}
	public int countReadNotice(int nid){
		return noticeStatusDao.queryCount(null,nid,false,true,NoticeStatusDAO.READ|NoticeStatusDAO.NID);
	}

	public int countStarNotice(String uid){
		return noticeStatusDao.queryCount(uid,0,true,false,NoticeStatusDAO.STAR|NoticeStatusDAO.SID);
	}

	public int countTotalNotice(String uid){
		return noticeStatusDao.queryCount(uid,0,false,false,NoticeStatusDAO.SID);
	}

	public int countSentNotice(String sender){
		return noticeDao.queryCountBySender(sender);
	}

	public int countReceivers(int nid){
		return noticeStatusDao.queryCount(null,nid,false,false,NoticeStatusDAO.NID);
	}

	public List<Notice> getUnreadNotice(String uid){
		List<NoticeStatus> statusUnread=noticeStatusDao.query(uid,0,false,false,NoticeStatusDAO.READ|NoticeStatusDAO.SID);
		return convertNoticeStatusToNotice(statusUnread);
	}

	public List<Notice> getReadNotice(String uid){
		List<NoticeStatus> statusRead=noticeStatusDao.query(uid,0,false,true,NoticeStatusDAO.READ|NoticeStatusDAO.SID);
		return convertNoticeStatusToNotice(statusRead);
	}

	public List<Notice> getStarNotice(String uid){
		List<NoticeStatus> statusStar=noticeStatusDao.query(uid,0,true,false,NoticeStatusDAO.STAR|NoticeStatusDAO.SID);
		return convertNoticeStatusToNotice(statusStar);
	}

	public List<Notice> getSentNotice(String sender){
		return noticeDao.queryBySender(sender);
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

	public void publishNewNotice(NewNotice newNotice){
		Notice notice=new Notice();
		notice.setTitle(newNotice.getTitle());
		notice.setContent(newNotice.getContent());
		notice.setSender(newNotice.getSender());
		notice.setPublishTime(newNotice.getPublishTime());
		noticeDao.insert(notice);
		
		String[] receivers=newNotice.getReceivers();
		for(String receiver: receivers){
			noticeStatusDao.insert(new NoticeStatus(receiver,notice.getID(),false,false));
		}
	}
	
	public String getNoticePath(int nid){
		return NOTICE_PATH_BASE+nid;
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
