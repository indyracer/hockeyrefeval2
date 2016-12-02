package org.launchcode.refeval.controllers;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.refeval.models.EvaluationRequest;
import org.launchcode.refeval.models.Official;
import org.launchcode.refeval.models.dao.EvaluationRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OfficialController extends AbstractController{
	
	@Autowired
	private EvaluationRequestDao evaluationRequestDao;

	@RequestMapping(value="/officialhome")
	public String officialHome(Model model){
		//check that isOfficial is set to true
		
		
		
		
		
		
		//add officials evaluations with clickable links to each eval here, do once Evaluation table and models are completed
		
		
		
		return "officialhome";
	}
	
	@RequestMapping(value = "/officialevaluationrequest", method = RequestMethod.GET)
	public String requestForm(){
		return "officialevaluationrequest";
	}
	
	@RequestMapping(value = "/officialevaluationrequest", method = RequestMethod.POST)
	public String requestEvaluation(HttpServletRequest request, Model model){
		//get parameters from form NEED TO ADD USERNAME FOR VALIDATION PURPOSES
		String username = request.getParameter("username");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String location = request.getParameter("location");
		
		//validate official is in database
		//get username of official
		Official official = officialDao.findByUsername(username);
		
		//validate fields were filled in
		if(username == "" || username == null || date == "" || date == null || time == "" || time == null || location == "" || location == null){
			model.addAttribute("missing_field_error", "Missing information, please fill in all fields");
			return "officialevaluationrequest";
		}
		
		//validate official is in database
		if(official == null){
			model.addAttribute("username_error", "Username not found, please try again");
			return "officialevaluationrequest";
		}
		
		
			
		//validation complete add request to db
		EvaluationRequest newRequest = new EvaluationRequest(username, date, time, location);
		evaluationRequestDao.save(newRequest);
		
		model.addAttribute("request_received", "Your evaluation request has been received");
		return "redirect:/officialhome";
	}
	
	
	
	
}
