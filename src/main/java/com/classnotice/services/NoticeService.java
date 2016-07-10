package com.classnotice.services;

import com.classnotice.db.entities.Notice;
import com.classnotice.beans.NewNotice;

import java.util.List;

public interface NoticeService {
	public int countUnreadNotice(String uid);
	public int countReadNotice(String uid);
	public int countReadNotice(int nid);
	public int countStarNotice(String uid);
	public int countTotalNotice(String uid);
	public int countSentNotice(String sender);
	public int countReceivers(int nid);

	public List<Notice> getUnreadNotice(String uid,int page);
	public List<Notice> getReadNotice(String uid,int page);
	public List<Notice> getStarNotice(String uid,int page);
	public List<Notice> getSentNotice(String sender,int page);

	public Notice getNotice(int nid);

	public void setStar(String sid,int nid,boolean star);
	public void setRead(String sid,int nid,boolean read);
	
	public boolean getStar(String sid,int nid);
	public boolean getRead(String sid,int nid);

	public void publishNewNotice(NewNotice newNotice);
	
	public String getNoticePath(int nid);
}
