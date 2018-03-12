package edu.hanoi.message.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import edu.hanoi.message.model.Group;

@Component
public class GroupRowMapper implements RowMapper<Group>{

	@Override
	public Group mapRow(ResultSet rs, int rowNum) throws SQLException {

		Group group = new Group();
		group.setId(rs.getInt(1));
		group.setName(rs.getString(2));
		return group;
	}

	
}
