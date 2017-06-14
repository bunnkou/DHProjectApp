package com.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Feedback;
import com.entity.Group;
import com.service.FeedbackService;
import com.service.GroupService;
import com.service.UserService;

@Controller
@RequiresRoles("admin")
@RequestMapping("/admin")
public class AdminController {
	
	static String curUser = null;
	
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping("vwGroups")
	public String vwGroups(HttpSession httpSession){
		curUser = (String) SecurityUtils.getSubject().getPrincipal();
		httpSession.setAttribute("vwPage", "vwGroups");
    	return "vwGroups";
    }
	
	@RequestMapping("vwMonitor")
	public String vwMonitor(HttpSession httpSession){
		return "vwMonitor";
	}
	
	@RequestMapping("showMonitor.do")
	@ResponseBody
	public List<Feedback> getMonitor() {
		return feedbackService.getFbList(null);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView openGroup( @PathVariable ( "id" ) int id, HttpServletRequest request){
		Group group = null;
		if ( 0 == id ) {	//新建
			group = new Group();
			group.setId(0);
		}else{				//更新
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			List<Group> gpList = groupService.getFbList(map);
			group = (Group) gpList.get(0);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("nameLst", userService.getAllNames());
		mav.addObject("group", group);
		mav.setViewName("Group");
		return mav;
	}
	
	@RequestMapping("saveGroup.do")
	public String saveFdbk( Group group, HttpServletRequest request ) throws IOException{
		Date nDate = new Date();
		Timestamp ts = new Timestamp( nDate.getTime() );
		group.setCreator(curUser);
		group.setCreateDate(ts.toString());
		//Integer groupID = groupService.saveGroup(group);
		//return "redirect:/admin/"+groupID.toString();
		groupService.saveGroup(group);
		return "vwGroups";
	}
	
	@RequestMapping("showGroupList.do")
	@ResponseBody
	public List<Group> getGroupList() {
		return groupService.getFbList(null);
	}
	
	@RequestMapping("delGroups.do")
	@ResponseBody
	public String delGroups(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		return groupService.delGroups(ids);
	}
	
	@RequestMapping("vwAccess")
	public String vwAccess(HttpSession httpSession){
		curUser = (String) SecurityUtils.getSubject().getPrincipal();
		httpSession.setAttribute("vwPage", "vwAccess");
    	return "vwAccess";
    }
	
	@RequestMapping("showAccessList.do")
	@ResponseBody
	public List<Group> getAccessList() {
		return groupService.getFbList(null);
	}
	
}
