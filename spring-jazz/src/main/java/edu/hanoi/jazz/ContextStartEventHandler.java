package edu.hanoi.jazz;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.jdbc.core.JdbcTemplate;

public class ContextStartEventHandler implements ApplicationListener<ContextStartedEvent>{

	private final static Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class);
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void onApplicationEvent(ContextStartedEvent arg0) {

		try {
			createTable("HN_GROUP", "create table HN_GROUP (id bigint primary key, name varchar(100))");
			createTable("HN_USER", "create table HN_USER(username VARCHAR(100) primary key, "
					+ " password varchar(100),"
					+ " email varchar(100),"
					+ " age Integer, "
					+ " groupid bigint,"
					+ " CONSTRAINT GROUP_FX FOREIGN KEY(groupid) REFERENCES HN_GROUP(id))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		LOGGER.info("context start application " + dataSource);
	}
	
	private void createTable(String name,String script)throws SQLException{
		DatabaseMetaData dbmd = null;
		ResultSet rs = null;
		// lấy meta data mà datasource này chứa
		try {
			dbmd = dataSource.getConnection().getMetaData();
			rs = dbmd.getTables(null,null,name,null);
			if(rs.next()){
				System.out.println("table" + rs.getString("TABLE_NAME") + "exist ");
				return;
			}
			dataSource.getConnection().createStatement().executeUpdate(script);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
