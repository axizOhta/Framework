package jp.co.axiz.entity;

import javax.validation.constraints.NotBlank;

public class AdminForm {

	@NotBlank
	private String adminId;

	@NotBlank
	private String password;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
