package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.entity.Group;

@Repository
public class GroupDaoImpl implements GroupDao {
	
	private static final String Table_MySql = "dh_fdbk_group";
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public Integer saveGroup(final Group group) {
		String sql = null;
		
		if ( 0 == group.getId() ) {	//Insert
			sql = "INSERT INTO "+Table_MySql+" (groupName,groupMember,creator,createDate,lastUpdator,lastUpdate) VALUES (?,?,?,?,?,?)";
		} else {	//Update
			sql = "UPDATE "+Table_MySql+" SET groupName=?,groupMember=?,lastUpdator=?,lastUpdate=? WHERE id=?";
		}
		final String SQL_INSERT_DATA = sql;
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
			new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(SQL_INSERT_DATA, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, group.getGroupName());
					ps.setString(2, group.getGroupMember());
					if ( 0 != group.getId() ) {
						ps.setString(3, group.getCreator());
						ps.setString(4, group.getCreateDate());
						ps.setInt(5, group.getId());
					} else {
						ps.setString(3, group.getCreator());
						ps.setString(4, group.getCreateDate());
						ps.setString(5, group.getCreator());
						ps.setString(6, group.getCreateDate());
					}
					return ps;
				}
			}, keyHolder
		);
		if ( 0 != group.getId() ) return group.getId();
		else return keyHolder.getKey().intValue();
	}
	
	@Override
	public List<Group> getFbList(Map<String, Object> map) {
		String sql = null, condition = null;
		sql = "SELECT * FROM "+Table_MySql;
		if ( null != map ){
			if ( map.containsKey("id") ) condition = " WHERE id = "+map.get("id");
			if ( map.containsKey("gpName") ) condition = " WHERE groupName = '"+map.get("gpName")+"'";
		}
		if ( null != condition) sql += condition;
		sql += " ORDER BY lastUpdate DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Group>(Group.class));
	}
	
	@Override
	public String delGroups(String ids) {
		String sql = "DELETE FROM "+Table_MySql+" WHERE FIND_IN_SET(id,'"+ids+"')>0";
		jdbcTemplate.execute(sql);
		return "SUCCESS";
	}
	
	@Override
	public List<Map<String, Object>> getAllNames() {
		String sql = "SELECT CONCAT(groupName,'[G]') as name FROM dh_fdbk_group";
		return jdbcTemplate.queryForList(sql);
	}
	
}
