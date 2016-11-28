package org.launchcode.refeval.controllers;

import javax.servlet.http.HttpSession;

import org.launchcode.refeval.models.Official;
import org.launchcode.refeval.models.dao.AdminDao;
import org.launchcode.refeval.models.dao.EvaluatorDao;
import org.launchcode.refeval.models.dao.OfficialDao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {
	@Autowired
	protected OfficialDao officialDao;
	
	@Autowired 
	protected EvaluatorDao evaluatorDao;
	
	@Autowired
	protected AdminDao adminDao;
	
	public static final String officialSessionKey = "official_id";
	
	protected Official getOfficialFromSession(HttpSession session) {
		Integer officialId = (Integer) session.getAttribute(officialSessionKey);
		return officialId == null ? null : officialDao.findByUid(officialId);
	}
	
	protected void setOfficialInSession(HttpSession session, Official official) {
		session.setAttribute(officialSessionKey, official.getUid());
	}
	
	

}
