package com.reliant.sm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.UsageHistoryUtil;

public class EsenseDtlPCMonthlyData extends UsageHistoryResponse{
	
	private MonthlyUsage selfMonthUsage;
	private MonthlyUsage effMonthUsage;
	private MonthlyUsage avgMonthUsage;
	
	public MonthlyUsage getSelfMonthUsage() {
		return selfMonthUsage;
	}
	public void setSelfMonthUsage(MonthlyUsage selfMonthUsage) {
		this.selfMonthUsage = selfMonthUsage;
	}
	public MonthlyUsage getEffMonthUsage() {
		return effMonthUsage;
	}
	public void setEffMonthUsage(MonthlyUsage effMonthUsage) {
		this.effMonthUsage = effMonthUsage;
	}
	public MonthlyUsage getAvgMonthUsage() {
		return avgMonthUsage;
	}
	public void setAvgMonthUsage(MonthlyUsage avgMonthUsage) {
		this.avgMonthUsage = avgMonthUsage;
	}
	
	
	public void fillGapIfAllDataNotAvailable(){
		
		List<Date> dateList = UsageHistoryUtil.getMonthDateListFromActualDay(super.getActualDay());
		if(null != this.selfMonthUsage && null != this.selfMonthUsage.getDailyDataList() && 
				this.selfMonthUsage.getDailyDataList().size() <= 28){
			List<Day> selfDayList = this.selfMonthUsage.getDailyDataList();
			List<Day> newSelfDayList = new ArrayList<Day>();
			for(Date date : dateList){
				Day day = isThisDayAvailable(selfDayList,date);
				if(null == day){day = new Day();}
				newSelfDayList.add(day);
			}
			this.selfMonthUsage.setDailyDataList(newSelfDayList);
		}
		if(null != this.avgMonthUsage && null != this.avgMonthUsage.getDailyDataList() && 
				this.avgMonthUsage.getDailyDataList().size() <= 28){
			List<Day> avgDayList = this.avgMonthUsage.getDailyDataList();
			List<Day> newAvgDayList = new ArrayList<Day>();
			for(Date date : dateList){
				Day day = isThisDayAvailable(avgDayList,date);
				if(null == day){day = new Day();}
				newAvgDayList.add(day);
			}
			this.avgMonthUsage.setDailyDataList(newAvgDayList);
		}
		if(null != this.effMonthUsage && null != this.effMonthUsage.getDailyDataList() && 
				this.effMonthUsage.getDailyDataList().size() <= 28){
			List<Day> effDayList = this.effMonthUsage.getDailyDataList();
			List<Day> newEffDayList = new ArrayList<Day>();
			for(Date date : dateList){
				Day day = isThisDayAvailable(effDayList,date);
				if(null == day){day = new Day();}
				newEffDayList.add(day);
			}
			this.effMonthUsage.setDailyDataList(newEffDayList);
		}
	}
	
	
	private Day isThisDayAvailable(List<Day> selfDayList, Date date){
		
		String dayNum = DateUtil.getDayMonth(date);
		for(Day day: selfDayList){
			if(StringUtils.equalsIgnoreCase(dayNum, DateUtil.getDayMonth(DateUtil.getDate(day.getActualDay(), "yyyy-MM-dd")))){
				return day;
			}
		}
		return null;
	}
	
	

}
