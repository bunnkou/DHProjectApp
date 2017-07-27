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

import com.Mapper.FeedbackMapper;
import com.entity.Feedback;

@Repository
public class FeedbackDaoImpl implements FeedbackDao {
	
	private static final String Table_MySql = "dh_feedback";
	private static final String f_condition = " a WHERE NOT EXISTS( SELECT * FROM "+Table_MySql+" WHERE a.PjName = PjName AND a.RoleName = RoleName AND a.LastUpdate < LastUpdate )";
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private FeedbackMapper feedbackMapper;
	
	@Override
	public List<Feedback> getFbList(Map<String, Object> map){
		String sql = null, condition = null, limit = null;
		sql = "SELECT * FROM "+Table_MySql;
		if ( null != map ){
			if ( map.containsKey("id") ) condition = " WHERE id = "+map.get("id");
			if ( map.containsKey("pjname") ) condition = " WHERE PjName = '"+map.get("pjname")+"' AND rolename = '"+map.get("rolename")+"' AND id != "+map.get("id");
			if ( map.containsKey("reader") ) {
				condition = f_condition;
				condition += " AND FIND_IN_SET('"+map.get("reader")+"',mpMember)>0";
				condition += " AND FIND_IN_SET('"+map.get("reader")+"',RecordReader)=0";
			}
			if ( map.containsKey("page") ) {
				condition = f_condition;
				String sQuery = (String) map.get("sQuery");
				if (sQuery != null && sQuery.length() > 0) condition += " AND PjName LIKE '%"+sQuery+"%' OR RoleName LIKE '%"+sQuery+"%'";
				limit = " LIMIT "+map.get("page")+","+map.get("rows");
			}
		} else {
			condition = f_condition;
		}
		sql += condition;
		sql += " ORDER BY LastUpdate DESC";
		if ( null != limit ) sql += limit;
//		System.out.println(sql);
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Feedback>(Feedback.class));
	}
	
	@Override
	public Integer saveFdbk(final Feedback fdbk) {
		String sql = null;
		if ( 0 == fdbk.getId() ) {	//Insert
			sql = "INSERT INTO "+Table_MySql+" (PjName,Batch,RoleName,ModifyPerson,Feedback,mpMember,Creator,CreateDate,LastUpdator,LastUpdate,RecordReader) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		} else {	//Update
			sql = "UPDATE "+Table_MySql+" SET PjName=?,Batch=?,RoleName=?,ModifyPerson=?,Feedback=?,mpMember=?,LastUpdator=?,LastUpdate=? WHERE id=?";
		}
		final String SQL_INSERT_DATA = sql;
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
			new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(SQL_INSERT_DATA, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, fdbk.getPjName());
					ps.setString(2, fdbk.getBatch());
					ps.setString(3, fdbk.getRoleName());
					ps.setString(4, fdbk.getModifyPerson());
					ps.setString(5, fdbk.getFeedback());
					ps.setString(6, fdbk.getMpMember());
					ps.setString(7, fdbk.getCreator());
					ps.setString(8, fdbk.getCreateDate());
					if ( 0 != fdbk.getId() ) {
						ps.setInt(9, fdbk.getId());
					} else {
						ps.setString(9, fdbk.getCreator());
						ps.setString(10, fdbk.getCreateDate());
						ps.setString(11, ",");
					}
					return ps;
				}
			}, keyHolder
		);
		if ( 0 != fdbk.getId() ) return fdbk.getId();
		else return keyHolder.getKey().intValue();
	}
	
	@Override
	public String updateRecRdr(Object[] arg) {
		String sql = "UPDATE "+Table_MySql+" SET RecordReader = ? WHERE id = ?";
		jdbcTemplate.update(sql,arg);
		return null;
	}
	
	@Override
	public Integer getTodoCount(String userName) {
		String sql = "SELECT COUNT(*) as c FROM "+Table_MySql;
		sql += f_condition;
		sql += " AND FIND_IN_SET('"+userName+"',mpMember)>0";
		sql += " AND FIND_IN_SET('"+userName+"',RecordReader)=0";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		Long cl = (Long) map.get("c");
		Integer c = new Integer(cl.intValue());
		return c ;
	}
	
	@Override
	public Long getFbListCount(String sQuery) {
		String sql = "SELECT COUNT(*) as c FROM "+Table_MySql;
		sql += f_condition;
		if (sQuery != null && sQuery.length() > 0) sql += " AND PjName LIKE '%"+sQuery+"%' OR RoleName LIKE '%"+sQuery+"%'";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		return (Long) map.get("c");
	}
	
	@Override
	public String delByID(Map<String, Object> map) {
		String sql = null, condition = null;
		sql = "DELETE FROM "+Table_MySql;
		if ( null != map ){
			if ( map.containsKey("id") ) condition = " WHERE id = "+map.get("id");
			if ( map.containsKey("pjName") ) condition = " WHERE PjName = '"+map.get("pjName")+"' AND RoleName = '"+map.get("roleName")+"'";
		}
		sql += condition;
		jdbcTemplate.execute(sql);
		return "SUCCESS";
	}

	@Override
	public List<Map<String, String>> getFbGroupByPjname() {
		return feedbackMapper.getFbGroupByPjname();
	}
	
}
