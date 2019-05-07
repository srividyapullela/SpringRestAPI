package com.reliant.sm.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.UsageHistoryUtil;

public class WeeklyData {
	
	
	private List<Day> dailyDataList;
	private String weekOfMessage;
	private String weekTotalCost = "0.00";
	private String weekTotalUsage = "0.00";
	private String yearWeekNumber;
	private String dateRange;
	private int month;
	private String dayAndYear;
	
	
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
	public String getYearWeekNumber() {
		return yearWeekNumber;
	}

	public void setYearWeekNumber(String yearWeekNumber) {
		this.yearWeekNumber = yearWeekNumber;
	}

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth() {
		if(StringUtils.isNotBlank(this.weekOfMessage)){
			this.month = DateUtil.getMonthInt((DateUtil.getDate(this.weekOfMessage, "MMMM dd, yyyy")));
		}
	}

	public String getDayAndYear() {
		return dayAndYear;
	}

	public void setDayAndYear(String dayAndYear) {
		StringBuffer weekMessage = new StringBuffer("");
		weekMessage.append(DateUtil.getFormatedDate(UsageHistoryUtil.getDateFromYearWeekNum(dayAndYear), "dd, yyyy"));
		this.dayAndYear = weekMessage.toString();
	}

	
	
	
}
