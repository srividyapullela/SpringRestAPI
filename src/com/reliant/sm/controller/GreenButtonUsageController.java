package com.reliant.sm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reliant.sm.model.GreenButtonDateRange;
import com.reliant.sm.model.GreenButtonUsageData;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.service.GreenButtonDataUsageService;
import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;

/**
 * @author bbachin1
 * 
 */
@RestController
@RequestMapping(value = "/greenbutton")
public class GreenButtonUsageController implements CommonConstants{
	
	private static final long serialVersionUID = 3980570968009370036L;

	private static LoggerUtil logger = LoggerUtil.getInstance(GreenButtonUsageController.class);
	
	@Autowired
	private GreenButtonDataUsageService greenButtonDataUsageService;
	
	@RequestMapping(value = DATA, method = RequestMethod.POST)
    public GreenButtonUsageData getGreenButtonData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		GreenButtonUsageData greenButtonUsageData = greenButtonDataUsageService.getGreenButtonData(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_DATA, GREEN_BUTTON+DATA, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, greenButtonUsageData);
		return greenButtonUsageData;
    }
	
	
	@RequestMapping(value = DATE_RANGE, method = RequestMethod.POST)
    public GreenButtonDateRange getGreenButtonDateRange(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		GreenButtonDateRange greenButtonDateRange = greenButtonDataUsageService.getGreenButtonDateRange(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_DATE_RANGE, GREEN_BUTTON+DATE_RANGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, greenButtonDateRange);
		return greenButtonDateRange;
    }
	

}
