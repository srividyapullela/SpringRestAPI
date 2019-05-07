package com.reliant.sm.service;

import org.springframework.stereotype.Component;

import com.reliant.sm.model.NeighbourhoodSettings;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.model.UsageHistoryResponse;

/**
 * @author bbachin1
 * 
 */
@Component
public interface NeighbourhoodSettingsService {
	
	public NeighbourhoodSettings getNieghbourhoodSettings(UsageHistoryRequest usageHistoryRequest);
	
	public UsageHistoryResponse updateNeighbourhoodSettings(UsageHistoryRequest usageHistoryRequest);

}
