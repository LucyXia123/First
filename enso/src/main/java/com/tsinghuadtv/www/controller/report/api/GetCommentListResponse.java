package com.tsinghuadtv.www.controller.report.api;

import java.util.List;

import com.tsinghuadtv.www.controller.api.common.PagingResponse;

public class GetCommentListResponse extends PagingResponse {
	private static final long serialVersionUID = 1L;

	private List<CommentVO> comments;

	public List<CommentVO> getComments() {
		return comments;
	}

	public void setComments(List<CommentVO> comments) {
		this.comments = comments;
	}
}
