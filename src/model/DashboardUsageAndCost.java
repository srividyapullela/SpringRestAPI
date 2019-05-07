package com.reliant.sm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.UsageHistoryUtil;



public class DashboardUsageAndCost extends UsageHistoryResponse{
	
	private WeeklyData previousWeekUsage;
	private WeeklyData currentWeekUsage;
	
	
	public WeeklyData getPreviousWeekUsage() {
		return previousWeekUsage;
	}
	public void setPreviousWeekUsage(WeeklyData previousWeekUsage) {
		this.previousWeekUsage = previousWeekUsage;
	}
	public WeeklyData getCurrentWeekUsage() {
		return currentWeekUsage;
	}
	public void setCurrentWeekUsage(WeeklyData currentWeekUsage) {
		this.currentWeekUsage = currentWeekUsage;
	}
	
	
	public void fillGapIfAllDataNotAvailable(){
		
		List<Date> currDateList = UsageHistoryUtil.getWeekDateListFromWeekNum(this.currentWeekUsage.getYearWeekNumber());
		List<Date> prevDateList = UsageHistoryUtil.getWeekDateListFromWeekNum(this.previousWeekUsage.getYearWeekNumber());
		if(null != this.currentWeekUsage && null != this.currentWeekUsage.getDailyDataList()
				&& this.currentWeekUsage.getDailyDataList().size() != 7){
			List<Day> newDayList = new ArrayList<Day>();
			for(Date date : currDateList){
				Day day = isThisDayAvailableInDBList(date,this.currentWeekUsage.getDailyDataList());
				if(null == day){day = new Day();}
				newDayList.add(day);
			}
			this.currentWeekUsage.setDailyDataList(newDayList);
		}
		if(null != this.previousWeekUsage && null != this.previousWeekUsage.getDailyDataList()
				&& this.previousWeekUsage.getDailyDataList().size() != 7){
			List<Day> newDayList = new ArrayList<Day>();
			for(Date date : prevDateList){
				Day day = isThisDayAvailableInDBList(date,this.previousWeekUsage.getDailyDataList());
				if(null == day){day = new Day();}
				newDayList.add(day);
			}
			this.previousWeekUsage.setDailyDataList(newDayList);
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
