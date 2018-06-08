package com.tsinghuadtv.www.util;

import java.util.List;

import com.tsinghuadtv.www.entity.energy.EnergyLevel;

public class EnergyUtil {

	public static Integer getEnergyLevel(int energy, List<EnergyLevel> levels) {
		int myLevel = 0;
		for (EnergyLevel level : levels) {
			if (energy >= level.getEnergy()) {
				myLevel = level.getLevel();
			} else {
				break;
			}
		}
		return myLevel;		
	}
}
