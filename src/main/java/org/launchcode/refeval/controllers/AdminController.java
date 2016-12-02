package org.launchcode.refeval.controllers;

import javax.servlet.http.HttpServletRequest;

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
		return "adminhome";
	}

	@RequestMapping(value="/adminevalsetup", method = RequestMethod.GET)
	public String evalSetupForm(){
		return "adminevalsetup";
	}

	@RequestMapping(value="adminevalsetup", method = RequestMethod.POST)
	public String evalSetup(HttpServletRequest request, Model model){
		//get parameters from from
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastName");


		//validate that fields were input 
		if(firstName == "" || firstName == null || lastName == "" || lastName == null){
			model.addAttribute("missing_field_error", "Missing field input, please try again");
			return "adminevalsetup";
		}
		
		//create new admin using Official object
		//set up evaluator username using firstname + lastname + Evaluator, eg. bobcohenEvaluator
		String evalUsername = firstName + lastName + "Evaluator";
		//set up password for evalator, lastname + firstname
		String evalPassword = lastName + firstName;
		Official newEvaluator = new Official(firstName, lastName, evalUsername, evalPassword, 0, false, true, false);
		
		//add evaluator to database
		officialDao.save(newEvaluator);
		
		return "adminhome";
		

	}
	
}
