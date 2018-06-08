package com.tsinghuadtv.www.entity.report;

import java.util.Date;

import com.tsinghuadtv.www.entity.Student;
import com.tsinghuadtv.www.model.Bool;

public class ReportComment {

	private Integer id;
	private String content;
	private Date createTime;
	private Bool deleted;
	private String userNumber;
	private Integer reportId;
	
	private Student student;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Bool getDeleted() {
		return deleted;
	}
	public void setDeleted(Bool deleted) {
		this.deleted = deleted;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
