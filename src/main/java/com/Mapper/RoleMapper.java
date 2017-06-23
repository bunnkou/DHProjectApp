package com.Mapper;

import java.util.List;

import com.entity.Role;

public interface RoleMapper {
	
	Role selectRoleById(Integer role_id);
	
	List<Role> selectRolesById(String user_id);
	
	List<Role> all();
	
}
