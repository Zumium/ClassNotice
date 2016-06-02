package com.classnotice.services;

import com.classnotice.beans.Account;
import com.classnotice.db.entities.Student;
import com.classnotice.db.StudentDAO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;

@Service
public class UserService {

	@Autowired
	private StudentDAO studentDao;

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
		File file=new File(portraitsFolderPath+sid);
		if(file.exists()){
			return "/portraits/"+sid;
		}
		return "/portraits/default";
	}
}
