package com.tsinghuadtv.www.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tsinghuadtv.www.entity.report.Report;
import com.tsinghuadtv.www.entity.report.ReportComment;
import com.tsinghuadtv.www.entity.report.ReportLike;
import com.tsinghuadtv.www.entity.report.ReportStatus;
import com.tsinghuadtv.www.entity.report.ReportTopic;
import com.tsinghuadtv.www.entity.report.ReportType;
import com.tsinghuadtv.www.entity.report.UserFavoriteReport;
import com.tsinghuadtv.www.mapper.ReportMapper;
import com.tsinghuadtv.www.model.Bool;
import com.tsinghuadtv.www.model.common.PagingData;
import com.tsinghuadtv.www.model.filter.OrderingProperty;
import com.tsinghuadtv.www.model.filter.report.ReportCommentFilter;
import com.tsinghuadtv.www.model.filter.report.ReportFilter;

public class ReportMapperTest extends AbstractPersistenceTest {

	@Autowired
	private ReportMapper reportMapper;
	
//	@Test
	public void insertReport() {
		
		Report report = new Report();
		report.setTitle("title");
		report.setCoverImage("imageUrl");
		report.setContent("content");
		report.setContentImage("illustrationUrl");
		report.setAbstractInfo("abstract");
		report.setAudioUrl("audioUrl");
		report.setVideoUrl("videoUrl");
		report.setUserNumber("STU201709291720560001");
		report.setType(ReportType.IMAGE_TEXT);
		report.setStatus(ReportStatus.APPROVING);
		report.setTopicId(1);
		
		reportMapper.insertReport(report);
		
	}
	
//	@Test
	public void updateReport() {
		
		Report report = new Report();
		
		report.setId(1);
		report.setTitle("title1");
		report.setCoverImage("imageUrl1");
		report.setContent("content1");
		report.setContentImage("illustrationUrl1");
		report.setAbstractInfo("abstract1");
		report.setAudioUrl("audioUrl1");
		report.setVideoUrl("videoUrl1");
		report.setApproveTime(new Date());
		report.setDeleteTime(new Date());
		report.setStatus(ReportStatus.PASSED);
		
		System.out.println(reportMapper.updateReport(report));
		
	}
	
//	@Test
	public void increaseReportViewCount() {
		
		System.out.println(reportMapper.increaseReportViewCount(1));
		
	}
	
//	@Test
	public void updateReportCommentCount() {
		
		System.out.println(reportMapper.updateReportCommentCount(1));
		
	}
	
//	@Test
	public void updateReportLikeCount() {
		
		System.out.println(reportMapper.updateReportLikeCount(1));
		
	}
	
//	@Test
	public void selectReportByFilter() {
		
		ReportFilter filter = new ReportFilter();
		
		filter.setPaged(true);
		filter.setPagingData(new PagingData(1, 10));
		
		filter.setOrdered(true);
		
		List<OrderingProperty> orderingProperties = new ArrayList<>();
		orderingProperties.add(new OrderingProperty(1, "viewCount", false));
		orderingProperties.add(new OrderingProperty(2, "commentCount", false));
		
		filter.setOrderingProperties(orderingProperties);
		
		filter.setId(1);
		filter.setUserNumber("STU201709291720560001");
		
		List<Report> list = reportMapper.selectReportByFilter(filter);
		
		System.out.println(reportMapper.countReportByFilter(filter));
		
		printList(list);
	}
		
//	@Test
	public void insertReportComment() {
		
		ReportComment comment = new ReportComment();
		comment.setContent("content");
		comment.setUserNumber("STU201709291720560001");
		comment.setReportId(1);
		
		System.out.println(reportMapper.insertReportComment(comment));
		
	}
	
//	@Test
	public void updateReportComment() {
		
		ReportComment comment = new ReportComment();
		comment.setId(1);
		comment.setContent("content1");
		comment.setDeleted(Bool.Y);
		
		System.out.println(reportMapper.updateReportComment(comment));
		
	}
	
//	@Test
	public void selectReportCommentByFilter() {
		
		ReportCommentFilter filter = new ReportCommentFilter();
		
		filter.setPaged(true);
		filter.setPagingData(new PagingData(1, 10));
		
		filter.setReportId(1);
		
		List<ReportComment> list = reportMapper.selectReportCommentByFilter(filter);
		
		System.out.println(reportMapper.countReportCommentByFilter(filter));
		
		printList(list);
	}
		
//	@Test
	public void insertReportLike() {
		
		ReportLike like = new ReportLike();
		like.setUserNumber("STU201709291720560001");
		like.setReportId(1);
		
		System.out.println(reportMapper.insertReportLike(like));		
	}
	
//	@Test
	public void deleteReportLikeById() {
		
		System.out.println(reportMapper.deleteReportLikeById(1));	
		
	}
	
//	@Test
	public void selectReportLikeByUserAndReport() {
		
		System.out.println(reportMapper.selectReportLikeByUserAndReport("STU201709291720560001", 1));	
		
	}
	
//	@Test
	public void selectAllReportTopic() {
		
		printList(reportMapper.selectAllReportTopic());
		
	}
	
//	@Test
	public void insertReportTopic() {
		
		ReportTopic topic = new ReportTopic();
		
		topic.setName("任务主题");
		
		int result = reportMapper.insertReportTopic(topic);
		
		System.out.println(result);
		
	}
	
//	@Test
	public void insertUserFavoriteReport() {
		
		UserFavoriteReport favorite = new UserFavoriteReport();
		favorite.setReportId(1);
		favorite.setUserNumber("STU201709291720560001");
		
		System.out.println(reportMapper.insertUserFavoriteReport(favorite));	
		
	}
	
//	@Test
	public void deleteUserFavoriteReportById() {
		
		System.out.println(reportMapper.deleteUserFavoriteReportById(1));	
		
	}
	
//	@Test
	public void selectUserFavoriteReportByUserAndReport() {
		
		System.out.println(reportMapper.selectUserFavoriteReportByUserAndReport("STU201709291720560001", 1));	
		
	}

}
