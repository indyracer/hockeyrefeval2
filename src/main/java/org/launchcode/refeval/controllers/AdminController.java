package org.launchcode.refeval.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.refeval.models.EvaluationInput;
import org.launchcode.refeval.models.Official;
import org.launchcode.refeval.models.dao.EvaluationInputDao;
import org.launchcode.refeval.models.dao.OfficialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class AdminController extends AbstractController{

	@Autowired
	private OfficialDao officialDao;

	@Autowired
	private EvaluationInputDao evaluationInputDao;

	//admins home page
	@RequestMapping(value="/adminhome")
	public String adminHome(Model model){
		List<EvaluationInput> evaluation = evaluationInputDao.findAll();
		model.addAttribute("evaluation", evaluation);



		return "adminhome";
	}

	//Form to create new admin
	@RequestMapping(value="/adminsetup", method = RequestMethod.GET)
	public String adminSetupForm(){
		return "adminsetup";
	}

	//saves new admin to database
	@RequestMapping(value="/adminsetup", method = RequestMethod.POST)
	public String adminSetup(HttpServletRequest request, Model model){

		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");

		//validate that fields were input 
		if(firstName == "" || firstName == null || lastName == "" || lastName == null){
			model.addAttribute("missing_field_error", "Missing field input, please try again");
			return "adminsetup";
		}

		//create new admin using Official object
		//set up admin username using firstname + lastname + Admin, eg. BobCohenAdmin
		String adminUsername = firstName + lastName + "Admin";
		//set up password for admin, same as Admin username
		String adminPassword = adminUsername;
		//Admin does not need a level, use "0" as default
		Official newAdmin = new Official(firstName, lastName, adminUsername, adminPassword, 0, false, false, true);

		//add admin to database
		officialDao.save(newAdmin);

		return "redirect:/adminhome";


	}

	//form to create up new new evaluator
	@RequestMapping(value="/adminevalsetup", method = RequestMethod.GET)
	public String evalSetupForm(){
		return "adminevalsetup";
	}

	//saves new evaluator to database
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

		//create new evaluator using Official object
		//set up evaluator username using firstname + lastname + Evaluator, eg. bobcohenEvaluator
		String evalUsername = firstName + lastName + "Evaluator";
		//set up password for evaulator, lastname + firstname
		String evalPassword = lastName + firstName;
		//Evaluator does not need a level, use "0" as default
		Official newEvaluator = new Official(firstName, lastName, evalUsername, evalPassword, 0, false, true, false);

		//add evaluator to database
		officialDao.save(newEvaluator);

		return "redirect:/adminhome";

	}

	//To access Reports
	@RequestMapping(value="adminreports", method = RequestMethod.GET)
	public String reports(){
		return "adminreports";
	}

	//Reports
	@RequestMapping(value="adminreportsaverages", method = RequestMethod.GET)
	public String aveReports(HttpServletRequest request, Model model){
		//average Score all officials
		
		double overallAverage = overallAverage();

		model.addAttribute("aveScore", overallAverage);

		//average by level
		
		//calculation by level
		double level1Ave = Math.round((totalByLevel().get(0) / countByLevel().get(0)) * 10) / 10.0;
		double level2Ave = Math.round((totalByLevel().get(1) / countByLevel().get(1)) * 10) / 10.0;
		double level3Ave = Math.round((totalByLevel().get(2) / countByLevel().get(2)) * 10) / 10.0;
		double level4Ave = Math.round((totalByLevel().get(3) / countByLevel().get(3)) * 10) / 10.0;

		model.addAttribute("level1Ave", level1Ave);
		model.addAttribute("level2Ave", level2Ave);
		model.addAttribute("level3Ave", level3Ave);
		model.addAttribute("level4Ave", level4Ave);

		return "adminreportsaverages";
	}

	//Scores by each criteria
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

		double aveAppearance = Math.round((appearanceTotal / count) * 10) / 10.0;
		double aveSkating = Math.round((skatingTotal / count) * 10) / 10.0;
		double aveRuleKnowledge = Math.round((ruleKnowledgeTotal / count) * 10) / 10.0;
		double avePositioning = Math.round((positioningTotal / count) * 10) / 10.0;
		double aveCommunication = Math.round((communicationTotal / count) * 10) / 10.0;

		model.addAttribute("aveAppearance", aveAppearance);
		model.addAttribute("aveSkating", aveSkating);
		model.addAttribute("aveRuleKnowledge", aveRuleKnowledge);
		model.addAttribute("avePositioning", avePositioning);
		model.addAttribute("aveCommunication", aveCommunication);
		
		//Scores by level, by criteria
		double appearanceLevel1 = Math.round((appearanceByLevelTotals().get(0)/ countByLevel().get(0)) * 10) / 10.0;
		double appearanceLevel2 = Math.round((appearanceByLevelTotals().get(1)/ countByLevel().get(1)) * 10) / 10.0;
		double appearanceLevel3 = Math.round((appearanceByLevelTotals().get(2)/ countByLevel().get(2)) * 10) / 10.0;
		double appearanceLevel4 = Math.round((appearanceByLevelTotals().get(3)/ countByLevel().get(3)) * 10) / 10.0;
		
		model.addAttribute("appearanceLevel1", appearanceLevel1);
		model.addAttribute("appearanceLevel2", appearanceLevel2);
		model.addAttribute("appearanceLevel3", appearanceLevel3);
		model.addAttribute("appearanceLevel4", appearanceLevel4);
		
		double skatingLevel1 = Math.round((skatingByLevelTotals().get(0)/ countByLevel().get(0)) * 10) / 10.0;
		double skatingLevel2 = Math.round((skatingByLevelTotals().get(1)/ countByLevel().get(1)) * 10) / 10.0;
		double skatingLevel3 = Math.round((skatingByLevelTotals().get(2)/ countByLevel().get(2)) * 10) / 10.0;
		double skatingLevel4 = Math.round((skatingByLevelTotals().get(3)/ countByLevel().get(3)) * 10) / 10.0;
		
		model.addAttribute("skatingLevel1", skatingLevel1);
		model.addAttribute("skatingLevel2", skatingLevel2);
		model.addAttribute("skatingLevel3", skatingLevel3);
		model.addAttribute("skatingLevel4", skatingLevel4);
		
		double rulesLevel1 = Math.round((rulesByLevelTotals().get(0)/ countByLevel().get(0)) * 10) / 10.0;
		double rulesLevel2 = Math.round((rulesByLevelTotals().get(1)/ countByLevel().get(1)) * 10) / 10.0;
		double rulesLevel3 = Math.round((rulesByLevelTotals().get(2)/ countByLevel().get(2)) * 10) / 10.0;
		double rulesLevel4 = Math.round((rulesByLevelTotals().get(3)/ countByLevel().get(3)) * 10) / 10.0;
		
		model.addAttribute("rulesLevel1", rulesLevel1);
		model.addAttribute("rulesLevel2", rulesLevel2);
		model.addAttribute("rulesLevel3", rulesLevel3);
		model.addAttribute("rulesLevel4", rulesLevel4);
		
		double positioningLevel1 = Math.round((positioningByLevelTotals().get(0)/ countByLevel().get(0)) * 10) / 10.0;
		double positioningLevel2 = Math.round((positioningByLevelTotals().get(1)/ countByLevel().get(1)) * 10) / 10.0;
		double positioningLevel3 = Math.round((positioningByLevelTotals().get(2)/ countByLevel().get(2)) * 10) / 10.0;
		double positioningLevel4 = Math.round((positioningByLevelTotals().get(3)/ countByLevel().get(3)) * 10) / 10.0;
		
		model.addAttribute("positioningLevel1", positioningLevel1);
		model.addAttribute("positioningLevel2", positioningLevel2);
		model.addAttribute("positioningLevel3", positioningLevel3);
		model.addAttribute("positioningLevel4", positioningLevel4);
		
		double communicationLevel1 = Math.round((communicationByLevelTotals().get(0)/ countByLevel().get(0)) * 10) / 10.0;
		double communicationLevel2 = Math.round((communicationByLevelTotals().get(1)/ countByLevel().get(1)) * 10) / 10.0;
		double communicationLevel3 = Math.round((communicationByLevelTotals().get(2)/ countByLevel().get(2)) * 10) / 10.0;
		double communicationLevel4 = Math.round((communicationByLevelTotals().get(3)/ countByLevel().get(3)) * 10) / 10.0;
		
		model.addAttribute("communicationLevel1", communicationLevel1);
		model.addAttribute("communicationLevel2", communicationLevel2);
		model.addAttribute("communicationLevel3", communicationLevel3);
		model.addAttribute("communicationLevel4", communicationLevel4);
		
		return "adminreportsbycriteria";


	}

	//pull up individual official's evaluation
	@RequestMapping(value="/adminevalreview{uid}", method = RequestMethod.GET)
	public String adminEvaluationReview(@PathVariable int uid, Model model){
		EvaluationInput evaluation = evaluationInputDao.findByUid(uid);
		model.addAttribute("evaluation", evaluation);

		return "adminevalreview";
	}

	//methods to calculate report metrics
	public double overallAverage(){
		List<EvaluationInput> evaluations = evaluationInputDao.findAll();

		double totalScore = 0;

		EvaluationInput temp;

		for(int i = 0; i < evaluations.size(); i++){
			temp = evaluations.get(i);
			totalScore = totalScore + temp.getTotalScore();
		}

		double aveScore = Math.round((totalScore / evaluations.size()) * 10) / 10.0;

		return aveScore;
	}

	public List<Double> totalByLevel(){
		List<EvaluationInput> evaluations = evaluationInputDao.findAll();
		List<Double> ScoresByLevel = new ArrayList<Double>();

		EvaluationInput levelTemp;

		double level1Total = 0;
		double level2Total = 0;
		double level3Total = 0;
		double level4Total = 0;

		for(int i = 0; i < evaluations.size(); i++){
			levelTemp = evaluations.get(i);

			if(levelTemp.getOffLevel() == 1){
				level1Total = level1Total + levelTemp.getTotalScore();
			}
			else if (levelTemp.getOffLevel() == 2){
				level2Total = level2Total + levelTemp.getTotalScore();
			}
			else if (levelTemp.getOffLevel() == 3){
				level3Total = level3Total + levelTemp.getTotalScore();
			}
			else{
				level4Total = level4Total + levelTemp.getTotalScore();
			}
		}


		//add scores to list
		ScoresByLevel.add(level1Total);
		ScoresByLevel.add(level2Total);
		ScoresByLevel.add(level3Total);
		ScoresByLevel.add(level4Total);

		return ScoresByLevel;

	}
	
	public List<Double> countByLevel(){
		List<EvaluationInput> evaluations = evaluationInputDao.findAll();
		List<Double> countByLevel = new ArrayList<Double>();
		
		double level1Count = 0;
		double level2Count = 0;
		double level3Count = 0;
		double level4Count = 0;
		
		EvaluationInput temp;
		
		for(int i = 0; i < evaluations.size(); i++){
			temp = evaluations.get(i);
			
			if(temp.getOffLevel() == 1){
				level1Count++;
			}
			else if(temp.getOffLevel() == 2){
				level2Count++;
			}
			else if(temp.getOffLevel() == 3){
				level3Count++;
			}
			else{
				level4Count++;
			}
		}
		
		//add count by levels to List
		countByLevel.add(level1Count);
		countByLevel.add(level2Count);
		countByLevel.add(level3Count);
		countByLevel.add(level4Count);
		
		return countByLevel;
	}
	
	public List <Double> appearanceByLevelTotals(){
		List<EvaluationInput> evaluations = evaluationInputDao.findAll();
		List<Double> appearanceByLevelTotals = new ArrayList<Double>();
		
		EvaluationInput temp;
		
		double Level1Total = 0;
		double Level2Total = 0;
		double Level3Total = 0;
		double Level4Total = 0;
		
		for(int i = 0; i < evaluations.size(); i++){
			temp = evaluations.get(i);
			if(temp.getOffLevel() == 1){
				Level1Total = Level1Total + temp.getAppearance();
			}
			else if(temp.getOffLevel() == 2){
				Level2Total = Level2Total + temp.getAppearance();
			}
			else if(temp.getOffLevel() == 3){
				Level3Total = Level3Total + temp.getAppearance();
				}
			else {
				Level4Total = Level4Total + temp.getAppearance();
			}			
		}
		
		//add to list
		appearanceByLevelTotals.add(Level1Total);
		appearanceByLevelTotals.add(Level2Total);
		appearanceByLevelTotals.add(Level3Total);
		appearanceByLevelTotals.add(Level4Total);
		
		return appearanceByLevelTotals;
	}

	public List <Double> skatingByLevelTotals(){
		List<EvaluationInput> evaluations = evaluationInputDao.findAll();
		List<Double> skatingByLevelTotals = new ArrayList<Double>();
		
		EvaluationInput temp;
		
		double Level1Total = 0;
		double Level2Total = 0;
		double Level3Total = 0;
		double Level4Total = 0;
		
		for(int i = 0; i < evaluations.size(); i++){
			temp = evaluations.get(i);
			if(temp.getOffLevel() == 1){
				Level1Total = Level1Total + temp.getSkating();
			}
			else if(temp.getOffLevel() == 2){
				Level2Total = Level2Total + temp.getSkating();
			}
			else if(temp.getOffLevel() == 3){
				Level3Total = Level3Total + temp.getSkating();
				}
			else {
				Level4Total = Level4Total + temp.getSkating();
			}			
		}
		
		//add to list
		skatingByLevelTotals.add(Level1Total);
		skatingByLevelTotals.add(Level2Total);
		skatingByLevelTotals.add(Level3Total);
		skatingByLevelTotals.add(Level4Total);
		
		return skatingByLevelTotals;
	}
	
	public List <Double> rulesByLevelTotals(){
		List<EvaluationInput> evaluations = evaluationInputDao.findAll();
		List<Double> rulesByLevelTotals = new ArrayList<Double>();
		
		EvaluationInput temp;
		
		double Level1Total = 0;
		double Level2Total = 0;
		double Level3Total = 0;
		double Level4Total = 0;
		
		for(int i = 0; i < evaluations.size(); i++){
			temp = evaluations.get(i);
			if(temp.getOffLevel() == 1){
				Level1Total = Level1Total + temp.getRuleKnowledge();
			}
			else if(temp.getOffLevel() == 2){
				Level2Total = Level2Total + temp.getRuleKnowledge();
			}
			else if(temp.getOffLevel() == 3){
				Level3Total = Level3Total + temp.getRuleKnowledge();
				}
			else {
				Level4Total = Level4Total + temp.getRuleKnowledge();
			}			
		}
		
		//add to list
		rulesByLevelTotals.add(Level1Total);
		rulesByLevelTotals.add(Level2Total);
		rulesByLevelTotals.add(Level3Total);
		rulesByLevelTotals.add(Level4Total);
		
		return rulesByLevelTotals;
	}
	
	public List <Double> positioningByLevelTotals(){
		List<EvaluationInput> evaluations = evaluationInputDao.findAll();
		List<Double> positioningByLevelTotals = new ArrayList<Double>();
		
		EvaluationInput temp;
		
		double Level1Total = 0;
		double Level2Total = 0;
		double Level3Total = 0;
		double Level4Total = 0;
		
		for(int i = 0; i < evaluations.size(); i++){
			temp = evaluations.get(i);
			if(temp.getOffLevel() == 1){
				Level1Total = Level1Total + temp.getPositioning();
			}
			else if(temp.getOffLevel() == 2){
				Level2Total = Level2Total + temp.getPositioning();
			}
			else if(temp.getOffLevel() == 3){
				Level3Total = Level3Total + temp.getPositioning();
				}
			else {
				Level4Total = Level4Total + temp.getPositioning();
			}			
		}
		
		//add to list
		positioningByLevelTotals.add(Level1Total);
		positioningByLevelTotals.add(Level2Total);
		positioningByLevelTotals.add(Level3Total);
		positioningByLevelTotals.add(Level4Total);
		
		return positioningByLevelTotals;
	}
	
	public List <Double> communicationByLevelTotals(){
		List<EvaluationInput> evaluations = evaluationInputDao.findAll();
		List<Double> communicationByLevelTotals = new ArrayList<Double>();
		
		EvaluationInput temp;
		
		double Level1Total = 0;
		double Level2Total = 0;
		double Level3Total = 0;
		double Level4Total = 0;
		
		for(int i = 0; i < evaluations.size(); i++){
			temp = evaluations.get(i);
			if(temp.getOffLevel() == 1){
				Level1Total = Level1Total + temp.getCommunication();
			}
			else if(temp.getOffLevel() == 2){
				Level2Total = Level2Total + temp.getCommunication();
			}
			else if(temp.getOffLevel() == 3){
				Level3Total = Level3Total + temp.getCommunication();
				}
			else {
				Level4Total = Level4Total + temp.getCommunication();
			}			
		}
		
		//add to list
		communicationByLevelTotals.add(Level1Total);
		communicationByLevelTotals.add(Level2Total);
		communicationByLevelTotals.add(Level3Total);
		communicationByLevelTotals.add(Level4Total);
		
		return communicationByLevelTotals;
	}



}
