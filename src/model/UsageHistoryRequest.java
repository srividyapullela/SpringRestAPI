package com.reliant.sm.model;

public class UsageHistoryRequest {
	
	private String esiid;
	private String contractId;
	private String contractAccNumber;
	private String bpNumber;
	private String yearWeekNumber;
	private String actualDay;
	private String yearMonthNum;
	
	//used these for green button and daily temperature data and for montly usage
	private String fromDate;
	private String toDate;
	
	private boolean comparitionWithPrevYear;
	
	//only for the neighbour settings
	private String sqFootVal;
	private String homeAgeVal;
	private String residenseTypeVal;
	private String residenseVal;
	private String heatingUsedVal;
	private String poolTypeVal;
	
	
	public String getEsiid() {
		return esiid;
	}
	public void setEsiid(String esiid) {
		this.esiid = esiid;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getContractAccNumber() {
		return contractAccNumber;
	}
	public void setContractAccNumber(String contractAccNumber) {
		this.contractAccNumber = contractAccNumber;
	}
	public String getYearWeekNumber() {
		return yearWeekNumber;
	}
	public void setYearWeekNumber(String yearWeekNumber) {
		this.yearWeekNumber = yearWeekNumber;
	}
	public String getActualDay() {
		return actualDay;
	}
	public void setActualDay(String actualDay) {
		this.actualDay = actualDay;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getYearMonthNum() {
		return yearMonthNum;
	}
	public void setYearMonthNum(String yearMonthNum) {
		this.yearMonthNum = yearMonthNum;
	}
	public boolean isComparitionWithPrevYear() {
		return comparitionWithPrevYear;
	}
	public void setComparitionWithPrevYear(boolean comparitionWithPrevYear) {
		this.comparitionWithPrevYear = comparitionWithPrevYear;
	}
	public String getBpNumber() {
		return bpNumber;
	}
	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}
	public String getSqFootVal() {
		return sqFootVal;
	}
	public void setSqFootVal(String sqFootVal) {
		this.sqFootVal = sqFootVal;
	}
	public String getHomeAgeVal() {
		return homeAgeVal;
	}
	public void setHomeAgeVal(String homeAgeVal) {
		this.homeAgeVal = homeAgeVal;
	}
	public String getResidenseTypeVal() {
		return residenseTypeVal;
	}
	public void setResidenseTypeVal(String residenseTypeVal) {
		this.residenseTypeVal = residenseTypeVal;
	}
	public String getResidenseVal() {
		return residenseVal;
	}
	public void setResidenseVal(String residenseVal) {
		this.residenseVal = residenseVal;
	}
	public String getHeatingUsedVal() {
		return heatingUsedVal;
	}
	public void setHeatingUsedVal(String heatingUsedVal) {
		this.heatingUsedVal = heatingUsedVal;
	}
	public String getPoolTypeVal() {
		return poolTypeVal;
	}
	public void setPoolTypeVal(String poolTypeVal) {
		this.poolTypeVal = poolTypeVal;
	}
	
}
