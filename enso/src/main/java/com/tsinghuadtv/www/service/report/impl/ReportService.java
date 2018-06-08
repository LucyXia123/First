package com.tsinghuadtv.www.service.report.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.tsinghuadtv.www.entity.report.Report;
import com.tsinghuadtv.www.entity.report.ReportComment;
import com.tsinghuadtv.www.entity.report.ReportLike;
import com.tsinghuadtv.www.entity.report.ReportStatus;
import com.tsinghuadtv.www.entity.report.ReportTopic;
import com.tsinghuadtv.www.entity.report.ReportView;
import com.tsinghuadtv.www.entity.report.UserFavoriteReport;
import com.tsinghuadtv.www.mapper.ReportMapper;
import com.tsinghuadtv.www.model.common.PagingData;
import com.tsinghuadtv.www.model.common.PagingResult;
import com.tsinghuadtv.www.model.filter.SearchResult;
import com.tsinghuadtv.www.model.filter.report.ReportCommentFilter;
import com.tsinghuadtv.www.model.filter.report.ReportFilter;
import com.tsinghuadtv.www.model.filter.report.UserFavoriteReportFilter;
import com.tsinghuadtv.www.service.report.IReportService;
import com.tsinghuadtv.www.service.task.ITaskService;
import com.tsinghuadtv.www.util.QrcodeUtils;

@Service
public class ReportService implements IReportService {

	@Autowired
	private ReportMapper reportMapper;
	
	@Autowired
	private ITaskService taskService;
	
	@Override
	public List<ReportTopic> getAllReportTopics() {
		return reportMapper.selectAllReportTopic();
	}
	
	@Override
	public SearchResult<Report> searchReportByFilter(ReportFilter filter) {
		
        SearchResult<Report> result = new SearchResult<>();

        List<Report> list = reportMapper.selectReportByFilter(filter);
        result.setResult(list);

        PagingData pagingData = filter.getPagingData();
        if (filter.isPaged() && pagingData != null) {
            int recordNumber = reportMapper.countReportByFilter(filter);

            PagingResult pagingResult = new PagingResult();

            pagingResult.setRecordNumber(recordNumber);
            pagingResult.setPageSize(pagingData.getPageSize());
            pagingResult.setPageNumber(pagingData.getPageNumber());

            result.setPaged(true);
            result.setPagingResult(pagingResult);
        }

        return result;
    }
	
	@Override
	@Transactional
	public void createReport(Report report) {
		reportMapper.insertReport(report);
	}
	
	@Override
	@Transactional
	public void updateReport(Report report) {
		reportMapper.updateReport(report);
	}
	
	@Override
	@Transactional
	public void deleteReport(Report report) {
		Report updateReport = new Report();
		
		updateReport.setId(report.getId());
		updateReport.setStatus(ReportStatus.DELETED);
		updateReport.setDeleteTime(new Date());
		
		reportMapper.updateReport(updateReport);
		
		// 能量回滚
		if (report.getStatus() == ReportStatus.PASSED
				&& report.getTopicId() != null) {
			taskService.minusTaskProgress(report.getTopicId(), report.getType(), report.getUserNumber());
		}
	}
	
	@Override
	public Report getReportById(int id) {
		
		ReportFilter filter = new ReportFilter();
		filter.setId(id);
		
		List<Report> reports = reportMapper.selectReportByFilter(filter);
		if (reports.size() > 0) {
			return reports.get(0);
		}
		return null;
	}
	
	@Override
	public ReportLike getReportLikeByUserAndReport(String userNumber, Integer reportId) {
		return reportMapper.selectReportLikeByUserAndReport(userNumber, reportId);
	}
	
	@Override
	@Transactional
	public void createReportLike(ReportLike like) {
		
		reportMapper.insertReportLike(like);
		
		reportMapper.updateReportLikeCount(like.getReportId());
	}
	
	@Override
	@Transactional
	public void deleteReportLike(ReportLike like) {
		
		reportMapper.deleteReportLikeById(like.getId());
		
		reportMapper.updateReportLikeCount(like.getReportId());
	}
	
