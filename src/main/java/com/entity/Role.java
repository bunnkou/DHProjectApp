package com.entity;

import java.util.List;

public class Role {
	private Integer id;
	private String roleName;
	private String roleTile;
	private String createDate;
	
//	id
	public Integer getId(){	return id; }
	public void setId(Integer id){ this.id = id; }
	
//	roleName
	public String getRoleName(){ return roleName; }
	public void setRoleName(String roleName){ this.roleName = roleName; }
	
//	roleTitle
	public String getRoleTitle(){ return roleTile; }
	public void setRoleTitle(String roleTitle){ this.roleTile = roleTitle; }
	
//	createDate
	public String getCreateDate(){ return createDate; }
	public void setCreateDate(String createDate){ this.createDate = createDate; }
	
//	role
	public void setRole(Role role){ 
		this.id = role.getId(); 
		this.roleName = role.getRoleName();
		this.roleTile = role.getRoleTitle();
		this.createDate = role.getCreateDate();
	}
	
}
