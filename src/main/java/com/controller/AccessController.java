package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AccessDao;
import com.dao.RoleDao;
import com.dao.UserDao;
import com.entity.Access;
import com.entity.User;
import com.validator.AccessValidator;

@Controller
public class AccessController {
	
	@Autowired
	private AccessDao accessDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	@Qualifier("accessValidator")
	private AccessValidator accessValidator;
		
	@RequestMapping("/access/index")
	public String index(HttpSession httpSession){
		httpSession.setAttribute("vwPage", "vwAccess");
    	return "vwAccess";
    }
	
	@RequestMapping(value="/access/{id}", method=RequestMethod.GET)
	public ModelAndView show( @PathVariable ( "id" ) String id, HttpServletRequest request){
		Access access = null;
		ModelAndView mav = new ModelAndView();
		if (id.equals("0")) {		//�½�		
			access = new Access();
		}else{		//ִ�д򿪲���
			access = accessDao.getAccessById(id);
		}
		mav.addObject("access", access );
		mav.addObject("roleLst", roleDao.all() );
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			mav.addObject("userLst", objectMapper.writeValueAsString( userDao.allUser() ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		mav.setViewName("Access");
		return mav;
	}
	
	@RequestMapping(value="/access/store", method=RequestMethod.POST)
	public ModelAndView store(
			Access access,
			Errors errors,
			HttpServletRequest request)
	{
		String user_id = access.getUser_id(),
			   roleIds[] = request.getParameterValues("inputRoleMember");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Access");
		mav.addObject("access", access );
		mav.addObject("roleLst", roleDao.all() );
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			mav.addObject("userLst", objectMapper.writeValueAsString( userDao.allUser() ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		accessValidator.validate(access, errors);
		if( errors.hasErrors() ){
			List<String> err = new ArrayList<String>();
			List<FieldError> list = errors.getFieldErrors();
			FieldError error = null;
			for( int i=0; i<list.size(); i++){
				error = list.get(i);
				err.add(error.getCode());
			}
			mav.addObject("errfields", err);
			return mav;
		}
		
		User user = userDao.getByUserName(user_id);	//�� ml_pwd_interface �в�ѯ�Ƿ���ڸ��û�
		if ( null == user ){
			user = newUser(user_id);
			if ( null == user ){	//δ�ҵ��û�
				mav.addObject("errfields", "û������Ա��֯�����ҵ���Ӧ�û�����ȷ���û������Ƿ�������ȷ");
				return mav;
			}
		}
		
		if ( userDao.findCountByCode("dh_fdbk_user", user.getUser_id()).equals(0) ){	//�� dh_fdbk_user �в�ѯ�Ƿ���ڸ��û� 
			userDao.saveAsFdbkUser(user);
		}
		
		accessDao.delAccessByUserId(access.getUser_id());	//ɾ�� dh_fdbk_user_role ���и��û���Ȩ��
		
		//���� dh_fdbk_user_role; role_id
		for ( String role_id : roleIds ){
			access.setRole_id( Integer.valueOf(role_id) );
			accessDao.saveAccess(access);	//���� dh_fdbk_user_role
		}
		
		//�������
		access.setRoleId( StringUtils.join( roleIds, ";") );
		
		return mav;
	}
	
	public User newUser(String user_id)
	{
//		�� ml_user �в�ѯ�û��� user_id, name_py
		Map<String, String> map = new HashMap<String, String>();
//		map = userDao.findCPAByName(user_id);	���ã���Ϊʹ��ID����
		map = userDao.findUserById(user_id);
		
		if ( null == map ) return null;
//		���� ml_pwd_interface
		User user = new User();
		user.setUser_id( user_id );
		user.setName( map.get("name") );
		user.setName_py( map.get("phonetic") );
		user.setPassword( "123456" );	//Ĭ������
		userDao.saveUser( user );
		return user;
	}
	
	@RequestMapping("/access/getLst.do")
	@ResponseBody
	public List<Access> getLst() {
		return accessDao.getLst();
	}
	
}
