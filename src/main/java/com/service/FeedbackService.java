package com.service;

import java.util.List;
import java.util.Map;

import com.entity.Feedback;

public interface FeedbackService {
	/** 
     * »ñÈ¡feedback¼ÇÂ¼
     * @param int 
     * @return 
     */
    List<Feedback> getFbList(Map<String, Object> map);
    
    Long getFbListCount(String sQuery);
    
    Integer getTodoCount(String userName);
    
    Integer saveFdbk(Feedback fdbk);
    
    String updateRecRdr(Object[] arg);
    
    String delByID(Map<String, Object> map);
    
    List<Map<String, Object>> getModifyPerson();
}
