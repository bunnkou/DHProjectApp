package com.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timecard {
	private int id;
	private Date date1;
	private Date date2;
	private String title;
	private String persons;
	private String lastUpdatePerson;
	private Timestamp lastUpdateDate;
	private String dateRange;
	
//	id
	public int getId(){return this.id;}
	public void setId(int id){this.id = id;}
	
//	date1
	public String getDate1(){
		return (new SimpleDateFormat("yyyy-MM-dd")).format(this.date1);
	}
	public void setDate1(Date date1){this.date1 = date1;}
	
//	date2
	public String getDate2(){
		return (new SimpleDateFormat("yyyy-MM-dd")).format(this.date2);
	}
	public void setDate2(Date date2){this.date2 = date2;}
	
//	title
	public String getTitle(){return this.title;}
	public void setTitle(String title){this.title = title;}
	
//	persons
	public String getPersons(){return this.persons;}
	public void setPersons(String persons){this.persons = persons;}
	
//	lastUpdatePerson
	public String getLastUpdatePerson(){return this.lastUpdatePerson;}
	public void setLastUpdatePerson(String lastUpdatePerson){this.lastUpdatePerson = lastUpdatePerson;}
	
//	lastUpdateDate
	public Timestamp getLastUpdateDate(){return this.lastUpdateDate;}
	public void setLastUpdateDate(Timestamp lastUpdateDate){this.lastUpdateDate = lastUpdateDate;}
	 
//	dateRange
	public String getDateRange(){
		String s_date1 = (new SimpleDateFormat("yyyy-MM-dd")).format(this.date1);
		String s_date2 = (new SimpleDateFormat("yyyy-MM-dd")).format(this.date2);
		return s_date1 + " ~ " + s_date2;	
	}
	public void setDateRange(String dateRange){this.dateRange = dateRange;}
	
}
