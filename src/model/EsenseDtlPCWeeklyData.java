package com.reliant.sm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.UsageHistoryUtil;

public class EsenseDtlPCWeeklyData extends UsageHistoryResponse{
	
	private WeeklyData selfWeekUsage;
	private WeeklyData effWeekUsage;
	private WeeklyData avgWeekUsage;
	private String avgTempHigh;
	private String avgTempLow;
	
	public WeeklyData getSelfWeekUsage() {
		return selfWeekUsage;
	}
	public void setSelfWeekUsage(WeeklyData selfWeekUsage) {
		this.selfWeekUsage = selfWeekUsage;
	}
	public WeeklyData getEffWeekUsage() {
		return effWeekUsage;
	}
	public void setEffWeekUsage(WeeklyData effWeekUsage) {
		this.effWeekUsage = effWeekUsage;
	}
	public WeeklyData getAvgWeekUsage() {
		return avgWeekUsage;
	}
	public void setAvgWeekUsage(WeeklyData avgWeekUsage) {
		this.avgWeekUsage = avgWeekUsage;
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
		if(null != this.selfWeekUsage && null != this.selfWeekUsage.getDailyDataList() && 
				this.selfWeekUsage.getDailyDataList().size() >0){
			for(Day day : this.selfWeekUsage.getDailyDataList()){
				totalTemp += Integer.valueOf(("null" != day.getTempHigh() && StringUtils.isNotBlank(day.getTempHigh()))?day.getTempHigh():"0");
			}
			this.avgTempHigh = String.valueOf(totalTemp/this.selfWeekUsage.getDailyDataList().size());
		}
	}
	
	
	public void calculateAvgTempLow(){
		
		int totalTemp = 0;
		if(null != this.selfWeekUsage && null != this.selfWeekUsage.getDailyDataList() && 
				this.selfWeekUsage.getDailyDataList().size() >0){
			for(Day day : this.selfWeekUsage.getDailyDataList()){
				totalTemp += Integer.valueOf(("null" != day.getTempLow() && StringUtils.isNotBlank(day.getTempLow()))?day.getTempLow():"0");
			}
			this.avgTempLow = String.valueOf(totalTemp/this.selfWeekUsage.getDailyDataList().size());
		}
	}
	
	
	public void fillGapIfAllDataNotAvailable(){
		
		List<Date> dateList = UsageHistoryUtil.getWeekDateListFromWeekNum(super.getCurrentYearWeekNum());
		if(null != this.selfWeekUsage && null != this.selfWeekUsage.getDailyDataList() && 
				this.selfWeekUsage.getDailyDataList().size() != 7){
			List<Day> selfDayList = this.selfWeekUsage.getDailyDataList();
			List<Day> newSelfDayList = new ArrayList<Day>();
			for(Date date : dateList){
				Day day = isThisDayAvailable(selfDayList,date);
				if(null == day){day = new Day();}
				newSelfDayList.add(day);
			}
			this.selfWeekUsage.setDailyDataList(newSelfDayList);
		}
		if(null != this.avgWeekUsage && null != this.avgWeekUsage.getDailyDataList() && 
				this.avgWeekUsage.getDailyDataList().size() != 7){
			List<Day> avgDayList = this.avgWeekUsage.getDailyDataList();
			List<Day> newAvgDayList = new ArrayList<Day>();
			for(Date date : dateList){
				Day day = isThisDayAvailable(avgDayList,date);
				if(null == day){day = new Day();}
				newAvgDayList.add(day);
			}
			this.avgWeekUsage.setDailyDataList(newAvgDayList);
		}
		if(null != this.effWeekUsage && null != this.effWeekUsage.getDailyDataList() && 
				this.effWeekUsage.getDailyDataList().size() != 7){
			List<Day> effDayList = this.effWeekUsage.getDailyDataList();
			List<Day> newEffDayList = new ArrayList<Day>();
			for(Date date : dateList){
				Day day = isThisDayAvailable(effDayList,date);
				if(null == day){day = new Day();}
				newEffDayList.add(day);
			}
			this.effWeekUsage.setDailyDataList(newEffDayList);
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
