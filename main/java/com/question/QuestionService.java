package com.question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.subject.SubjectBean;
import com.subject.SubjectDAO;

@Service
public class QuestionService {

	@Autowired
	private QuestionDAO questionDao;

	@Autowired
	private AnswerDAO answerDao;

	@Autowired
	private SubjectDAO subjectDao;

	@Autowired
	private CorrectAnswerDAO correctAnswerDao;

	// show list in question master page
	public List<QuestionBean> getQuestionList() {
		
		
		List<QuestionBean> queList = questionDao.findByStatusOrderByQuestionIdDesc(1);
		List<QuestionBean> finalQueList = new ArrayList<>();
		try {
			for (QuestionBean bean : queList) {
				// System.out.println("Question Id :"+bean.getQuestionId());
				QuestionBean finalBean = new QuestionBean();

				SubjectBean tempSubjectName = subjectDao.findBySubjectId(bean.getSubjectId());
				finalBean.setSubjectName(tempSubjectName.getSubjectName());

				CorrectAnswerBean correctAnswerBean = correctAnswerDao.findByQuestionId(bean.getQuestionId());

				if (correctAnswerBean != null) {
					AnswerBean ansBean = new AnswerBean();
					ansBean = answerDao.findByAnswerId(correctAnswerBean.getAnswerId());
					if (ansBean != null) {
						// System.out.println("Correct Answer :" +ansBean.getAnswer());
						finalBean.setCorrectAns(ansBean.getAnswer());

					}
				}

				finalBean.setQuestionId(bean.getQuestionId());
				finalBean.setQuestion(bean.getQuestion());
				finalBean.setQueStatus(bean.getQueStatus());

				finalQueList.add(finalBean);

			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return finalQueList;
	}

	private Sort orderByIdDesc() {
		// TODO Auto-generated method stub
		return new Sort(Sort.Direction.DESC, "questionId");
	}

	// Get subject name list in add questionPage
	public List<SubjectBean> getSubjectName() {
		return subjectDao.findAll();
	}

	// save the question with Answers
	public void save(QuestionBean questionBean) {
		// save question in lu_quiz_question
		questionBean.setQueStatus(1);
		questionBean.setStatus(1);
		questionBean.setCreatedOn(new Date());
		questionDao.save(questionBean);

		QuestionBean tempQueId = questionDao
				.findBySubjectIdAndStatusOrderByQuestionIdDesc(questionBean.getSubjectId(), 1).get(0);

		// save Answer in lu_quiz_answer
		List<AnswerBean> ansList = questionBean.getAnsList();
		for (AnswerBean bean : ansList) {
			bean.setQuestionId(tempQueId.getQuestionId());
			bean.setAnswer(bean.getAnswer());
			bean.setStatus(1);
			bean.setCreatedOn(new Date());
			answerDao.save(bean);
		}
		// answerDao.saveAll(ansList);

		// save correct Answer in lu_quiz_correct_answer
		CorrectAnswerBean correctAnsBean = new CorrectAnswerBean();
		correctAnsBean.setQuestionId(tempQueId.getQuestionId());

		String anstext = ansList.get(questionBean.getCorrectAnswerId()).getAnswer();
		AnswerBean ansBean = answerDao.findByQuestionIdAndAnswer(tempQueId.getQuestionId(), anstext);

		correctAnsBean.setAnswerId(ansBean.getAnswerId());
		correctAnsBean.getStatus();
		correctAnsBean.getCreatedOn();
		correctAnswerDao.save(correctAnsBean);

	}

	// Find the Question With answer using Id in update Question Page
	public QuestionBean FindById(int id) {
		QuestionBean questionBean = questionDao.findByQuestionId(id).get(0);
		try {
			questionBean = questionDao.findByQuestionId(id).get(0);
			questionBean.setAnsList(answerDao.findByQuestionId(id));

			CorrectAnswerBean correctAnswerBean = correctAnswerDao.findByQuestionId(id);
			questionBean.setCorrectAnswerId(correctAnswerBean.getAnswerId());
		} catch (NullPointerException e) {

		}
		return questionBean;

	}

	// update And Save Question Details with answer
	public void updateQuestion(int id, QuestionBean questionBean) {
		try {
			QuestionBean updateDetails = questionDao.findByQuestionId(id).get(0);
			updateDetails.setSubjectId(questionBean.getSubjectId());
			updateDetails.setQueStatus(questionBean.getQueStatus());
			updateDetails.setQuestion(questionBean.getQuestion());
			questionDao.save(updateDetails);

			List<AnswerBean> ansList = questionBean.getAnsList();
			for (AnswerBean bean : ansList) {
				// QuestionBean tempQueIdAndSubId =
				// questionDao.findByQuestionIdOrderByQuestionIdDesc(questionBean.getQuestionId());
				bean.setAnswerId(bean.getAnswerId());
				;
				bean.setQuestionId(id);
				answerDao.save(bean);
			}
			// answerDao.saveAll(ansList);

			CorrectAnswerBean updateAnswer = correctAnswerDao.findByQuestionId(id);
			updateAnswer.setAnswerId(questionBean.getCorrectAnswerId());
			correctAnswerDao.save(updateAnswer);

		} catch (NullPointerException e) {

		}
	}

	public Object findAll(Sort orderByIdAsc) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteQuestion(int id, QuestionBean questionBean) {
		// TODO Auto-generated method stub
		try {
			QuestionBean updateDetails = questionDao.findByQuestionId(id).get(0);
			updateDetails.setStatus(0);
			questionDao.save(updateDetails);
		} catch (NullPointerException e) {

		}
	}
	
	//SearchingQuery Listing 
	public List<QuestionBean> SearchQueryResult(String keyword,Integer subId){
		if(subId <=0) {
			List<QuestionBean> queList1 = questionDao.findByQuestionContainingAndQueStatusAndStatusOrderByQuestionIdAsc(keyword,1,1);
			//List<QuestionBean> queList = questionDao.findByQuestionContainingAndSubjectIdAndQueStatusAndStatusOrderByQuestionIdAsc(keyword, subId, 1, 1);
			List<QuestionBean> finalQueList = new ArrayList<>();
			try {
				for (QuestionBean bean : queList1) {
					// System.out.println("Question Id :"+bean.getQuestionId());
					QuestionBean finalBean = new QuestionBean();

					SubjectBean tempSubjectName = subjectDao.findBySubjectId(bean.getSubjectId());
					finalBean.setSubjectName(tempSubjectName.getSubjectName());

					CorrectAnswerBean correctAnswerBean = correctAnswerDao.findByQuestionId(bean.getQuestionId());

					if (correctAnswerBean != null) {
						AnswerBean ansBean = new AnswerBean();
						ansBean = answerDao.findByAnswerId(correctAnswerBean.getAnswerId());
						if (ansBean != null) {
							// System.out.println("Correct Answer :" +ansBean.getAnswer());
							finalBean.setCorrectAns(ansBean.getAnswer());

						}
					}

					finalBean.setQuestionId(bean.getQuestionId());
					finalBean.setQuestion(bean.getQuestion());
					finalBean.setQueStatus(bean.getQueStatus());

					finalQueList.add(finalBean);

				}

			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			return finalQueList;
		} else {
		//List<QuestionBean> queList = questionDao.findByQuestionContainingAndQueStatusAndStatusOrderByQuestionIdAsc(keyword,1,1);
		List<QuestionBean> queList2 = questionDao.findByQuestionContainingAndSubjectIdAndQueStatusAndStatusOrderByQuestionIdAsc(keyword, subId, 1, 1);
		List<QuestionBean> finalQueList = new ArrayList<>();
		try {
			for (QuestionBean bean : queList2) {
				// System.out.println("Question Id :"+bean.getQuestionId());
				QuestionBean finalBean = new QuestionBean();

				SubjectBean tempSubjectName = subjectDao.findBySubjectId(bean.getSubjectId());
				finalBean.setSubjectName(tempSubjectName.getSubjectName());

				CorrectAnswerBean correctAnswerBean = correctAnswerDao.findByQuestionId(bean.getQuestionId());

				if (correctAnswerBean != null) {
					AnswerBean ansBean = new AnswerBean();
					ansBean = answerDao.findByAnswerId(correctAnswerBean.getAnswerId());
					if (ansBean != null) {
						// System.out.println("Correct Answer :" +ansBean.getAnswer());
						finalBean.setCorrectAns(ansBean.getAnswer());

					}
				}

				finalBean.setQuestionId(bean.getQuestionId());
				finalBean.setQuestion(bean.getQuestion());
				finalBean.setQueStatus(bean.getQueStatus());

				finalQueList.add(finalBean);

			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return finalQueList;
	}
	}
	
	public List<QuestionBean> getAllQuestion(int limit, int offset) {
        Pageable pageable = new OffsetBasedPageRequest(limit, offset);
        List<QuestionBean> queList2 = questionDao.findByStatusOrderByQuestionIdDesc(1, pageable);
		List<QuestionBean> finalQueList = new ArrayList<>();
		try {
			for (QuestionBean bean : queList2) {
				// System.out.println("Question Id :"+bean.getQuestionId());
				QuestionBean finalBean = new QuestionBean();

				SubjectBean tempSubjectName = subjectDao.findBySubjectId(bean.getSubjectId());
				finalBean.setSubjectName(tempSubjectName.getSubjectName());

				CorrectAnswerBean correctAnswerBean = correctAnswerDao.findByQuestionId(bean.getQuestionId());

				if (correctAnswerBean != null) {
					AnswerBean ansBean = new AnswerBean();
					ansBean = answerDao.findByAnswerId(correctAnswerBean.getAnswerId());
					if (ansBean != null) {
						// System.out.println("Correct Answer :" +ansBean.getAnswer());
						finalBean.setCorrectAns(ansBean.getAnswer());

					}
				}

				finalBean.setQuestionId(bean.getQuestionId());
				finalBean.setQuestion(bean.getQuestion());
				finalBean.setQueStatus(bean.getQueStatus());

				finalQueList.add(finalBean);

			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return finalQueList;
    }
}
