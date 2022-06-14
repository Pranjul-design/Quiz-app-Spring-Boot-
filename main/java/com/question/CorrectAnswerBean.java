package com.question;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lu_quiz_correct_answer")
public class CorrectAnswerBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="correct_answer_id")
	private int correctAnswerId;
	
	@Column(name="question_id")
	private int questionId;
	
	@Column(name="answer_id")
	private int answerId;
	
	@Column(name="status")
	private Integer status=1;
	
	@Column(name="created_on")
	private Date createdOn = new Date();
	
	public CorrectAnswerBean() {
		
	}

	public int getCorrectAnswerId() {
		return correctAnswerId;
	}

	public void setCorrectAnswerId(int correctAnswerId) {
		this.correctAnswerId = correctAnswerId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
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
	
	
}
