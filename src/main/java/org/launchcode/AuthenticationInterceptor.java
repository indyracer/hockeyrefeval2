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
	//need to create once Dao files created
	@Autowired
	OfficialDao officialDao;
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		//list of restricted Official URLs
		List<String> officialAuthPages = Arrays.asList("officialhome", "officialevaluationrequest", "adminhome", "adminevalsetup");
		
		if(officialAuthPages.contains(request.getRequestURI())){
			boolean isLoggedIn = false;
			Official official;
			Integer officialId = (Integer) request.getSession().getAttribute(AbstractController.officialSessionKey);
			
			if(officialId != null){
				official = officialDao.findByUid(officialId);
				
				if(official != null){
					isLoggedIn = true;
				}
			}
			//if user is not logged in, redirect to login page
			if(!isLoggedIn){
				response.sendRedirect("/officiallogin");
				return false;
			}
		}
		
		return true;
		
	}
	

}
