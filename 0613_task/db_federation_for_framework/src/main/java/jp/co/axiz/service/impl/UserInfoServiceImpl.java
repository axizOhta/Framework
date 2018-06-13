package jp.co.axiz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.dao.UserInfoDao;
import jp.co.axiz.entity.UserInfo;
import jp.co.axiz.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao uID;

	@Override
	public UserInfo findById(Integer id) {
		return uID.findById(id);
	}

	//数字判定
	public boolean isNumber(String id) {
		try {
			Integer.parseInt(id);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}


	//検索結果を取得
	public List<UserInfo> search (String id, String name, String tel) {
		if(id != "" && !isNumber(id)) {
			return null;
		}
		return uID.search(id, name, tel);

	}

	//		public Integer latestId () {
	//			return uID.latestId();
	//		}

	//IDを元にデータ取得
	public UserInfo findById (String id) {
		return uID.findById(Integer.parseInt(id));

	}

	public void register(String name,String tel,String pass) {
		try{
			uID.register(name,tel,pass);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public List<UserInfo> findMaxId() {
		try{
			return uID.findMax();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public int updateName(UserInfo user) {
		return uID.updateName(user);
	}

	public int updateTel(UserInfo user) {
		return uID.updateTel(user);
	}

	public int updatePass(UserInfo user) {
		return uID.updatePass(user);
	}

	public void deleteById(Integer id) {
		try{
			uID.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
