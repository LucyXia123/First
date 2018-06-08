package com.tsinghuadtv.www.model.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.tsinghuadtv.www.controller.api.common.PagingRequest;
import com.tsinghuadtv.www.model.common.PagingData;

public class SearchFilter {
    // 用于控制最多的返回记录数，避免造成数据性能问题
    private static final int MAX_RECORD = 1000;
    
    private Integer limit;
    
    private boolean paged;
    private PagingData pagingData;
    
    private boolean ordered;
    private List<OrderingProperty> orderingProperties;

    public boolean isPaged() {
        return paged;
    }
    
    public void setPaged(boolean paged) {
        this.paged = paged;
    }
    
    public PagingData getPagingData() {
        return pagingData;
    }
    
    public void setPagingData(PagingData pagingData) {
        this.pagingData = pagingData;
    }
    
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public int getLimit() {
        if (limit  == null) {
            return MAX_RECORD;
        }
        return limit;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

	public List<OrderingProperty> getOrderingProperties() {
		return orderingProperties;
	}

	public void setOrderingProperties(List<OrderingProperty> orderingProperties) {
		this.orderingProperties = orderingProperties;
	}

	public String getOrderBy() {
	    String orderBy = OrderByBuilder.getOrderByString(this);
		return orderBy;
	}
	
	public void initPagingAndOrdering(PagingRequest request) {
		if (request.isPaged()) {
			Assert.isTrue(request.getPageSize() <= 50, "max page size is 50.");
			setPaged(true);
			setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));
		}
		
		if (!StringUtils.isEmpty(request.getOrderingBy())) {
			setOrdered(true);
			setOrderingProperties(Arrays.asList(
					new OrderingProperty(1, request.getOrderingBy(), request.ascOrdering())));
		}		
	}
	
}