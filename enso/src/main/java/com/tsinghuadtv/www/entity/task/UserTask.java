package com.tsinghuadtv.www.entity.task;

import java.util.Date;

import com.tsinghuadtv.www.model.Bool;

public class UserTask {

	private Integer id;
	private Integer currentCount;
	private Date finishTime;
	private Bool finished;
	private String userNumber;
	private Integer taskId;
	
	private Boolean nullFinishTime;
	
	private Task task;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Bool getFinished() {
		return finished;
	}
	public void setFinished(Bool finished) {
		this.finished = finished;
	}
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
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Boolean getNullFinishTime() {
		return nullFinishTime;
	}
	public void setNullFinishTime(Boolean nullFinishTime) {
		this.nullFinishTime = nullFinishTime;
	}
	
}
