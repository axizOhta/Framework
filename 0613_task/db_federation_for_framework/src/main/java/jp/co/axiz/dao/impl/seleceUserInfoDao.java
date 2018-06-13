package jp.co.axiz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.axiz.dao.UserInfoDao;
import jp.co.axiz.entity.UserInfo;

@Repository
public class seleceUserInfoDao implements UserInfoDao {

	public final String SQL_SEARCH_ALL = "SELECT user_id, user_name, telephone, password FROM user_info ORDER BY user_id";
	public final String SQL_SEARCH_ID = "SELECT MAX(user_id) FROM user_info";
	public final String SQL_SEARCH_WHERE_ID = "SELECT user_id, user_name, telephone, password FROM user_info WHERE user_id = ?";

	@Autowired
    private JdbcTemplate jT;

	@Autowired
    private NamedParameterJdbcTemplate nPJT;

    public UserInfo findById(Integer id) {

            SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

            try {
                return nPJT.queryForObject(
                        "SELECT * FROM user_info WHERE user_id = :id",
                        param,
                        new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
    }

    public List<UserInfo> findAll () {
		return jT.query(SQL_SEARCH_ALL, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
	}

	//ユーザーの検索
	public List<UserInfo> search (String id, String name, String tel) {
		String SQL_SELECT_INFO = "SELECT user_id, user_name, telephone FROM user_info";

		boolean flg = (!(id.equals("")) || !(name.equals("")) || !(tel.equals("")));
		boolean exId = !(id.equals(""));
		boolean exName = !(name.equals(""));
		boolean exTel = !(tel.equals(""));

		if (flg) {
			SQL_SELECT_INFO += " WHERE";

			if (!(id.equals(""))) {
				SQL_SELECT_INFO += " user_id = ?";
			} else {
				SQL_SELECT_INFO += "'1' = ?";
			}
			if (!(name.equals(""))) {
				SQL_SELECT_INFO += " AND user_name = ?";
			} else {
				SQL_SELECT_INFO += " AND user_name LIKE ?";
			}
			if (!(tel.equals(""))) {
				SQL_SELECT_INFO += " AND telephone = ?";
			} else {
				SQL_SELECT_INFO += " AND telephone LIKE ?";
			}
		}

		SQL_SELECT_INFO += " ORDER BY user_id";

			if (flg) {

				if (exId && exName && exTel) {
					//id○name○tel○
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<UserInfo>(UserInfo.class), Integer.parseInt(id), name, tel);

				} else if (!exId && exName && exTel) {
					//id×name○tel○
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<UserInfo>(UserInfo.class), 1, name, tel);

				} else if (exId && !exName && exTel) {
					//id○name×tel○
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<UserInfo>(UserInfo.class), Integer.parseInt(id), "%%", tel);

				} else if (exId && exName && !exTel) {
					//id○name○tel×
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<UserInfo>(UserInfo.class), Integer.parseInt(id), name, "%%");

				} else if (exId && !exName && !exTel) {
					//id○name×tel×
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<UserInfo>(UserInfo.class), Integer.parseInt(id), "%%", "%%");

				} else if (!exId && exName && !exTel) {
					//id×name○tel×
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<UserInfo>(UserInfo.class), 1, name, "%%");

				} else if (!exId && !exName && exTel) {
					//id×name×tel○
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<UserInfo>(UserInfo.class), 1, "%%", tel);

				}
			} else {
				//検索条件がない場合
				return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
			}
		return null;
	}

	//最新IDの取得

//	public Integer latestId () {
//		return jT.queryForObject(SQL_SEARCH_ID, Integer.class);
//	}
//
	//IDを元に検索
//	public UserInfo findById (int id) {
//		return (UserInfo) jT.query(SQL_SEARCH_WHERE_ID, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
//	}

	public void register(String name,String tel,String pass) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("name",name).addValue("tel", tel).addValue("pass",pass);
		try {
			nPJT.update("INSERT INTO user_info (user_name,telephone,password) VALUES(:name,:tel,:pass)",param);
		}catch(EmptyResultDataAccessException e) {
		}
	}


	public List<UserInfo> findMax() {
		SqlParameterSource param = new MapSqlParameterSource();
		try {
			return  nPJT.query("SELECT * FROM user_info WHERE user_id = (SELECT MAX(user_id) AS max FROM user_info)",param,new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		}catch(EmptyResultDataAccessException e) {
		}
		return null;
	}

    public int updateName(UserInfo user){

    	Integer id = user.getUserId();
    	String name = user.getUserName();

    	SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("name", name);

    	return nPJT.update("UPDATE user_info SET user_name = :name WHERE user_id= :id", param);

    }

	public int updateTel(UserInfo user) {

		Integer id = user.getUserId();
    	String tel = user.getTelephone();

    	SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("tel", tel);

    	return nPJT.update("UPDATE user_info SET telephone = :tel WHERE user_id= :id", param);

	}

	public int updatePass(UserInfo user) {

		Integer id = user.getUserId();
    	String pass = user.getPassword();

    	SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("pass", pass);

    	return nPJT.update("UPDATE user_info SET password = :pass WHERE user_id= :id", param);

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
