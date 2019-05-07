package com.reliant.sm.model;

public class EsenseDtlCompTwoDaysData extends UsageHistoryResponse{
	
	private DayUsage previousDayUsage;
	private DayUsage currentDayUsage;
	
	public DayUsage getPreviousDayUsage() {
		return previousDayUsage;
	}
	public void setPreviousDayUsage(DayUsage previousDayUsage) {
		this.previousDayUsage = previousDayUsage;
	}
	public DayUsage getCurrentDayUsage() {
		return currentDayUsage;
	}
	public void setCurrentDayUsage(DayUsage currentDayUsage) {
		this.currentDayUsage = currentDayUsage;
	}
	
	

}
