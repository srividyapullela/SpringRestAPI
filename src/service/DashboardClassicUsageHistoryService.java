package com.reliant.sm.service;

import org.springframework.stereotype.Component;

import com.reliant.sm.model.ClassicData;
import com.reliant.sm.model.ClassicDetailData;
import com.reliant.sm.model.UsageHistoryRequest;

/**
 * @author bbachin1
 * 
 */
@Component
public interface DashboardClassicUsageHistoryService {
	
	/**START  Dashboard CLASSIC */
	public ClassicData getDashBoardClassicConsumptionData(UsageHistoryRequest usageHistoryRequest);
	
	public ClassicData getDashBoardClassicDemandData(UsageHistoryRequest usageHistoryRequest);
	
	public ClassicDetailData getDashBoardClassicDetailData(UsageHistoryRequest usageHistoryRequest);
	/**END  Dashboard CLASSIC */

}
