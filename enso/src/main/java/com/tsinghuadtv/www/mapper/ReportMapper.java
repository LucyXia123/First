package com.tsinghuadtv.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tsinghuadtv.www.entity.report.Report;
import com.tsinghuadtv.www.entity.report.ReportComment;
import com.tsinghuadtv.www.entity.report.ReportLike;
import com.tsinghuadtv.www.entity.report.ReportTopic;
import com.tsinghuadtv.www.entity.report.ReportView;
import com.tsinghuadtv.www.entity.report.UserFavoriteReport;
import com.tsinghuadtv.www.model.filter.report.ReportCommentFilter;
import com.tsinghuadtv.www.model.filter.report.ReportFilter;
import com.tsinghuadtv.www.model.filter.report.UserFavoriteReportFilter;

public interface ReportMapper {
	
	// report
	int insertReport(Report report);
	
	int updateReport(Report report);
	
	List<Report> selectReportByFilter(@Param("filter") ReportFilter filter);
	
	int countReportByFilter(@Param("filter") ReportFilter filter);
	
	// report view
	int insertOrUpdateReportView(ReportView view);
	
	int increaseReportViewCount(@Param("id") int id);
	
	// report comment
	int insertReportComment(ReportComment comment);

	int updateReportCommentCount(@Param("id") int id);
	
	int updateReportComment(ReportComment comment);
	
	List<ReportComment> selectReportCommentByFilter(@Param("filter") ReportCommentFilter filter);
	
	int countReportCommentByFilter(@Param("filter") ReportCommentFilter filter);
	
	// report like
	ReportLike selectReportLikeByUserAndReport(
			@Param("userNumber") String userNumber, @Param("reportId") Integer reportId);
	
	int insertReportLike(ReportLike like);
	
	int deleteReportLikeById(@Param("id") int id);
	
	int updateReportLikeCount(@Param("id") int id);
	
	// report favorite
	UserFavoriteReport selectUserFavoriteReportByUserAndReport(
			@Param("userNumber") String userNumber, @Param("reportId") Integer reportId);

	int insertUserFavoriteReport(UserFavoriteReport favorite);
	
	int deleteUserFavoriteReportById(@Param("id") int id);
	
	List<UserFavoriteReport> selectUserFavoriteReportByFilter(@Param("filter") UserFavoriteReportFilter filter);
	
	int countUserFavoriteReportByFilter(@Param("filter") UserFavoriteReportFilter filter);

	// report topic
	List<ReportTopic> selectAllReportTopic();
	
	int insertReportTopic(ReportTopic topic);
	
}
