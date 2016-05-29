package com.classnotice.services;

import com.classnotice.db.entities.Notice;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class NoticeService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

}
