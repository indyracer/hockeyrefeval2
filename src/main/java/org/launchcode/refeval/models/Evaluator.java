package org.launchcode.refeval.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*
@Entity
@Table(name="evaluator")
public class Evaluator extends AbstractUser{
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	protected String pwHash;
	protected static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	
	//no arg constructor for Hibernate
	public Evaluator(){
		
	}
	
	public Evaluator (String firstName, String lastName, String username, String password){
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.pwHash = hashPassword(password);
	}
	
	@NotNull
	@Column(name = "firstName")
	public String getFirstName(){
		return this.firstName;
	}

	
	protected void setFirstName(String firstName){
		this.firstName = firstName;
	}

	@NotNull
	@Column(name = "lastName")
	public String getLastName(){
		return this.lastName;
	}

	protected void setLastName (String lastName){
		this.lastName = lastName;
	}

	@NotNull
	@Column(name = "username", unique = true)
	public String getUsername(){
		return this.username;
	}

	
	protected void setUsername (String username){
		this.username = username;
	}
	
	@NotNull
	@Column(name="isEvaluator")
	public boolean IsEvaluator(){
		return true;
	}
	
	
	private static String hashPassword(String password) {		
		return encoder.encode(password);
	}
	
	//set up methods to review official's previous evaluations
	
	//set up methods to input evaluations into db

}
*/
