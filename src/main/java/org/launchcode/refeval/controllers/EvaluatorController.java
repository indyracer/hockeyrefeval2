package org.launchcode.refeval.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.refeval.models.EvaluationInput;
import org.launchcode.refeval.models.dao.EvaluationInputDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EvaluatorController extends AbstractController{
	
	@Autowired
	private EvaluationInputDao evaluationInputDao;
	
	@RequestMapping(value="/evaluatorhome")
	public String evaluatorHome(Model model){
		return "evaluatorhome";
		
		//ADD METHODS TO LIST THE EVALUTION REQUESTS HERE
	}
	
	@RequestMapping(value="evaluatorevalinput", method = RequestMethod.GET)
	public String evalInputForm(){
		return "evaluatorevalinput";
	}
	
	@RequestMapping(value="evaluatorevalinput", method = RequestMethod.POST)
	public String evalInput(HttpServletRequest request, Model model){
		//get parameters from the form
		String officialFirstName = request.getParameter("officialFirstName");
		String officialLastName = request.getParameter("officialLastName");
		String evaluationDate = request.getParameter("evaluationDate");
		String evaluationLocation = request.getParameter("evaluationLocation");
		String gameLevel = request.getParameter("gameLevel");
		String appearance = request.getParameter("appearance");//need to parse to int
		String appearanceComment = request.getParameter("appearanceComment");
		String positioning = request.getParameter("positioning");//need to parse to int
		String positioningComment = request.getParameter("positioningComment");
		String ruleKnowLedge = request.getParameter("ruleKnowLedge");//need to parse to int
		String ruleKnowLedgeComment = request.getParameter("ruleKnowLedgeComment");
		String communication = request.getParameter("communication");//need to parse to int
		String communicationComment = request.getParameter("communicationComment");
		String generalComment = request.getParameter("generalComment");
		
		//parse scoring variables to ints
		int appearanceScore = Integer.parseInt(appearance);
		int positioningScore = Integer.parseInt(positioning);
		int ruleKnowLedgeScore = Integer.parseInt(ruleKnowLedge);
		int communicationScore = Integer.parseInt(communication);
		
		//validate all required fields were input
		if(officialFirstName == "" || officialLastName == "" || evaluationDate == "" || evaluationLocation == "" || 
			gameLevel == "" || appearance == "" || positioning == "" || ruleKnowLedge == "" || communication == ""){
			
			model.addAttribute("missing_field_error", "Please ensure all required fields are filled in");
			return "evaluatorevalinput";
		}
		
		//validate date is in correct format
		if(!isValidDate(evaluationDate)){
			model.addAttribute("evaluationDate_error", "Please input date in M/D/Y format");
			return "evaluatorevalinput";
		}
		
		//validation complete, add to db
		EvaluationInput newEval = new EvaluationInput(officialFirstName, officialLastName, evaluationDate, evaluationLocation, gameLevel, appearanceScore, appearanceComment, 
				positioningScore, positioningComment, ruleKnowLedgeScore, ruleKnowLedgeComment, communicationScore, communicationComment, generalComment);
		evaluationInputDao.save(newEval);
		
		return "redirect:/evaluatorhome";
		
		
		
		
		
		
		
	}
	
	public boolean isValidDate(String date){
		Pattern validDatePattern = Pattern.compile("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$");
		Matcher matcher = validDatePattern.matcher(date);
		return matcher.matches();
	}

}
