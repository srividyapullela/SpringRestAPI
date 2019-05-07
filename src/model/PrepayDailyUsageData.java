package com.reliant.sm.model;

import com.reliant.sm.util.DateUtil;

public class PrepayDailyUsageData extends UsageHistoryResponse{
	
	private Hour hourlyData;
	private String dayOfMessage;

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
		dayMessage.append(DateUtil.getFormatedDate(DateUtil.getDate(dayOfMessage, "yyyy-MM-dd"), "MMMM dd, yyyy"));
		this.dayOfMessage = dayMessage.toString();
	}

}
