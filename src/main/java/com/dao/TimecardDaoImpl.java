package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Mapper.TimecardMapper;
import com.entity.Timecard;

@Repository
public class TimecardDaoImpl implements TimecardDao {

	@Autowired
	private TimecardMapper tcMapper;
	
	@Override
	public int insertTC(Timecard timecard) {
		return tcMapper.insertTC(timecard);
	}

	@Override
	public List<Timecard> getTcByDate(String date1, String date2) {
		return tcMapper.getTcByDate(date1, date2);
	}

	@Override
	public void updateTC(Timecard tc) {
		tcMapper.updateTC(tc);
	}

	@Override
	public void deleteTC(int id) {
		tcMapper.deleteTC(id);		
	}
}
