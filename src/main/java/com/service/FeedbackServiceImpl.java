package com.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.FeedbackDao;
import com.entity.Feedback;
import com.entity.Group;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
    private FeedbackDao feedbackDao;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	
	@Override
	public List<Feedback> getFbList(Map<String, Object> map){
		return feedbackDao.getFbList(map);
	}

	@Override
	public Integer saveFdbk(Feedback fdbk) {
		String member = null;
		String mp = fdbk.getModifyPerson();
		int beginIndex = -1;
		Set<String> mSet = new HashSet<String>();
		if (mp != null && mp.length() > 0) {
			String[] mp_ret = mp.split(",");
			for(String mpObj:mp_ret){	//遍历修改人员，解压数组
				beginIndex = mpObj.indexOf("[G]");
				if (beginIndex > -1){
					String gpName = mpObj.substring(0,beginIndex);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("gpName", gpName);
					List<Group> gpLst = groupService.getFbList(map);
					Group group = gpLst.get(0);
					member = group.getGroupMember();
					if (member != null && member.length() > 0) {	//将人员赋值到Set中
						String[] memberRet = member.split(",");
						for (String memberObj:memberRet){
							if (!mSet.contains(memberObj)) mSet.add(memberObj);
						}
					}
				}else{
					if (!mSet.contains(mpObj)) mSet.add(mpObj);
				}
			}
			if (!mSet.isEmpty()) fdbk.setMpMember(StringUtils.join(mSet.toArray(), ","));
		}
		return feedbackDao.saveFdbk(fdbk);
	}
	
	@Override
	public String updateRecRdr(Object[] arg) {
		return feedbackDao.updateRecRdr(arg);
	}

	@Override
	public Integer getTodoCount(String userName) {
		return feedbackDao.getTodoCount(userName);
	}

	@Override
	public Long getFbListCount(String sQuery) {
		return feedbackDao.getFbListCount(sQuery);
	}
	
	@Override
	public List<Map<String, Object>> getModifyPerson() {
		List<Map<String, Object>> pn = userService.getAllNames();
		List<Map<String, Object>> gp = groupService.getAllNames();
		pn.addAll(gp);
		return pn;
	}
	
	@Override
	public String delByID(Map<String, Object> map) {
		return feedbackDao.delByID(map);
	}

}
