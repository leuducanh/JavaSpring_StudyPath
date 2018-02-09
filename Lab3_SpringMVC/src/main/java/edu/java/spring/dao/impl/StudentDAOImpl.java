package edu.java.spring.dao.impl;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;

@Component
public class StudentDAOImpl implements StudentDAO,DisposableBean {

	private DataSource dataSource;	
	private JdbcTemplate jdbcTemplate;
	public DataSource getDataSource() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@PostConstruct
	private void createTableIfNotExist(){
		DatabaseMetaData dbmd = null;
		ResultSet rs = null;
		// lấy meta data mà datasource này chứa
		try {
			dbmd = dataSource.getConnection().getMetaData();
			rs = dbmd.getTables(null,null,"student",null);
			if(rs.next()){
				System.out.println("table" + rs.getString("TABLE_NAME") + "exist ");
				return;
			}
			String sql="create table student (id   bigint primary key ,  name varchar(1000), age  integer)";
			jdbcTemplate.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(Student student) {
		jdbcTemplate.update("insert into student (name,age) values(?,?)",student.getName(),student.getAge());
		System.out.println("Create rec " + student.getName());
	}

	public void destroy() throws Exception {
		DriverManager.getConnection("jdbc:mysql://localhost:3306/sampled;shutdown=true");
	}

	public List<Student> list(final String query) {
		return jdbcTemplate.execute(new StatementCallback<List<Student>>() {
			public List<Student> doInStatement(Statement stmt) throws SQLException, DataAccessException {
				ResultSet rs = null;
				rs = stmt.executeQuery("select * from student where name like '%"+query+"%'");
				List<Student> lst = new LinkedList<Student>();
				while(rs.next()){
					lst.add(new Student(rs.getInt(1),rs.getString(2),rs.getInt(3)));
				}
				
				return lst;
			}
		});
		
	}

	public void delete(int id) {
		jdbcTemplate.execute("delete from student where id=" + id);
	}	
	
	
	
	class StudentRowMapper implements RowMapper<Student>{

		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Student(rs.getInt(1),rs.getString(2),rs.getInt(3));
		}
	}



	public Student get(int id) {
		return (Student)jdbcTemplate.query("select * from student where id = " + id,new StudentRowMapper()).get(0);		
	}

	public void update(Student student) {
		jdbcTemplate.update("update student set name=? where id=?",student.getName(),student.getId());
	}
}
