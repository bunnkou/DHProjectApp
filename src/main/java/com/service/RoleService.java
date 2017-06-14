package com.service;

import java.util.Set;

import com.entity.Role;


public interface RoleService {
	
	Set<String> findRoles(Set<Long> set);
	
	public Role findOne(Long roleId);
	
}
