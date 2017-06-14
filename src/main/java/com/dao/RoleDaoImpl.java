package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public Role findOne(Long roleId) {
		String sql = "select * from dh_fdbk_role where id="+roleId.toString();
		List<Role> roleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
        if(roleList.size() == 0) {
            return null;
        }
        return roleList.get(0);
	}
	
}
