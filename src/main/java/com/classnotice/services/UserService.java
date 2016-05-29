package com.classnotice.services;

import com.classnotice.beans.Account;
import com.classnotice.db.entities.Student;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
public class UserService {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	public boolean vertifyUserAccount(Account account) {
		Connection conn=null;
		boolean result=false;
		try{
			conn=dataSource.getConnection();
			Student loginStudent=Student.Query(conn,account.getId());
			if(loginStudent==null){
				result=false;
			}
			result=loginStudent.getPassword().equals(account.getPassword());
		}
		catch(Exception e){
			result=false;
		}
		finally{
			conn.close();
		}

		return result;
		/*String user=(String)account.getId();
		String password=(String)account.getPassword();
		return (user.equals("2014220402028")&&password.equals("123456")); */
	}
}
