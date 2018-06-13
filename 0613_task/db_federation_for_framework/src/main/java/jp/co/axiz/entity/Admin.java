package jp.co.axiz.entity;

public class Admin {

	private String adminId;
	private String adminName;
	private String password;

	public Admin() {
	}

	public Admin(String adminId, String adminName, String password) {
		this.adminId = adminId;
		this.adminName = adminName;
		this.password = password;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
