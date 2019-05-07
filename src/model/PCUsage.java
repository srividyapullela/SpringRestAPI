package com.reliant.sm.model;

public class PCUsage extends Temperature{
	
	private String selfUsage;
	private String avgUsage;
	private String effUsage;
	private String usageComparitionMessage;
	private String dateRange;
	private String yearWeekNum;
	
	
	
	public String getSelfUsage() {
		return selfUsage;
	}
	public void setSelfUsage(String selfUsage) {
		this.selfUsage = selfUsage;
	}
	public String getAvgUsage() {
		return avgUsage;
	}
	public void setAvgUsage(String avgUsage) {
		this.avgUsage = avgUsage;
	}
	public String getEffUsage() {
		return effUsage;
	}
	public void setEffUsage(String effUsage) {
		this.effUsage = effUsage;
	}
	public String getUsageComparitionMessage() {
		return usageComparitionMessage;
	}
	public void setUsageComparitionMessage(String usageComparitionMessage) {
		this.usageComparitionMessage = usageComparitionMessage;
	}
	public String getDateRange() {
		return dateRange;
	}
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}
	public String getYearWeekNum() {
		return yearWeekNum;
	}
	public void setYearWeekNum(String yearWeekNum) {
		this.yearWeekNum = yearWeekNum;
	}
	
}
