package com.reliant.sm.model;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.util.DateUtil;

public class DetailUsageAndCost {
	
	private List<Day> dayUsageList;
	private String weekOfMessage;
	private String weekTotalCost = "0.00";
	private String weekTotalUsage = "0.00";
	private String previousWeekNumber;
	private String nextWeekNumber;
	private boolean prevDataAvailable;
	private boolean nextDataAvailable;
	private boolean dataAvailable = false;
	
	
	public List<Day> getDayUsageList() {
		return dayUsageList;
	}
	public void setDayUsageList(List<Day> dayUsageList) {
		this.dayUsageList = dayUsageList;
	}
	public String getWeekOfMessage() {
		return weekOfMessage;
	}
	public void setWeekOfMessage(String weekOfMessage) {
		StringBuffer weekMessage = new StringBuffer("Week Of ");
		weekMessage.append(DateUtil.getFormatedDate(DateUtil.getDate(weekOfMessage, "yyyy-MM-dd"), "MMMM dd, yyyy"));
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
	public String getPreviousWeekNumber() {
		return previousWeekNumber;
	}
	public void setPreviousWeekNumber(String previousWeekNumber) {
		this.previousWeekNumber = previousWeekNumber;
	}
	public String getNextWeekNumber() {
		return nextWeekNumber;
	}
	public void setNextWeekNumber(String nextWeekNumber) {
		this.nextWeekNumber = nextWeekNumber;
	}
	public boolean isPrevDataAvailable() {
		return prevDataAvailable;
	}
	public void setPrevDataAvailable(boolean prevDataAvailable) {
		this.prevDataAvailable = prevDataAvailable;
	}
	public boolean isNextDataAvailable() {
		return nextDataAvailable;
	}
	public void setNextDataAvailable(boolean nextDataAvailable) {
		this.nextDataAvailable = nextDataAvailable;
	}
	public boolean isDataAvailable() {
		return dataAvailable;
	}
	public void setDataAvailable(boolean dataAvailable) {
		this.dataAvailable = dataAvailable;
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
	
}
