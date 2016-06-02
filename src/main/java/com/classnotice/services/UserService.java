package com.classnotice.services;

import com.classnotice.beans.Account;
import com.classnotice.db.entities.Student;
import com.classnotice.db.StudentDAO;
import com.classnotice.db.PortraitDAO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;

@Service
public class UserService {

	@Autowired
	private StudentDAO studentDao;
	@Autowired
	private PortraitDAO portraitDao;

	@Autowired
	private String portraitsFolderPath;

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
}
