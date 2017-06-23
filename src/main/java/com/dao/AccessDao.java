package com.dao;

import java.util.List;
import com.entity.Access;

public interface AccessDao {
	
	List<Access> getLst();
	
	Access getAccessById(Integer id);
	
	void saveAccess(Access access);
}
