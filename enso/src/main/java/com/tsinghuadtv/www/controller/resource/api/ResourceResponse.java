package com.tsinghuadtv.www.controller.resource.api;

import java.util.ArrayList;
import java.util.List;

import com.tsinghuadtv.www.controller.api.common.ServiceResponse;

public class ResourceResponse extends ServiceResponse {
	private static final long serialVersionUID = 1L;

	private List<ResourceVO> list = new ArrayList<ResourceVO>();

	public List<ResourceVO> getList() {
		return list;
	}

	public void setList(List<ResourceVO> list) {
		this.list = list;
	}
}

