package com.tsinghuadtv.www.controller.task.api;

import java.util.List;

import com.tsinghuadtv.www.controller.api.common.ServiceResponse;

public class GetTaskListResponse extends ServiceResponse {
	private static final long serialVersionUID = 1L;

	private List<TaskVO> tasks;

	public List<TaskVO> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskVO> tasks) {
		this.tasks = tasks;
	}
}
