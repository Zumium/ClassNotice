package com.classnotice.services.impls;

import com.classnotice.beans.Account;
import com.classnotice.db.entities.Student;
import com.classnotice.db.PortraitDAO;
import com.classnotice.db.GroupDAO;
import com.classnotice.db.StudentDAO;
import com.classnotice.services.UserService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

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

	@Override
	public boolean vertifyUserAccount(Account account) {
		Student loginStudent=studentDao.findOne(account.getId());
		if(loginStudent==null){
			return false;
		}
		return loginStudent.getPassword().equals(account.getPassword());
	}

	@Override
	public String getPortraitUrl(String sid) {
		if(portraitDao.queryPortraitExists(sid)){
			return "/portraits/"+sid;
		}
		return "/portraits/default";
	}

	@Override
	public Student getStudent(String sid){
		return studentDao.findOne(sid);
	}
	
	@Override
	public boolean isAdmin(String sid){
		Student student=this.getStudent(sid);
		return (!student.getRole().equals(ROLE_CLASSMATE));
	}
	
	@Override
	public List<Student> getAllStudents(){
		return (List<Student>)studentDao.findAll();
	}

	@Override
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
