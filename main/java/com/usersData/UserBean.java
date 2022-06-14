package com.usersData;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trx_users_question_answer")
public class UserBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="que_ans_id")
	private int queAnsId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="subject_id")
	private String subjectId;
	
	@Column(name="question_id")
	private int questionId;
	
	@Column(name="answer_id")
	private int answerId;
	
	@Column(name="status")
	private Integer status = 1;
	
	@Column(name="created_on")
	private Date createdOn = new Date();

	public int getQueAnsId() {
		return queAnsId;
	}

	public void setQueAnsId(int queAnsId) {
		this.queAnsId = queAnsId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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
