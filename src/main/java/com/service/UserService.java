package com.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.entity.User;

public interface UserService { 
	
	public User getByUserName(String userName);
	
	public Set<Long> getRolesByUserID(String userID);
    
    public Set<String> findRoles(String username);
    
    public Set<String> findPermissions(String username);
    
    public List<Map<String, Object>> getAllNames();
}
