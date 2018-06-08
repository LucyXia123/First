package com.tsinghuadtv.www.service.ask;

import java.util.List;

import com.tsinghuadtv.www.entity.ask.AskQuestion;
import com.tsinghuadtv.www.entity.ask.UserAskQuestionAnswer;

public interface IAskService {

	public List<AskQuestion> getAskQuestionsByTaskId(int taskId);
	
	public void batchInsertUserAskQuestionAnswers(List<UserAskQuestionAnswer> answers);
	
}
