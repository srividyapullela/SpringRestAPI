package com.reliant.sm.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.dao.hibernate.dataobject.DayWeeklyUsageDO;
import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.UsageHistoryUtil;

public class EsenseDetailWeeklyData extends UsageHistoryResponse{
	
	private List<Day> dailyDataList;
	private String weekOfMessage;
	private String weekTotalCost = "0.00";
	private String weekTotalUsage = "0.00";
	private String yearWeekNum;
	private String avgTempHigh;
	private String avgTempLow;
	private String dayAndYear;
	private int month;
	
	
	public List<Day> addDayUsageList(){
	    if (this.dailyDataList == null) {
	      this.dailyDataList = new ArrayList<Day>();
	    }
	    return this.dailyDataList;
	}
	
	public void addCostToTotalCost(String cost){
		if("null" != cost && StringUtils.isNotBlank(cost)){
			BigDecimal bd = new BigDecimal(this.weekTotalCost);
			this.weekTotalCost = bd.add(new BigDecimal(cost)).toString();
		}
	}
	
	public void addUsageToTotalUsage(String usage){
		if("null" != usage && StringUtils.isNotBlank(usage)){
			BigDecimal bd = new BigDecimal(this.weekTotalUsage);
			this.weekTotalUsage = bd.add(new BigDecimal(usage)).toString();
		}
	}
	
	public List<Day> getDailyDataList() {
		return dailyDataList;
	}

	public void setDailyDataList(List<Day> dailyDataList) {
		this.dailyDataList = dailyDataList;
	}

	public String getWeekOfMessage() {
		return weekOfMessage;
	}

	public void setWeekOfMessage(String weekOfMessage) {
		StringBuffer weekMessage = new StringBuffer("");
		weekMessage.append(DateUtil.getFormatedDate(UsageHistoryUtil.getDateFromYearWeekNum(weekOfMessage), "MMMM dd, yyyy"));
		this.weekOfMessage = weekMessage.toString();
	}

	public String getWeekTotalCost() {
		return weekTotalCost;
	}

	public void setWeekTotalCost(String weekTotalCost) {
		this.weekTotalCost = weekTotalCost;
	}

	public String getWeekTotalUsage() {
		return weekTotalUsage;
	}

	public void setWeekTotalUsage(String weekTotalUsage) {
		this.weekTotalUsage = weekTotalUsage;
	}

	public String getYearWeekNum() {
		return yearWeekNum;
	}

	public void setYearWeekNum(String yearWeekNum) {
		this.yearWeekNum = yearWeekNum;
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
		if(null != this.dailyDataList && this.dailyDataList.size() >0){
			for(Day day : this.dailyDataList){
				totalTemp += Integer.valueOf(("null" != day.getTempHigh() && StringUtils.isNotBlank(day.getTempHigh()))?day.getTempHigh():"0");
			}
			this.avgTempHigh = String.valueOf(totalTemp/this.dailyDataList.size());
		}
	}
	
	
	public void calculateAvgTempLow(){
		
		int totalTemp = 0;
		if(null != this.dailyDataList && this.dailyDataList.size() >0){
			for(Day day : this.dailyDataList){
				totalTemp += Integer.valueOf(("null" != day.getTempLow() && StringUtils.isNotBlank(day.getTempLow()))?day.getTempLow():"0");
			}
			this.avgTempLow = String.valueOf(totalTemp/this.dailyDataList.size());
		}
	}

	public String getDayAndYear() {
		return dayAndYear;
	}

	public void setDayAndYear(String dayAndyear) {
		StringBuffer weekMessage = new StringBuffer("");
		weekMessage.append(DateUtil.getFormatedDate(UsageHistoryUtil.getDateFromYearWeekNum(dayAndyear), "dd, yyyy"));
		this.dayAndYear = weekMessage.toString();
	}
	
	public int getMonth() {
		return month;
	}

	public void setMonth() {
		if(StringUtils.isNotBlank(this.weekOfMessage)){
			this.month = DateUtil.getMonthInt((DateUtil.getDate(this.weekOfMessage, "MMMM dd, yyyy")));
		}
	}
	
	
	public void fillGapIfAllDataNotAvailable(){
		
		List<Date> dateList = UsageHistoryUtil.getWeekDateListFromWeekNum(super.getCurrentYearWeekNum());
		if(null != this.dailyDataList && this.dailyDataList.size() != 7){
			List<Day> newDayList = new ArrayList<Day>();
			for(Date date : dateList){
				Day day = isThisDayAvailableInDBList(date,this.dailyDataList);
				if(null == day){day = new Day();}
				newDayList.add(day);
			}
			this.dailyDataList = newDayList;
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
