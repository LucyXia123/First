package com.tsinghuadtv.www.service.task;

import java.util.List;

import com.tsinghuadtv.www.entity.report.ReportType;
import com.tsinghuadtv.www.entity.task.Task;
import com.tsinghuadtv.www.entity.task.UserTask;
import com.tsinghuadtv.www.model.filter.SearchResult;
import com.tsinghuadtv.www.model.filter.task.UserTaskFilter;

public interface ITaskService {
	
	public Task getTaskById(int id);
	
	public Task getTaskByTopicId(int topicId);
	
	public List<Task> getTaskList(String userNumber);

	public SearchResult<UserTask> searchUserTaskByFilter(UserTaskFilter filter);
	
	public void minusTaskProgress(Integer topicId, ReportType reportType, String userNumber);
	
	public void addTaskProgress(Integer taskId, String userNumber);
	
	public void addTaskProgress(Integer topicId, ReportType reportType, String userNumber);
}
