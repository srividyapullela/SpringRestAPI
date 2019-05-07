package com.reliant.sm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reliant.sm.model.DashBoardBDData;
import com.reliant.sm.model.DashBoardPCData;
import com.reliant.sm.model.DashboardUsageAndCost;
import com.reliant.sm.model.EsenseDetailDailyData;
import com.reliant.sm.model.Hour;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.service.DashboardEsenseUsageHistoryService;
import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;
/**
 * @author bbachin1
 * 
 */
@RestController
@RequestMapping(value = "/esense")
public class DashBoardEsenseUsgHistoryController implements CommonConstants{
	
	private static final long serialVersionUID = -665479404323256774L;

	private static LoggerUtil logger = LoggerUtil.getInstance(DashBoardEsenseUsgHistoryController.class);
	
	@Autowired
	private DashboardEsenseUsageHistoryService dashboardEsenseUsageHistoryService;
	
	@RequestMapping(value = DB_USAGE_AND_COST, method = RequestMethod.POST)
    public DashboardUsageAndCost getDashBoardUsageAndCost(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		DashboardUsageAndCost dashboardUsageAndCost = dashboardEsenseUsageHistoryService.getDashBoardUsageAndCost(usageHistoryRequest);
		logger.logTransaction(DashBoardEsenseUsgHistoryController.class.getName(), TX_DB_USAGE_AND_COST, ESENSE+DB_USAGE_AND_COST, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, dashboardUsageAndCost);
		return dashboardUsageAndCost;
    }
	
	
	@RequestMapping(value = DB_BD_DATA, method = RequestMethod.POST)
    public DashBoardBDData getDashBoardBDData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		DashBoardBDData dashBoardBDData = dashboardEsenseUsageHistoryService.getDashBoardBDData(usageHistoryRequest);
		logger.logTransaction(DashBoardEsenseUsgHistoryController.class.getName(), TX_DB_BD_DATA, ESENSE+DB_BD_DATA, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, dashBoardBDData);
		return dashBoardBDData;
    }
	
	
	@RequestMapping(value = DB_PC_DATA, method = RequestMethod.POST)
    public DashBoardPCData getDashBoardPCData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		DashBoardPCData dashBoardPCData = dashboardEsenseUsageHistoryService.getDashBoardPCData(usageHistoryRequest);
		logger.logTransaction(DashBoardEsenseUsgHistoryController.class.getName(), TX_DB_PC_DATA, ESENSE+DB_PC_DATA, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, dashBoardPCData);
		return dashBoardPCData;
    }
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public EsenseDetailDailyData GET() {
		//{"esiid":"1008901023815466670104","contractId":"0027599242","contractAccNumber":"000007106651"}
		EsenseDetailDailyData data = new EsenseDetailDailyData();
		List<Hour> hourList = new ArrayList<Hour>();
		
		return data;
    }
	
}
