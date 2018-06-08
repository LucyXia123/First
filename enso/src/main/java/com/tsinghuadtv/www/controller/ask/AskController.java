package com.tsinghuadtv.www.controller.ask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsinghuadtv.www.constant.SessionConstants;
import com.tsinghuadtv.www.controller.api.common.ServiceResponse;
import com.tsinghuadtv.www.controller.ask.api.AnswerRO;
import com.tsinghuadtv.www.controller.ask.api.AskQuestionVO;
import com.tsinghuadtv.www.controller.ask.api.GetAskQuestionListResponse;
import com.tsinghuadtv.www.controller.ask.api.SubmitAnswerRequest;
import com.tsinghuadtv.www.entity.ask.AskQuestion;
import com.tsinghuadtv.www.entity.ask.AskQuestionChoice;
import com.tsinghuadtv.www.entity.ask.UserAskQuestionAnswer;
import com.tsinghuadtv.www.entity.task.Task;
import com.tsinghuadtv.www.service.ask.IAskService;
import com.tsinghuadtv.www.service.task.ITaskService;
import com.tsinghuadtv.www.util.ServiceResponseUtility;

@Controller
@RequestMapping("/ask")
public class AskController {

	@Autowired
	private IAskService askService;
	
	@Autowired
	private ITaskService taskService;
	
	@RequestMapping("/question/list")
	@ResponseBody
	public ServiceResponse getQuestionList(@RequestParam("taskId") Integer taskId) {		
		
		GetAskQuestionListResponse response = new GetAskQuestionListResponse();
		
		Task task = taskService.getTaskById(taskId);
		if (task == null) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("任务不存在！");
			return response;
		}
		
		List<AskQuestion> questions = askService.getAskQuestionsByTaskId(taskId);
		
		response.setTaskTitle(task.getTitle());
		response.setQuestions(AskQuestionVO.fromAskQuestionList(questions));
		
		return response;
	}
	
	@RequestMapping("/commit")
	@ResponseBody
	public ServiceResponse submitAnswers(@RequestBody SubmitAnswerRequest request, HttpSession session) {	
		
		ServiceResponse response = new ServiceResponse();
		
		String userNumber = (String)session.getAttribute(SessionConstants.KEY_USER_NUMBER);
		if (userNumber == null) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("未登录！");
			return response;
		}
		
		List<AskQuestion> questions = askService.getAskQuestionsByTaskId(request.getTaskId());
		
		List<AnswerRO> answers = request.getAnswers();
		
		Map<Integer, Set<String>> validAnswers = new HashMap<>();
		Set<Integer> answerQuestionIds = new HashSet<>();
		
		Set<String> answerSet = null;
		for (AskQuestion question : questions) {
			answerSet = new HashSet<>();
			for (AskQuestionChoice choice : question.getChoices()) {
				answerSet.add(choice.getChoice());
			}
			validAnswers.put(question.getId(), answerSet);
		}
		
		for (AnswerRO answer : answers) {
			answerSet = validAnswers.get(answer.getQuestionId());
			if (answerSet == null || !answerSet.contains(answer.getAnswer())) {
				response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
				response.setResultMessage("数据错误！");
				return response;
			}
			answerQuestionIds.add(answer.getQuestionId());
		}
		if (validAnswers.size() != answerQuestionIds.size()) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("数据错误！");
			return response;
		}
		
		List<UserAskQuestionAnswer> answerList = new ArrayList<>();
		for (AnswerRO ro : answers) {
			
			UserAskQuestionAnswer answer = new UserAskQuestionAnswer();
			
			answer.setAskQuestionId(ro.getQuestionId());
			answer.setAnswer(ro.getAnswer());
			answer.setUserNumber(userNumber);
			
			answerList.add(answer);
		}
		
		if (!answerList.isEmpty()) {
			askService.batchInsertUserAskQuestionAnswers(answerList);
		}
		
		taskService.addTaskProgress(request.getTaskId(), userNumber);
		
		return response;
	}
	
}


