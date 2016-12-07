package org.launchcode.refeval.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "evaluations")
public class EvaluationInput extends AbstractUser{
	
	//evaluated official and game information
	private String officialFirstName;
	private String officialLastName;
	private String evaluationDate;
	private String evaluationLocation;
	private String ageLevel; //ie pee wee, High School Varsity, etc.
	//evaluation criteria, int = rating, String = comments
	private int appearance;
	private String appearanceComment;
	private int positioning;
	private String positioningComment;
	private int ruleKnowledge;
	private String ruleKnowledgeComment;
	private int communication;
	private String communicationComment;
	private String generalComments;
	
	public EvaluationInput() {} //no arg constructor so hibernate will work
	
	public EvaluationInput (String officialFirstName, String officialLastName, String evaluationDate, 
							String evaluationLocation, String ageLevel, int appearance, String appearanceComment,
							int positioning, String positioningComment, int ruleKnowledge, String ruleKnowLedgeComment,
							int communication, String communicationComment, String generalComments){
		
		super();
		
		this.officialFirstName = officialFirstName;
		this.officialLastName = officialLastName;
		this.evaluationDate = evaluationDate;
		this.evaluationLocation = evaluationLocation;
		this.ageLevel = ageLevel;
		this.appearance = appearance;
		this.appearanceComment = appearanceComment;
		this.positioning = positioning;
		this.positioningComment = positioningComment;
		this.ruleKnowledge = ruleKnowledge;
		this.ruleKnowledgeComment= ruleKnowLedgeComment;
		this.communication = communication;
		this.generalComments = generalComments;
		
		
		
	}
	@NotNull
	@Column(name = "officialFirstName")
	public String getOfficialFirstName() {
		return officialFirstName;
	}
	
	public void setOfficialFirstName(String officialFirstName) {
		this.officialFirstName = officialFirstName;
	}
	
	@NotNull
	@Column(name = "officialLastName")
	public String getOfficialLastName() {
		return officialLastName;
	}
	
	public void setOfficialLastName(String officialLastName) {
		this.officialLastName = officialLastName;
	}

	@NotNull
	@Column(name = "evaluationDate")
	public String getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(String evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	@NotNull
	@Column(name = "evaluationLocation")
	public String getEvaluationLocation() {
		return evaluationLocation;
	}

	public void setEvaluationLocation(String evaluationLocation) {
		this.evaluationLocation = evaluationLocation;
	}

	@NotNull
	@Column(name = "ageLevel")
	public String getAgeLevel() {
		return ageLevel;
	}

	public void setAgeLevel(String ageLevel) {
		this.ageLevel = ageLevel;
	}

	@NotNull
	@Column(name = "appearance")
	public int getAppearance() {
		return appearance;
	}

	public void setAppearance(int appearance) {
		this.appearance = appearance;
	}

	
	@Column(name = "appearanceComment")
	public String getApperanceComment() {
		return appearanceComment;
	}

	public void setApperanceComment(String apperanceComment) {
		this.appearanceComment = appearanceComment;
	}
	
	@NotNull
	@Column(name = "postioning")
	public int getPositioning() {
		return positioning;
	}

	public void setPositioning(int positioning) {
		this.positioning = positioning;
	}

	@Column (name = "positioningComment")
	public String getPositioningComment() {
		return positioningComment;
	}

	public void setPositioningComment(String positioningComment) {
		this.positioningComment = positioningComment;
	}

	@NotNull
	@Column (name = "ruleKnowledge")
	public int getRuleKnowledge() {
		return ruleKnowledge;
	}

	public void setRuleKnowledge(int ruleKnowledge) {
		this.ruleKnowledge = ruleKnowledge;
	}

	@Column (name = "ruleKnowledgeComment")
	public String getRuleKnowledgeComment() {
		return ruleKnowledgeComment;
	}

	public void setRuleKonwledgeComment(String ruleKonwledgeComment) {
		this.ruleKnowledgeComment = ruleKnowledgeComment;
	}

	@NotNull
	@Column (name = "communication")
	public int getCommunication() {
		return communication;
	}

	public void setCommunication(int communication) {
		this.communication = communication;
	}

	@Column(name = "communicationComment")
	public String getCommunicationComment() {
		return communicationComment;
	}

	public void setCommunicationComment(String communicationComment) {
		this.communicationComment = communicationComment;
	}
	
	@Column(name = "generalComments")
	public String getGeneralComments(){
		return generalComments;
	}
	
	public void setGeneralComments(String generalComments){
		this.generalComments = generalComments;
	}
	
	
}
