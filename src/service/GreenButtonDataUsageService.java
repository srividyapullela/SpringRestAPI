package com.reliant.sm.service;

import org.springframework.stereotype.Component;

import com.reliant.sm.model.GreenButtonDateRange;
import com.reliant.sm.model.GreenButtonUsageData;
import com.reliant.sm.model.UsageHistoryRequest;

/**
 * @author bbachin1
 * 
 */
@Component
public interface GreenButtonDataUsageService {
	
	public GreenButtonUsageData getGreenButtonData(UsageHistoryRequest usageHistoryRequest);
	
	public GreenButtonDateRange getGreenButtonDateRange(UsageHistoryRequest usageHistoryRequest);

}
