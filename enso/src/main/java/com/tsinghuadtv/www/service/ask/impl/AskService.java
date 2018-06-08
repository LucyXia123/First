package com.tsinghuadtv.www.service.ask.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsinghuadtv.www.entity.ask.AskQuestion;
import com.tsinghuadtv.www.entity.ask.UserAskQuestionAnswer;
import com.tsinghuadtv.www.mapper.AskMapper;
import com.tsinghuadtv.www.service.ask.IAskService;

@Service
public class AskService implements IAskService{

	@Autowired
	private AskMapper askMapper;
	
	@Override
	public List<AskQuestion> getAskQuestionsByTaskId(int taskId) {
		return askMapper.selectAskQuestionsByTaskId(taskId);
	}
	
	@Override
	@Transactional
	public void batchInsertUserAskQuestionAnswers(List<UserAskQuestionAnswer> answers) {
		askMapper.batchInsertUserAskQuestionAnswers(answers);
	}
}
