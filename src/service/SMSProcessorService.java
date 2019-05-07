package com.reliant.sm.service;

import org.springframework.stereotype.Component;

import com.reliant.sm.model.SMSProcessorRestResponse;
import com.reliant.sm.model.UsageHistoryRequest;

/**
 * @author bbachin1
 * 
 */
@Component
public interface SMSProcessorService {
	
	public SMSProcessorRestResponse getCADetailsForDashBoard(UsageHistoryRequest usageHistoryRequest);

}
