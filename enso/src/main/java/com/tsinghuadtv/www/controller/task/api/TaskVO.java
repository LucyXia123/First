package com.tsinghuadtv.www.controller.task.api;

import java.util.ArrayList;
import java.util.List;

import com.tsinghuadtv.www.entity.report.ReportType;
import com.tsinghuadtv.www.entity.task.Task;

public class TaskVO {

	private Integer id;
	private String title;
	private Integer typeId;
	private Integer topicId;
	private Integer reportTypeId;
	private Integer energy;
	
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
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public Integer getEnergy() {
		return energy;
	}
	public void setEnergy(Integer energy) {
		this.energy = energy;
	}
	public Integer getReportTypeId() {
		return reportTypeId;
	}
	public void setReportTypeId(Integer reportTypeId) {
		this.reportTypeId = reportTypeId;
	}
	public static List<TaskVO> fromTaskList(List<Task> tasks) {
		
		List<TaskVO> list = new ArrayList<>();
		for (Task task : tasks) {
			list.add(fromTask(task));
		}
		
		return list;
	}
	
	public static TaskVO fromTask(Task task) {
		
		TaskVO vo = new TaskVO();
		
		vo.setId(task.getId());
		vo.setTitle(task.getTitle());
		vo.setTypeId(task.getType().getId());
		vo.setTopicId(task.getTopicId());
		vo.setEnergy(task.getEnergy());
		
		ReportType reportType = task.getReportType();
		if (reportType != null) {
			vo.setReportTypeId(reportType.getId());
		}
		
		return vo;
	}
}
