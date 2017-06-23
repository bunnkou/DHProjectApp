package com.entity;

public class User {
	private String user_id;
	private String name;
	private String name_py;
	private String password;
	
	public User(){
		this.user_id = "";
		this.name = "";
		this.name_py = "";
		this.password = "123456";
	}
	
	public String getUser_id(){
		return user_id;
	}
	public void setUser_id(String user_id){
		this.user_id = user_id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName_py(){
		return name_py;
	}
	public void setName_py(String name_py){
		this.name_py = name_py;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
}
