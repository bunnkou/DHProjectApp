package com.Mapper;

import java.util.List;

import com.entity.Timecard;

public interface TimecardMapper {
	
	List<Timecard> getTcByDate(String date1, String date2);
	
	int insertTC(Timecard timecard);
	
	void updateTC(Timecard timecard);
	
	void deleteTC(int id);
	
}
