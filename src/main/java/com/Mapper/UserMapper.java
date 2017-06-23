package com.Mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.User;

public interface UserMapper {
	
	User selectUserById(String user_id);
	
	Map<String, String> getCPAByName(String userName);
	
	void saveUser(User user);
	
	Map<String, String> getUserByName(String userName);
	
	Integer findCountByCode(
			@Param("tableName") String tableName,
			@Param("code") String code
	);
	
	void saveAsFdbkUser(User user);
}
