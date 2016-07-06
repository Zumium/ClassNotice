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
	private static final int NOTICE_PAGESIZE=4; //4 notices for each page
	private static final int NOTICESTATUS_PAGESIZE=5; //5 noticestatuses for each page

	@Autowired
	private NoticeStatusDAO noticeStatusDao;
	@Autowired
	private NoticeDAO noticeDao;

	public int countUnreadNotice(String uid){
		return noticeStatusDao.count(uid,false,false,NoticeStatusDAO.READ);
	}

	public int countReadNotice(String uid){
		return noticeStatusDao.count(uid,false,true,NoticeStatusDAO.READ);
	}
	public int countReadNotice(int nid){
		return noticeStatusDao.count(nid,false,true,NoticeStatusDAO.READ);
	}

	public int countStarNotice(String uid){
		return noticeStatusDao.count(uid,true,false,NoticeStatusDAO.STAR);
	}

	public int countTotalNotice(String uid){
		return noticeStatusDao.count(uid);
	}

	public int countSentNotice(String sender){
		return noticeDao.countSent(sender);
	}

	public int countReceivers(int nid){
		return noticeStatusDao.count(nid);
	}

	public List<Notice> getUnreadNotice(String uid,int page){
		return noticeDao.findRecv(uid,false,false,NoticeDAO.READ,NOTICE_PAGESIZE,calNoticePagingOffset(page));
	}

	public List<Notice> getReadNotice(String uid,int page){
		return noticeDao.findRecv(uid,false,true,NoticeDAO.READ,NOTICE_PAGESIZE,calNoticePagingOffset(page));
	}

	public List<Notice> getStarNotice(String uid,int page){
		return noticeDao.findRecv(uid,true,false,NoticeDAO.STAR,NOTICE_PAGESIZE,calNoticePagingOffset(page));
	}

	public List<Notice> getSentNotice(String sender,int page){
		return noticeDao.findSent(sender,NOTICESTATUS_PAGESIZE,calNoticeStatusPagingOffset(page));
	}

	public Notice getNotice(int nid){
		return noticeDao.findOne(nid);
	}

	public void setStar(String sid,int nid,boolean star){
		NoticeStatus status=noticeStatusDao.findOne(sid,nid);
		if(status.getStar()==star) return; //avoid useless i/o
		status.setStar(star);
		noticeStatusDao.save(status);
	}

	public void setRead(String sid,int nid,boolean read){
		NoticeStatus status=noticeStatusDao.findOne(sid,nid);
		if(status.getRead()==read) return; //avoid useless i/o
		status.setRead(read);
		noticeStatusDao.save(status);
	}
	
	public boolean getStar(String sid,int nid){
		return noticeStatusDao.findOne(sid,nid).getStar();
	}

	public boolean getRead(String sid,int nid){
		return noticeStatusDao.findOne(sid,nid).getRead();
	}

	public void publishNewNotice(NewNotice newNotice){
		Notice notice=new Notice();
		notice.setTitle(newNotice.getTitle());
		notice.setContent(newNotice.getContent());
		notice.setSender(newNotice.getSender());
		notice.setPublishTime(newNotice.getPublishTime());
		notice=noticeDao.save(notice);
		
		String[] receivers=newNotice.getReceivers();
		for(String receiver: receivers){
			noticeStatusDao.save(new NoticeStatus(receiver,notice.getID(),false,false));
		}
	}
	
	public String getNoticePath(int nid){
		return NOTICE_PATH_BASE+nid;
	}

	//calculate offset for paging(notice)
	private int calNoticePagingOffset(int page){
		return page*NOTICE_PAGESIZE;
	}
	//calculate offset for paging(noticestatus)
	private int calNoticeStatusPagingOffset(int page){
		return page*NOTICESTATUS_PAGESIZE;
	}

}
