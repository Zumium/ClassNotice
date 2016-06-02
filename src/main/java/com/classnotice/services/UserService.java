package com.classnotice.services;

import com.classnotice.beans.Account;
import com.classnotice.db.entities.Student;
import com.classnotice.db.StudentDAO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
public class UserService {

	@Autowired
	private StudentDAO studentDao;

	public boolean vertifyUserAccount(Account account) {
		Student loginStudent=studentDao.query(account.getId());
		if(loginStudent==null){
			return false;
		}
		return loginStudent.getPassword().equals(account.getPassword());
	}
}
