package com.reliant.sm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.UsageHistoryUtil;

public class EsenseDtlCompTwoYearsData extends UsageHistoryResponse{
	
	private YearlyUsage currentYearUsage;
	private YearlyUsage previousYearUsage;
	
	public YearlyUsage getCurrentYearUsage() {
		return currentYearUsage;
	}
	public void setCurrentYearUsage(YearlyUsage currentYearUsage) {
		this.currentYearUsage = currentYearUsage;
	}
	public YearlyUsage getPreviousYearUsage() {
		return previousYearUsage;
	}
	public void setPreviousYearUsage(YearlyUsage previousYearUsage) {
		this.previousYearUsage = previousYearUsage;
	}
	
	public void fillGapIfAllDataNotAvailable(){
		
		String[] monthAry = UsageHistoryUtil.yearMonthAry;
		if(null != this.currentYearUsage && null != this.currentYearUsage.getMonthlyDataList() &&
				 this.currentYearUsage.getMonthlyDataList().size() != 12){
			List<Month> newMonthList = new ArrayList<Month>();
			for(int i=1; i<=12;i++){
				Month month = isMonthDataAvailable(this.currentYearUsage.getMonthlyDataList(),i);
				if(null == month){month = new Month();month.setMonth(monthAry[i-1]);}
				newMonthList.add(month);
			}
			this.currentYearUsage.setMonthlyDataList(newMonthList);
		}
		if(null != this.previousYearUsage && null != this.previousYearUsage.getMonthlyDataList() &&
				 this.previousYearUsage.getMonthlyDataList().size() != 12){
			List<Month> newMonthList = new ArrayList<Month>();
			for(int i=1; i<=12;i++){
				Month month = isMonthDataAvailable(this.previousYearUsage.getMonthlyDataList(),i);
				if(null == month){month = new Month();month.setMonth(monthAry[i-1]);}
				newMonthList.add(month);
			}
			this.previousYearUsage.setMonthlyDataList(newMonthList);
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
