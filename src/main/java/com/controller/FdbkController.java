package com.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Feedback;
import com.service.FeedbackService;

@Controller
@RequestMapping("/fdbk")
public class FdbkController {
	
	static String curUser = null;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping("/vwList")
    public String vwList(HttpSession httpSession){
		curUser = (String) SecurityUtils.getSubject().getPrincipal();
		httpSession.setAttribute("vwPage", "vwList");
    	return "vwList";
    }
	
	@RequestMapping("showFbList.do")
	@ResponseBody
	public Map<String, Object> getFbList(HttpServletRequest request) {
		String sQuery = request.getParameter("sQuery");
		Integer page = new Integer( request.getParameter("page") );
		Integer rows = new Integer( request.getParameter("rows") );
//		if ( page.equals(1) ) page = 0;
//		else page = rows * (page-1);
		page = rows * (page-1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page.toString());
		map.put("rows", rows.toString());
		map.put("sQuery", sQuery);
		Map<String, Object> map_FbLst = new HashMap<String, Object>();
		map_FbLst.put("total", feedbackService.getFbListCount(sQuery));
		map_FbLst.put("rows", feedbackService.getFbList(map));
		return map_FbLst;
	}
	
	@RequestMapping("/todoList")
    public String todoList(HttpSession httpSession){
		curUser = (String) SecurityUtils.getSubject().getPrincipal();
		httpSession.setAttribute("vwPage", "todoList");
    	return "todoList";
    }
	
	@RequestMapping("showTodoList.do")
	@ResponseBody
	public List<Feedback> getTodoList() {
		Map<String, Object> map_td = new HashMap<String, Object>();
		map_td.put("reader", curUser);
		return feedbackService.getFbList(map_td);
	}
	
	//新建或打开feedback
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView showFb( @PathVariable ( "id" ) int id, HttpServletRequest request){
		Feedback fdbk = null;
		List<Feedback> fbHist = null;
		JSONArray mp_JSON = null;
		if ( 0 == id ) {	//新建
			fdbk = new Feedback();
			fdbk.setId(id);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			List<Feedback> fbList = feedbackService.getFbList(map);
			fdbk = (Feedback) fbList.get(0);
			map.put("id", fdbk.getId());
			map.put("pjname", fdbk.getPjName());
			map.put("rolename", fdbk.getRoleName());
			fbHist = feedbackService.getFbList(map);
			//处理显示已阅事务
			String mp = fdbk.getMpMember();		//增加群组后，修改为展开数据	Modify at 161229 by guanwenhao;
			if ( mp!=null ){
				List<Map<String,String>> mp_Lst = new ArrayList<Map<String,String>>();
				String[] mp_ret = mp.split(",");
				String rr = fdbk.getRecordReader();
				String[] rr_ret =  {"noperson"};
				if ( rr!=null ) rr_ret = rr.split(",");
				Arrays.sort(rr_ret);
				int a = 0;
				for(String mpObj:mp_ret){
					a = Arrays.binarySearch(rr_ret, mpObj);
					Map<String, String> mp_map = new HashMap<String, String>();
					mp_map.put("name", mpObj);
					mp_map.put("label", String.valueOf(a));
					mp_Lst.add(mp_map);
				}
				mp_JSON = JSONArray.fromObject(mp_Lst);
			}
		}
		JSONArray nameLst = JSONArray.fromObject(feedbackService.getModifyPerson());
		ModelAndView mav = new ModelAndView();
		mav.addObject("curUser", curUser);
		mav.addObject("feedback", fdbk);
		mav.addObject("fbHist", fbHist);
		mav.addObject("nameLst",nameLst);
		mav.addObject("mpJSON",mp_JSON);
		mav.setViewName("Feedback");
		return mav;
	}
	
	@RequiresPermissions(value={"fdbk:create","fdbk:edit"}, logical=Logical.OR)
	@RequestMapping("saveFdbk.do")
	public String saveFdbk( Feedback feedback, HttpServletRequest request ) throws IOException{
		String isNew = request.getParameter("SaveAsNewFdbk");	//1:新建
		if(isNew.equals("1")) feedback.setId(0);
		Date nDate = new Date();
		Timestamp ts = new Timestamp( nDate.getTime() );
		feedback.setCreator(curUser);
		feedback.setCreateDate(ts.toString());
		Integer fdbkID = feedbackService.saveFdbk(feedback);
		return "redirect:/fdbk/"+fdbkID.toString();
	}
	
	@RequiresPermissions("fdbk:delete")
	@RequestMapping("delFb.do")
	@ResponseBody
	public String delFb(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if(request.getParameter("id")!=null) map.put("id", request.getParameter("id"));
		if(request.getParameter("pjName")!=null) map.put("pjName", request.getParameter("pjName"));
		if(request.getParameter("roleName")!=null) map.put("roleName", request.getParameter("roleName"));
		feedbackService.delByID(map);
		return "SUCCESS";
	}
	
	@RequestMapping("recordRdr.do")
	@ResponseBody
	public String recordRdr(HttpServletRequest request){
		boolean flag = true;
		List<String> rr_Lst = new ArrayList<String>();
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<Feedback> fbList = feedbackService.getFbList(map);
		Feedback fdbk = (Feedback) fbList.get(0);
		String mp = fdbk.getMpMember();		//增加群组后，修改为展开数据	Modify at 161229 by guanwenhao;
		if ( mp == null ) return "No one added";
		String[] mp_ret = mp.split(",");
		Arrays.sort(mp_ret);
		int a = Arrays.binarySearch(mp_ret, curUser);
		if ( a >= 0 ){	//在修改人员中
			String rr = fdbk.getRecordReader();
			if ( rr != null ){
				String[] rr_ret = rr.split(",");
				for(String rrObj:rr_ret){	//已在列表中
					if( rrObj.equals(curUser) ) flag = false;
					rr_Lst.add(rrObj);
				}
			}
			if ( flag ) {
				rr_Lst.add(curUser);
				feedbackService.updateRecRdr(new Object[]{StringUtils.join(rr_Lst,","),id});
			}
		}
		return "SUCCESS";
	}
	
}
