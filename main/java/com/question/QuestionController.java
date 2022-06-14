package com.question;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.commonBean.CommonBean;
import com.subject.SubjectDAO;

@Controller
public class QuestionController {
	
	@Autowired 
	private QuestionService questionService;
	@Autowired
	private QuestionDAO questionDao;
	@Autowired
	private SubjectDAO subjectDao;
	
	@GetMapping("/question")
	public String showQuestionPage(Model theModel,@ModelAttribute("customSearch")CommonBean commonBean) {
		int offset=0;
		int noOfPages=(int) Math.ceil(questionDao.count()*1.0/10);
		//System.out.println("Pages: "+noOfPages);
		theModel.addAttribute("noOfPages", noOfPages); 
	    theModel.addAttribute("currentPage",offset);
		theModel.addAttribute("subjectDetails",subjectDao.findByStatusOrderBySubjectIdDesc(1));
		theModel.addAttribute("questionDetails",questionService.getAllQuestion(10, 0));
		//System.out.println("Entries: " +questionDao.count());
		return "questionMaster";
	}
	
	@GetMapping("/question/{limit}/{offset}")
	public String offset(Model theModel,@ModelAttribute("customSearch")CommonBean commonBean,@PathVariable int limit,@PathVariable int offset) {
		int noOfPages=(int) Math.ceil(questionDao.count()*1.0/10);
		//System.out.println("Pages: "+noOfPages);
		theModel.addAttribute("noOfPages", noOfPages); 
	    theModel.addAttribute("currentPage",offset);
		theModel.addAttribute("subjectDetails",subjectDao.findByStatusOrderBySubjectIdDesc(1));
		theModel.addAttribute("questionDetails",questionService.getAllQuestion(limit, offset) );
		return "questionMaster";
	}
	
	@PostMapping("/question/search")
	public String customSearchPage(Model theModel,@ModelAttribute("customSearch")CommonBean commonBean) {
		theModel.addAttribute("subjectDetails",subjectDao.findByStatusOrderBySubjectIdDesc(1));
		theModel.addAttribute("questionDetails",questionService.SearchQueryResult(commonBean.getKeyword(), commonBean.getSubId()));
		//System.out.println("keyword: " +commonBean.getKeyword()+ " subId: "+commonBean.getSubId());
		return "questionMaster";
		
	}
	
	@GetMapping("/question/{subjectId}")
	public String showQuestionPageSubjectWise(@PathVariable int subjectId,Model theModel) {
		theModel.addAttribute("questionDetails",questionDao.findBySubjectIdAndStatusOrderByQuestionIdDesc(subjectId,1));
		return "questionMaster";
	}
	


	@GetMapping("/question/addquestion")
	public String addQuestionPage(@ModelAttribute("questionWithAnswer")QuestionBean questionBean,Model theModel) {
		theModel.addAttribute("subjectDetails",questionService.getSubjectName());
		return "addQuestion";
	}
	
	@PostMapping("/question/savequestion")
	public String saveQuestionPage(Model theModel,QuestionBean questionBean) {
		questionService.save(questionBean);
		theModel.addAttribute("questionDetails",questionService.getQuestionList());
		return "redirect:/question";
	}
	
	@GetMapping("/question/updatequestion/{id}")
	public String showQuestionDetailsPage(@PathVariable int id,Model theModel) {
		theModel.addAttribute("subjectDetails",questionService.getSubjectName());
		theModel.addAttribute("questionDetails",questionService.FindById(id));
		return "updateQuestion";
	}
	
	@RequestMapping(value="/question/updatequestion/edit/{id}",method = RequestMethod.POST)
	public String updateQuestionPage(@PathVariable int id, Model theModel,QuestionBean questionBean) {
		questionService.updateQuestion(id,questionBean);
		//theModel.addAttribute("questionDetails",questionService.getQuestionList());
		return "redirect:/question";
	}
	
	@RequestMapping(value="/question/deletequestion/{id}")
	public String deletequestion(@PathVariable int id, Model theModel,QuestionBean questionBean) {
		questionService.deleteQuestion(id,questionBean);
		//theModel.addAttribute("questionDetails",questionDao.findByStatusOrderByQuestionIdDesc(1));
		return "redirect:/question";
	}
}
