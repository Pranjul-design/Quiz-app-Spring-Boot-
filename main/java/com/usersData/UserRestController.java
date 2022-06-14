package com.usersData;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.CorrectAnswerBean;
import com.question.CorrectAnswerDAO;


@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired 
	private UserDao userDao;
	@Autowired
	private CorrectAnswerDAO correctAnswerDAO;
	
	@PostMapping("/quizResult")
	public String usersAnswers(@RequestBody String usersAns) {
		int correctAnswerbyUser = 0;
		JSONObject jsonStatus = new JSONObject();
		try {
		
		JSONObject jsonObject = new JSONObject(usersAns);
		System.out.println(jsonObject.toString());
		JSONObject jsonObject1 = jsonObject.getJSONObject("data");
		System.out.println(jsonObject1.toString());
		String deviceId = jsonObject1.get("deviceId").toString();
		String ansJson = jsonObject1.get("ansList").toString();
		System.out.println(ansJson);
		JSONArray array = new JSONArray(ansJson);
		int questionCount = array.length();
		for(int i=0; i < questionCount; i++)   
		{  
		JSONObject obj = array.getJSONObject(i);
		String questionId = obj.get("questionId").toString();
		System.out.println("questionId : "+questionId);
		String answerId = obj.get("answerId").toString();
		System.out.println("answerId : "+answerId);
		
		CorrectAnswerBean cbean = correctAnswerDAO.findByQuestionId(Integer.valueOf(questionId));
		if(cbean.getAnswerId() == Integer.valueOf(answerId)) {
			correctAnswerbyUser++;
		}
		
		UserBean userbean = new UserBean();
		userbean.setUserId(Integer.valueOf(deviceId));
		userbean.setQuestionId(Integer.valueOf(questionId));
		userbean.setAnswerId(Integer.valueOf(answerId));
		userbean.setStatus(0);
		userbean.setCreatedOn(new Date());
		userDao.save(userbean);
		
		}  
		
		jsonStatus.put("status", 200);
		jsonStatus.put("message", "success");
		
		JSONObject jsondata = new JSONObject();
		jsondata.put("TotalquestionsCount", questionCount);
		jsondata.put("TotalCorrectAnswersCount", correctAnswerbyUser);
		
		jsonStatus.put("data", jsondata);
		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception in usersAnswers : "+e);
			e.printStackTrace();
		}
		return jsonStatus.toString();
	}
		 
/*	public List<UserBean> getData() {
		 try {

				URL url = new URL("http://localhost:8080/api/subject/1/question/2");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
					
				}
				conn.disconnect();
				

			  } catch (MalformedURLException e) {
				  e.printStackTrace();
			  } catch (IOException e) {
				e.printStackTrace();
			  }
		return null;
	}*/
//	@GetMapping("/saveUser/user/{id}")
//	public void Save(@PathVariable int id,UserBean userBean) {
//		List<UserBean> userData = getData();
//		try {
//			for(UserBean bean: userData) {
//				bean.setUserId(id);
//				bean.setSubjectId(bean.getSubjectId());
//				System.out.println("subId: "+ bean.getSubjectId());
//			}
//		} catch(NullPointerException e) {
//			
//		}
//		
//		//userDao.save(userBean);
//	}
	
	@PostMapping("/userQuestionAnswerSave")
	UserBean SaveQuestion(@RequestBody UserBean user) throws IOException {
		URL url = null;
		try {
			url = new URL("http://localhost:8080/api/subject/1/question/2");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
	    return userDao.save(user);
	     }
}



