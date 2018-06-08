package com.tsinghuadtv.www.controller.report.api;

import java.util.ArrayList;
import java.util.List;

import com.tsinghuadtv.www.entity.report.ReportComment;
import com.tsinghuadtv.www.util.DateTimeUtility;

public class CommentVO {

	private Integer id;
	private String userImage;
	private String userName;
	private String createTime;
	private String content;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public static List<CommentVO> fromReportCommentList(List<ReportComment> comments) {
		
		List<CommentVO> list = new ArrayList<>();
		for (ReportComment comment : comments) {
			list.add(fromReportComment(comment));
		}
		return list;
	}
	
	public static CommentVO fromReportComment(ReportComment comment) {
		
		CommentVO vo = new CommentVO();
		
		vo.setId(comment.getId());
		vo.setUserImage("");
		vo.setUserName(comment.getStudent().getRealname());
		vo.setContent(comment.getContent());
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(comment.getCreateTime()));
		
		return vo;
	}
}
