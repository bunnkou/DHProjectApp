package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Feedback;

public interface FeedbackDao {
	List<Feedback> getFbList(Map<String, Object> map);
	
	Long getFbListCount(String sQuery);

	Integer getTodoCount(String userName);
	
	Integer saveFdbk(Feedback fdbk);
	
	String updateRecRdr(Object[] arg);
	
	String delByID(Map<String, Object> map);
	
	List<Map<String, String>> getFbGroupByPjname();
}
