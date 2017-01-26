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

	//handles the login in verifications for the different users:  Officials, Evaluators & Admins
	
	
	//Login page for officials
	@RequestMapping(value="/officiallogin", method = RequestMethod.GET)
	public String loginForm() {
		
		return "officiallogin";
	}

	//processes official login info
	@RequestMapping(value="/officiallogin", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
				
		//get parameters from login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//finds official by username to validate login info
		Official official = officialDao.findByUsername(username);
		
		//verify a username and password were submitted
		if(username == null || username == "" || password == null || password == ""){
			model.addAttribute("missing_field_error", "Missing username or password, please try again");
			return "officiallogin";
		}
		
		//validate official is in database
		if(official == null){
			model.addAttribute("username_error", "Username not found, please try again");
			return "officiallogin";
		}
		
		//check password matches to official
		if(!official.isMatchingPassword(password)){
			model.addAttribute("password_error", "Incorrect password, please try again");
			return "officiallogin";
		}
		
		//Official has been verified and is ready to be logged in
		//log them in by setting official in session
		setOfficialInSession(request.getSession(), official);
		return "redirect:/officialhome";
	}
	
	//Page for new officials to sign up
	@RequestMapping(value="/officialsignup", method = RequestMethod.GET)
	public String officialSignupForm(){
		return "/officialsignup";
	}
	
	//Process new official data
	@RequestMapping(value="/officialsignup", method = RequestMethod.POST)
	public String officialSignup(HttpServletRequest request, Model model){
		
		//get parameters from signup form
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String certLevel = request.getParameter("level"); //will need to be converted to int
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		
		//converts to int
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
		
		
		//validate official level is within norms, between 1-4
		
		
		if(level <1 || level > 4){
			model.addAttribute("level_error", "Invalid Official Level. Please input valid level between 1-4");
			return"/officialsignup";
		}
		
			
		//once validated, create new official
		//set isOfficial to true, isEvaluator to false, isAdmin to false
		Official newOfficial = new Official(firstName, lastName, username, password, level, true, false, false);
		officialDao.save(newOfficial);
		//logs in new official and send to officials home page
		setOfficialInSession(request.getSession(), newOfficial);
		
		return "redirect:/officialhome";
	}
	
	//Admin Login
	//Admin login form
	@RequestMapping(value="/adminlogin", method = RequestMethod.GET)
	public String adminloginForm() {
		// pulls up login form
		return "adminlogin";
	}

	//process admin login input
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
			model.addAttribute("missing_field_error", "Missing username or password, please try again");
			return "adminlogin";
		}
		
		//validate official is in database
		if(official == null){
			model.addAttribute("username_error", "Username not found, please try again");
			return "adminlogin";
		}
		
		//check password matches to official
		if(!official.isMatchingPassword(password)){
			model.addAttribute("password_error", "Incorrect password, please try again");
			return "adminlogin";
		}
		
		//log them in by setting official in session
		setOfficialInSession(request.getSession(), official);
		return "redirect:/adminhome";
	}
	
	
	//Evaluator Login
	//Evaluator login form
	@RequestMapping(value="/evaluatorlogin", method=RequestMethod.GET)
	public String evalLoginForm() {
		return "evaluatorlogin";
	}
	
	//Process evaluator login info
	@RequestMapping(value="/evaluatorlogin", method=RequestMethod.POST)
	public String evalLogin(HttpServletRequest request, Model model){
		
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
			 model.addAttribute("password_error", "Incorrect Password, please try again");
			 return "evaluatorlogin";
		 }
		 
		 //log in evaluator
		 setOfficialInSession(request.getSession(), evaluator);
		 return "redirect:/evaluatorhome";
	}
	
	//if try to access page not authorized
	@RequestMapping(value="/403forbidden", method=RequestMethod.GET)
	public String accessDenied(){
		return "403forbidden";
	}
	
	//if try to access page when not logged in
	@RequestMapping(value="/403login", method=RequestMethod.GET)
	public String notLoggedIn(){
		return "403login";
	}

}

