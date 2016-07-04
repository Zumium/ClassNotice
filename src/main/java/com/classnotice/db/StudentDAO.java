package com.classnotice.db;

import org.springframework.data.repository.CrudRepository;

import com.classnotice.db.entities.Student;

public interface StudentDAO extends CrudRepository<Student,String>{}
