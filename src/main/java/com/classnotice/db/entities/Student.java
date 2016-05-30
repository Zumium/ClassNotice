package com.classnotice.db.entities;
import java.sql.*;

public class Student {
	private String name=null;
	private String id=null;
	private String password=null;
	private String role=null;
	
	public Student(){}
	
	public Student(String name,String id,String password,String role){
		this.name=name;
		this.id=id;
		this.password=password;
		this.role=role;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setID(String id){
		this.id=id;
	}
	public String getID(){
		return id;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
	
	public void setRole(String role){
		this.role=role;
	}
	public String getRole(){
		return role;
	}
	
}

