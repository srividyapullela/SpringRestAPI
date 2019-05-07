package com.reliant.sm.service;

import org.springframework.stereotype.Component;

import com.reliant.sm.model.DailyTemperatureData;
import com.reliant.sm.model.PrepayDailyUsageData;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.model.prepay.PrepayDates;

/**
 * @author bbachin1
 * 
 */
@Component
public interface PrepayUsageHistoryService {
	
	public PrepayDailyUsageData getDailyUsageData(UsageHistoryRequest usageHistoryRequest);
	
	public DailyTemperatureData getTemperatureData(UsageHistoryRequest usageHistoryRequest);
	
	public PrepayDates getPrepayDateRange();

}
