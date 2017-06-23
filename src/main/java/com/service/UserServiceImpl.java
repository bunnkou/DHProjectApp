package com.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;

@Service
public class UserServiceImpl implements UserService{  
	
	@Autowired
    private UserDao userDao;
	@Autowired
	private RoleService roleService;
	
	@Override
    public User getByUserName(String userName) {
		return userDao.getByUserName(userName);
    }
	
	@Override
	public Set<String> findRoles(String username) {
		User user = getByUserName(username);
        if(user == null) return Collections.EMPTY_SET;
        return roleService.findRoles(getRolesByUserID(user.getUser_id()));
	}
	
	@Override
	public Set<String> findPermissions(String username) {
		User user = getByUserName(username);
        if(user == null) return Collections.EMPTY_SET;
		return userDao.findPermissionsByUserID(user.getUser_id());
	}
	
	@Override
	public Set<Long> getRolesByUserID(String userID) {
		return userDao.getRolesByUserID(userID);
	}
	
	@Override
	public List<Map<String, Object>> getAllNames() {
		return userDao.getAllNames();
	}

	@Override
	public void checkUser(String userName) {
		//1.获得 User 对象
		Map<String, String> map = new HashMap<String, String>();
		map = userDao.findUserByName(userName);
		if ( null == map ) return;
		User user = new User();
		user.setUser_id( map.get("Code") );
		user.setName( map.get("Name_CN") );
		user.setName_py( map.get("Name_EN") );
		
		//2.在 ml_pwd_interface 查询是否存在
		if ( userDao.findCountByCode( "ml_pwd_interface", user.getUser_id() ).equals(0) ){	//不存在
			userDao.saveUser(user);
		}
		
		//3.在 dh_fdbk_user 查询是否存在
		if ( userDao.findCountByCode( "dh_fdbk_user", user.getUser_id() ).equals(0) ){	//不存在
			userDao.saveAsFdbkUser(user);
		}
		
	}
          
}
