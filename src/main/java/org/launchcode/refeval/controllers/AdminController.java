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
		
		// average score for level 1
		
		List<EvaluationInput> level1Evals = evaluationInputDao.findByOffLevel(1);
		
		EvaluationInput level1Temp;
		double level1Score = 0;
		
		if(level1Evals != null){
			
		
		
		//get total score for each level 1 official
		
		for(int i = 0; i < level1Evals.size(); i++){
			
			level1Temp = level1Evals.get(i);
			
			level1Score = level1Score + level1Temp.getTotalScore();
			
		}
		
		double aveLevel1Score = level1Score / level1Evals.size();
		
		aveLevel1Score = Math.round(aveLevel1Score) * 10 / 10.0;
		
		model.addAttribute("aveLevel1Score", aveLevel1Score);
		}
		
		else {
			String noLevel1 = "No evaluations currently recored for Level 1 officials";
			model.addAttribute("aveLevel1Score", noLevel1);
		}
		
		// average score for level 2
		
				List<EvaluationInput> level2Evals = evaluationInputDao.findByOffLevel(2);
				
				EvaluationInput level2Temp;
				double level2Score = 0;
				
				if(level2Evals != null){
					
				
				
				//get total score for each level 2 official
				
				for(int i = 0; i < level2Evals.size(); i++){
					
					level2Temp = level2Evals.get(i);
					
					level2Score = level2Score + level2Temp.getTotalScore();
					
				}
				
				double aveLevel2Score = level2Score / level1Evals.size();
				
				aveLevel2Score = Math.round(aveLevel2Score) * 10 / 10.0;
				
				model.addAttribute("aveLevel2Score", aveLevel2Score);
				}
				
				else {
					String noLevel2 = "No evaluations currently recored for Level 2 officials";
					model.addAttribute("noLevel2", noLevel2);
				}
		
		return "adminreportsaverages";
		
		
	}
	
}
