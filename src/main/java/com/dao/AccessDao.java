package com.dao;

import java.util.List;
import com.entity.Access;

public interface AccessDao {
	
	List<Access> getLst();
	
	Access getAccessById(String id);
	
	void saveAccess(Access access);
	
	Integer delAccessByUserId(String user_id);
}
