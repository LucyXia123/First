package com.tsinghuadtv.www.controller.report.api;

import java.util.ArrayList;
import java.util.List;

import com.tsinghuadtv.www.entity.task.Task;
import com.tsinghuadtv.www.entity.task.UserTask;
import com.tsinghuadtv.www.util.DateTimeUtility;

public class UserTaskVO {

	private Integer id;
	private String title;
	private String beginDate;
	private String endDate;
	private String finishDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
	public static List<UserTaskVO> fromUserTaskList(List<UserTask> userTasks) {
		
		List<UserTaskVO> list = new ArrayList<>();
		for (UserTask userTask : userTasks) {
			list.add(fromUserTask(userTask));
		}
		
		return list;
	}
	
	public static UserTaskVO fromUserTask(UserTask userTask) {
		
		Task task = userTask.getTask();
		
		UserTaskVO vo = new UserTaskVO();
		vo.setId(task.getId());
		vo.setTitle(task.getTitle());
		vo.setBeginDate(DateTimeUtility.formatYYYYMMDD(task.getBeginDate()));
		vo.setEndDate(DateTimeUtility.formatYYYYMMDD(task.getEndDate()));
		vo.setFinishDate(DateTimeUtility.formatYYYYMMDD(userTask.getFinishTime()));
		
		return vo;
	}
}
