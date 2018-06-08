package com.tsinghuadtv.www.controller.report.api;

import java.util.List;

import com.tsinghuadtv.www.controller.api.common.PagingResponse;

public class GetMyFinishedTasksResponse extends PagingResponse {
	private static final long serialVersionUID = 1L;

	private List<UserTaskVO> tasks;

	public List<UserTaskVO> getTasks() {
		return tasks;
	}

	public void setTasks(List<UserTaskVO> tasks) {
		this.tasks = tasks;
	}
}
