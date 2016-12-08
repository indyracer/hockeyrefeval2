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
public class OfficialAuthenticationController extends AbstractController {
	
	@Autowired
	private OfficialDao officialDao;

	@RequestMapping(value="/officiallogin", method = RequestMethod.GET)
	public String loginForm() {
		// pulls up login form
		return "officiallogin";
	}


	@RequestMapping(value="/officiallogin", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		// implements the login
		
		//get parameters from login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//validate parameters in order to login
		Official official = officialDao.findByUsername(username);
		
		//official username and password were submitted
		if(username == null || username == "" || password == null || password == ""){
			model.addAttribute("login_error", "Missing username or password, please try again");
			return "officiallogin";
		}
		
		//validate official is in database
		if(official == null){
			model.addAttribute("login_error", "Username not found, please try again");
			return "officiallogin";
		}
		
		//check password matches to official
		if(!official.isMatchingPassword(password)){
			model.addAttribute("login_error", "Incorrect password, please try again");
			return "officiallogin";
		}
		
		//log them in by setting official in session
		setOfficialInSession(request.getSession(), official);
		return "redirect:/officialhome";
	}
	
	@RequestMapping(value="/officialsignup", method = RequestMethod.GET)
	public String officialSignupForm(){
		return "/officialsignup";
	}
	
	@RequestMapping(value="/officialsignup", method = RequestMethod.POST)
	public String officialSignup(HttpServletRequest request, Model model){
		
		//get parameters from signup form
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String certLevel = request.getParameter("level");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		
		int level = Integer.parseInt(certLevel);
		
		//validate parameters (all parameters were included)
		
		if(firstName == "" || lastName == "" || certLevel == "" || username == "" || password == "" || verify == ""){
			model.addAttribute("missing_field_error", "Missing field, please ensure all fields are filled in");
			return "/officialsignup";
		}
		
		//valid username
		if(!Official.isValidUsername(username)){
			model.addAttribute("username_error", "Username is not valid, must be between 4 and 15 characters");
			return "/officialsignup";
		}
		
		//valid password
		if(!Official.isValidPassword(password)){
			model.addAttribute("password_error", "Password is not valid, must be between 6 and 20 characters");
			return "/officialsignup";
		}
		
		//password matches verify
		if(!password.equals(verify)){
			model.addAttribute("verify_error", "Password and Verify do not match, please try again");
			return "/officialsignup";
		}
		
		//set isOfficial to true, isEvaluator to false, isAdmin to false
		
	
		
		//once validated, create new official
		Official newOfficial = new Official(firstName, lastName, username, password, level, true, false, false);
		officialDao.save(newOfficial);
		setOfficialInSession(request.getSession(), newOfficial);
		
		return "redirect:/officialhome";
	}
	
	@RequestMapping(value="/adminlogin", method = RequestMethod.GET)
	public String adminloginForm() {
		// pulls up login form
		return "adminlogin";
	}

	@RequestMapping(value="/adminlogin", method = RequestMethod.POST)
	public String adminLogin(HttpServletRequest request, Model model) {
		// implements the login
		
		//get parameters from login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//validate parameters in order to login
		Official official = officialDao.findByUsername(username);
		
		//official username and password were submitted
		if(username == null || username == "" || password == null || password == ""){
			model.addAttribute("login_error", "Missing username or password, please try again");
			return "adminlogin";
		}
		
		//validate official is in database
		if(official == null){
			model.addAttribute("login_error", "Username not found, please try again");
			return "adminlogin";
		}
		
		//check password matches to official
		if(!official.isMatchingPassword(password)){
			model.addAttribute("login_error", "Incorrect password, please try again");
			return "adminlogin";
		}
		
		//log them in by setting official in session
		setOfficialInSession(request.getSession(), official);
		return "redirect:/adminhome";
	}
	
	@RequestMapping(value="/evaluatorlogin", method=RequestMethod.GET)
	public String evalLoginForm() {
		return "evaluatorlogin";
	}
	
	@RequestMapping(value="/evaluatorlogin", method=RequestMethod.POST)
	public String evalLogin(HttpServletRequest request, Model model){
		//implements evaluator login
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Official evaluator = officialDao.findByUsername(username);
		
		//validate username and password
		
		if(username == null || username == "" || password == null || password == ""){
			model.addAttribute("missing_field_error", "Missing username or password, please try again");
			return "evaluatorlogin";
		}
		
		//validate evaluator is in database
		 if(evaluator == null){
			 model.addAttribute("username_error", "Username not found, please try again");
			 return "evaluatorlogin";
		 }
		 
		 //check password matches to evaluator
		 if(!evaluator.isMatchingPassword(password)){
			 model.addAttribute("password_error", "Password doesn't match username, please try again");
			 return "evaluatorlogin";
		 }
		 
		 //log in evaluator
		 setOfficialInSession(request.getSession(), evaluator);
		 return "redirect:/evaluatorhome";
	}

}

