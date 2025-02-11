package com.jackbravo21.products_java.dto;

public class UserDTO {

	Long id;
	String fullname;
	String mail;
	String password;
	Integer administrator = 0;
	String created_at;
	
	public UserDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Integer administrator) {
		this.administrator = administrator;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
		
}
