package com.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.User;

public interface UserMapper {
	
	User selectUserById(String user_id);
	
	Map<String, String> getCPAByName(String userName);
	
	Map<String, String> getUserByName(String userName);
	
	Map<String, String> getUserById(String userId);
	
	Integer findCountByCode(
			@Param("tableName") String tableName,
			@Param("code") String code
	);
	
	void saveUser(User user);
	
	void saveAsFdbkUser(User user);
	
	List<Map<String, Object>> allUser();
	
	List<String> getEmployeeOfDH();
}
