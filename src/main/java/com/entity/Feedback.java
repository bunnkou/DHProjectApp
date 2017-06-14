package com.entity;

public class Feedback {
	private int id;
	private String PjName;
	private String Batch;
	private String RoleName;
	private String ModifyPerson;
	private String Feedback;
	private String Creator;
	private String CreateDate;
	private String LastUpdator;
	private String LastUpdate;
	private String RecordReader;
	private String mpMember;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPjName() {
		return PjName;
	}
	
	public void setPjName(String PjName) {
		this.PjName = PjName;
	}
	
	public String getBatch() {
		return Batch;
	}
	
	public void setBatch(String Batch) {
		this.Batch = Batch;
	}
	
	public String getRoleName() {
		return RoleName;
	}
	
	public void setRoleName(String RoleName) {
		this.RoleName = RoleName;
	}
	
	public String getModifyPerson() {
		return ModifyPerson;
	}
	
	public void setModifyPerson(String ModifyPerson) {
		this.ModifyPerson = ModifyPerson;
	}
	
	public String getFeedback() {
		return Feedback;
	}
	
	public void setFeedback(String Feedback) {
		this.Feedback = Feedback;
	}
	
	public String getCreator() {
		return Creator;
	}
	
	public void setCreator(String Creator) {
		this.Creator = Creator;
	}
	
	public String getCreateDate() {
		return CreateDate;
	}
	
	public void setCreateDate(String CreateDate) {
		this.CreateDate = CreateDate;
	}
	
	public String getLastUpdator() {
		return LastUpdator;
	}
	
	public void setLastUpdator(String LastUpdator) {
		this.LastUpdator = LastUpdator;
	}
	
	public String getLastUpdate() {
		return LastUpdate;
	}
	
	public void setLastUpdate(String LastUpdate) {
		this.LastUpdate = LastUpdate;
	}
	
	public String getRecordReader() {
		return RecordReader;
	}
	public void setRecordReader(String RecordReader) {
		this.RecordReader = RecordReader;
	}
	//mpMember
	public String getMpMember() {
		return mpMember;
	}
	public void setMpMember(String mpMember){
		this.mpMember = mpMember;
	}
}
