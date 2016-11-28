package org.launchcode.refeval.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="evaluator")
public class Evaluator extends AbstractUser{
	
	//no arg constructor for Hibernate
	public Evaluator(){
		
	}
	
	public Evaluator (String firstName, String lastName, String username, String password){
		super();
	}
	
	

	
	//set up methods to review official's previous evaluations
	
	//set up methods to input evaluations into db

}
