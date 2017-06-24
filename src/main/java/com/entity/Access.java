package com.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Access {
	private int id;
	private int role_id;
	private String user_id;
	private String userName;
	private String roleName;
	private List<Role> roleLst;
		
	public Access()
	{
		this.id = 0;
		this.role_id = 0;
		this.user_id = "";
		this.userName = "";
	}
		
//	id
	public Integer getId(){ return id; }
	public void setId(Integer id){ this.id = id; }
	
//	user_id
	public String getUser_id(){ return user_id; }
	public void setUser_id( String user_id ){ this.user_id = user_id; }
	
//	role_id
	public Integer getRole_id(){ return role_id; }
	public void setRole_id( Integer role_id ){ this.role_id = role_id; }
	
//	username
	public String getUserName(){ return userName; }
	public void setUserName( String userName ){ this.userName = userName; }
	
//	role_name
	public String getRoleName(){ return roleName; }
	public void setRoleName( String roleName ){ this.roleName = roleName; }
	
//	roleLst
	public void setRoleLst( List<Role> roleLst ){
		List role_name_lst = new ArrayList<String>();
		for(Role role : roleLst){
			role_name_lst.add( role.getRoleTitle() );
		}
		this.roleName = StringUtils.join( role_name_lst , ";" );
	}
	
//	Get user	
	public void setUser( User user )
	{ 
		this.userName = user.getName();
	}
	
}
