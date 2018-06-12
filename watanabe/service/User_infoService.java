package jp.co.example.service;
import org.springframework.beans.factory.annotation.Autowired;

import jp.co.example.dao.User_infoDao;
public class User_infoService {

	@Autowired
	private User_infoDao userInfoDao;

	public void register(String name,String tel,String pass) {
		try{
			userInfoDao.register(name,tel,pass);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public Integer findMaxId() {
		try{
			return userInfoDao.findMax();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}






}
