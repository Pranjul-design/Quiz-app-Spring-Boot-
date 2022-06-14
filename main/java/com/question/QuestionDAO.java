package com.question;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface QuestionDAO extends JpaRepository<QuestionBean, Long> {
	
	public List<QuestionBean> findByQuestionId(int id);
	public List<QuestionBean> findBySubjectIdAndStatusOrderByQuestionIdDesc(int id,int status);
	public QuestionBean findByQuestionIdOrderByQuestionIdDesc(int queId,int limit,int offset);
	public List<QuestionBean> findByStatusOrderByQuestionIdDesc(int status);
	public List<QuestionBean> findByQueStatusAndStatusOrderByQuestionIdAsc(int queStatus,int status);
	public List<QuestionBean> findByQueStatusAndStatusAndSubjectIdOrderByQuestionIdAsc(int queStatus,int status,int subId);
	
	//searching query
	public List<QuestionBean> findByQuestionContainingAndQueStatusAndStatusOrderByQuestionIdAsc(String keyword,int queStatus,int status);
	public List<QuestionBean> findByQuestionContainingAndSubjectIdAndQueStatusAndStatusOrderByQuestionIdAsc(String keyword,Integer subId,int queStatus,int status);
	
	
	public List<QuestionBean> findByStatusOrderByQuestionIdDesc(int status,Pageable pageable);
	
}
