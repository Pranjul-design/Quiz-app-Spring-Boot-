package com.subject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SubjectDAO extends JpaRepository<SubjectBean,Integer> {
	public SubjectBean findBySubjectId(int subjectId);
	public List<SubjectBean> findByStatusOrderBySubjectIdDesc(int status);
	public List<SubjectBean> findBySubjectStatusAndStatusOrderBySubjectIdDesc(int subStatus,int status);
}
