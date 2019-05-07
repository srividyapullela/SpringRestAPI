package com.reliant.sm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reliant.sm.model.DailyTemperatureData;
import com.reliant.sm.model.EsenseDtlCompTwoDaysData;
import com.reliant.sm.model.PrepayDailyUsageData;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.model.prepay.PrepayDates;
import com.reliant.sm.service.EsenseDetailUsageHistoryService;
import com.reliant.sm.service.PrepayUsageHistoryService;
import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;

/**
 * @author bbachin1
 * 
 */
@RestController
@RequestMapping(value = "/prepay")
public class PrepayUsageHistoryController implements CommonConstants{
	
	private static LoggerUtil logger = LoggerUtil.getInstance(PrepayUsageHistoryController.class);
	
	@Autowired
	private EsenseDetailUsageHistoryService esenseDetailUsageHistoryService;
	
	@Autowired
	private PrepayUsageHistoryService prepayUsageHistoryService;
	
	@RequestMapping(value = DAILY_USAGE, method = RequestMethod.POST)
    public PrepayDailyUsageData getDailyUsage(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		PrepayDailyUsageData prepayDailyUsageData = prepayUsageHistoryService.getDailyUsageData(usageHistoryRequest);
		logger.logTransaction(PrepayUsageHistoryController.class.getName(), TX_PDAILY_USAGE, PREPAY+DAILY_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, prepayDailyUsageData);
		return prepayDailyUsageData;
    }
	
	
	@RequestMapping(value = TWO_DAYS_USAGE, method = RequestMethod.POST)
    public EsenseDtlCompTwoDaysData getTwoDaysUsageForCompare(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlCompTwoDaysData esenseDtlCompTwoDaysData = esenseDetailUsageHistoryService.getTwoDaysDataForCompare(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_TWO_DAYS_USAGE, PREPAY+TWO_DAYS_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDtlCompTwoDaysData);
		return esenseDtlCompTwoDaysData;
    }
	
	
	@RequestMapping(value = "/temperatureData", method = RequestMethod.POST)
    public DailyTemperatureData getTemperatureData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		DailyTemperatureData dailyTemperatureData = prepayUsageHistoryService.getTemperatureData(usageHistoryRequest);
		logger.logTransaction(PrepayUsageHistoryController.class.getName(), "PREPAY TEMPERATURE DATA", "/temperatureData", 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, dailyTemperatureData);
		return dailyTemperatureData;
    }
	
	/*@RequestMapping(value = "/dateRange", method = RequestMethod.POST)
    public PrepayDates getPrepayDateRange() {
		PrepayDates prepayDates = prepayUsageHistoryService.getPrepayDateRange();
		logger.logTransaction(PrepayUsageHistoryController.class.getName(), "PREPAY DATE RANGE", "/dateRnage", 
				"", null, prepayDates);
		return prepayDates;
    }*/

}
