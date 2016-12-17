package org.launchcode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.launchcode.refeval.controllers.AbstractController;
import org.launchcode.refeval.models.Official;
import org.launchcode.refeval.models.dao.OfficialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	OfficialDao officialDao;
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		//list of restricted Official URLs
		List<String> officialAuthPages = Arrays.asList("officialhome", "officialevaluationrequest", "officialevaluation");
		List<String> evaluatorAuthPages = Arrays.asList("evaluatorhome", "evaluatorevalinput", "evaluatorevalrequest");
		List<String> adminAuthPages = Arrays.asList("adminhome", "adminevalsetup");
		
		if(officialAuthPages.contains(request.getRequestURI())){
			boolean isLoggedIn = false;
			Official official;
			Integer officialId = (Integer) request.getSession().getAttribute(AbstractController.officialSessionKey);
			
			if(officialId != null){
				official = officialDao.findByUid(officialId);
				
				if(official.isAdmin || official.isEvaluator){
					response.sendRedirect("/index");
					return false;
				}
				
				if(official != null){
					isLoggedIn = true;
				}
			}
			//if user is not logged in, redirect to login page ADD !isOfficial to send Admin and Evaluators back to homepage
			//check that official is an official, not an admin or evaluator
			//official = officialDao.findByUid(officialId);
			
			
			if(!isLoggedIn){
				response.sendRedirect("/officiallogin");//FIX, SEND TO HOME PAGE FOR APPROPRIATE LOGIN, LOOK INTO ERROR 403, FORBIDDEN ACCESS ERROR
				return false;
			}	
		}
		
		//Evaluator pages
		if(evaluatorAuthPages.contains(request.getRequestURI())){
			boolean isLoggedIn = false;
			Integer evaluatorId = (Integer) request.getSession().getAttribute(AbstractController.officialSessionKey);
			Official evaluator = officialDao.findByUid(evaluatorId);
			
			if(evaluatorId != null){				
				if(evaluator != null){
					isLoggedIn = true;
				}
			}
			
			//check that official is an evaluator, not an admin or official			
			if(!evaluator.isEvaluator){
				response.sendRedirect("/index");
				return false;
			}
			
			//if user is not logged in, redirect to login page 
			if(!isLoggedIn){
				response.sendRedirect("/evaluatorlogin");
				return false;
			}			
		}
		
		//Admin pages
				if(adminAuthPages.contains(request.getRequestURI())){
					boolean isLoggedIn = false;
					Integer adminId = (Integer) request.getSession().getAttribute(AbstractController.officialSessionKey);
					Official admin = officialDao.findByUid(adminId);
					
					if(adminId != null){
						if(admin != null){
							isLoggedIn = true;
						}
					}
					
					//check that official is an evaluator, not an admin or official
					if(!admin.isAdmin){
						response.sendRedirect("/index");
						return false;
					}
					
					//if user is not logged in, redirect to login page 
					if(!isLoggedIn){
						response.sendRedirect("/adminlogin");
						return false;
					}			
				}
			
		return true;
		
	}
	

}
