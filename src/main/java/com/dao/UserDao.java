package com.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.entity.User;

public interface UserDao {
	
	User getByUserName(String userName);
	
	Set<Long> getRolesByUserID(String userID);
	
	Set<String> findPermissionsByUserID(String userID);
	
	List<Map<String, Object>> getAllNames();
	
	Map<String, String> findCPAByName(String userName);
	
	void saveUser(User user);
	
	Map<String, String> findUserByName(String userName);
	
	Integer findCountByCode(String tableName, String code);
	
	void saveAsFdbkUser(User user);
}
