package com.reliant.sm.model;



public class DashBoardBDData extends UsageHistoryResponse{
	
	private BreakDownWeekData previousBDData;
	private BreakDownWeekData currentBDData;
	
	public BreakDownWeekData getPreviousBDData() {
		return previousBDData;
	}
	public void setPreviousBDData(BreakDownWeekData previousBDData) {
		this.previousBDData = previousBDData;
	}
	public BreakDownWeekData getCurrentBDData() {
		return currentBDData;
	}
	public void setCurrentBDData(BreakDownWeekData currentBDData) {
		this.currentBDData = currentBDData;
	}
	
	
	
	
}
