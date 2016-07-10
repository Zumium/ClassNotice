package com.classnotice.services;

import com.classnotice.beans.Account;
import com.classnotice.db.entities.Student;

import java.util.List;

public interface UserService {
	public boolean vertifyUserAccount(Account account) ;
	public String getPortraitUrl(String sid) ;
	public Student getStudent(String sid);
	public boolean isAdmin(String sid);
	public List<Student> getAllStudents();
	public String[] getReceiversIdArray(String receiversGroup);
}
