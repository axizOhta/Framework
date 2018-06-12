package jp.co.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.entity.UserInfo;

@Repository
public class UserInfoDao {
	@Autowired
	private NamedParameterJdbcTemplate nPJT;


	public List<UserInfo> findById(Integer id) {

		SqlParameterSource param =new MapSqlParameterSource().addValue("id",id);

		try {
			return nPJT.query(
					"SELECT * FROM user_info WHERE user_id = :id",
					param,
					new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void deleteById(Integer id) {

		SqlParameterSource param =new MapSqlParameterSource().addValue("id",id);

		try {
			nPJT.update(
					"DELETE FROM user_info WHERE user_id = :id",
					param);

		}catch(EmptyResultDataAccessException e) {
		}
	}

}
