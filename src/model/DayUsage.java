package com.reliant.sm.model;

import com.reliant.sm.util.DateUtil;

public class DayUsage {
	
	private Hour hourlyData;
	private String dayOfMessage;
	private String dayAndYear;
	private int month;

	public Hour getHourlyData() {
		return hourlyData;
	}

	public void setHourlyData(Hour hourlyData) {
		this.hourlyData = hourlyData;
	}

	public String getDayOfMessage() {
		return dayOfMessage;
	}

	public void setDayOfMessage(String dayOfMessage) {
		StringBuffer dayMessage = new StringBuffer();
		dayMessage.append(DateUtil.getFormatedDate(DateUtil.getDate(dayOfMessage, "MM/dd/yyyy"), "MMMM dd, yyyy"));
		this.dayOfMessage = dayMessage.toString();
		if(null != this.dayOfMessage){
			this.month = DateUtil.getMonthInt((DateUtil.getDate(this.dayOfMessage, "MMMM dd, yyyy")));
			StringBuffer dayAndYear = new StringBuffer("");
			dayAndYear.append(DateUtil.getFormattedDate("dd, yyyy", "MMMM dd, yyyy", this.dayOfMessage));
			this.dayAndYear = dayAndYear.toString();
		}
	}

	public String getDayAndYear() {
		return dayAndYear;
	}

	public void setDayAndYear(String dayAndYear) {
		this.dayAndYear = dayAndYear;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	
}
