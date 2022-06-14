package com.question;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name="lu_quiz_question")
public class QuestionBean {

	// Field Name
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="question_id")
	private int questionId;
	@Column(name="subject_id")
	private int subjectId;
	@Column(name="question")
	private String question;
	@Column(name="que_status")
	private Integer queStatus;
	@Column(name="status")
	private Integer status;
	@Column(name="created_on")
	private Date createdOn ;
	
	@Nationalized
	@Transient
	List<AnswerBean> ansList;
	
	@Transient 
	private String subjectName;

	
	@Transient
	private int correctAnswerId;
	
	@Transient
	private String correctAns;
	
	
	
	// Getter and Setters
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Integer getQueStatus() {
		return queStatus;
	}
	public void setQueStatus(Integer questionStatus) {
		this.queStatus = questionStatus;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public List<AnswerBean> getAnsList() {
		return ansList;
	}
	public void setAnsList(List<AnswerBean> ansList) {
		this.ansList = ansList;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getCorrectAnswerId() {
		return correctAnswerId;
	}
	public void setCorrectAnswerId(int correctAnswerId) {
		this.correctAnswerId = correctAnswerId;
	}
	@Override
	public String toString() {
		return "QuestionMasterBean [questionId=" + questionId + ", subjectId=" + subjectId + ", question=" + question
				+ ", questionStatus=" + queStatus + ", status=" + status + ", createdOn=" + createdOn + "]";
	}
	public String getCorrectAns() {
		return correctAns;
	}
	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}
	
}
