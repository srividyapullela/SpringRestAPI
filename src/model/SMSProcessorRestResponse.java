package com.reliant.sm.model;

import java.util.List;

public class SMSProcessorRestResponse {
	
	private String caNumber;
	private String projectedBillAmount;
	private String actualBillAmount;
	private String actualDay;
	private String billStartPeriod;
	private String billEndPeriod;
	private List<Usage> usageList;
	private boolean dataAvailable;
	
	
	public String getProjectedBillAmount() {
		return projectedBillAmount;
	}
	public void setProjectedBillAmount(String projectedBillAmount) {
		this.projectedBillAmount = projectedBillAmount;
	}
	public String getActualBillAmount() {
		return actualBillAmount;
	}
	public void setActualBillAmount(String actualBillAmount) {
		this.actualBillAmount = actualBillAmount;
	}
	public String getActualDay() {
		return actualDay;
	}
	public void setActualDay(String actualDay) {
		this.actualDay = actualDay;
	}
	public String getBillStartPeriod() {
		return billStartPeriod;
	}
	public void setBillStartPeriod(String billStartPeriod) {
		this.billStartPeriod = billStartPeriod;
	}
	public String getBillEndPeriod() {
		return billEndPeriod;
	}
	public void setBillEndPeriod(String billEndPeriod) {
		this.billEndPeriod = billEndPeriod;
	}
	public List<Usage> getUsageList() {
		return usageList;
	}
	public void setUsageList(List<Usage> usageList) {
		this.usageList = usageList;
	}
	public boolean isDataAvailable() {
		return dataAvailable;
	}
	public void setDataAvailable(boolean dataAvailable) {
		this.dataAvailable = dataAvailable;
	}
	public String getCaNumber() {
		return caNumber;
	}
	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}
	
}
