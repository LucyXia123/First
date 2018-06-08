package com.tsinghuadtv.www.controller.ask.api;

import java.util.List;

import com.tsinghuadtv.www.controller.api.common.ServiceResponse;

public class GetAskQuestionListResponse extends ServiceResponse {
	private static final long serialVersionUID = 1L;

	private String taskTitle;
	private List<AskQuestionVO> questions;
	
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public List<AskQuestionVO> getQuestions() {
		return questions;
	}
	public void setQuestions(List<AskQuestionVO> questions) {
		this.questions = questions;
	}
}
