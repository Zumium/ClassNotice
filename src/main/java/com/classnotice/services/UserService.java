package com.classnotice.services;

import com.classnotice.beans.Account;
import com.classnotice.db.entities.Student;
import com.classnotice.db.StudentDAO;
import com.classnotice.db.PortraitDAO;
import com.classnotice.db.GroupDAO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
import java.util.List;

@Service
public class UserService {

	private static final String ROLE_CLASSMATE="同学";
	private static final String GROUP_ALL="all";
	private static final String GROUP_COMMITTEE="committee";
	private static final String[] STRING_ARRAY={"A"};

	@Autowired
	private StudentDAO studentDao;
	@Autowired
	private PortraitDAO portraitDao;
	@Autowired
	private GroupDAO groupDao;

	public boolean vertifyUserAccount(Account account) {
		Student loginStudent=studentDao.query(account.getId());
		if(loginStudent==null){
			return false;
		}
		return loginStudent.getPassword().equals(account.getPassword());
	}

	public String getPortraitUrl(String sid) {
		if(portraitDao.queryPortraitExists(sid)){
			return "/portraits/"+sid;
		}
		return "/portraits/default";
	}

	public Student getStudent(String sid){
		return studentDao.query(sid);
	}
	
	public boolean isAdmin(String sid){
		Student student=this.getStudent(sid);
		return (!student.getRole().equals(ROLE_CLASSMATE));
	}
	
	public List<Student> getAllStudents(){
		return studentDao.queryAll();
	}

	public String[] getReceiversIdArray(String receiversGroup){
		if(receiversGroup.equals(GROUP_ALL)){
			//return studentDao.queryAllIds().toArray(STRING_ARRAY);
			return groupDao.queryMembers(null,"all");
		}
		else if(receiversGroup.equals(GROUP_COMMITTEE)){
			//return studentDao.queryCommitteeIds().toArray(STRING_ARRAY);
			return groupDao.queryMembers(null,"committee");
		}
		return null;
	}
}
