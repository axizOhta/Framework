package jp.co.example.entity;
//Adminテーブル用のDTO
public class Admin {
	private String admin_id;
	private String admin_name;
	private String password;

	public Admin() {
	}
	public Admin(String admin_id, String admin_name, String password) {
		this.admin_id=admin_id;
		this.admin_name=admin_name;
		this.password=password;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
