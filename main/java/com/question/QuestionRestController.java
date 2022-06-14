package com.question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.subject.SubjectBean;
import com.subject.SubjectDAO;

@RestController
@RequestMapping("/api")
public class QuestionRestController {
	
	@Autowired
	private QuestionDAO questionDao;
	@Autowired
	private AnswerDAO answerDao;
	
	@Autowired 
	private SubjectDAO subjectDao;
	
	@Autowired 
	private CorrectAnswerDAO correctAnswerDao;
	   
	@GetMapping("/question")
	public String questionmaster() {
		List<QuestionBean> queList = questionDao.findByQueStatusAndStatusOrderByQuestionIdAsc(1,1);
		try {
		for(QuestionBean bean : queList) {
			SubjectBean tempSubjectName = subjectDao.findBySubjectId(bean.getSubjectId());
			CorrectAnswerBean  tempCorrectAnswer = correctAnswerDao.findByQuestionId(bean.getQuestionId());
			bean.setCorrectAnswerId(tempCorrectAnswer.getAnswerId());
			bean.setSubjectName(tempSubjectName.getSubjectName());
			bean.setAnsList(answerDao.findByQuestionId(bean.getQuestionId()));
		}
		}catch(NullPointerException e) {
			
		}
		JSONObject jsonStatus = new JSONObject();
		try {
		if(queList.size()>0) {
			jsonStatus.put("status", 200);
			jsonStatus.put("message", "success");
			jsonStatus.put("data", queList);
		}else {
			jsonStatus.put("status", "400");
			jsonStatus.put("message", "Failure");
			jsonStatus.put("data", "");	
		}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return jsonStatus.toString(); 
	}
	
	@GetMapping("/question/{id}")
	public String showPage(@PathVariable int id) {
		List<QuestionBean> queList = questionDao.findByQuestionId(id);
		try {
		for(QuestionBean bean : queList) {
			SubjectBean tempSubjectName = subjectDao.findBySubjectId(bean.getSubjectId());
			CorrectAnswerBean  tempCorrectAnswer = correctAnswerDao.findByQuestionId(bean.getQuestionId());
			bean.setCorrectAnswerId(tempCorrectAnswer.getAnswerId());
			bean.setSubjectName(tempSubjectName.getSubjectName());
			bean.setAnsList(answerDao.findByQuestionId(bean.getQuestionId()));	
		}
		}catch(NullPointerException e) {
			
		}
			JSONObject jsonStatus = new JSONObject();
			try {
			
			if(queList.size()>0) {
				jsonStatus.put("status", 200);
				jsonStatus.put("message", "success");
				jsonStatus.put("data", queList);
			}else {
				jsonStatus.put("status", "400");
				jsonStatus.put("message", "Failure");
				jsonStatus.put("data", "");	
			}
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			return jsonStatus.toString();
	}
	
	
	@GetMapping("/questionsbysubject/{id}")
	public List<QuestionBean> subjectDetails(@PathVariable int id) {
		List<QuestionBean> queList = questionDao.findBySubjectIdAndStatusOrderByQuestionIdDesc(id,1);
		try {
		for(QuestionBean bean : queList) {
			SubjectBean tempSubjectName = subjectDao.findBySubjectId(bean.getSubjectId());
			bean.setSubjectName(tempSubjectName.getSubjectName());
			bean.setAnsList(answerDao.findByQuestionId(bean.getQuestionId()));
			CorrectAnswerBean  tempCorrectAnswer = correctAnswerDao.findByQuestionId(bean.getQuestionId());
			bean.setCorrectAnswerId(tempCorrectAnswer.getAnswerId());
			System.out.println("CorrectAnswer: " + tempCorrectAnswer.getAnswerId());
			//bean.setCorrectAnswerId(tempCorrectAnswer.getCorrectAnswerId());
		}
		} catch(NullPointerException e) {
			
		}
			return queList;
	}
	
	@GetMapping("/subject/{id}/questionlimit/{count}")
	public String questionWithSubject(@PathVariable int id,@PathVariable int count ){
			List<QuestionBean> tempQueList = questionDao.findByQueStatusAndStatusAndSubjectIdOrderByQuestionIdAsc(1,1,id);
			List<QuestionBean> questionList = new ArrayList<>();
			try {
				for(QuestionBean tempbean : tempQueList) {
					QuestionBean bean = new QuestionBean();
					bean.setQuestionId(tempbean.getQuestionId());
					bean.setQuestion(tempbean.getQuestion());
					bean.setCorrectAnswerId(correctAnswerDao.findByQuestionId(tempbean.getQuestionId()).getAnswerId());
					SubjectBean tempSubjectBean = subjectDao.findBySubjectId(tempbean.getSubjectId());
					bean.setSubjectName(tempSubjectBean.getSubjectName());
					bean.setSubjectId(tempbean.getSubjectId());
					List<AnswerBean> tempAnsList = answerDao.findByQuestionId(tempbean.getQuestionId());
					List<AnswerBean> AnswerList = new ArrayList<>();
					for(AnswerBean tempAnsbean : tempAnsList) {
						AnswerBean ansbean = new AnswerBean();
						ansbean.setQuestionId(tempAnsbean.getQuestionId());
						ansbean.setAnswerId(tempAnsbean.getAnswerId());
						ansbean.setAnswer(tempAnsbean.getAnswer());
						AnswerList.add(ansbean);
					}
					bean.setAnsList(AnswerList);
					questionList.add(bean);
				}
			} catch (NullPointerException e) {
				// TODO: handle exception
				
			}
			List<QuestionBean> jsonQueList = questionList.stream().limit(count).collect(Collectors.toList());
			JSONObject jsonStatus = new JSONObject();
			
			try {
			if(jsonQueList.size()>0) {
				jsonStatus.put("status", 200);
				jsonStatus.put("message", "success");
				jsonStatus.put("data", jsonQueList);
			}else {
				jsonStatus.put("status", "400");
				jsonStatus.put("message", "Failure");
				jsonStatus.put("data", "");	
			}
			} catch(Exception e) {
				
			}
			
			return jsonStatus.toString();
}
	
	
	//random mode quiz API
	@GetMapping("/random/questionlimit/{count}")
	public String randomQuiz(@PathVariable int count ){
		List<SubjectBean> subList = subjectDao.findBySubjectStatusAndStatusOrderBySubjectIdDesc(1, 1);
		List<QuestionBean> jsonQueList  = new ArrayList<>();
		try {
		for(SubjectBean sBean : subList) {
			List<QuestionBean> tempQueList = questionDao.findByQueStatusAndStatusAndSubjectIdOrderByQuestionIdAsc(1,1,sBean.getSubjectId());
			List<QuestionBean> questionList = new ArrayList<>();
			for(QuestionBean tempbean : tempQueList) {
				QuestionBean bean = new QuestionBean();
				bean.setQuestionId(tempbean.getQuestionId());
				bean.setQuestion(tempbean.getQuestion());
				bean.setCorrectAnswerId(correctAnswerDao.findByQuestionId(tempbean.getQuestionId()).getAnswerId());
				SubjectBean tempSubjectBean = subjectDao.findBySubjectId(tempbean.getSubjectId());
				bean.setSubjectName(tempSubjectBean.getSubjectName());
				bean.setSubjectId(tempbean.getSubjectId());
				List<AnswerBean> tempAnsList = answerDao.findByQuestionId(tempbean.getQuestionId());
				List<AnswerBean> AnswerList = new ArrayList<>();
				for(AnswerBean tempAnsbean : tempAnsList) {
					AnswerBean ansbean = new AnswerBean();
					ansbean.setQuestionId(tempAnsbean.getQuestionId());
					ansbean.setAnswerId(tempAnsbean.getAnswerId());
					ansbean.setAnswer(tempAnsbean.getAnswer());
					AnswerList.add(ansbean);
				}
				bean.setAnsList(AnswerList);
				questionList.add(bean);
			}
		List<QuestionBean> jsonsubjectWiseQueList = questionList.stream().limit(count).collect(Collectors.toList());
		jsonQueList.addAll(jsonsubjectWiseQueList);
		}
		} catch (NullPointerException e) {
			// TODO: handle exception
			
		}
			JSONObject jsonStatus = new JSONObject();
			try {
		
			if(jsonQueList.size()>0) {
				jsonStatus.put("status", 200);
				jsonStatus.put("message", "success");
				jsonStatus.put("data", jsonQueList);
			}else {
				jsonStatus.put("status", "400");
				jsonStatus.put("message", "Failure");
				jsonStatus.put("data", "");	
			}
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			return jsonStatus.toString();
}
}
