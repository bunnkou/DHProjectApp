package com.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;

@Service
public class UserServiceImpl implements UserService{  
	
	@Autowired
    private UserDao userDao;
	@Autowired
	private RoleService roleService;
	
	@Override
    public User getByUserName(String userName) {
		return userDao.getByUserName(userName);
    }
	
	@Override
	public Set<String> findRoles(String username) {
		User user = getByUserName(username);
        if(user == null) return Collections.EMPTY_SET;
        return roleService.findRoles(getRolesByUserID(user.getUser_id()));
	}
	
	@Override
	public Set<String> findPermissions(String username) {
		User user = getByUserName(username);
        if(user == null) return Collections.EMPTY_SET;
		return userDao.findPermissionsByUserID(user.getUser_id());
	}
	
	@Override
	public Set<Long> getRolesByUserID(String userID) {
		return userDao.getRolesByUserID(userID);
	}
	
	@Override
	public List<Map<String, Object>> getAllNames() {
		return userDao.getAllNames();
	}
          
}
