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
	private int offLevel; //official's level, must be between 1 - 4, NOTE:  Admin and Evaluator don't need so default to "0"
	private String evaluationDate;
	private String evaluationLocation;
	private String gameLevel; //ie pee wee, High School Varsity, etc.
	//evaluation criteria, int = rating, String = comments
	private double appearance;
	private String appearanceComment;
	private double skating;
	private String skatingComment;
	private double positioning;
	private String positioningComment;
	private double ruleKnowledge;
	private String ruleKnowledgeComment;
	private double communication;
	private String communicationComment;
	private String generalComments;
	private double totalScore;

	public EvaluationInput() {} //no arg constructor so hibernate will work

	public EvaluationInput (String officialFirstName, String officialLastName, int offUid, int offLevel, String evaluationDate, 
			String evaluationLocation, String gameLevel, double appearance, String appearanceComment,
			double skating, String skatingComment, double positioning, String positioningComment, double ruleKnowledge, String ruleKnowLedgeComment,
			double communication, String communicationComment, String generalComments){

		super();

		this.officialFirstName = officialFirstName;
		this.officialLastName = officialLastName;
		this.offUid = offUid;
		this.offLevel = offLevel;
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
		this.communicationComment = communicationComment;
		this.generalComments = generalComments;
		this.totalScore = appearance + skating + positioning + ruleKnowledge + communication;
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
	@Column(name = "offLevel")
	public int getOffLevel(){
		return offLevel;
	}
	
	public void setOffLevel(int offLevel){
		this.offLevel = offLevel;
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
	public double getAppearance() {
		return appearance;
	}

	public void setAppearance(double appearance) {
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
	public double getSkating(){
		return skating;
	}

	public void setSkating(double skating){
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
	public double getPositioning() {
		return positioning;
	}

	public void setPositioning(double positioning) {
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
	public double getRuleKnowledge() {
		return ruleKnowledge;
	}

	public void setRuleKnowledge(double ruleKnowledge) {
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
	public double getCommunication() {
		return communication;
	}

	public void setCommunication(double communication) {
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
	
	@Column(name = "score")
	public double getTotalScore(){
		return totalScore;
	}
	
	public void setTotalScore(double totalScore){
		this.totalScore = totalScore;
	}
	
}
