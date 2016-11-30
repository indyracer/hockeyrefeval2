package org.launchcode.refeval.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="evalrequests")
public class EvaluationRequest {
	
	private int requestId;
	private String username;
	private String date;
	private String time;
	private String location;
	
	//no arg constructor for hibernate
	public EvaluationRequest(){}
	
	public EvaluationRequest(String username, String date, String time, String location){
		
		this.requestId = requestId;
		this.username = username;
		this.date = date;
		this.time = time;
		this.location = location;
		
		
	}
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "requestId", unique = true)
	public int getRequestId(){
		return this.requestId;
	}
	
	protected void setRequestId(int requestId){
		this.requestId = requestId;
	}
	
	@NotNull
	@Column(name = "username")
	public String getUsername(){
		return this.username;
	}
	
	protected void setUsername(String username){
		this.username = username;
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
