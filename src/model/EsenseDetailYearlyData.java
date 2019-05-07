package com.reliant.sm.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.dao.hibernate.dataobject.YearUsageDO;
import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.UsageHistoryUtil;

public class EsenseDetailYearlyData extends UsageHistoryResponse{
	
	private List<Month> monthlyDataList;
	private String maxYearDataMonth;
	private String previousYear;
	private String nextYear;
	private String totalCost = "0.00";;
	private String totalUsage = "0.00";
	private String year;
	private String avgTempHigh;
	private String avgTempLow;
	
	
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
	public String getAvgTempHigh() {
		return avgTempHigh;
	}

	public void setAvgTempHigh(String avgTempHigh) {
		this.avgTempHigh = avgTempHigh;
	}

	public String getAvgTempLow() {
		return avgTempLow;
	}

	public void setAvgTempLow(String avgTempLow) {
		this.avgTempLow = avgTempLow;
	}

	public void calculateAvgTempHigh(){
		
		int totalTemp = 0;
		if(null != this.monthlyDataList && this.monthlyDataList.size() >0){
			for(Month month : this.monthlyDataList){
				totalTemp += Integer.valueOf(("null" != month.getTempHigh() && StringUtils.isNotBlank(month.getTempHigh()))?month.getTempHigh():"0");
			}
			this.avgTempHigh = String.valueOf(totalTemp/this.monthlyDataList.size());
		}
	}
	
	
	public void calculateAvgTempLow(){
		
		int totalTemp = 0;
		if(null != this.monthlyDataList && this.monthlyDataList.size() >0){
			for(Month month : this.monthlyDataList){
				totalTemp += Integer.valueOf(("null" != month.getTempLow() && StringUtils.isNotBlank(month.getTempLow()))?month.getTempLow():"0");
			}
			this.avgTempLow = String.valueOf(totalTemp/this.monthlyDataList.size());
		}
	}
	
	public void fillGapIfAllDataNotAvailable(){
		
		String[] monthAry = UsageHistoryUtil.yearMonthAry;
		if(null != this.monthlyDataList && this.monthlyDataList.size() != 12){
			List<Month> newMonthList = new ArrayList<Month>();
			for(int i=1; i<=12;i++){
				Month month = isMonthDataAvailable(this.monthlyDataList,i);
				if(null == month){month = new Month();month.setMonth(monthAry[i-1]);}
				newMonthList.add(month);
			}
			this.monthlyDataList = newMonthList;
		}
	}
	
	
	private Month isMonthDataAvailable(List<Month> monthList, int monthNum){
		
		for(Month month : monthList){
			if(monthNum == DateUtil.getMonthInt(DateUtil.getDate(month.getActualDay(), "yyyy-MM-dd"))){
				return month;
			}
		}
		return null;
	}
	
}
