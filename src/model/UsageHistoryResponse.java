package com.reliant.sm.model;

import java.util.List;

public class UsageHistoryResponse extends GenericResponse{
	
	private boolean prevDataAvailable;
	private boolean nextDataAvailable;
	
	private String prevYearWeekNumber;
	private String nextYearWeekNumber;
	private String currentYearWeekNum;
	
	private String prevDay;
	private String nextDay;
	private String actualDay;
	
	private String currentYear;
	private String previousYear;
	private String nextYear;
	
	private String fromDate;
	private String toDate;
	
	private String currentMonthNum;
	private String previousMonthNum;
	private String nextMonthNum;
	
	private List<String> strDateList;
	private List<String> previousStrDateList;
	private List<String> currentStrDateList;
	private String dateRange;
	private boolean pcDataAvailable;
	
	
	
	public String getActualDay() {
		return actualDay;
	}
	public void setActualDay(String actualDay) {
		this.actualDay = actualDay;
	}
	public String getPrevDay() {
		return prevDay;
	}
	public void setPrevDay(String prevDay) {
		this.prevDay = prevDay;
	}
	public String getNextDay() {
		return nextDay;
	}
	public void setNextDay(String nextDay) {
		this.nextDay = nextDay;
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
	public String getPrevYearWeekNumber() {
		return prevYearWeekNumber;
	}
	public void setPrevYearWeekNumber(String prevYearWeekNumber) {
		this.prevYearWeekNumber = prevYearWeekNumber;
	}
	public String getNextYearWeekNumber() {
		return nextYearWeekNumber;
	}
	public void setNextYearWeekNumber(String nextYearWeekNumber) {
		this.nextYearWeekNumber = nextYearWeekNumber;
	}
	public String getCurrentYearWeekNum() {
		return currentYearWeekNum;
	}
	public void setCurrentYearWeekNum(String currentYearWeekNum) {
		this.currentYearWeekNum = currentYearWeekNum;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public String getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	public String getPreviousYear() {
		return previousYear;
	}
	public void setPreviousYear(String previousYear) {
		this.previousYear = previousYear;
	}
	public String getNextYear() {
		return nextYear;
	}
	public void setNextYear(String nextYear) {
		this.nextYear = nextYear;
	}
	public String getCurrentMonthNum() {
		return currentMonthNum;
	}
	public void setCurrentMonthNum(String currentMonthNum) {
		this.currentMonthNum = currentMonthNum;
	}
	public String getPreviousMonthNum() {
		return previousMonthNum;
	}
	public void setPreviousMonthNum(String previousMonthNum) {
		this.previousMonthNum = previousMonthNum;
	}
	public String getNextMonthNum() {
		return nextMonthNum;
	}
	public void setNextMonthNum(String nextMonthNum) {
		this.nextMonthNum = nextMonthNum;
	}
	public List<String> getStrDateList() {
		return strDateList;
	}
	public void setStrDateList(List<String> strDateList) {
		this.strDateList = strDateList;
	}
	public String getDateRange() {
		return dateRange;
	}
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}
	public List<String> getPreviousStrDateList() {
		return previousStrDateList;
	}
	public void setPreviousStrDateList(List<String> previousStrDateList) {
		this.previousStrDateList = previousStrDateList;
	}
	public List<String> getCurrentStrDateList() {
		return currentStrDateList;
	}
	public void setCurrentStrDateList(List<String> currentStrDateList) {
		this.currentStrDateList = currentStrDateList;
	}
	public boolean isPcDataAvailable() {
		return pcDataAvailable;
	}
	public void setPcDataAvailable(boolean pcDataAvailable) {
		this.pcDataAvailable = pcDataAvailable;
	}
}
