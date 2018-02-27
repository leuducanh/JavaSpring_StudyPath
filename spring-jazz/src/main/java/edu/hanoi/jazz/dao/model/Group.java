package edu.hanoi.jazz.dao.model;

import java.util.List;
import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SortComparator;
import org.hibernate.annotations.SortNatural;

@javax.persistence.Entity
@Table(name="HN_GROUP")
public class Group {
	private int id;
	private String name;
	private SortedSet<User> users;
	public Group(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Group() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
//	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = "group")
	
//public List<User> getUsers() {
//		return users;
//	}
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="groupid")
	@SortComparator(UsernameComparator.class)
	public SortedSet<User> getUsers() {
		return users;
	}
	public void setUsers(SortedSet<User> users) {
		this.users = users;
	}
	@Column(name="name",nullable=false,length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}