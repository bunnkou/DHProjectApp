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
	
	List<Map<String, Object>> allUser();
	
	Map<String, String> findCPAByName(String userName);
	
	Map<String, String> findUserByName(String userName);
	
	Map<String, String> findUserById(String userId);
	
	Integer findCountByCode(String tableName, String code);

	void  saveUser(User user);
	
	void  saveAsFdbkUser(User user);
	
}
