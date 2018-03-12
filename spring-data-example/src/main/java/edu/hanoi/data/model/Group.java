package edu.hanoi.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@NamedQuery(name="Group.findByName",query="from Group g where g.name like CONCAT('%',?1,'%')")
@Table(name="HN_GROUP",uniqueConstraints={@UniqueConstraint(columnNames="id")})

public class Group implements Serializable{

	private int id;
	private String name;
//	private SortedSet<User> users;
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
//	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name="groupid")
//	@SortComparator(UsernameComparator.class)
//	public SortedSet<User> getUsers() {
//		return users;
//	}
//	public void setUsers(SortedSet<User> users) {
//		this.users = users;
//	}
	@Column(name="name",nullable=false,length=200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
