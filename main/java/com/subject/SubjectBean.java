package com.subject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="lu_quiz_subject")
public class SubjectBean {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subject_id")
	private int subjectId;
	
	@Column(name="subject_name")
	private String subjectName;
	
	@Column(name="subject_status")
	private Integer subjectStatus;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="created_on")
	private Date createdOn;
	
	
	//** getter setter

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getSubjectStatus() {
		return subjectStatus;
	}

	public void setSubjectStatus(Integer subjectStatus) {
		this.subjectStatus = subjectStatus;
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
