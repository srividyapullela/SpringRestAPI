package com.reliant.sm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.UsageHistoryUtil;

public class EsenseDtlCompTwoMonthsData extends UsageHistoryResponse{
	
	private MonthlyUsage currentMonthUsage;
	private MonthlyUsage previousMonthUsage;
	
	public MonthlyUsage getCurrentMonthUsage() {
		return currentMonthUsage;
	}
	public void setCurrentMonthUsage(MonthlyUsage currentMonthUsage) {
		this.currentMonthUsage = currentMonthUsage;
	}
	public MonthlyUsage getPreviousMonthUsage() {
		return previousMonthUsage;
	}
	public void setPreviousMonthUsage(MonthlyUsage previousMonthUsage) {
		this.previousMonthUsage = previousMonthUsage;
	}
	
	
	public void fillGapIfAllDataNotAvailable(){
		
		List<Date> currMonthDateList = UsageHistoryUtil.getMonthDateListFromActualDay(super.getActualDay());
		List<Date> prevMonthDateList = UsageHistoryUtil.getMonthDateListFromActualDay(super.getPrevDay());
		if(null != this.currentMonthUsage && null != this.currentMonthUsage.getDailyDataList() &&
				this.currentMonthUsage.getDailyDataList().size() <=28){
			List<Day> newDayList = new ArrayList<Day>();
			for(Date date : currMonthDateList){
				Day day = isThisDayAvailableInDBList(date,this.currentMonthUsage.getDailyDataList());
				if(null == day){day = new Day(); day.setActualDay(DateUtil.getFormatedDate(date, "yyyy-MM-dd"));}
				newDayList.add(day);
			}
			this.currentMonthUsage.setDailyDataList(newDayList);
		}
		if(null != this.previousMonthUsage && null != this.previousMonthUsage.getDailyDataList() &&
				this.previousMonthUsage.getDailyDataList().size() <=28){
			List<Day> newDayList = new ArrayList<Day>();
			for(Date date : prevMonthDateList){
				Day day = isThisDayAvailableInDBList(date,this.previousMonthUsage.getDailyDataList());
				if(null == day){day = new Day(); day.setActualDay(DateUtil.getFormatedDate(date, "yyyy-MM-dd"));}
				newDayList.add(day);
			}
			this.previousMonthUsage.setDailyDataList(newDayList);
		}
	}
		
	private Day isThisDayAvailableInDBList(Date date, List<Day> dayList){
		
		String dayNum = DateUtil.getDayMonth(date);
		for(Day day : dayList){
			if(StringUtils.equalsIgnoreCase(dayNum, DateUtil.getDayMonth(day.getActualDay()))){return day;}
		}
		return null;
	}
	
	
}
