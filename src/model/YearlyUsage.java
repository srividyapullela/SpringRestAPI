package com.reliant.sm.model;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class YearlyUsage {
	
	
	private List<Month> monthlyDataList;
	private String maxYearDataMonth;
	private String previousYear;
	private String nextYear;
	private String totalCost = "0.00";;
	private String totalUsage = "0.00";
	private String year;
	
	
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

	public List<Month> getMonthlyDataList() {
		return monthlyDataList;
	}

	public void setMonthlyDataList(List<Month> monthlyDataList) {
		this.monthlyDataList = monthlyDataList;
	}

	public String getMaxYearDataMonth() {
		return maxYearDataMonth;
	}

	public void setMaxYearDataMonth(String maxYearDataMonth) {
		this.maxYearDataMonth = maxYearDataMonth;
	}

	public String getPreviousYear() {
		return previousYear;
	}

	public void setPreviousYear(String previousYear) {
		this.previousYear = previousYear;
	}

	public String getNextYear() {
		return nextYear;
	}

	public void setNextYear(String nextYear) {
		this.nextYear = nextYear;
	}
	
	public void addCostToTotalCost(String cost){
		if("null" != cost && StringUtils.isNotBlank(cost)){
			BigDecimal bd = new BigDecimal(this.totalCost);
			this.totalCost = bd.add(new BigDecimal(cost)).toString();
		}
	}
	
	public void addUsageToTotalUsage(String usage){
		if("null" != usage && StringUtils.isNotBlank(usage)){
			BigDecimal bd = new BigDecimal(this.totalUsage);
			this.totalUsage = bd.add(new BigDecimal(usage)).toString();
		}
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	

}
