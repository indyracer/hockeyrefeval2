package org.launchcode.refeval.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String location = request.getParameter("location");
		
		//validate fields have been filled in
		//identifies which official is logged in and submitting the request
		Official offInSession = getOfficialFromSession(request.getSession());
		
		//need to find logged in officials username
		//String username = offInSession.getUsername();
		
		//used to validate there is an official with this username
		//Official official = officialDao.findByUsername(username);
		
		if(firstName == ""){
			model.addAttribute("firstName_error", "Please include First Name");
			return "officialevaluationrequest";			
		}
		
		if(lastName == ""){
			model.addAttribute("lastName_error", "Please include Last Name");
			return "officialevaluationrequest";			
		}
		
		
		if(date == ""){
			model.addAttribute("date_error", "Please include date");
			return "officialevaluationrequest";
		}
		
		if(time == ""){
			model.addAttribute("time_error", "Please include time");
			return "officialevaluationrequest";
		}
		
		if(location == ""){
			model.addAttribute("location_error", "Please include location");
			return "officialevalationrequest";
		}
		
		
		//validate official is in database
		if(offInSession == null){
			model.addAttribute("missing_field_error", "No official with that name found, please try again");
			return "officialevaluationrequest";
		}
		
		//validate date is formatted correctly
		if(!isValidDate(date)){
			model.addAttribute("date_error", "Please input your date in M/D/Y format");
			return "officialevaluationrequest";
			
		}
		
		//validate time is formatted correctly
		if(!isValidTime(time)){
			model.addAttribute("time_error", "Please input the time in h:m am/pm format");
			return "officialevaluationrequest";
		}
	
		
		
			
		//validation complete add request to db
		EvaluationRequest newRequest = new EvaluationRequest(firstName, lastName, date, time, location);
		evaluationRequestDao.save(newRequest);
		
		model.addAttribute("request_received", "Your evaluation request has been received");
		return "redirect:/officialhome";
	}
	
	public boolean isValidDate(String date){
		Pattern validDatePattern = Pattern.compile("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$");
		Matcher matcher = validDatePattern.matcher(date);
		return matcher.matches();
	}
	
	public static boolean isValidTime(String time){
		Pattern validTimePattern = Pattern.compile("(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)");
		Matcher matcher = validTimePattern.matcher(time);
		return matcher.matches();
	}
	
	
	
	
}
