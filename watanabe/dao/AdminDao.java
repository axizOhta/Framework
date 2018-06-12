package jp.co.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import jp.co.example.entity.Admin;

public class AdminDao {

	@Autowired
	private NamedParameterJdbcTemplate nPJT;

	public Admin findByIdAndPass(String id,String pass) {

		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id).addValue("pass",pass);
		try {
			return nPJT.queryForObject("SELECT*FROM Admin WHERE admin_id= :id AND password = :pass",
					param,
					new BeanPropertyRowMapper<Admin>(Admin.class));
		}catch(EmptyResultDataAccessException e) {
			return null;
		}


	}





}

