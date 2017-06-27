package com.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Mapper.UserMapper;
import com.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserMapper userMapper;
		
	@Override
	public User getByUserName(String userName){
		String sql = "select * from ml_pwd_interface where name = ? or name_py = ? or user_id = ?";
        List<User> userList = jdbcTemplate.query(sql, 
        		new Object[]{userName,userName,userName}, 
        		new BeanPropertyRowMapper<User>(User.class));
        if(userList.size() == 0) return null;
        return userList.get(0);
	}
	
	@Override
	public Set<Long> getRolesByUserID(String userID) {
		Set<Long> RoleLst = new HashSet<Long>();
		String sql = "SELECT role_id FROM dh_fdbk_user_role WHERE user_id = ?";
		List rows = jdbcTemplate.queryForList(sql, userID);
		for(int i=0; i<rows.size(); i++){
			Map userMap = (Map) rows.get(i);
			RoleLst.add( Long.valueOf((Integer) userMap.get("role_id")) );
		}
		return RoleLst;
	}
	
	@Override
	public Set<String> findPermissionsByUserID(String userID) {
		Set<String> pmsnLst = new HashSet<String>();
		String sql = "SELECT a.permissionName FROM dh_fdbk_permission a LEFT JOIN dh_fdbk_user_role b ON a.roleId = b.role_id WHERE user_id = ?";
		List rows = jdbcTemplate.queryForList(sql, userID);
		for ( Object row : rows ) {
			Map map = (Map) row;
			pmsnLst.add( (String) map.get("permissionName") );
		}
		return pmsnLst;
	}
	
	@Override
	public List<Map<String, Object>> getAllNames() {
		String sql = "SELECT Name_CN as name FROM ml_user WHERE `Status` = '1' AND BudgetDeptCode = '1001-06001'";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public Map<String, String> findCPAByName(String userName) {
		return userMapper.getCPAByName(userName);
	}

	@Override
	public void saveUser(User user) {
		userMapper.saveUser(user);		
	}

	@Override
	public Map<String, String> findUserByName(String userName) {
		return userMapper.getUserByName(userName);
	}

	@Override
	public Integer findCountByCode(String tableName, String code) {
		return userMapper.findCountByCode(tableName, code);
	}

	@Override
	public void saveAsFdbkUser(User user) {
		userMapper.saveAsFdbkUser(user);
	}

	@Override
	public List<Map<String, Object>> allUser() {
		return userMapper.allUser();
	}

	@Override
	public Map<String, String> findUserById(String userId) {
		return userMapper.getUserById(userId);
	}
	
}
