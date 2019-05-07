package com.reliant.sm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reliant.sm.model.ClassicData;
import com.reliant.sm.model.ClassicDetailData;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.service.DashboardClassicUsageHistoryService;
import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;

/**
 * @author bbachin1
 * 
 */
@RestController
@RequestMapping(value = "/classic")
public class DashBoardClassicUsgHistoryController implements CommonConstants{
	
	private static final long serialVersionUID = 1L;

	private static LoggerUtil logger = LoggerUtil.getInstance(DashBoardClassicUsgHistoryController.class);
	
	@Autowired
	private DashboardClassicUsageHistoryService dashboardClassicUsageHistoryService;
	
	@RequestMapping(value = CONSUMPTION_DATA, method = RequestMethod.POST)
    public ClassicData getDashBoardConsumptionData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		ClassicData clasicData = dashboardClassicUsageHistoryService.getDashBoardClassicConsumptionData(usageHistoryRequest);
		logger.logTransaction(DashBoardClassicUsgHistoryController.class.getName(), TX_CONSUMPTION_DATA, CLASSIC+CONSUMPTION_DATA, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, clasicData);
		return clasicData;
    }
	
	
	@RequestMapping(value = DEMAND_DATA, method = RequestMethod.POST)
    public ClassicData getDashBoardDemandData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		ClassicData clasicData = dashboardClassicUsageHistoryService.getDashBoardClassicDemandData(usageHistoryRequest);
		logger.logTransaction(DashBoardClassicUsgHistoryController.class.getName(), TX_DEMAND_DATA, CLASSIC+DEMAND_DATA, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, clasicData);
		return clasicData;
    }
	
	
	@RequestMapping(value = CONSUMPTION_AND_DEMAND_DATA, method = RequestMethod.POST)
    public ClassicDetailData getDashBoardDetailData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		ClassicDetailData classicDetailData = dashboardClassicUsageHistoryService.getDashBoardClassicDetailData(usageHistoryRequest);
		logger.logTransaction(DashBoardClassicUsgHistoryController.class.getName(), TX_CONSUMPTION_AND_DEMAND_DATA, CLASSIC+CONSUMPTION_AND_DEMAND_DATA, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, classicDetailData);
		return classicDetailData;
    }
	
	
	
	

}
