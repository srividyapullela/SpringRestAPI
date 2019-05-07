package com.reliant.sm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reliant.sm.model.SMSProcessorRestResponse;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.service.SMSProcessorService;
import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;

/**
 * @author bbachin1
 * 
 */
@RestController
@RequestMapping(value = "/sms")
public class SMSProcessorController implements CommonConstants{
	
	private static final long serialVersionUID = -4593915197736181765L;

	private static LoggerUtil logger = LoggerUtil.getInstance(SMSProcessorController.class);
	
	@Autowired
	private SMSProcessorService smsProcessorService;
	
	@RequestMapping(value = BILL_COST_USAGE, method = RequestMethod.POST)
    public SMSProcessorRestResponse getDailyUsage(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		SMSProcessorRestResponse dashBoardCAResponse = smsProcessorService.getCADetailsForDashBoard(usageHistoryRequest);
		logger.logTransaction(PrepayUsageHistoryController.class.getName(), TX_BILL_COST_USAGE, SMS+BILL_COST_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, dashBoardCAResponse);
		return dashBoardCAResponse;
    }
}
