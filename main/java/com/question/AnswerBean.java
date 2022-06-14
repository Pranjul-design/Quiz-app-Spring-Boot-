package com.question;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lu_quiz_answer")
public class AnswerBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="answer_id")
	private int answerId;
	@Column(name="question_id")
	private int questionId;
	@Column(name="answer")
	private String answer;
	@Column(name="status")
	private Integer status;
	@Column(name="created_on")
	private Date createdOn ;
	
	public AnswerBean() {
		
	}
	
	
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
	@Override
	public String toString() {
		return "AnswerBean [answerId=" + answerId + ", questionId=" + questionId + ", answer=" + answer + ", status="
				+ status + ", createdOn=" + createdOn + "]";
	}

}
