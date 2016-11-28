package org.launchcode.refeval.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends AbstractUser{
	
	//No arg constructor for Hibernate
	public Admin(){}
	
	public Admin(String firstName, String lastName, String username, String password){
		super();
	}
	
	
	
	//setup methods to add evaluators to evaluator db
	
	
	

}
