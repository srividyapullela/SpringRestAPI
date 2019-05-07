package com.reliant.sm.model;

import java.util.List;

public class DailyTemperatureData extends UsageHistoryResponse{
	
	List<Temperature> tempList;

	public List<Temperature> getTempList() {
		return tempList;
	}

	public void setTempList(List<Temperature> tempList) {
		this.tempList = tempList;
	}
	
	
}
