package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AccessDao;
import com.dao.RoleDao;
import com.dao.UserDao;
import com.entity.Access;
import com.entity.Role;
import com.entity.User;

@Controller
public class AccessController {
	
	@Autowired
	private AccessDao accessDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;
		
	@RequestMapping("/access/index")
	public String index(HttpSession httpSession){
		httpSession.setAttribute("vwPage", "vwAccess");
    	return "vwAccess";
    }
	
	@RequestMapping(value="/access/{id}", method=RequestMethod.GET)
	public ModelAndView show( @PathVariable ( "id" ) Integer id, HttpServletRequest request){
		Access access = null;
		ModelAndView mav = new ModelAndView();
		if (id.equals(0)) {		//新建		
			access = new Access();
		}else{		//执行打开操作
			access = accessDao.getAccessById(id);
		}
		mav.addObject("access", access );
		mav.addObject("roleLst", roleDao.all() );
		mav.setViewName("Access");
		return mav;
	}
	
	@RequestMapping(value="/access/store", method=RequestMethod.POST)
	public String store(HttpServletRequest request)
	{
		String id = request.getParameter("id"), 
		userName = request.getParameter("inputUserName"),
		roleIds[] = request.getParameterValues("inputRoleMember");
		
		if ( id.equals("0") ){	
			User user = userDao.getByUserName(userName);	//在 ml_pwd_interface 查询是否存在用户
			if ( null == user ){
				user = newUser(userName);
				if ( null == user ){	//未找到用户
					
				}
			}
			for ( String role_id : roleIds ){
				Access access = new Access();
				access.setRole_id( Integer.valueOf(role_id) );
				access.setUser_id( user.getUser_id() );
//				插入 dh_fdbk_user_role
				accessDao.saveAccess(access);
			}
		}
		
//		}ELSE{	更新
		
//		1.删除 dh_fdbk_user_role 所有该用户的权限
//		2.更新 dh_fdbk_user_role; role_id
		
//		}
		
		return "vwAccess";
	}
	
	public User newUser(String userName)
	{
//		在 ml_user 中查询用户的 user_id, name_py
		Map<String, String> map = new HashMap<String, String>();
		map = userDao.findCPAByName(userName);
		if ( null == map ) return null;
//		插入 ml_pwd_interface
		User user = new User();
		user.setUser_id(map.get("code"));
		user.setName(userName);
		user.setName_py(map.get("phonetic"));
		user.setPassword("123456");
		userDao.saveUser(user);
		return user;
	}
	
	@RequestMapping("/access/getLst.do")
	@ResponseBody
	public List<Access> getLst() {
		return accessDao.getLst();
	}
	
}
