package com.reliant.sm.model;

public class EsenseDtlCompMonthToMonthData extends UsageHistoryResponse{
	
	private Month currentMonth;
	private Month comparedMonth;
	
	public Month getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(Month currentMonth) {
		this.currentMonth = currentMonth;
	}
	public Month getComparedMonth() {
		return comparedMonth;
	}
	public void setComparedMonth(Month comparedMonth) {
		this.comparedMonth = comparedMonth;
	}
	
}
