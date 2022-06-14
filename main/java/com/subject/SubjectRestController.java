package com.subject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class SubjectRestController {
	
	@Autowired 
	private SubjectService subjectService;
	@Autowired 
	private SubjectDAO subjectDao;
	
	@GetMapping("/subject")
	public String showSubject (){	
		List<SubjectBean> tempSubList = subjectDao.findBySubjectStatusAndStatusOrderBySubjectIdDesc(1,1);
		List<SubjectBean> subjectList = new ArrayList<SubjectBean>();
		try {
		for(SubjectBean obj : tempSubList) {
			SubjectBean bean = new SubjectBean();
			bean.setSubjectId(obj.getSubjectId());
			bean.setSubjectName(obj.getSubjectName());
			subjectList.add(bean);
		}
		}catch(NullPointerException e) {
			
		}
		JSONObject jsonStatus = new JSONObject();
		try {
			if(tempSubList.size()>0) {
				jsonStatus.put("status", "200");
				jsonStatus.put("message", "success");
				jsonStatus.put("data", subjectList);
		
		
			}
			else {
				jsonStatus.put("status", "400");
				jsonStatus.put("message", "Failure");
				jsonStatus.put("data", "");
				
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonStatus.toString();
	}
	
	@GetMapping("/subject/{id}")
	public SubjectBean showSubjectById(@PathVariable int id) {
		SubjectBean subjectBean = subjectService.findById(id);
		return subjectBean;
	}
	
	@PostMapping("/addsubject")
	public SubjectBean addSubject(@RequestBody SubjectBean subjectBean) {
		return subjectService.save(subjectBean);
	}
	
	@PutMapping("/updatesubject/{id}")
	public SubjectBean updateSubject(@PathVariable int id,@RequestBody SubjectBean subjectBean) {
		return subjectService.update(id,subjectBean);
	}
	
	
	
	///Extra ListFiltering to find specific data in a list
	@GetMapping("/subjectname")
	public List<String> subjectNameList() {
		List<SubjectBean> list = subjectService.findAll();
		List<String> subjectName = list.stream().map(SubjectBean::getSubjectName).collect(Collectors.toList());
		return subjectName;
	}
	
	@GetMapping("/subjectfilterlist")
	public List<Object> subjectList() {
		List<SubjectBean> list = subjectService.findAll();
		List<Object> getlist = list.stream()
				.flatMap(x->Stream.of(x.getSubjectId(),x.getSubjectName(),x.getSubjectStatus())).collect(Collectors.toList());
		return getlist;
		
	}
	
	
}
