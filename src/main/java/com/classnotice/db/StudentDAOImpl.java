package com.classnotice.db;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.classnotice.db.entities.Student;
import com.classnotice.db.StudentDAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Repository("studentDao")
public class StudentDAOImpl implements StudentDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String TABLE_NAME="Student";
	
	@Override
	public long count(){
		String sql=String.format("SELECT COUNT(*) FROM %s;",TABLE_NAME);
		return jdbcTemplate.queryForObject(sql,Long.class);
	}

	@Override
	public void delete(String id){
		String sql=String.format("DELETE FROM %s WHERE ID=?;",TABLE_NAME);
		jdbcTemplate.update(sql,id);
	}

	@Override
	public void delete(Student student){
		String sql=String.format("DELETE FROM %s WHERE ID=?;",TABLE_NAME);
		jdbcTemplate.update(sql,student.getID());
	}

	@Override
	public void delete(Iterable<? extends Student> students){
		String sql=String.format("DETELE FROM %s WHERE ID=?;",TABLE_NAME);
		Iterator iter=students.iterator();
		Student each=null;
		while(iter.hasNext()){
			each=(Student)iter.next();
			jdbcTemplate.update(sql,each.getID());
		}
	}

	@Override
	public void deleteAll(){
		String sql=String.format("DELETE FROM %s;",TABLE_NAME);
		jdbcTemplate.update(sql);
	}

	@Override
	public boolean exists(String id){
		String sql=String.format("SELECT COUNT(*) FROM %s WHERE ID=?;",TABLE_NAME);
		long count=jdbcTemplate.queryForObject(sql,Long.class,id);
		return count!=0;
	}

	@Override
	public Iterable<Student> findAll(){
		String sql=String.format("SELECT * FROM %s;",TABLE_NAME);
		return jdbcTemplate.query(sql,new RowMapper<Student>(){
			public Student mapRow(ResultSet rs,int rowNum) throws SQLException{
				Student student=new Student();
				student.setID(rs.getString(1));
				student.setName(rs.getString(2));
				student.setPassword(rs.getString(3));
				student.setRole(rs.getString(4));
				return student;
			}
		});
	}

	@Override
	public Iterable<Student> findAll(Iterable<String> ids){
		ArrayList<Student> students=new ArrayList<Student>();
		Iterator<String> iter=ids.iterator();
		String eachId=null;
		while(iter.hasNext()){
			eachId=iter.next();
			students.add(this.findOne(eachId));
		}
		return students;
	}

	@Override
	public Student findOne(String id){
		String sql=String.format("SELECT * FROM %s WHERE ID=?;",TABLE_NAME);
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

	@Override
	public <S extends Student> Iterable<S> save(Iterable<S> students){
		Iterator<S> studentIter=students.iterator();
		ArrayList<S> savedStudents=new ArrayList<S>();
		S eachStudent=null;
		while(studentIter.hasNext()){
			eachStudent=studentIter.next();
			eachStudent=this.save(eachStudent);
			if(eachStudent==null) continue;
			savedStudents.add(eachStudent);
		}
		return savedStudents;
	}

	@Override
	public <S extends Student> S save(S student){
		String sql=String.format("UPDATE %s SET Name=?,Password=?,Role=? WHERE ID=?;",TABLE_NAME);
		int ret=jdbcTemplate.update(sql,student.getName(),student.getPassword(),student.getRole(),student.getID());
		if(ret!=0){
			return student;
		}
		return null;
	}
}

