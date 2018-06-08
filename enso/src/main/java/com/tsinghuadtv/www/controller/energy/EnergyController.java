package com.tsinghuadtv.www.controller.energy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsinghuadtv.www.controller.api.common.ServiceResponse;
import com.tsinghuadtv.www.controller.energy.api.EnergyLevelVO;
import com.tsinghuadtv.www.controller.energy.api.GetLevelListResponse;
import com.tsinghuadtv.www.entity.energy.EnergyLevel;
import com.tsinghuadtv.www.service.energy.IEnergyService;

@Controller
@RequestMapping("/energy")
public class EnergyController {

	@Autowired
	private IEnergyService energyService;
	
	@RequestMapping("/level/list")
	@ResponseBody
	public ServiceResponse getLevelList() {		
		
		GetLevelListResponse response = new GetLevelListResponse();
		
		List<EnergyLevel> levels = energyService.getAllEnergyLevels();
		
		response.setLevels(EnergyLevelVO.fromEnergyLevelList(levels));
		
		return response;
	}
	
}


