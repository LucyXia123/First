package com.tsinghuadtv.www.model.filter.task;

import java.util.Date;
import java.util.List;

import com.tsinghuadtv.www.entity.task.TaskType;
import com.tsinghuadtv.www.model.Bool;

public class TaskFilter {

	private Date date;
	private Bool open;
	private List<Integer> excludeIds;
	private TaskType type;
	private Boolean inProgress;
	private String userNumber;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Bool getOpen() {
		return open;
	}
	public void setOpen(Bool open) {
		this.open = open;
	}
	public List<Integer> getExcludeIds() {
		return excludeIds;
	}
	public void setExcludeIds(List<Integer> excludeIds) {
		this.excludeIds = excludeIds;
	}
	public TaskType getType() {
		return type;
	}
	public void setType(TaskType type) {
		this.type = type;
	}
	public Boolean getInProgress() {
		return inProgress;
	}
	public void setInProgress(Boolean inProgress) {
		this.inProgress = inProgress;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	
}
