package com.quizsetting;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.AnswerDAO;
import com.question.CorrectAnswerBean;
import com.question.CorrectAnswerDAO;
import com.question.QuestionBean;
import com.question.QuestionDAO;
import com.subject.SubjectBean;
import com.subject.SubjectDAO;

@RestController
@RequestMapping("/api")
public class QuizSettingRestController {
	
	@Autowired
	private QuizSettingDAO quizSettingDao;
	@Autowired
	private QuestionDAO questionDao;
	@Autowired 
	private SubjectDAO subjectDao;
	@Autowired
	private AnswerDAO answerDao;
	@Autowired
	private CorrectAnswerDAO correctAnswerDao;
	
	@GetMapping("/quizsetting")
	public List<QuizSettingBean> showQuizSetting() {
		return quizSettingDao.findAll();	
	}
	
	@GetMapping("/quizsetting/{id}")
	public QuizSettingBean showQuizSettingById(@PathVariable int id) {
		return quizSettingDao.findByQuizSettingId(id);
	}
	
	//Save QuizSetting
	@PostMapping("addquizsetting")
	public QuizSettingBean addQuizSetting(@RequestBody QuizSettingBean quizSettingBean ) {
		return quizSettingDao.save(quizSettingBean);
	}
	
	//update QuizSetting
	@PutMapping("updatequizsetting/{id}")
	public QuizSettingBean updateQuizSetting(@PathVariable int id,@RequestBody QuizSettingBean quizSettingBean) {
		return update(id,quizSettingBean);
	}
	
	//Getting Appropriate Question by using limit
	@GetMapping("/subjectquestion/{id}")
	public List<QuestionBean> subjectDetails(@PathVariable int id) {
		List<QuestionBean> queList = questionDao.findBySubjectIdAndStatusOrderByQuestionIdDesc(id,1);
		QuizSettingBean tempBean = quizSettingDao.findByQuizSettingId(3);
		int limit = tempBean.getQuizModeCount();
		try {
		for(QuestionBean bean : queList) {			
			SubjectBean tempSubjectBean=subjectDao.findBySubjectId(bean.getSubjectId());
			CorrectAnswerBean  tempCorrectAnswer = correctAnswerDao.findByQuestionId(bean.getQuestionId());
			bean.setSubjectName(tempSubjectBean.getSubjectName());
			bean.setAnsList(answerDao.findByQuestionId(bean.getQuestionId()));
			bean.setCorrectAnswerId(tempCorrectAnswer.getAnswerId());
		}
		}catch(NullPointerException e) {
			
		}
		//limit definition
		return queList.stream().limit(limit).collect(Collectors.toList());
	}
	
	//method for update
	public QuizSettingBean update(int id, QuizSettingBean quizSettingBean) {
		QuizSettingBean updateDetails = quizSettingDao.findByQuizSettingId(id);
		updateDetails.setQuizTime(quizSettingBean.getQuizTime());
		updateDetails.setQuizModeCount(quizSettingBean.getQuizModeCount());
		updateDetails.setRandomModeCount(quizSettingBean.getRandomModeCount());
		return quizSettingDao.save(updateDetails);	
	}
}
