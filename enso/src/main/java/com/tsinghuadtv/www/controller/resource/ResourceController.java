package com.tsinghuadtv.www.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsinghuadtv.www.controller.api.common.ServiceResponse;
import com.tsinghuadtv.www.controller.resource.api.ResourceResponse;
import com.tsinghuadtv.www.controller.resource.api.ResourceVO;
import com.tsinghuadtv.www.entity.report.ReportTopic;
import com.tsinghuadtv.www.entity.report.ReportType;
import com.tsinghuadtv.www.service.report.IReportService;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private IReportService reportService;
	
	@RequestMapping("/topics")
	@ResponseBody
	public ServiceResponse getTopics() {		
		
		ResourceResponse response = new ResourceResponse();
		List<ReportTopic> topics = reportService.getAllReportTopics();
		for (ReportTopic info : topics) {
			ResourceVO vo = new ResourceVO();
			response.getList().add(vo);
			vo.setLabel(info.getName());
			vo.setValue(info.getId());
		}

		return response;
	}
	
	@RequestMapping("/reportTypes")
	@ResponseBody
	public ServiceResponse getReportTypes() {		
		
		ResourceResponse response = new ResourceResponse();
		for (ReportType info : ReportType.values()) {
			ResourceVO vo = new ResourceVO();
			response.getList().add(vo);
			vo.setLabel(info.getDescription());
			vo.setValue(info.getId());
		}

		return response;
	}
}


