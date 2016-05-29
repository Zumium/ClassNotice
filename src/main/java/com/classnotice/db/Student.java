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
	
	
	public static void Insert(Connection conn,Student student)throws SQLException{
		String sql = "INSERT INTO Student VALUES (?,?,?,?);";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, student.getID());
		stmt.setString(2,student.getName());
		stmt.setString(3, student.getPassword());
		stmt.setString(4, student.getRole());
	
		
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	public static void Update(Connection conn,Student student) throws SQLException{//ID cannot change
		String sql = "UPDATE Student SET Name=?,Password=?,Role=? WHERE ID =? ;";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, student.getName());
		stmt.setString(2,student.getPassword());
		stmt.setString(3, student.getRole());
		stmt.setString(4, student.getID());
		
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	public static void Delete (Connection conn,Student student){
		
	}
	
	public static Student Query(Connection conn,String id) throws Exception{
		Statement stmt =conn.createStatement();
		String sql = "select * from Student where id = '"+id+"';";
		stmt.executeQuery(sql);
		ResultSet rs=stmt.getResultSet();
		rs.first();
		return new Student(rs.getString("Name"),rs.getString("ID"),rs.getString("Password"),rs.getString("Role"));//create table using these names
	}
}

