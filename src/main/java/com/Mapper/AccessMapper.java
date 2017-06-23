package com.Mapper;

import java.util.List;
import com.entity.Access;

public interface AccessMapper {
	
	List<Access> getAccessBySpec();
	
	void saveAccess(Access access);
	
	Access getAccessById(Integer id);
}
