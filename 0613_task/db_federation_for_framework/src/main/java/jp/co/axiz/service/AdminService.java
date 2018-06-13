package jp.co.axiz.service;

import jp.co.axiz.entity.Admin;

public interface AdminService {

	public Admin findById(String id, String pass);

}
