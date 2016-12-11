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
	private int offUid; //official's UID so they can pull up evaluations
	private String evaluationDate;
	private String evaluationLocation;
	private String gameLevel; //ie pee wee, High School Varsity, etc.
	//evaluation criteria, int = rating, String = comments
	private int appearance;
	private String appearanceComment;
	private int skating;
	private String skatingComment;
	private int positioning;
	private String positioningComment;
	private int ruleKnowledge;
	private String ruleKnowledgeComment;
	private int communication;
	private String communicationComment;
	private String generalComments;
	
	public EvaluationInput() {} //no arg constructor so hibernate will work
	
	public EvaluationInput (String officialFirstName, String officialLastName, int offUid, String evaluationDate, 
							String evaluationLocation, String gameLevel, int appearance, String appearanceComment,
							int skating, String skatingComment, int positioning, String positioningComment, int ruleKnowledge, String ruleKnowLedgeComment,
							int communication, String communicationComment, String generalComments){
		
		super();
		
		this.officialFirstName = officialFirstName;
		this.officialLastName = officialLastName;
		this.offUid = offUid;
		this.evaluationDate = evaluationDate;
		this.evaluationLocation = evaluationLocation;
		this.gameLevel = gameLevel;
		this.appearance = appearance;
		this.appearanceComment = appearanceComment;
		this.skating = skating;
		this.skatingComment = skatingComment;
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
	@Column(name = "offuid")
	public int getOffUid(){
		return offUid;
	}
	
	public void setOffUid(int offUid){
		this.offUid = offUid;
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
	@Column(name = "gameLevel")
	public String getGameLevel() {
		return gameLevel;
	}

	public void setGameLevel(String gameLevel) {
		this.gameLevel = gameLevel;
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
	public String getAppearanceComment() {
		return appearanceComment;
	}

	public void setAppearanceComment(String appearanceComment) {
		this.appearanceComment = appearanceComment;
	}
	
	@NotNull
	@Column(name = "skating")
	public int getSkating(){
		return skating;
	}
	
	public void setSkating(int skating){
		this.skating = skating;
	}
	
	@Column(name = "skatingComment")
	public String getSkatingComment(){
		return skatingComment;
	}
	
	public void setSkatingComment(String skatingComment){
		this.skatingComment = skatingComment;
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

	public void setRuleKnowledgeComment(String ruleKnowledgeComment) {
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
