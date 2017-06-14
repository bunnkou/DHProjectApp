package com.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RoleDao;
import com.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
    private RoleDao roleDao;
	
	@Override
	public Set<String> findRoles(Set<Long> roleIds){
		Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRoleName());
            }
        }
        return roles;
	}
	
	@Override
    public Role findOne(Long roleId) {
        return roleDao.findOne(roleId);
    }
	
}
