package com.entity;

import java.util.List;

public class Access {
	private int id;
	private int role_id;
	private String user_id;
	private String userName;
	private Object roleLst;
		
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
	
//	roleLst
	public Object getRoleLst(){ return roleLst; }
	public void setRoleLst( Object roleLst ){ this.roleLst = roleLst; }
	
//	Get user	
	public void setUser( User user )
	{ 
		this.userName = user.getName();
	}
	
}
