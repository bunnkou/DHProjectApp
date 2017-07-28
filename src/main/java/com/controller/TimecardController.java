package com.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.FeedbackDao;
import com.dao.TimecardDao;
import com.dao.UserDao;
import com.entity.Timecard;

@Controller
@RequestMapping("/timecard")
public class TimecardController {
	
	@Autowired
	private FeedbackDao feedbackDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TimecardDao tcDao;
	
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			mav.addObject("sData", 
				objectMapper.writeValueAsString( feedbackDao.getFbGroupByPjname() )
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
		mav.addObject("persons", userDao.getEmployeeOfDH());
		mav.setViewName("vwTimecard");
		return mav;
	}
	
	@RequestMapping(value="show", method=RequestMethod.POST)
	@ResponseBody
	public List<Timecard> show(HttpServletRequest request)
	{
		String date1 = request.getParameter("date1"),
			   date2 = request.getParameter("date2");
		return tcDao.getTcByDate(date1, date2);
	}
	
	@RequestMapping(value="store", method=RequestMethod.POST)
	@ResponseBody
	public List<Timecard> store(@RequestBody JSONObject requestJson) 
	{
		String S_date1 = requestJson.getString("date1"),
		S_date2 = requestJson.getString("date2");
		if (S_date2.equals("")) return null;
		Date date1 = null, date2 = null;
		int tcId = 0;
		List<Timecard> tcList = new ArrayList<Timecard>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date1 = sdf.parse(S_date1);
			date2 = sdf.parse(S_date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONArray rows = requestJson.getJSONArray("rows");
		for(int i=0; i<rows.length();i++){
			JSONObject row = rows.getJSONObject(i);
			Timecard tc = new Timecard();
			boolean IS_NEW = true;
			if (! row.getString("id").equals("undefined")){
				IS_NEW = false;
				tcId = Integer.parseInt( row.getString("id") );
				tc.setId(tcId);
				if (!IS_NEW) {
					try {
						date1 = sdf.parse( row.getString("date1") );
						date2 = sdf.parse( row.getString("date2") );
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			tc.setDate1(date1);
			tc.setDate2(date2);
			tc.setTitle(row.getString("title"));
			String persons = row.getString("persons");
			if (persons.equals("undefined")) persons = "";
			tc.setPersons(persons);
			tc.setLastUpdatePerson( (String) SecurityUtils.getSubject().getPrincipal() );
			Date date = new Date();
			Timestamp ts = new Timestamp( date.getTime() );
			tc.setLastUpdateDate(ts);
			if (IS_NEW) tcId = tcDao.insertTC(tc);
			else tcDao.updateTC(tc);
			tc.setId(tcId);
			tcList.add(tc);
		}
		return tcList;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(HttpServletRequest request) 
	{
		int id = Integer.parseInt( request.getParameter("id") );
		tcDao.deleteTC(id);
		return "{'status':'ok'}";
	}
}
