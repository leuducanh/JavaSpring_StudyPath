package edu.hanoi.service.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class User {

	private String username;
	private String password;
	private String email;
	private int age;
	private int groupid;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, String email, int age, int groupid) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.groupid = groupid;
	}
	
	@Id
	@Column(name="username",unique=true,nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="age")
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Column(name="groupid",nullable=false)
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	
	
}
