package com.quizsetting;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizSettingService {
	
	@Autowired 
	private QuizSettingDAO quizSettingDao;
	
	public QuizSettingBean findByID(Integer id){
		return quizSettingDao.findByQuizSettingId(id);
	}
	
	public QuizSettingBean update(Integer id,QuizSettingBean newSetting) {
		QuizSettingBean bean =  quizSettingDao.findByQuizSettingId(id);
			bean.setQuizTime(newSetting.getQuizTime());
			bean.setQuizModeCount(newSetting.getQuizModeCount());
			bean.setRandomModeCount(newSetting.getRandomModeCount());
			return quizSettingDao.save(bean);
	}
	

}
