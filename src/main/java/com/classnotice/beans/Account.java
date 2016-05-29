package com.classnotice.beans;

public class Account{
	private String id;
	private String password;

	public Account(){}

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return this.id;
	}

	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return this.password;
	}
}
