package com.reliant.sm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.CPDBMainDAO;
import com.reliant.sm.dao.response.ClassicConsumptionResponse;
import com.reliant.sm.model.ClassicData;
import com.reliant.sm.model.ClassicDetailData;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.service.DashboardClassicUsageHistoryService;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;

/**
 * @author bbachin1
 * 
 */
@Component
public class DashboardClassicUsageHistoryServiceImpl implements DashboardClassicUsageHistoryService{
	
	private static LoggerUtil logger = LoggerUtil.getInstance(DashboardClassicUsageHistoryServiceImpl.class);
	
	@Autowired
	private DashboardClassicUsageHistoryServiceImplHelper dashboardClassicUsageHistoryServiceImplHelper;
	
	@Autowired
	private CPDBMainDAO cpdbMainDAO;


	@Override
	public ClassicData getDashBoardClassicConsumptionData(UsageHistoryRequest usageHistoryRequest) {
		
		ClassicData consumptionData = new ClassicData();
		try{
			ClassicConsumptionResponse response = cpdbMainDAO.getConsumptionUsageData(usageHistoryRequest.getContractId());
			dashboardClassicUsageHistoryServiceImplHelper.processClassicConsumptionData(response,consumptionData);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE DASHBOARD CLASSIC CONSUMPTION DATA:::::", ex);
			consumptionData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return consumptionData;
	}
	
	
	@Override
	public ClassicData getDashBoardClassicDemandData(UsageHistoryRequest usageHistoryRequest) {
		
		ClassicData consumptionData = new ClassicData();
		try{
			ClassicConsumptionResponse response = cpdbMainDAO.getDemandUsageData(usageHistoryRequest.getContractId());
			dashboardClassicUsageHistoryServiceImplHelper.processClassicConsumptionData(response,consumptionData);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE DASHBOARD CLASSIC CONSUMPTION DATA:::::", ex);
			consumptionData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return consumptionData;
	}





	@Override
	public ClassicDetailData getDashBoardClassicDetailData(UsageHistoryRequest usageHistoryRequest) {
		
		ClassicDetailData classicDetailData = new ClassicDetailData();
		try{
			ClassicConsumptionResponse response = cpdbMainDAO.getClassicDetailData(usageHistoryRequest.getContractId());
			classicDetailData = dashboardClassicUsageHistoryServiceImplHelper.processClassicDetailData(response,classicDetailData);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE DASHBOARD CLASSIC CONSUMPTION DATA:::::", ex);
			classicDetailData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return classicDetailData;
	}
	
	
	
	
}
