package com.question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrectAnswerDAO extends JpaRepository<CorrectAnswerBean,Integer> {
	public CorrectAnswerBean findByQuestionId(int queId);
}
