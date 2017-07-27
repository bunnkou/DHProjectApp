package com.dao;

import java.util.List;

import com.entity.Timecard;

public interface TimecardDao {
	
	List<Timecard> getTcByDate(String date1, String date2);
	
	int insertTC(Timecard timecard);
	
	void updateTC(Timecard tc);
	
	void deleteTC(int id);	
}
