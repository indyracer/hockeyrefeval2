package org.launchcode.refeval.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "official")
public class Official extends AbstractUser {

	private int level; //certification level
	

	//no arg constructor so Hibernate will work
	public Official() {}

	public Official(String firstName, String lastName, String username, String password, int level){

		super();

		this.level = level;
	}
	
	@NotNull
	@Column(name = "level")
	public int getLevel(){
		return this.level;
	}

	
	public void setLevel(int level) {
		this.level = level;
	}

	
	
	//password meets minimum standards
	public static boolean isValidPassword(String password) {
		Pattern validPasswordPattern = Pattern.compile("(\\S){6,20}");
		Matcher matcher = validPasswordPattern.matcher(password);
		return matcher.matches();
	}
	
	
	public boolean isMatchingPassword(String password){
		return encoder.matches(password, pwHash);
	}
	
	//check that username meets minimum standards
	public static boolean isValidUsername(String username) {
		Pattern validUserNamePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]{4,15}");
		Matcher matcher = validUserNamePattern.matcher(username);
		return matcher.matches();
	}
	
	//need to add methods so official can view their evaluations, make another change
}
