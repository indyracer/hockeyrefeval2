package org.launchcode.refeval.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*
@Entity
@Table(name = "admin")
public class Admin extends AbstractUser{
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	protected String pwHash;
	protected static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	
	//No arg constructor for Hibernate
	public Admin(){}
	
	public Admin(String firstName, String lastName, String username, String password){
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
	@Column(name="isAdmin")
	public boolean IsAdmin(){
		return true;
	}
	
	
	private static String hashPassword(String password) {		
		return encoder.encode(password);
	}
	
	
	
	//setup methods to add evaluators to evaluator db
	
	
	

}
*/
