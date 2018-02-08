package thaythuan.springjdbc;

import java.sql.DatabaseMetaData;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class StudentJdbcDAO {
	
	@Autowired
	@Qualifier("transactionManager")
	private DataSourceTransactionManager transactionManager;	

	private static Logger LOGGER;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	private String insertQuery;
	private String updateAgeByNameSQL;
	
	
	
	public void save(Object name,Object age){
		TransactionDefinition def = new DefaultTransactionDefinition();
	
		TransactionStatus status = transactionManager.getTransaction(def);
		String sql = "";
		int total ;
		String countQuery = "select count(*) from student";
		try{
			total = jdbcTemplate.queryForObject(countQuery, Integer.class);
			System.out.println("before save data, total record is " + total);
			
			sql = "insert into student(name,age) values(?,?)";
			jdbcTemplate.update(sql,name,age);

			total = jdbcTemplate.queryForObject(countQuery, Integer.class);
			System.out.println("after save data, total rec is "+ total);
			throw new Exception();
		
			
		}catch(Exception e){
			
			transactionManager.rollback(status);
			total = jdbcTemplate.queryForObject(countQuery, Integer.class);
			System.out.println("after rollback : " + total);
		}
		
		
	}
	
	public void updateAgeByName(String name,int age){
		jdbcTemplate.execute(updateAgeByNameSQL, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, age);
				ps.setString(2, name);
				
				return ps.executeUpdate();
			}
		});
	}
	
	public void insert(String name,int age){
		jdbcTemplate.update(insertQuery,name,age);
		System.out.println("create  rec " + name + " " + age);
	}
	
	
	
	public String getInsertQuery() {
		return insertQuery;
	}



	public void setInsertQuery(String insertQuery) {
		this.insertQuery = insertQuery;
	}



	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
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
	
	public int totalRecord(){
		return jdbcTemplate.execute(new StatementCallback<Integer>() {
			@Override
			public Integer doInStatement(Statement stmt) throws SQLException, DataAccessException {
				ResultSet rs = stmt.executeQuery("select count(*) from student");
				return rs.next()?rs.getInt(1):0;
			}
		});
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	
	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public String getUpdateAgeByNameSQL() {
		return updateAgeByNameSQL;
	}

	public void setUpdateAgeByNameSQL(String updateAgeByNameSQL) {
		this.updateAgeByNameSQL = updateAgeByNameSQL;
	}

	public List loadStudent(String name){
		return jdbcTemplate.query("select * from student where name like '%" + name + "%'", new StudentRowMapper());
	}
	
	public int[] add(List<Student> students){
		List<Object[]> batch = new ArrayList<>();
		students.forEach(s->batch.add(new Object[]{s.getName(),s.getAge()}));
		return jdbcTemplate.batchUpdate(insertQuery,batch);
	}
	
	 class StudentRowMapper implements RowMapper<Student>{

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Student(rs.getInt(1),rs.getString(2),rs.getInt(3));
		}
		
	}
}
