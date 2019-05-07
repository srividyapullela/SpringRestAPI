package com.reliant.sm.service;

import org.springframework.stereotype.Component;

import com.reliant.sm.model.DashBoardBDData;
import com.reliant.sm.model.DashBoardPCData;
import com.reliant.sm.model.DashboardUsageAndCost;
import com.reliant.sm.model.UsageHistoryRequest;
/**
 * @author bbachin1
 * 
 */
@Component
public interface DashboardEsenseUsageHistoryService {
	
	
	/**START  Dashboard ESENSE */
	public DashboardUsageAndCost getDashBoardUsageAndCost(UsageHistoryRequest usageHistoryRequest);
	
	public DashBoardPCData getDashBoardPCData(UsageHistoryRequest usageHistoryRequest);
	
	public DashBoardBDData getDashBoardBDData(UsageHistoryRequest usageHistoryRequest);
	/**END  Dashboard ESENSE */
	
	
}
