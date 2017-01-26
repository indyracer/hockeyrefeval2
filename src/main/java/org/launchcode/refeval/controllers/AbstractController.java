package org.launchcode.refeval.controllers;

import javax.servlet.http.HttpSession;

import org.launchcode.refeval.models.Official;
import org.launchcode.refeval.models.dao.EvaluationInputDao;
import org.launchcode.refeval.models.dao.EvaluationRequestDao;
import org.launchcode.refeval.models.dao.OfficialDao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {
	@Autowired
	protected OfficialDao officialDao;
	
	@Autowired
	protected EvaluationInputDao evaluationInputDao;
	
	@Autowired
	protected EvaluationRequestDao evaluationRequestDao;

	

	//Uses the official ID from database as the session key
	public static final String officialSessionKey = "official_id";
	
	//finds the official by using the session key as the official id
	protected Official getOfficialFromSession(HttpSession session) {
		Integer officialId = (Integer) session.getAttribute(officialSessionKey);
		return officialId == null ? null : officialDao.findByUid(officialId);
	}
	
	protected void setOfficialInSession(HttpSession session, Official official) {
		session.setAttribute(officialSessionKey, official.getUid());
	}
	
	

}
