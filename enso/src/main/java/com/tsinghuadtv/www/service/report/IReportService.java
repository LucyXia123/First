package com.tsinghuadtv.www.service.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.google.zxing.WriterException;
import com.tsinghuadtv.www.entity.report.Report;
import com.tsinghuadtv.www.entity.report.ReportComment;
import com.tsinghuadtv.www.entity.report.ReportLike;
import com.tsinghuadtv.www.entity.report.ReportTopic;
import com.tsinghuadtv.www.entity.report.UserFavoriteReport;
import com.tsinghuadtv.www.model.filter.SearchResult;
import com.tsinghuadtv.www.model.filter.report.ReportCommentFilter;
import com.tsinghuadtv.www.model.filter.report.ReportFilter;
import com.tsinghuadtv.www.model.filter.report.UserFavoriteReportFilter;

public interface IReportService {

	public List<ReportTopic> getAllReportTopics();
	
	public SearchResult<Report> searchReportByFilter(ReportFilter filter);
	
	public void createReport(Report report);
	
	public void updateReport(Report report);
	
	public void deleteReport(Report report);
	
	public Report getReportById(int id);
	
	public ReportLike getReportLikeByUserAndReport(String userNumber, Integer reportId);
	
	public void createReportLike(ReportLike like);
	
	public void deleteReportLike(ReportLike like);
	
	public UserFavoriteReport getUserFavoriteReportByUserAndReport(String userNumber, Integer reportId);
	
	public void createUserFavoriteReport(UserFavoriteReport favorite);
	
	public void deleteUserFavoriteReportById(int id);
	
	public SearchResult<ReportComment> searchReportCommentByFilter(ReportCommentFilter filter);
	
	public void createReportComment(ReportComment comment);
	
	public SearchResult<UserFavoriteReport> searchUserFavoriteReportByFilter(UserFavoriteReportFilter filter);
	
	public void getReportQrcode(Integer reportId, OutputStream stream) throws WriterException, IOException;
	
	public void reportApproved(Report report, boolean passed);
	
	public void onReportViewed(Report report, String userNumber);
	
}
