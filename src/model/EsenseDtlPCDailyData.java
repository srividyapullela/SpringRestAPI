package com.reliant.sm.model;

import com.reliant.sm.util.DateUtil;

public class EsenseDtlPCDailyData extends UsageHistoryResponse{
	
	private Hour selfHourlyData;
	private Hour effHourlyData;
	private Hour avgHourlyData;
	private String dayOfMessage;
	
	public Hour getSelfHourlyData() {
		return selfHourlyData;
	}
	public void setSelfHourlyData(Hour selfHourlyData) {
		this.selfHourlyData = selfHourlyData;
	}
	public Hour getEffHourlyData() {
		return effHourlyData;
	}
	public void setEffHourlyData(Hour effHourlyData) {
		this.effHourlyData = effHourlyData;
	}
	public Hour getAvgHourlyData() {
		return avgHourlyData;
	}
	public void setAvgHourlyData(Hour avgHourlyData) {
		this.avgHourlyData = avgHourlyData;
	}
	public String getDayOfMessage() {
		return dayOfMessage;
	}
	public void setDayOfMessage(String dayOfMessage) {
		StringBuffer dayMessage = new StringBuffer();
		dayMessage.append(DateUtil.getFormatedDate(DateUtil.getDate(dayOfMessage, "MM/dd/yyyy"), "MMMM dd, yyyy"));
		this.dayOfMessage = dayMessage.toString();
	}
	
	

}
