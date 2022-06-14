package com.subject;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class  SubjectService {
	@Autowired
	private SubjectDAO subjectDAO;

	//Getting Subject Lists
	public List<SubjectBean> findAll() {
		return subjectDAO.findAll(orderByIdDesc()) ;
	}
	
	private Sort orderByIdDesc() {
		// TODO Auto-generated method stub
		return new Sort(Sort.Direction.DESC, "subjectId");
	}

	//Saving Subject
	public SubjectBean save(SubjectBean subjectBean) {
		subjectBean.setSubjectStatus(1);
		subjectBean.setStatus(1);
		subjectBean.setCreatedOn(new Date());
		return subjectDAO.save(subjectBean);
		
	}
	
	//Subject Details BY ID
	public SubjectBean findById(int id) {
	   SubjectBean subjectBean = subjectDAO.findBySubjectId(id);
       subjectBean.setSubjectId(subjectBean.getSubjectId());
       subjectBean.setSubjectName(subjectBean.getSubjectName());
       subjectBean.setSubjectStatus(subjectBean.getSubjectStatus());
		return subjectBean;
	}
	
	//Subject UPDATE 
	public SubjectBean update(int id, SubjectBean subjectBean) {
		SubjectBean updateDetails = subjectDAO.findBySubjectId(id);
		updateDetails.setSubjectName(subjectBean.getSubjectName());
		updateDetails.setSubjectStatus(subjectBean.getSubjectStatus());
		updateDetails.setStatus(1);
		updateDetails.setCreatedOn(new Date());
		return subjectDAO.save(updateDetails);
		
	}
	
	//Subject delete 
		public SubjectBean delete(int id, SubjectBean subjectBean) {
			SubjectBean updateDetails = subjectDAO.findBySubjectId(id);
			updateDetails.setStatus(0);
			return subjectDAO.save(updateDetails);
			
		}
}
