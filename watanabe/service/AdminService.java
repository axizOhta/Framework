package jp.co.example.service;
import org.springframework.beans.factory.annotation.Autowired;

import jp.co.example.dao.AdminDao;
import jp.co.example.entity.Admin;
public class AdminService {

	@Autowired
	private AdminDao adminDao;


	public Admin authentication(String id,String pass) {
		try{
			Admin admin = adminDao.findByIdAndPass(id,pass);
			return admin;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}