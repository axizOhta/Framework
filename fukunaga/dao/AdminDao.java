package jp.co.example.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.entity.Admin;
@Repository
public class AdminDao {

	private static final String SQL_SELECT_ID_AND_PASS ="SELECT admin_id,admin_name,password FROM admin WHERE admin_id = ? AND password = ?";
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	public AdminDao(){

	}
	//ログインに使うメソッド
	public List<Admin> findByIdAndPass(String id, String pass) {

				return jdbcTemplate.query(SQL_SELECT_ID_AND_PASS,
						new BeanPropertyRowMapper<Admin>(Admin.class),id,pass);

	}
}