	@Override
	public UserFavoriteReport getUserFavoriteReportByUserAndReport(String userNumber, Integer reportId) {
		return reportMapper.selectUserFavoriteReportByUserAndReport(userNumber, reportId);
	}
	
	@Override
	@Transactional
	public void createUserFavoriteReport(UserFavoriteReport favorite) {
		reportMapper.insertUserFavoriteReport(favorite);
	}
	
	@Override
	@Transactional
	public void deleteUserFavoriteReportById(int id) {
		reportMapper.deleteUserFavoriteReportById(id);
	}
	
	@Override
	public SearchResult<ReportComment> searchReportCommentByFilter(ReportCommentFilter filter) {
		
        SearchResult<ReportComment> result = new SearchResult<>();

        List<ReportComment> list = reportMapper.selectReportCommentByFilter(filter);
        result.setResult(list);

        PagingData pagingData = filter.getPagingData();
        if (filter.isPaged() && pagingData != null) {
            int recordNumber = reportMapper.countReportCommentByFilter(filter);

            PagingResult pagingResult = new PagingResult();

            pagingResult.setRecordNumber(recordNumber);
            pagingResult.setPageSize(pagingData.getPageSize());
            pagingResult.setPageNumber(pagingData.getPageNumber());

            result.setPaged(true);
            result.setPagingResult(pagingResult);
        }

        return result;
    }
	
	@Override
	@Transactional
	public void createReportComment(ReportComment comment) {
		
		reportMapper.insertReportComment(comment);
		
		reportMapper.updateReportCommentCount(comment.getReportId());
	}
	
	@Override
	public SearchResult<UserFavoriteReport> searchUserFavoriteReportByFilter(UserFavoriteReportFilter filter) {
		
        SearchResult<UserFavoriteReport> result = new SearchResult<>();

        List<UserFavoriteReport> list = reportMapper.selectUserFavoriteReportByFilter(filter);
        result.setResult(list);

        PagingData pagingData = filter.getPagingData();
        if (filter.isPaged() && pagingData != null) {
            int recordNumber = reportMapper.countUserFavoriteReportByFilter(filter);

            PagingResult pagingResult = new PagingResult();

            pagingResult.setRecordNumber(recordNumber);
            pagingResult.setPageSize(pagingData.getPageSize());
            pagingResult.setPageNumber(pagingData.getPageNumber());

            result.setPaged(true);
            result.setPagingResult(pagingResult);
        }

        return result;
    }
	
	@Override
	public void getReportQrcode(Integer reportId, OutputStream stream) throws WriterException, IOException {
    	
		String charset = "UTF-8";
		Map<EncodeHintType, Object> hintMap = new HashMap<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		hintMap.put(EncodeHintType.MARGIN, 0);
		
		String qrcontent = "http://xxx.xxx?id=" + reportId;
		
		QrcodeUtils.createQRCode(qrcontent, charset, hintMap, 500, 500, stream);		
    }
	
	@Override
	@Transactional
	public void reportApproved(Report report, boolean passed) {
		
		Report updateReport = new Report();
		
		updateReport.setId(report.getId());
		updateReport.setApproveTime(new Date());
		updateReport.setStatus(passed ? ReportStatus.PASSED : ReportStatus.NOT_PASS); 
		
		reportMapper.updateReport(updateReport);
		
		if (passed && report.getTopicId() != null) {
			
			taskService.addTaskProgress(report.getTopicId(), report.getType(), report.getUserNumber());
			
		}
	}
	
	@Override
	@Transactional
	public void onReportViewed(Report report, String userNumber) {
		
		reportMapper.increaseReportViewCount(report.getId());
		
		if (userNumber != null) {
			ReportView view = new ReportView();
			view.setReportId(report.getId());
			view.setUserNumber(userNumber);
			
			reportMapper.insertOrUpdateReportView(view);
		}
	}

}
