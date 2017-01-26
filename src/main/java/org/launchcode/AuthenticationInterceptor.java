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
		List<String> officialAuthPages = Arrays.asList("/officialhome", "/officialevaluationrequest", "/officialevaluationrequestconfirm","/officialevaluation");
		List<String> evaluatorAuthPages = Arrays.asList("/evaluatorhome", "/evaluatorevalinput", "/evaluatorevalrequest");
		List<String> adminAuthPages = Arrays.asList("/adminhome", "/adminreports", "/adminreportsaverages", "/adminreportsbycriteria", "/adminsetup", "/adminevalsetup");

		//for pages in the following list, must be logged in to access
		if(officialAuthPages.contains(request.getRequestURI()) || evaluatorAuthPages.contains(request.getRequestURI()) || adminAuthPages.contains(request.getRequestURI())){

			boolean isLoggedIn = false;
			Official official;
			Integer officialId = (Integer) request.getSession().getAttribute(AbstractController.officialSessionKey);
			
			//checks if user is logged in by looking for a officialSessioKey, if null, not logged in and redirects to error message
			if(officialId == null){
				response.sendRedirect("403login");
				return false;
			} else
				//If logged in, find the user by using the officialId field
			{
				official = officialDao.findByUid(officialId);
			}
			
			//confirms that user is a valid user:  has an officialId and is found in the database
			if(officialId != null){
				if(official != null){
					isLoggedIn = true;
				}

				//sends back to log in page
				if(!isLoggedIn){
					response.sendRedirect("/");
					return false;
				}

			}
			
			
			
			//check that user has access to appropriate pages
			//if not authorized, send to page stating "Access Denied"
			
			
			//Official Pages			
			if(officialAuthPages.contains(request.getRequestURI())){
				if(official.isOfficial == false){
					response.sendRedirect("403forbidden");
					return false;
				} 
			}
			
			//Evaluator Pages
			if(evaluatorAuthPages.contains(request.getRequestURI())){
				if(official.isEvaluator == false){
					response.sendRedirect("403forbidden");
					return false;
				} 
			}
			
			//Admin Pages
			if(adminAuthPages.contains(request.getRequestURI())){
				if(official.isAdmin == false){
					response.sendRedirect("403forbidden");
					return false;
				} 
			}

		}

		return true;
	}
}
