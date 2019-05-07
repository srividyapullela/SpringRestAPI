package com.reliant.sm.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.UsageHistoryUtil;

public class EsenseDtlPCYearlyData extends UsageHistoryResponse{
	
	private YearlyUsage selfYearUsage;
	private YearlyUsage avgYearUsage;
	private YearlyUsage effYearUsage;
	private String avgTempHigh;
	private String avgTempLow;
	
	public YearlyUsage getSelfYearUsage() {
		return selfYearUsage;
	}
	public void setSelfYearUsage(YearlyUsage selfYearUsage) {
		this.selfYearUsage = selfYearUsage;
	}
	public YearlyUsage getAvgYearUsage() {
		return avgYearUsage;
	}
	public void setAvgYearUsage(YearlyUsage avgYearUsage) {
		this.avgYearUsage = avgYearUsage;
	}
	public YearlyUsage getEffYearUsage() {
		return effYearUsage;
	}
	public void setEffYearUsage(YearlyUsage effYearUsage) {
		this.effYearUsage = effYearUsage;
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
		if(null != this.selfYearUsage && null != this.selfYearUsage.getMonthlyDataList() && 
				this.selfYearUsage.getMonthlyDataList().size() >0){
			for(Month month : this.selfYearUsage.getMonthlyDataList()){
				totalTemp += Integer.valueOf(("null" != month.getTempHigh() && StringUtils.isNotBlank(month.getTempHigh()))?month.getTempHigh():"0");
			}
			this.avgTempHigh = String.valueOf(totalTemp/this.selfYearUsage.getMonthlyDataList().size());
		}
	}
	
	
	public void calculateAvgTempLow(){
		int totalTemp = 0;
		if(null != this.selfYearUsage && null != this.selfYearUsage.getMonthlyDataList() && 
				this.selfYearUsage.getMonthlyDataList().size() >0){
			for(Month month : this.selfYearUsage.getMonthlyDataList()){
				totalTemp += Integer.valueOf(("null" != month.getTempLow() && StringUtils.isNotBlank(month.getTempLow()))?month.getTempLow():"0");
			}
			this.avgTempLow = String.valueOf(totalTemp/this.selfYearUsage.getMonthlyDataList().size());
		}
	}
	
	
	public void fillGapIfAllDataNotAvailable(){
		
		String[] monthAry = UsageHistoryUtil.yearMonthAry;
		if(null != this.selfYearUsage && null != this.selfYearUsage.getMonthlyDataList() && 
				this.selfYearUsage.getMonthlyDataList().size() != 12){
			List<Month> selfMonthList = this.selfYearUsage.getMonthlyDataList();
			List<Month> newSelfMonthList = new ArrayList<Month>();
			for(int i=1; i<=12; i++){
				Month month = isThisMonthAvailable(selfMonthList,i);
				if(null == month){month = new Month();month.setMonth(monthAry[i-1]);}
				newSelfMonthList.add(month);
			}
			this.selfYearUsage.setMonthlyDataList(newSelfMonthList);
		}
		if(null != this.avgYearUsage && null != this.avgYearUsage.getMonthlyDataList() && 
				this.avgYearUsage.getMonthlyDataList().size() != 12){
			List<Month> avgMonthList = this.avgYearUsage.getMonthlyDataList();
			List<Month> newAvgMonthList = new ArrayList<Month>();
			for(int i=1; i<=12; i++){
				Month month = isThisMonthAvailable(avgMonthList,i);
				if(null == month){month = new Month();month.setMonth(monthAry[i-1]);}
				newAvgMonthList.add(month);
			}
			this.avgYearUsage.setMonthlyDataList(newAvgMonthList);
		}
		if(null != this.effYearUsage && null != this.effYearUsage.getMonthlyDataList() && 
				this.effYearUsage.getMonthlyDataList().size() != 12){
			List<Month> effMonthList = this.effYearUsage.getMonthlyDataList();
			List<Month> newEffMonthList = new ArrayList<Month>();
			for(int i=1; i<=12; i++){
				Month month = isThisMonthAvailable(effMonthList,i);
				if(null == month){month = new Month();month.setMonth(monthAry[i-1]);}
				newEffMonthList.add(month);
			}
			this.effYearUsage.setMonthlyDataList(newEffMonthList);
		}
	}
	
	
	private Month isThisMonthAvailable(List<Month> monthList, int monthNum){
		
		for(Month month: monthList){
			if(monthNum == DateUtil.getMonthInt(DateUtil.getDate(month.getActualDay(), "yyyy-MM-dd"))){
				return month;
			}
		}
		return null;
	}

}
