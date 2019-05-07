package com.reliant.sm.service;

import org.springframework.stereotype.Component;

import com.reliant.sm.model.ProjectedBill;
import com.reliant.sm.model.UsageHistoryRequest;

/**
 * @author bbachin1
 * 
 */

@Component
public interface CustomerPrefService {
	
	/** CUSTOMER  PROJECT BILL */
	public ProjectedBill getProjectBillForContractAccount(UsageHistoryRequest usageHistoryRequest);

}
