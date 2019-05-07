package com.reliant.sm.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;


public class EsenseDtlBDYearlyUsage extends UsageHistoryResponse{
	
	private BDUsage bdUsage;
	private String totalCost;
	private String totalUsage;
	private String month;
	private String maxYearMonthNum;
	private int monthNum;
	private int year;

	public BDUsage getBdUsage() {
		return bdUsage;
	}

	public void setBdUsage(BDUsage bdUsage) {
		this.bdUsage = bdUsage;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public String getTotalUsage() {
		return totalUsage;
	}

	public void setTotalUsage(String totalUsage) {
		this.totalUsage = totalUsage;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getMaxYearMonthNum() {
		return maxYearMonthNum;
	}

	public void setMaxYearMonthNum(String maxYearMonthNum) {
		this.maxYearMonthNum = maxYearMonthNum;
	}

	public void calculatePercentage(){
		
		for(BDSlice bdSlice : this.bdUsage.sliceList){
			if("null" != bdSlice.getUsage() && StringUtils.isNotBlank(bdSlice.getUsage())){
				BigDecimal bd1 = new BigDecimal(bdSlice.getUsage());
				bd1 = bd1.multiply(new BigDecimal("100"));
				float usgPercentage = (bd1.floatValue())/((new BigDecimal(this.totalUsage)).floatValue());
				bdSlice.setUsagePercentage(String.valueOf(usgPercentage));
			}
			
			if("null" != bdSlice.getCost() && StringUtils.isNotBlank(bdSlice.getCost())){
				BigDecimal bd2 = new BigDecimal(bdSlice.getCost());
				bd2 = bd2.multiply(new BigDecimal("100"));
				float costPercentage = (bd2.floatValue())/((new BigDecimal(this.totalCost)).floatValue());
				bdSlice.setCostPercentage(String.valueOf(costPercentage));
			}
		}
	}

	public int getMonthNum() {
		return monthNum;
	}

	public void setMonthNum(int monthNum) {
		this.monthNum = monthNum;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	

}
