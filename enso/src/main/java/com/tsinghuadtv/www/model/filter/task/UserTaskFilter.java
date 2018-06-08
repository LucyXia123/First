package com.tsinghuadtv.www.model.filter.task;

import com.tsinghuadtv.www.model.Bool;
import com.tsinghuadtv.www.model.filter.SearchFilter;

public class UserTaskFilter extends SearchFilter {

	private String userNumber;
	private Integer taskId;
	private Bool finished;

	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Bool getFinished() {
		return finished;
	}
	public void setFinished(Bool finished) {
		this.finished = finished;
	}
}
