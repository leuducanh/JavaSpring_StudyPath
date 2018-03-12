package edu.hanoi.service.springservice.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="HN_GROUP",uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class Group {

	private String name;
	private int id;
	public Group(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}
	public Group() {
		super();
	}
	
	@Column(name="name",nullable=false,length=200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
}
