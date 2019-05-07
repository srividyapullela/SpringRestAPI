package com.reliant.sm.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.UsageHistoryUtil;

public class EsenseDetailMonthlyData extends UsageHistoryResponse{
	
	private List<Day> dailyDataList;
	private String totalCost = "0.00";
	private String totalUsage = "0.00";
	private String month;
	private int monthNum;
	private String avgTempHigh;
	private String avgTempLow;
	private int year;

	public List<Day> getDailyDataList() {
		return dailyDataList;
	}

	public void setDailyDataList(List<Day> dailyDataList) {
		this.dailyDataList = dailyDataList;
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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
		setMonthNum();
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
	
	public void calAverageTempHigh(){
		
		List<Day> dailyList = this.dailyDataList;
		BigDecimal highTempTtl = new BigDecimal("0");
		if(null != dailyList && dailyList.size() >0){
			for(Day day : dailyList){
				highTempTtl = highTempTtl.add(new BigDecimal("null" != day.getTempHigh() && StringUtils.isNotBlank(day.getTempHigh())?day.getTempHigh():"0"));
			}
			this.avgTempHigh = highTempTtl.divide(new BigDecimal(dailyList.size()), RoundingMode.valueOf(2)).toString();
		}
	}
	
	public void calAverageTempLow(){
		
		List<Day> dailyList = this.dailyDataList;
		BigDecimal lowTempTtl = new BigDecimal("0");
		if(null != dailyList && dailyList.size() >0){
			for(Day day : dailyList){
				lowTempTtl = lowTempTtl.add(new BigDecimal("null" != day.getTempLow() && StringUtils.isNotBlank(day.getTempLow())?day.getTempLow():"0"));
			}
			this.avgTempLow = lowTempTtl.divide(new BigDecimal(dailyList.size()), RoundingMode.valueOf(2)).toString();
		}
	}

	public int getMonthNum() {
		return monthNum;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonthNum() {
		if("null" != this.month && StringUtils.isNotBlank(this.month)){
			if(null != this.dailyDataList && this.dailyDataList.size() >0){
				for(Day day : this.dailyDataList){
					if(null != day.getActualDay()){
						Date date = DateUtil.getDate(this.getDailyDataList().get(0).getActualDay(), "yyyy-MM-dd");
						this.monthNum = DateUtil.getMonthInt(date);
						this.year = DateUtil.getYearInt(date);
						break;
					}
				}
			}
		}
	}
	
	
	public void fillGapIfAllDataNotAvailable(){
		
		List<Date> dateList = UsageHistoryUtil.getMonthDateListFromActualDay(super.getActualDay());
		if(null != this.dailyDataList && this.dailyDataList.size() <=28){
			List<Day> newDayList = new ArrayList<Day>();
			for(Date date : dateList){
				Day day = isThisDayAvailableInDBList(date,this.dailyDataList);
				if(null == day){day = new Day(); day.setActualDay(DateUtil.getFormatedDate(date, "yyyy-MM-dd"));}
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
