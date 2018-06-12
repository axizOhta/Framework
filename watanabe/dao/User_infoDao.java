package jp.co.example.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import jp.co.example.entity.User_info;

public class User_infoDao {
	@Autowired
	private NamedParameterJdbcTemplate nPJT;

	public void register(String name,String tel,String pass) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("name",name).addValue("tel", tel).addValue("pass",pass);
		try {
			nPJT.update("INSERT INTO user_info (user_name,telephone,password) VALUES(:name,:tel,:pass)",param);
		}catch(EmptyResultDataAccessException e) {
		}
	}


	public Integer findMax() {
		SqlParameterSource param = new MapSqlParameterSource();
		try {
			List<User_info> list= nPJT.query("SELECT MAX(user_id) AS max FROM user_info",param,new BeanPropertyRowMapper<User_info>(User_info.class));
			return list.get(0).getUser_id();
		}catch(EmptyResultDataAccessException e) {
		}
		return null;
	}

	//	private Connection con;
	//	private static final String SQL_LOCK="LOCK TABLE buy_history IN ACCESS EXCLUSIVE MODE";
	//	private final String SELECT_ALL = "SELECT * FROM user_info";
	//	private final String SELECT_BY_ID = "SELECT * FROM user_info WHERE user_id = ?";
	//	private final String SELECT_BY_NAME = "SELECT * FROM user_info WHERE user_name = ?";
	//	private final String SELECT_BY_TEL = "SELECT * FROM user_info WHERE telephone = ?";
	//	private final String SELECT_BY_ID_NAME = "SELECT * FROM user_info WHERE user_id = ? AND user_name = ?";
	//	private final String SELECT_BY_ID_TEL = "SELECT * FROM user_info WHERE user_id = ? AND telephone = ?";
	//	private final String SELECT_BY_NAME_TEL = "SELECT * FROM user_info WHERE user_name = ? AND telephone = ?";
	//	private final String SELECT_BY_ID_NAME_TEL = "SELECT * FROM user_info WHERE user_id = ? AND user_name = ? AND telephone = ?";
	//	private final String SQL_INSERT = "INSERT INTO user_info(user_name,telephone,password) VALUES(?,?,?)";
	//	private final String SQL_UPDATE="UPDATE user_info SET user_name=?,telephone=?,password=? WHERE user_id=?";
	//	private final String SQL_DELETE="DELETE FROM user_info WHERE user_id=?";
	//
	//	public User_infoDao(Connection connection) {
	//		this.con=connection;
	//	}
	//
	//	//テーブルロックメソッド
	//	private  void lock() {
	//		PreparedStatement stmt=null;
	//		try {
	//			stmt = this.con.prepareStatement(SQL_LOCK);
	//			stmt.executeUpdate();
	//		}catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//	}
	//
	//
	//	//データ取得用メソッド
	//	public List<User_info> find(String id, String name,String tel) {
	//
	//		if(id==null && name==null && tel==null) {
	//			//全検索
	//			return selectAll();
	//		}else if(id!=null && name!=null && tel!=null) {
	//			//普通に3つで検索
	//			return selectByIdNameTel(id,name,tel);
	//		}else if(id==null &&name!=null && tel!=null) {
	//			//名前とtel検索
	//			return selectByNameTel(name,tel);
	//		}else if(id!=null &&name==null && tel!=null) {
	//			//IDとtel検索
	//			return selectByIdTel(id,tel);
	//		}else if(id!=null &&name!=null && tel!=null) {
	//			//IDと名前検索
	//			return selectByIdName(id,name);
	//		}else if(id!=null &&name==null && tel==null) {
	//			//ID検索
	//			return selectById(id);
	//		}else if(id==null &&name!=null && tel==null) {
	//			//名前検索
	//			return selectByName(name);
	//		}else if(id==null &&name==null && tel!=null) {
	//			//tel検索
	//			return selectByTel(tel);
	//		}
	//
	//		return selectAll();
	//	}
	//
	//
	//
	//
	//	private List<User_info> selectAll() {
	//		List<User_info> list = new ArrayList<>();
	//		PreparedStatement stmt = null;
	//		try {
	//			stmt = con.prepareStatement(SELECT_ALL);
	//			ResultSet rs = stmt.executeQuery();
	//			while (rs.next()) {
	//				list.add(new User_info(rs.getString("user_id"), rs.getString("user_name"), rs.getString("telephone")));
	//			}
	//			return list;
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//		return null;
	//	}
	//
	//	private List<User_info> selectById(String id) {
	//
	//		List<User_info> list = new ArrayList<>();
	//
	//		PreparedStatement stmt = null;
	//		try {
	//			stmt = con.prepareStatement(SELECT_BY_ID);
	//			Integer Id=Integer.parseInt(id);
	//			stmt.setInt(1, Id);
	//			ResultSet rs = stmt.executeQuery();
	//
	//			while (rs.next()) {
	//				list.add(new User_info(rs.getString("user_id"), rs.getString("user_name"), rs.getString("telephone")));
	//			}
	//			return list;
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//		return null;
	//	}
	//
	//	private List<User_info> selectByName(String name) {
	//
	//		List<User_info> list = new ArrayList<>();
	//
	//		PreparedStatement stmt = null;
	//		try {
	//			stmt = con.prepareStatement(SELECT_BY_NAME);
	//			stmt.setString(1, name);
	//			ResultSet rs = stmt.executeQuery();
	//
	//			while (rs.next()) {
	//				list.add(new User_info(rs.getString("user_id"), rs.getString("user_name"), rs.getString("telephone")));
	//			}
	//			return list;
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//		return null;
	//	}
	//
	//
	//
	//	private List<User_info> selectByTel(String tel) {
	//
	//		List<User_info> list = new ArrayList<>();
	//
	//		PreparedStatement stmt = null;
	//		try {
	//			stmt = con.prepareStatement(SELECT_BY_TEL);
	//			stmt.setString(1, tel);
	//			ResultSet rs = stmt.executeQuery();
	//
	//			while (rs.next()) {
	//				list.add(new User_info(rs.getString("user_id"), rs.getString("user_name"), rs.getString("telephone")));
	//			}
	//			return list;
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//		return null;
	//	}
	//
	//
	//	private List<User_info> selectByIdName(String id,String name) {
	//
	//		List<User_info> list = new ArrayList<>();
	//
	//		PreparedStatement stmt = null;
	//		try {
	//			stmt = con.prepareStatement(SELECT_BY_ID_NAME);
	//			Integer Id=Integer.parseInt(id);
	//			stmt.setInt(1, Id);
	//			stmt.setString(2, name);
	//			ResultSet rs = stmt.executeQuery();
	//			while (rs.next()) {
	//				list.add(new User_info(rs.getString("user_id"), rs.getString("user_name"), rs.getString("telephone")));
	//			}
	//			return list;
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//		return null;
	//	}
	//
	//
	//	private List<User_info> selectByIdTel(String id,String tel) {
	//
	//		List<User_info> list = new ArrayList<>();
	//
	//		PreparedStatement stmt = null;
	//		try {
	//			stmt = con.prepareStatement(SELECT_BY_ID_TEL);
	//			Integer Id=Integer.parseInt(id);
	//			stmt.setInt(1, Id);
	//			stmt.setString(2, tel);
	//			ResultSet rs = stmt.executeQuery();
	//			while (rs.next()) {
	//				list.add(new User_info(rs.getString("user_id"), rs.getString("user_name"), rs.getString("telephone")));
	//			}
	//			return list;
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//		return null;
	//	}
	//
	//	private List<User_info> selectByNameTel(String name,String tel) {
	//
	//		List<User_info> list = new ArrayList<>();
	//
	//		PreparedStatement stmt = null;
	//		try {
	//			stmt = con.prepareStatement(SELECT_BY_NAME_TEL);
	//			stmt.setString(1, name);
	//			stmt.setString(2, tel);
	//			ResultSet rs = stmt.executeQuery();
	//			while (rs.next()) {
	//				list.add(new User_info(rs.getString("user_id"), rs.getString("user_name"), rs.getString("telephone")));
	//			}
	//			return list;
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//		return null;
	//	}
	//
	//
	//
	//	private List<User_info> selectByIdNameTel(String id,String name,String tel) {
	//
	//		List<User_info> list = new ArrayList<>();
	//
	//		PreparedStatement stmt = null;
	//		try {
	//			stmt = con.prepareStatement(SELECT_BY_ID_NAME_TEL);
	//			Integer Id=Integer.parseInt(id);
	//			stmt.setInt(1, Id);
	//			stmt.setString(2, name);
	//			stmt.setString(3, tel);
	//			ResultSet rs = stmt.executeQuery();
	//			while (rs.next()) {
	//				list.add(new User_info(rs.getString("user_id"), rs.getString("user_name"), rs.getString("telephone")));
	//			}
	//			return list;
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//		return null;
	//	}
	//	public int findMaxId() {
	//		PreparedStatement stmt=null;
	//		lock(); //テーブルロック
	//		//id最大値取得
	//		try {
	//			String sql1 = "SELECT MAX(user_id) AS max FROM user_info";
	//
	//			stmt = con.prepareStatement(sql1);
	//			ResultSet rs = stmt.executeQuery();
	//
	//			if (rs.next()) {
	//				return rs.getInt("max");
	//			} else {
	//				System.out.println("idが取得できませんでした");
	//			}
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//		return -1;
	//	}
	//	public void register(String name,String tel, String pass) {//返り値は入力したやつの予定
	//		//ResultSet rs;
	//		PreparedStatement stmt=null;
	//		try {
	//			stmt = this.con.prepareStatement(SQL_INSERT);
	//			stmt.setString(1, name);
	//			stmt.setString(2, tel);
	//			stmt.setString(3, pass);
	//			stmt.executeUpdate();	//登録！
	//		}catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//		//		try {
	//		//			String sql ="SELECT*FROM user_info WHERE id=?";
	//		//			int id=findMaxId();
	//		//			stmt=this.con.prepareStatement(sql);
	//		//			stmt.setInt(1, id);
	//		//			rs=stmt.executeQuery();
	//		//			return new User_info(rs.getString("user_id"),rs.getString("user_name"),rs.getString("telephone"),rs.getString("password"));
	//		//		}catch (SQLException e) {
	//		//			e.printStackTrace();
	//		//		} finally {
	//		//			if (stmt != null) {
	//		//				try {
	//		//					stmt.close();
	//		//				} catch (SQLException e) {
	//		//					e.printStackTrace();
	//		//				}
	//		//			}
	//		//		}
	//		//		return selectId(findMaxId());
	//	}
	//
	//
	//	public User_info findById(String id) {
	//		PreparedStatement stmt = null;
	//		try {
	//			stmt = con.prepareStatement(SELECT_BY_ID);
	//			stmt.setInt(1, Integer.parseInt(id));
	//			ResultSet rs = stmt.executeQuery();
	//			while (rs.next()) {
	//				return new User_info(rs.getString("user_id"), rs.getString("user_name"), rs.getString("telephone"),rs.getString("password"));
	//			}
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//		return null;
	//	}
	//
	//	public void update(String id, String name, String tel, String pass) {
	//		PreparedStatement stmt = null;
	//		try {
	//			stmt = con.prepareStatement(SQL_UPDATE);
	//			stmt.setString(1, name);
	//			stmt.setString(2, tel);
	//			stmt.setString(3, pass);
	//			stmt.setInt(4, Integer.parseInt(id));
	//			stmt.executeUpdate();	//更新！！
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//	}
	//
	//	public void delete(String id) {
	//		PreparedStatement stmt = null;
	//		try {
	//			stmt = con.prepareStatement(SQL_DELETE);
	//			stmt.setInt(1, Integer.parseInt(id));
	//			stmt.executeUpdate();	//削除！！
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (stmt != null) {
	//				try {
	//					stmt.close();
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//	}


}
