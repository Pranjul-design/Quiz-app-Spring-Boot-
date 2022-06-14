package com.quizsetting;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lu_quiz_setting")
public class QuizSettingBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="quiz_setting_id")
	private int quizSettingId;
	
	@Column(name="quiz_time")
	private int quizTime; 
	
	@Column(name="quiz_mode_que_count")
	private int quizModeCount;
	
	@Column(name="random_mode_que_count")
	private int randomModeCount;
	
	@Column(name="status")
	private Integer status=1;
	
	@Column(name="created_on")
	private Date newDate = new Date();
	
	
	//Getters And Setters

	public int getQuizSettingId() {
		return quizSettingId;
	}

	public void setQuizSettingId(int quizSettingId) {
		this.quizSettingId = quizSettingId;
	}

	public int getQuizTime() {
		return quizTime;
	}

	public void setQuizTime(int quizTime) {
		this.quizTime = quizTime;
	}

	public int getQuizModeCount() {
		return quizModeCount;
	}

	public void setQuizModeCount(int quizModeCount) {
		this.quizModeCount = quizModeCount;
	}

	public int getRandomModeCount() {
		return randomModeCount;
	}

	public void setRandomModeCount(int randomModeCount) {
		this.randomModeCount = randomModeCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getNewDate() {
		return newDate;
	}

	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}
	
	
	
	
}
