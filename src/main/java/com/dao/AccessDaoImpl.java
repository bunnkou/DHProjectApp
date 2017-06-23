package com.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.Mapper.AccessMapper;
import com.entity.Access;

@Repository
public class AccessDaoImpl implements AccessDao {
	
	@Autowired
	private AccessMapper accessMapper;
	
	@Override
	public List<Access> getLst() {
		return accessMapper.getAccessBySpec();
	}
	
	@Override
	public void saveAccess(Access access) {
		accessMapper.saveAccess(access);
	}

	@Override
	public Access getAccessById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
