package jp.co.axiz.entity;

public class UserInfo {

	private Integer userId;
	private String userName;
	private String telephone;
	private String password;

	public UserInfo() {
	}

	public UserInfo(Integer userId, String userName, String telephone, String password) {
		this.userId = userId;
		this.userName = userName;
		this.telephone = telephone;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
