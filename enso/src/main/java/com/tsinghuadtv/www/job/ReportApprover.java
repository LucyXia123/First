package com.tsinghuadtv.www.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tsinghuadtv.www.entity.report.Report;
import com.tsinghuadtv.www.entity.report.ReportStatus;
import com.tsinghuadtv.www.log.Log;
import com.tsinghuadtv.www.mapper.ReportMapper;
import com.tsinghuadtv.www.model.common.PagingData;
import com.tsinghuadtv.www.model.filter.report.ReportFilter;
import com.tsinghuadtv.www.service.report.IReportService;
import com.tsinghuadtv.www.util.dfa.SensitiveWordFilter;

@Component
@Lazy(false)
public class ReportApprover {
	private static Log LOG = Log.getLog(ReportApprover.class);
	
	@Autowired
	private ReportMapper reportMapper;
	
	@Autowired
	private IReportService reportService;
	
	@Scheduled(cron = "0 0 0 * * ?")
//	@Scheduled(cron = "0 */5 * * * ?")
    public void reportApprove() {
		
		LOG.info("start approve report.");
		
		ReportFilter filter = new ReportFilter();
		
		filter.setStatus(ReportStatus.APPROVING);
        
        int count = reportMapper.countReportByFilter(filter);
        if (count > 0) {
        	filter.setPaged(true);
            
            int pageSize = 10;
            int pageCount = (count + pageSize - 1) / pageSize;
            
            for (int pageNumber = 1; pageNumber <= pageCount; pageNumber ++) {
            	
            	filter.setPagingData(new PagingData(pageNumber, pageSize));
            	
            	List<Report> list = reportMapper.selectReportByFilter(filter);
            	if (list.isEmpty()) {
            		break;
            	}
            	for (Report report : list) {
            		try {
            			if (SensitiveWordFilter.isContaintSensitiveWord(report.getTitle(), SensitiveWordFilter.MAX_MATCH_TYPE)
            					|| SensitiveWordFilter.isContaintSensitiveWord(report.getAbstractInfo(), SensitiveWordFilter.MAX_MATCH_TYPE)
            					|| SensitiveWordFilter.isContaintSensitiveWord(report.getContent(), SensitiveWordFilter.MAX_MATCH_TYPE)) {
            				
            				reportService.reportApproved(report, false);
            			} else {
            				
            				reportService.reportApproved(report, true);
            			}            			
            		} catch (Exception e) {
            			LOG.error("approve report exception, id : " + report.getId(), e);
            		}
            	}
            }
        }
        
        LOG.info("end approve report, total : " + count);
    }
}
