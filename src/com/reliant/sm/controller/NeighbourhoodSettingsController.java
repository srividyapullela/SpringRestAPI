package com.reliant.sm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reliant.sm.model.NeighbourhoodSettings;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.model.UsageHistoryResponse;
import com.reliant.sm.service.NeighbourhoodSettingsService;
import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;

/**
 * @author bbachin1
 * 
 */
@RestController
@RequestMapping(value = "/homeprofile")
public class NeighbourhoodSettingsController implements CommonConstants{
	
	private static final long serialVersionUID = -5035561415148363444L;

	private static LoggerUtil logger = LoggerUtil.getInstance(NeighbourhoodSettingsController.class);
	
	@Autowired
	private NeighbourhoodSettingsService neighbourhoodSettingsService;
	
	@RequestMapping(value = NEIGHBOURHOOD_SETTINGS, method = RequestMethod.POST)
    public NeighbourhoodSettings getNieghbourhoodSettings(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		NeighbourhoodSettings neighbourhoodSettings = neighbourhoodSettingsService.getNieghbourhoodSettings(usageHistoryRequest);
		logger.logTransaction(NeighbourhoodSettingsController.class.getName(), TX_NEIGHBOURHOOD_SETTINGS, HOME_PROFILE+NEIGHBOURHOOD_SETTINGS, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, neighbourhoodSettings);
		return neighbourhoodSettings;
    }
	
	
	@RequestMapping(value = UPDATE_SETTINGS, method = RequestMethod.POST)
    public UsageHistoryResponse updateNeighbourhoodSettings(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		UsageHistoryResponse response = neighbourhoodSettingsService.updateNeighbourhoodSettings(usageHistoryRequest);
		logger.logTransaction(NeighbourhoodSettingsController.class.getName(), TX_UPDATE_SETTINGS, HOME_PROFILE+UPDATE_SETTINGS, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, response);
		return response;
    }

}
