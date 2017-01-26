package org.launchcode.refeval.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "official")
public class Official extends AbstractUser {

	private String firstName;
	private String lastName;
	private String username;
	protected String pwHash;
	protected static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	private int level; //certification level
	public boolean isOfficial;
	public boolean isEvaluator;
	public boolean isAdmin;



	//no arg constructor so Hibernate will work
	public Official() {}

	public Official(String firstName, String lastName, String username, String password, int level, boolean isOfficial, boolean isEvaluator, boolean isAdmin){

		super();

		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.pwHash = hashPassword(password);
		this.level = level;	
		this.isAdmin = isAdmin;
		this.isOfficial = isOfficial;
		this.isEvaluator = isEvaluator;
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
    @Column(name = "pwhash")
	public String getPwHash() {
		return pwHash;
	}
	
	@SuppressWarnings("unused")
	private void setPwHash(String pwHash) {
		this.pwHash = pwHash;
	}

	@NotNull
	@Column(name = "level")
	public int getLevel(){
		return this.level;
	}


	public void setLevel(int level) {
		this.level = level;
	}

	@NotNull
	@Column(name="isOfficial")
	public boolean getIsOfficial(){
		return this.isOfficial;
	}

	public void setIsOfficial(boolean x){
		this.isOfficial = x;
	}

	@NotNull
	@Column(name="isEvaluator")
	public boolean getIsEvaluator(){
		return this.isEvaluator;
	}

	public void setIsEvaluator(boolean x){
		this.isEvaluator= x;
	}
	

	@NotNull
	@Column(name="isAdmin")
	public boolean getIsAdmin(){
		return this.isAdmin;
	}

	public void setIsAdmin(boolean x){
		this.isAdmin = x;
	}


	private static String hashPassword(String password) {		
		return encoder.encode(password);
	}


	//password meets minimum standards, must be between 6 and 20 characters
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

}
