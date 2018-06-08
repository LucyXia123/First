package com.tsinghuadtv.www.controller.report;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.WriterException;
import com.tsinghuadtv.www.constant.SessionConstants;
import com.tsinghuadtv.www.controller.api.common.PagingRequest;
import com.tsinghuadtv.www.controller.api.common.ServiceResponse;
import com.tsinghuadtv.www.controller.report.api.CommentVO;
import com.tsinghuadtv.www.controller.report.api.CreateCommentRequest;
import com.tsinghuadtv.www.controller.report.api.CreateReportRequest;
import com.tsinghuadtv.www.controller.report.api.GetCommentListRequest;
import com.tsinghuadtv.www.controller.report.api.GetCommentListResponse;
import com.tsinghuadtv.www.controller.report.api.GetMyFavoritesResponse;
import com.tsinghuadtv.www.controller.report.api.GetMyFinishedTasksResponse;
import com.tsinghuadtv.www.controller.report.api.GetMyReportListResponse;
import com.tsinghuadtv.www.controller.report.api.GetReportDetailResponse;
import com.tsinghuadtv.www.controller.report.api.GetReportListRequest;
import com.tsinghuadtv.www.controller.report.api.GetReportListResponse;
import com.tsinghuadtv.www.controller.report.api.GetSchoolEnergyRankListResponse;
import com.tsinghuadtv.www.controller.report.api.GetSchoolInfoResponse;
import com.tsinghuadtv.www.controller.report.api.ModifyReportRequest;
import com.tsinghuadtv.www.controller.report.api.MyReportVO;
import com.tsinghuadtv.www.controller.report.api.ReportDetailVO;
import com.tsinghuadtv.www.controller.report.api.ReportFavoriteRequest;
import com.tsinghuadtv.www.controller.report.api.ReportLikeRequest;
import com.tsinghuadtv.www.controller.report.api.ReportVO;
import com.tsinghuadtv.www.controller.report.api.SchoolEnergyVO;
import com.tsinghuadtv.www.controller.report.api.SchoolInfoVO;
import com.tsinghuadtv.www.controller.report.api.UserTaskVO;
import com.tsinghuadtv.www.entity.School;
import com.tsinghuadtv.www.entity.energy.EnergyLevel;
import com.tsinghuadtv.www.entity.energy.SchoolEnergy;
import com.tsinghuadtv.www.entity.report.Report;
import com.tsinghuadtv.www.entity.report.ReportComment;
import com.tsinghuadtv.www.entity.report.ReportLike;
import com.tsinghuadtv.www.entity.report.ReportStatus;
import com.tsinghuadtv.www.entity.report.ReportType;
import com.tsinghuadtv.www.entity.report.UserFavoriteReport;
import com.tsinghuadtv.www.entity.task.UserTask;
import com.tsinghuadtv.www.model.Bool;
import com.tsinghuadtv.www.model.filter.OrderingProperty;
import com.tsinghuadtv.www.model.filter.SearchFilter;
import com.tsinghuadtv.www.model.filter.SearchResult;
import com.tsinghuadtv.www.model.filter.report.ReportCommentFilter;
import com.tsinghuadtv.www.model.filter.report.ReportFilter;
import com.tsinghuadtv.www.model.filter.report.UserFavoriteReportFilter;
import com.tsinghuadtv.www.model.filter.task.UserTaskFilter;
import com.tsinghuadtv.www.service.energy.IEnergyService;
import com.tsinghuadtv.www.service.report.IReportService;
import com.tsinghuadtv.www.service.school.ISchoolService;
import com.tsinghuadtv.www.service.task.ITaskService;
import com.tsinghuadtv.www.util.EnergyUtil;
import com.tsinghuadtv.www.util.ServiceResponseUtility;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private IReportService reportService;
	
	@Autowired
	private IEnergyService energyService;
	
	@Autowired
	private ISchoolService schoolService;
	
	@Autowired
	private ITaskService taskService;
	
	@RequestMapping("/school/info")
	@ResponseBody
	public ServiceResponse getSchoolInfo(HttpSession session) {		
		
		GetSchoolInfoResponse response = new GetSchoolInfoResponse();
		
		String schoolName = (String)session.getAttribute(SessionConstants.KEY_SCHOOL);
		if (schoolName == null) {
			return response;
		}
		
		School school = schoolService.getSchoolByName(schoolName);
		
		SchoolInfoVO schoolInfo = new SchoolInfoVO();
		
		schoolInfo.setName(schoolName);
		schoolInfo.setLogo(school.getLogo());
		schoolInfo.setArea(school.getArea());
		schoolInfo.setEnergy(0);
		schoolInfo.setRank(0);
		schoolInfo.setLighten(false);
		schoolInfo.setLevel(0);
		
		SchoolEnergy schoolEnergy = energyService.getSchoolEnergyBySchoolName(schoolName);
		if (schoolEnergy != null) {
			int energy = schoolEnergy.getEnergy();
			
			schoolInfo.setEnergy(energy);
			schoolInfo.setRank(schoolEnergy.getEnergyRank());
			
			List<EnergyLevel> levels = energyService.getAllEnergyLevels();
			
			int myLevel = EnergyUtil.getEnergyLevel(energy, levels);
			
			schoolInfo.setLevel(myLevel);
			schoolInfo.setLighten(myLevel > 0);			
		}
		
		response.setSchoolInfo(schoolInfo);
		
		return response;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ServiceResponse getReportList(GetReportListRequest request) {
		
		GetReportListResponse response = new GetReportListResponse();
				
		ReportFilter filter = new ReportFilter();
		
		filter.initPagingAndOrdering(request);
		
		if (!filter.isOrdered()) {
			filter.setOrdered(true);
			filter.setOrderingProperties(Arrays.asList(
					new OrderingProperty(1, "recommended", false),
					new OrderingProperty(2, "sequence", true),
					new OrderingProperty(3, "viewCount", false)));
		}
		
		filter.setTopicId(request.getTopicId());
		filter.setArea(request.getArea());
		filter.setSchoolName(request.getSchoolName());
		filter.setTypeId(request.getTypeId());
		filter.setStatus(ReportStatus.PASSED);
		
		SearchResult<Report> searchResult = reportService.searchReportByFilter(filter);
		
		response.setReports(ReportVO.fromReportList(searchResult.getResult()));
		response.setPagingResult(searchResult.getPagingResult());
		
		return response;
	}
	
	@RequestMapping("/school/energy/rank/list")
	@ResponseBody
	public ServiceResponse getSchoolEnergyRankList(PagingRequest request) {
		
		GetSchoolEnergyRankListResponse response = new GetSchoolEnergyRankListResponse();
		
		SearchFilter filter = new SearchFilter();
		
		filter.initPagingAndOrdering(request);
		
		SearchResult<SchoolEnergy> searchResult = energyService.searchSchoolEnergyByFilter(filter);
		
		List<EnergyLevel> levels = energyService.getAllEnergyLevels();
		
		response.setPagingResult(searchResult.getPagingResult());
		response.setSchoolEnergies(SchoolEnergyVO.fromSchoolEnergyList(searchResult.getResult(), levels));
		
		return response;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public ServiceResponse createReport(@RequestBody CreateReportRequest request, HttpSession session) {
		
		ServiceResponse response = new ServiceResponse();
		
		String userNumber = (String)session.getAttribute(SessionConstants.KEY_USER_NUMBER);
		if (userNumber == null) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("未登录！");
			return response;
		}
		
		if (!request.validate()) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("参数错误!");
			return response;
		}
		
		Report report = new Report();
		
		report.setTitle(request.getTitle());
		report.setCoverImage(request.getCoverImage());
		report.setContent(request.getContent());
		report.setContentImage(request.getContentImage());
		report.setAbstractInfo(request.getAbstractInfo());
		report.setAudioUrl(request.getAudioUrl());
		report.setType(ReportType.fromId(request.getTypeId()));
		report.setStatus(ReportStatus.APPROVING);
		report.setTopicId(request.getTopicId());
		
		report.setUserNumber(userNumber);
		
		reportService.createReport(report);
		
		return response;
	}
	
	@RequestMapping("/modify")
	@ResponseBody
	public ServiceResponse modifyReport(@RequestBody ModifyReportRequest request, HttpSession session) {
		
		ServiceResponse response = new ServiceResponse();
		
		String userNumber = (String)session.getAttribute(SessionConstants.KEY_USER_NUMBER);
		if (userNumber == null) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("未登录！");
			return response;
		}
		
		Report report = reportService.getReportById(request.getId());
		
		if (report == null 
				|| !report.getUserNumber().equals(userNumber) 
				|| !request.validate(report.getType())
				|| report.getStatus() != ReportStatus.NOT_PASS) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("参数错误!");
			return response;
		}
		
		Report updateReport = new Report();
		
		updateReport.setId(request.getId());
		updateReport.setTitle(request.getTitle());
		updateReport.setCoverImage(request.getCoverImage());
		updateReport.setContent(request.getContent());
		updateReport.setContentImage(request.getContentImage());
		updateReport.setAbstractInfo(request.getAbstractInfo());
		updateReport.setAudioUrl(request.getAudioUrl());
		
		reportService.updateReport(updateReport);
		
		return response;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServiceResponse deleteReport(@RequestParam("id") int id, HttpSession session) {
		
		ServiceResponse response = new ServiceResponse();
		
		String userNumber = (String)session.getAttribute(SessionConstants.KEY_USER_NUMBER);
		if (userNumber == null) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("未登录！");
			return response;
		}
		
		Report report = reportService.getReportById(id);
		
		if (report == null 
				|| !report.getUserNumber().equals(userNumber)
				|| report.getStatus() == ReportStatus.DELETED) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("参数错误!");
			return response;
		}
		
		reportService.deleteReport(report);
		
		return response;
	}
	
	@RequestMapping("/detail")
	@ResponseBody
	public ServiceResponse getReportDetail(@RequestParam("id") int id, HttpSession session) {
		
		GetReportDetailResponse response = new GetReportDetailResponse();
		
		String userNumber = (String)session.getAttribute(SessionConstants.KEY_USER_NUMBER);
		
		Report report = reportService.getReportById(id);
		
		boolean like = userNumber != null 
				&& reportService.getReportLikeByUserAndReport(userNumber, id) != null;
		boolean favorite = userNumber != null 
				&& reportService.getUserFavoriteReportByUserAndReport(userNumber, id) != null;
		
		// 记录pv，uv
		reportService.onReportViewed(report, userNumber);
		
		response.setDetail(ReportDetailVO.fromReport(report, like, favorite));
		
		return response;
	}
	
	@RequestMapping("/comment/list")
	@ResponseBody
	public ServiceResponse getCommentList(GetCommentListRequest request) {
		
		GetCommentListResponse response = new GetCommentListResponse();
		
		ReportCommentFilter filter = new ReportCommentFilter();
		
		filter.initPagingAndOrdering(request);
		filter.setReportId(request.getReportId());
		
		SearchResult<ReportComment> searchResult = reportService.searchReportCommentByFilter(filter);
		
		response.setPagingResult(searchResult.getPagingResult());
		response.setComments(CommentVO.fromReportCommentList(searchResult.getResult()));
		
		return response;
	}
	
	@RequestMapping("/comment/create")
	@ResponseBody
	public ServiceResponse createComment(@RequestBody CreateCommentRequest request, HttpSession session) {
		
		ServiceResponse response = new ServiceResponse();
		
		String userNumber = (String)session.getAttribute(SessionConstants.KEY_USER_NUMBER);
		if (userNumber == null) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("未登录！");
			return response;
		} 
		
		ReportComment comment = new ReportComment();
		
		comment.setReportId(request.getReportId());
		comment.setContent(request.getContent());
		comment.setUserNumber(userNumber);
		
		reportService.createReportComment(comment);
		
		return response;
	}
	
	@RequestMapping("/like")
	@ResponseBody
	public ServiceResponse reportLike(ReportLikeRequest request, HttpSession session) {
		
		ServiceResponse response = new ServiceResponse();
		
		String userNumber = (String)session.getAttribute(SessionConstants.KEY_USER_NUMBER);
		if (userNumber == null) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("未登录！");
			return response;
		} 
		
		int reportId = request.getReportId();
		
		ReportLike like = reportService.getReportLikeByUserAndReport(userNumber, reportId);
		if (like == null && request.getLike()) {
			
			like = new ReportLike();
			like.setReportId(reportId);
			like.setUserNumber(userNumber);
			
			reportService.createReportLike(like);
			
		} else if (like != null && !request.getLike()) {
			
			reportService.deleteReportLike(like);
			
		}
		
		return response;
	}
	
	@RequestMapping("/favorite")
	@ResponseBody
	public ServiceResponse reportFavorite(ReportFavoriteRequest request, HttpSession session) {
		
		ServiceResponse response = new ServiceResponse();
		
		String userNumber = (String)session.getAttribute(SessionConstants.KEY_USER_NUMBER);
		if (userNumber == null) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("未登录！");
			return response;
		} 
		
		int reportId = request.getReportId();
		
		UserFavoriteReport favorite = reportService.getUserFavoriteReportByUserAndReport(userNumber, reportId);
		if (favorite == null && request.getFavorite()) {
			
			favorite = new UserFavoriteReport();
			favorite.setReportId(reportId);
			favorite.setUserNumber(userNumber);
			
			reportService.createUserFavoriteReport(favorite);;
			
		} else if (favorite != null && !request.getFavorite()) {
			
			reportService.deleteUserFavoriteReportById(favorite.getId());
			
		}
		
		return response;
	}
	
	@RequestMapping(value = "/qrcode", method = {RequestMethod.GET})
	@ResponseBody
	public void getQrcode(@RequestParam Integer id, HttpServletResponse response, HttpServletRequest request) 
			throws IOException, WriterException {

		ServletOutputStream outputStream = response.getOutputStream();
		
		reportService.getReportQrcode(id, outputStream);
		
		outputStream.flush();
		outputStream.close();
	}
	
	@RequestMapping("/mylist")
	@ResponseBody
	public ServiceResponse getMyReportList(PagingRequest request, HttpSession session) {
		
		GetMyReportListResponse response = new GetMyReportListResponse();
		
		String userNumber = (String)session.getAttribute(SessionConstants.KEY_USER_NUMBER);
		if (userNumber == null) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("未登录！");
			return response;
		} 
		
		ReportFilter filter = new ReportFilter();
		
		filter.initPagingAndOrdering(request);
		
		filter.setUserNumber(userNumber);
		
		SearchResult<Report> searchResult = reportService.searchReportByFilter(filter);
		
		response.setReports(MyReportVO.fromReportList(searchResult.getResult()));
		response.setPagingResult(searchResult.getPagingResult());
		
		return response;
	}
	
	@RequestMapping("/mytask/finished")
	@ResponseBody
	public ServiceResponse getMyFinishedTasks(PagingRequest request, HttpSession session) {
		
		GetMyFinishedTasksResponse response = new GetMyFinishedTasksResponse();
		
		String userNumber = (String)session.getAttribute(SessionConstants.KEY_USER_NUMBER);
		if (userNumber == null) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("未登录！");
			return response;
		} 
		
		UserTaskFilter filter = new UserTaskFilter();
		
		filter.initPagingAndOrdering(request);
		
		filter.setUserNumber(userNumber);
		filter.setFinished(Bool.Y);
		
		SearchResult<UserTask> searchResult = taskService.searchUserTaskByFilter(filter);
		
		response.setTasks(UserTaskVO.fromUserTaskList(searchResult.getResult()));
		response.setPagingResult(searchResult.getPagingResult());
		
		return response;
	}
	
	@RequestMapping("/myfavorite")
	@ResponseBody
	public ServiceResponse getMyFavorites(PagingRequest request, HttpSession session) {
		
		GetMyFavoritesResponse response = new GetMyFavoritesResponse();
		
		String userNumber = (String)session.getAttribute(SessionConstants.KEY_USER_NUMBER);
		if (userNumber == null) {
			response.setResultCode(ServiceResponseUtility.CODE_ERROR_INNER);
			response.setResultMessage("未登录！");
			return response;
		} 
		
		UserFavoriteReportFilter filter = new UserFavoriteReportFilter();
		
		filter.initPagingAndOrdering(request);
		
		filter.setUserNumber(userNumber);
		
		SearchResult<UserFavoriteReport> searchResult = reportService.searchUserFavoriteReportByFilter(filter);
		
		response.setReports(ReportVO.fromUserFavoriteReportList(searchResult.getResult()));
		response.setPagingResult(searchResult.getPagingResult());
		
		return response;
		
	}
	
}
