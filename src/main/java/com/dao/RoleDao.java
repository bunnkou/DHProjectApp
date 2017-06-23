package com.dao;

import java.util.List;

import com.entity.Role;

public interface RoleDao {
	
	public Role findOne(Long roleId);
	
	public List<Role> all();
	
}
