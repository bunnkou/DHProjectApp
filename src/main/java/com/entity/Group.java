package com.entity;

public class Group {
	private int id;
	private String groupName;
	private String groupMember;
	private String creator;
	private String createDate;
	private String lastUpdator;
	private String lastUpdate;
	
	//id
	public int getId() {
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	//groupName
	public String getGroupName(){
		return groupName;
	}
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	//groupMember
	public String getGroupMember(){
		return groupMember;
	}
	public void setGroupMember(String groupMember){
		this.groupMember = groupMember;
	}
	//creator
	public String getCreator(){
		return creator;
	}
	public void setCreator(String creator){
		this.creator = creator;
	}
	//createDate
	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}
	//lastUpdator
	public String getLastUpdator(){
		return lastUpdator;
	}
	public void setLastUpdator(String lastUpdator){
		this.lastUpdator = lastUpdator;
	}
	//lastUpdateDate
	public String getLastUpdate(){
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate){
		this.lastUpdate = lastUpdate;
	}
}
