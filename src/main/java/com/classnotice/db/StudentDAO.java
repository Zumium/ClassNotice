package com.classnotice.db;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.classnotice.db.entities.Student;

import java.sql.*;

@Repository
public class StudentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(Connection conn,Student student)throws SQLException{
		String sql = "INSERT INTO Student VALUES (?,?,?,?);";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, student.getID());
		stmt.setString(2,student.getName());
		stmt.setString(3, student.getPassword());
		stmt.setString(4, student.getRole());
	
		
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	public void update(Connection conn,Student student) throws SQLException{//ID cannot change
		String sql = "UPDATE Student SET Name=?,Password=?,Role=? WHERE ID =? ;";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, student.getName());
		stmt.setString(2,student.getPassword());
		stmt.setString(3, student.getRole());
		stmt.setString(4, student.getID());
		
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	public void Delete (Connection conn,Student student){
		
	}
	
	/* public Student Query(Connection conn,String id) throws Exception{
		Statement stmt =conn.createStatement();
		String sql = "select * from Student where id = '"+id+"';";
		stmt.executeQuery(sql);
		ResultSet rs=stmt.getResultSet();
		rs.first();
		Student student=new Student(rs.getString("Name"),rs.getString("ID"),rs.getString("Password"),rs.getString("Role"));//create table using these names
		stmt.close();
		return student;
	} */
	public Student query(String id) {
		String sql="SELECT * FROM Student WHERE id=? ;";
		return jdbcTemplate.queryForObject(sql,new RowMapper<Student>(){
			public Student mapRow(ResultSet rs,int rowNum) throws SQLException{
				Student student=new Student();
				student.setID(rs.getString(1));
				student.setName(rs.getString(2));
				student.setPassword(rs.getString(3));
				student.setRole(rs.getString(4));
				return student;
			}
		},id);
	}
}

