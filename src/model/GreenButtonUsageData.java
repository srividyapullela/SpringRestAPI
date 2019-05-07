package com.reliant.sm.model;

import java.util.List;

public class GreenButtonUsageData{
	
	private List<Hour> hourlyDataList;
	private boolean dataAvailable;
	private String errorMessage;

	public List<Hour> getHourlyDataList() {
		return hourlyDataList;
	}

	public void setHourlyDataList(List<Hour> hourlyDataList) {
		this.hourlyDataList = hourlyDataList;
	}

	public boolean isDataAvailable() {
		return dataAvailable;
	}

	public void setDataAvailable(boolean dataAvailable) {
		this.dataAvailable = dataAvailable;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
