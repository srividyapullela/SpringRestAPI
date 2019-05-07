package com.reliant.sm.model;

public class ClassicDetailData {
	
	private ClassicData consumptionData;
	private ClassicData demandData;
	private String currentYear;
	private String previousYear;
	private String oldYear;
	private boolean dataAvailable = false;
	private String errorMessage;
	
	public ClassicData getConsumptionData() {
		return consumptionData;
	}
	public void setConsumptionData(ClassicData consumptionData) {
		this.consumptionData = consumptionData;
	}
	public ClassicData getDemandData() {
		return demandData;
	}
	public void setDemandData(ClassicData demandData) {
		this.demandData = demandData;
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
	public String getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	public String getPreviousYear() {
		return previousYear;
	}
	public void setPreviousYear(String previousYear) {
		this.previousYear = previousYear;
	}
	public String getOldYear() {
		return oldYear;
	}
	public void setOldYear(String oldYear) {
		this.oldYear = oldYear;
	}
	
	

}
