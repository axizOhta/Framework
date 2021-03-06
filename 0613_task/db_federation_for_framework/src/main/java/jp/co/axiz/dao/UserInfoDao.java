package jp.co.axiz.dao;

import java.util.List;

import jp.co.axiz.entity.UserInfo;

public interface UserInfoDao {

	public UserInfo findById(Integer id);

	public List<UserInfo> search (String id, String name, String tel);

	public void register(String name,String tel,String pass);

	public List<UserInfo> findMax();

	public int updateName(UserInfo user);

	public int updateTel(UserInfo user);

	public int updatePass(UserInfo user);

	public void deleteById(Integer id);

}
