package jp.co.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.dao.UserInfoDao;
import jp.co.example.entity.UserInfo;



@Service
public class UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;

	public UserInfo findById(Integer id) {
		try{
			List<UserInfo> log = userInfoDao.findById(id);
			UserInfo info=log.get(0);
			return info;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public void deleteById(Integer id) {
		try{
			userInfoDao.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
