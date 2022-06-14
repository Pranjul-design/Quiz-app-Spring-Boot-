package com.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerDAO extends JpaRepository<AnswerBean, Integer> {

	List<AnswerBean> findByQuestionId(int questionId);
	public AnswerBean findByAnswerId(int questionId);
	public AnswerBean findByQuestionIdAndAnswer(int queId,String anstext);
}
