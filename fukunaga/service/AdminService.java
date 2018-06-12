package jp.co.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.dao.AdminDao;
import jp.co.example.entity.Admin;
@Service
public class AdminService {
	@Autowired
private AdminDao adminDao;
	public Admin findByID(String id,String pass) {
		try{
			List<Admin> log = adminDao.findByIdAndPass(id,pass);
			Admin login=log.get(0);
			return login;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


}
