package org.launchcode.refeval.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="evalrequests")
public class EvaluationRequest extends AbstractUser{
	
	
	
	private String firstName;
	private String lastName;
	private Official official;//used to be able to pull evals for specific official
	private String fullName;
	private String date;
	private String time;
	private String location;
	
	//no arg constructor for hibernate
	public EvaluationRequest(){}
	
	public EvaluationRequest (String firstName, String lastName, Official official, String date, String time, String location){
		
		
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.official = official;
		this.fullName = firstName + "" + lastName;
		this.date = date;
		this.time = time;
		this.location = location;
		
		
	}
	
	
	
	
	@NotNull
	@Column(name = "firstName")
	public String getFirstName(){
		return this.firstName;
	}
	
	protected void setFirstName(String firstName){
		this.firstName= firstName;
	}
	
	@NotNull
	@Column(name = "lastName")
	public String getLastName(){
		return this.lastName;
	}
	
	protected void setLastName(String lastName){
		this.lastName= lastName;
	}
	
	
	@ManyToOne
	public Official getOfficial(){
		return official;
	}
	
	protected void setOfficial(Official official){
		this.official = official;
	}
	
	@NotNull
	@Column(name = "fullName")
	public String getFullName(){
		return this.fullName;
	}
	
	protected void setFullName(String fullName){
		this.fullName = fullName;
	}
	
	@NotNull
	@Column(name = "date")
	public String getDate(){
		return this.date;
	}
	
	protected void setDate(String date){
		this.date = date;
	}
	
	@NotNull
	@Column(name = "time")
	public String getTime(){
		return this.time;
	}
	
	protected void setTime(String time){
		this.time = time;
	}
	
	@NotNull
	@Column(name = "location")
	public String getLocation(){
		return this.location;
	}
	
	protected void setLocation(String location){
		this.location = location;
	}

	

}
