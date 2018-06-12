package jp.co.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.entity.User_info;

@Repository
public class SearchDao {

	//SQL文
	public final String SQL_SEARCH_ALL = "SELECT user_id, user_name, telephone, password FROM user_info ORDER BY user_id";
	public final String SQL_SEARCH_ID = "SELECT MAX(user_id) FROM user_info";
	public final String SQL_SEARCH_WHERE_ID = "SELECT user_id, user_name, telephone, password FROM user_info WHERE user_id = ?";

	@Autowired
    private JdbcTemplate jT;

	//全件取得
	public List<User_info> findAll () {
		return jT.query(SQL_SEARCH_ALL, new BeanPropertyRowMapper<User_info>(User_info.class));
	}

	//ユーザーの検索
	public List<User_info> search (String id, String name, String tel) {
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
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<User_info>(User_info.class), Integer.parseInt(id), name, tel);

				} else if (!exId && exName && exTel) {
					//id×name○tel○
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<User_info>(User_info.class), 1, name, tel);

				} else if (exId && !exName && exTel) {
					//id○name×tel○
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<User_info>(User_info.class), Integer.parseInt(id), "%%", tel);

				} else if (exId && exName && !exTel) {
					//id○name○tel×
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<User_info>(User_info.class), Integer.parseInt(id), name, "%%");

				} else if (exId && !exName && !exTel) {
					//id○name×tel×
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<User_info>(User_info.class), Integer.parseInt(id), "%%", "%%");

				} else if (!exId && exName && !exTel) {
					//id×name○tel×
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<User_info>(User_info.class), 1, name, "%%");

				} else if (!exId && !exName && exTel) {
					//id×name×tel○
					return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<User_info>(User_info.class), 1, "%%", tel);

				}
			} else {
				//検索条件がない場合
				return jT.query(SQL_SELECT_INFO, new BeanPropertyRowMapper<User_info>(User_info.class));
			}
		return null;
	}

	//最新IDの取得

	public Integer latestId () {
		return jT.queryForObject(SQL_SEARCH_ID, Integer.class);
	}
//
	//IDを元に検索
	public User_info findById (int id) {
		return (User_info) jT.query(SQL_SEARCH_WHERE_ID, new BeanPropertyRowMapper<User_info>(User_info.class));
	}
}
