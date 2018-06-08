package com.tsinghuadtv.www.controller.report.api;

import java.util.ArrayList;
import java.util.List;

import com.tsinghuadtv.www.entity.report.Report;
import com.tsinghuadtv.www.entity.report.UserFavoriteReport;

public class ReportVO {

	private Integer id;
	private String title;
	private String coverImage;
	private Integer typeId;
	private String typeName;
	private String userName;
	private Integer likeCount;
	private Integer commentCount;
	
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
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	
	public static List<ReportVO> fromUserFavoriteReportList(List<UserFavoriteReport> reports) {
		
		List<ReportVO> list = new ArrayList<>();
		for (UserFavoriteReport report : reports) {
			list.add(fromReport(report.getReport()));
		}
		
		return list;
	}
	
	public static List<ReportVO> fromReportList(List<Report> reports) {
		
		List<ReportVO> list = new ArrayList<>();
		for (Report report : reports) {
			list.add(fromReport(report));
		}
		
		return list;
	}
	
	public static ReportVO fromReport(Report report) {
		
		ReportVO vo = new ReportVO();
		
		vo.setId(report.getId());
		vo.setTitle(report.getTitle());
		vo.setCoverImage(report.getCoverImage());
		vo.setTypeId(report.getType().getId());
		vo.setTypeName(report.getType().getDescription());
		vo.setUserName(report.getUserNumber());
		vo.setLikeCount(report.getLikeCount());
		vo.setCommentCount(report.getCommentCount());
		
		return vo;
		
	}
}
