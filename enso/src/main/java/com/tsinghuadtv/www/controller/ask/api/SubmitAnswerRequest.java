package com.tsinghuadtv.www.controller.ask.api;

import java.util.List;

public class SubmitAnswerRequest {

	private Integer taskId;
	private List<AnswerRO> answers;
	
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public List<AnswerRO> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerRO> answers) {
		this.answers = answers;
	}
}
