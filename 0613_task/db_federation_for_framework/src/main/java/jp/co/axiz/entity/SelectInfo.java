package jp.co.axiz.entity;

import javax.validation.constraints.Pattern;

public class SelectInfo {

	@Pattern(regexp = "^\\d{0,3}$", message="無効なidです")
	private String user_id;

	private String user_name;

	private String telephone;
	private String password;

	public SelectInfo() {

	}

	public SelectInfo(String user_id, String user_name, String telephone) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.telephone = telephone;
	}

	public SelectInfo(String user_id, String user_name, String telephone, String password) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.telephone = telephone;
		this.password = password;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
