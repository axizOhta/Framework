package jp.co.axiz.dao;

import jp.co.axiz.entity.Admin;

public interface AdminDao {

	public Admin findById(String id, String pass);

}
