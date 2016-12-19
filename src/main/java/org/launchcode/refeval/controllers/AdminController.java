package org.launchcode.refeval.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.refeval.models.EvaluationInput;
import org.launchcode.refeval.models.Official;
import org.launchcode.refeval.models.dao.OfficialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class AdminController extends AbstractController{

	@Autowired
	private OfficialDao officialDao;

	@RequestMapping(value="/adminhome")
	public String adminHome(Model model){
		List<EvaluationInput> evaluation = evaluationInputDao.findAll();
		model.addAttribute("evaluation", evaluation);
		
		
		
		return "adminhome";
	}

	@RequestMapping(value="/adminevalsetup", method = RequestMethod.GET)
	public String evalSetupForm(){
		return "adminevalsetup";
	}

	@RequestMapping(value="adminevalsetup", method = RequestMethod.POST)
	public String evalSetup(HttpServletRequest request, Model model){
		//get parameters from form
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");


		//validate that fields were input 
		if(firstName == "" || firstName == null || lastName == "" || lastName == null){
			model.addAttribute("missing_field_error", "Missing field input, please try again");
			return "adminevalsetup";
		}
		
		//create new admin using Official object
		//set up evaluator username using firstname + lastname + Evaluator, eg. bobcohenEvaluator
		String evalUsername = firstName + lastName + "Evaluator";
		//set up password for evaulator, lastname + firstname
		String evalPassword = lastName + firstName;
		Official newEvaluator = new Official(firstName, lastName, evalUsername, evalPassword, 0, false, true, false);
		
		//add evaluator to database
		officialDao.save(newEvaluator);
		
		return "redirect:/adminhome";
		
	}
	
	@RequestMapping(value="adminreports", method = RequestMethod.GET)
	public String reports(){
			return "adminreports";
	}
	
	@RequestMapping(value="adminreportsaverages", method = RequestMethod.GET)
	public String aveReports(HttpServletRequest request, Model model){
		//average Score all officials
		List<EvaluationInput> evaluations = evaluationInputDao.findAll();
		
		int totalScore = 0;
		
		EvaluationInput temp;
		
		
		//to get the total score for each official
		for(int i = 0; i < evaluations.size(); i++){
			
			
			temp = evaluations.get(i);
					
			totalScore = totalScore + temp.getTotalScore();
						
		}
		
		//average the scores
		double aveScore = totalScore / evaluations.size();
		//format to 1 decimal place
		aveScore = aveScore * 10/10.0;
		
		model.addAttribute("aveScore", aveScore);
		
		//average by level
		
		double level1Total = 0;
		double level1Count = 0;
		double level2Total = 0;
		double level2Count = 0;
		double level3Total = 0;
		double level3Count = 0;
		double level4Total = 0;
		double level4Count = 0;
		
		EvaluationInput levelTemp;
		
		for(int i = 0; i < evaluations.size(); i++){
			levelTemp = evaluations.get(i);
			if(levelTemp.getOffLevel() == 1){
				level1Total = level1Total + levelTemp.getTotalScore();
				level1Count++;
			} 
			else if(levelTemp.getOffLevel() == 2){
				level2Total = level2Total + levelTemp.getTotalScore();
				level2Count++;
			}
			else if(levelTemp.getOffLevel() == 3){
				level3Total = level3Total + levelTemp.getTotalScore();
				level3Count++;
			}
			else{
				level4Total = level4Total + levelTemp.getTotalScore();
				level4Count++;
			}
		}
			
		//calculation by level
		double level1Ave = Math.round(level1Total / level1Count) * 10 / 10.0;
		double level2Ave = Math.round(level2Total / level2Count) * 10 / 10.0;
		double level3Ave = Math.round(level3Total / level3Count) * 10 / 10.0;
		double level4Ave = Math.round(level4Total / level4Count) * 10 / 10.0;
		
		model.addAttribute("level1Ave", level1Ave);
		model.addAttribute("level2Ave", level2Ave);
		model.addAttribute("level3Ave", level3Ave);
		model.addAttribute("level4Ave", level4Ave);
		
		return "adminreportsaverages";
	}
	
	@RequestMapping(value="adminreportsbycriteria", method = RequestMethod.GET)
	public String scoreByCriteria(HttpServletRequest request, Model model){
		
		List<EvaluationInput> evaluations = evaluationInputDao.findAll();
		
		EvaluationInput temp;
		
		//get score for each criteria and calculate average across all officials
		
		double appearanceTotal = 0;
		double skatingTotal = 0;
		double positioningTotal = 0;
		double ruleKnowledgeTotal = 0;
		double communicationTotal = 0;
		double count = evaluations.size();
		
		for(int i = 0; i < evaluations.size(); i++){
			temp = evaluations.get(i);
			appearanceTotal = appearanceTotal + temp.getAppearance();
			skatingTotal = skatingTotal + temp.getSkating();
			ruleKnowledgeTotal = ruleKnowledgeTotal + temp.getRuleKnowledge();
			positioningTotal = positioningTotal + temp.getPositioning();
			communicationTotal = communicationTotal + temp.getCommunication();
		}
		
		double aveAppearance = Math.round(appearanceTotal / count) * 10 / 10.0;
		double aveSkating = Math.round(skatingTotal / count) * 10 / 10.0;
		double aveRuleKnowledge = Math.round(ruleKnowledgeTotal / count) * 10 / 10.0;
		double avePositioning = Math.round(positioningTotal / count) * 10 / 10.0;
		double aveCommunication = Math.round(communicationTotal / count) * 10 / 10.0;
		
		model.addAttribute("aveAppearance", aveAppearance);
		model.addAttribute("aveSkating", aveSkating);
		model.addAttribute("aveRuleKnowledge", aveRuleKnowledge);
		model.addAttribute("avePositioning", avePositioning);
		model.addAttribute("aveCommunication", aveCommunication);
		
		return "adminreportsbycriteria";
		
		
		}
	
	
}
