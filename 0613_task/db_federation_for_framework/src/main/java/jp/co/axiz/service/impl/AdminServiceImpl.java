package jp.co.axiz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.dao.AdminDao;
import jp.co.axiz.entity.Admin;
import jp.co.axiz.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public Admin findById(String id,String pass) {
		return adminDao.findById(id,pass);
	}

}
