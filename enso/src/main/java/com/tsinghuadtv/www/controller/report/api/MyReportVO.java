package com.tsinghuadtv.www.controller.report.api;

import java.util.ArrayList;
import java.util.List;

import com.tsinghuadtv.www.entity.report.Report;
import com.tsinghuadtv.www.util.DateTimeUtility;

public class MyReportVO {

	private Integer id;
	private String title;
	private Integer typeId;
	private String typeName;
	private Integer statusId;
	private String statusName;
	private String createTime;
	private String approveTime;
	private String deleteTime;
	
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}

	public static List<MyReportVO> fromReportList(List<Report> reports) {
		
		List<MyReportVO> list = new ArrayList<>();
		for (Report report : reports) {
			list.add(fromReport(report));
		}
		
		return list;
	}
	
	public static MyReportVO fromReport(Report report) {
		
		MyReportVO vo = new MyReportVO();
		
		vo.setId(report.getId());
		vo.setTitle(report.getTitle());
		vo.setTypeId(report.getType().getId());
		vo.setTypeName(report.getType().getDescription());
		vo.setStatusId(report.getStatus().getId());
		vo.setStatusName(report.getStatus().getDescription());
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(report.getCreateTime()));
		vo.setApproveTime(DateTimeUtility.formatYYYYMMDDHHMMSS(report.getApproveTime()));
		vo.setDeleteTime(DateTimeUtility.formatYYYYMMDDHHMMSS(report.getDeleteTime()));
		
		return vo;
		
	}
}
