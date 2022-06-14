package com.quizsetting;


import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSettingDAO extends JpaRepository<QuizSettingBean,Integer> {
	
	public int countByQuizSettingId(int id);
	public QuizSettingBean findByQuizSettingId(int id);

}
