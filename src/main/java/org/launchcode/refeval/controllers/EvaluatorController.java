package org.launchcode.refeval.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.launchcode.refeval.models.EvaluationInput;
import org.launchcode.refeval.models.EvaluationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EvaluatorController extends AbstractController{
	
	
	//Evaluator Home Page
	@RequestMapping(value="/evaluatorhome")
	public String evaluatorHome(Model model){
		
		//fetch list of outstanding evaluation requests
		List<EvaluationRequest> evalRequest = evaluationRequestDao.findAll();
		model.addAttribute("evalRequests", evalRequest);
		
		return "evaluatorhome";
		
	}
	
	//Form to input evaluation data
	@RequestMapping(value="evaluatorevalinput", method = RequestMethod.GET)
	public String evalInputForm(){
		return "evaluatorevalinput";
	}
	
	//Saves evaluation to databaose
	@RequestMapping(value="evaluatorevalinput", method = RequestMethod.POST)
	public String evalInput(HttpServletRequest request, Model model){
		//get parameters from the form
		String officialFirstName = request.getParameter("officialFirstName");
		String officialLastName = request.getParameter("officialLastName");
		String officialUid = request.getParameter("officialUid");
		String offLevel = request.getParameter("offLevel");
		String evaluationDate = request.getParameter("evaluationDate");
		String evaluationLocation = request.getParameter("evaluationLocation");
		String gameLevel = request.getParameter("gameLevel");
		String appearance = request.getParameter("appearance");//need to parse to int
		String appearanceComment = request.getParameter("appearanceComment");
		String skating = request.getParameter("skating");
		String skatingComment = request.getParameter("skatingComment");
		String positioning = request.getParameter("positioning");//need to parse to int
		String positioningComment = request.getParameter("positioningComment");
		String ruleKnowLedge = request.getParameter("ruleKnowLedge");//need to parse to int
		String ruleKnowLedgeComment = request.getParameter("ruleKnowLedgeComment");
		String communication = request.getParameter("communication");//need to parse to int
		String communicationComment = request.getParameter("communicationComment");
		String generalComment = request.getParameter("generalComments");
		
		//parse scoring variables to ints
		int uid= Integer.parseInt(officialUid); 
		int level = Integer.parseInt(offLevel);
		double appearanceScore = Double.parseDouble(appearance);
		double skatingScore = Double.parseDouble(skating);
		double positioningScore = Double.parseDouble(positioning);
		double ruleKnowLedgeScore = Double.parseDouble(ruleKnowLedge);
		double communicationScore = Double.parseDouble(communication);
		
		//validate all required fields were input
		if(officialFirstName == "" || officialLastName == "" || officialUid == "" || offLevel == "" || evaluationDate == "" || evaluationLocation == "" || 
			gameLevel == "" || appearance == "" || positioning == "" || ruleKnowLedge == "" || communication == ""){
			
			model.addAttribute("missing_field_error", "Please ensure all required fields are filled in");
			return "evaluatorevalinput";
		}
		
		//validate date is in correct format
		if(!OfficialController.isValidDate(evaluationDate)){
			model.addAttribute("evaluationDate_error", "Please input date in M/D/Y format");
			return "evaluatorevalinput";
		}
		
		//validate that level is within norms (1-4)
		if(level < 1 || level > 4){
			model.addAttribute("offLevel_error", "Invalid Official Level. Please input valid level between 1-4");
			return "evaluatorevalinput";
			
		}
		
		//validation complete, add to db
		EvaluationInput newEval = new EvaluationInput(officialFirstName, officialLastName, uid, level, evaluationDate, evaluationLocation, gameLevel, appearanceScore, appearanceComment, 
				skatingScore, skatingComment, positioningScore, positioningComment, ruleKnowLedgeScore, ruleKnowLedgeComment, communicationScore, communicationComment, generalComment);
		evaluationInputDao.save(newEval);
		
		return "redirect:/evaluatorhome";	
	}
	
	//provides evaluators with details on officials who have requested an evaluation
	@RequestMapping(value="evaluator/{firstName}{lastName}evalrequest{uid}")
	public String singleEvalRequest(@PathVariable String firstName, @PathVariable String lastName, @PathVariable int uid, Model model){
		
		//find the official requesting eval
		EvaluationRequest evalRequest = evaluationRequestDao.findByUid(uid);
		model.addAttribute("evalRequest", evalRequest);
		
		return "evaluatorevalrequest";
	}
	

}
