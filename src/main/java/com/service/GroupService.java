package com.service;

import java.util.List;
import java.util.Map;

import com.entity.Group;

public interface GroupService {
	
	List<Group> getFbList(Map<String, Object> map);
	
	String delGroups(String ids);
	
	Integer saveGroup(Group group);
	
	List<Map<String, Object>> getAllNames();
}
