package com.classnotice.services;

import com.classnotice.beans.Account;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	public boolean vertifyUserAccount(Account account) {
		String user=(String)account.getId();
		String password=(String)account.getPassword();
		return (user.equals("2014220402028")&&password.equals("123456"));
	}
}
