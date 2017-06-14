package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.GroupDao;
import com.entity.Group;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private GroupDao groupDao;
	
	@Override
	public Integer saveGroup(Group group) {
		return groupDao.saveGroup(group);
	}
	
	@Override
	public List<Group> getFbList(Map<String, Object> map) {
		return groupDao.getFbList(map);
	}

	@Override
	public String delGroups(String ids) {
		return groupDao.delGroups(ids);
	}

	@Override
	public List<Map<String, Object>> getAllNames() {
		return groupDao.getAllNames();
	}

}
