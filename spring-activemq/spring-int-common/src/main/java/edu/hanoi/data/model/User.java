package edu.hanoi.data.model;

import java.io.Serializable;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import edu.hanoi.message.model.Group;
//
//
//@Entity
//@Table(name="HN_USER")
public class User implements Serializable{
	private String username;
	private String password;
	private String email;
	private int age;
	private int groupid;
	private Group group;	
//	@ManyToOne
//	@JoinColumn(name="groupid",nullable=false,insertable=false,updatable=false)
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public User(String username, String password, String email, int age, int groupid) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.groupid = groupid;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
//	@Id
//	@Column(name="username",unique=true,nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
//	@Column(name="password",nullable=false,length=100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	@Column(name="email",nullable=false,length=100)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	@Column(name="age",nullable=false)
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
//	@Column(name="groupid",nullable=false,length=100)
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
}
