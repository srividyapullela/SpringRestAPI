package com.reliant.sm.model;


public class DashBoardPCData extends UsageHistoryResponse{
	
	private PCUsage previousWeekPCData;
	private PCUsage currentWeekPCData;
	
	
	public PCUsage getPreviousWeekPCData() {
		return previousWeekPCData;
	}
	public void setPreviousWeekPCData(PCUsage previousWeekPCData) {
		this.previousWeekPCData = previousWeekPCData;
	}
	public PCUsage getCurrentWeekPCData() {
		return currentWeekPCData;
	}
	public void setCurrentWeekPCData(PCUsage currentWeekPCData) {
		this.currentWeekPCData = currentWeekPCData;
	}
	
}
