package com.reliant.sm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.SmartMainDAO;
import com.reliant.sm.dao.SmartMainHibernateDAO;
import com.reliant.sm.dao.hibernate.dataobject.BDDayWeekUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.DayWeeklyUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.PCWeeklyUsageDO;
import com.reliant.sm.exception.UsageHistoryRequestException;
import com.reliant.sm.model.DashBoardBDData;
import com.reliant.sm.model.DashBoardPCData;
import com.reliant.sm.model.DashboardUsageAndCost;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.service.DashboardEsenseUsageHistoryService;
import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;
/**
 * @author bbachin1
 * 
 */
@Component
public class DashboardEsenseUsageHistoryServiceImpl extends CommonUsageHistoryHelper implements DashboardEsenseUsageHistoryService{
	
	private static final long serialVersionUID = -1224280032254261493L;

	private static LoggerUtil logger = LoggerUtil.getInstance(DashboardEsenseUsageHistoryServiceImpl.class);
	
	@Autowired
	private SmartMainDAO smartMainDAO;
	
	@Autowired
	private SmartMainHibernateDAO smartMainHibernateDAO;
	
	@Autowired
	private DashboardEsenseUsageHistoryServiceImplHelper doashboardEsenseUsageHistoryServiceImplHelper;

	@Override
	public DashboardUsageAndCost getDashBoardUsageAndCost(UsageHistoryRequest usageHistoryRequest) {
		
		DashboardUsageAndCost dashBoardCostAndUsage = new DashboardUsageAndCost();
		try{
			List<String> weekNumberList = getDefaultYearWeekNumList(usageHistoryRequest);
			String queryName = GET_TWO_WEEKS_DATA_FOR_WEEKNUM_QRY;
			String[] paramNames = UsageHistoryUtil.getParamNamesForInput("compTwoWeeksQry");
			Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest,null);
			List<DayWeeklyUsageDO> twoWeeksUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			dashBoardCostAndUsage = doashboardEsenseUsageHistoryServiceImplHelper.processDashBoardCostAndUsage(twoWeeksUsageList,weekNumberList);
			dashBoardCostAndUsage.setPreviousStrDateList(UsageHistoryUtil.getWeekDateStrListFromWeekNum(weekNumberList.get(1)));
			dashBoardCostAndUsage.setCurrentStrDateList(UsageHistoryUtil.getWeekDateStrListFromWeekNum(weekNumberList.get(0)));
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				logger.info("ERROR OCCURED WHILE GETTING THE DASHBOARD USAGE AND COST:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE DASHBOARD USAGE AND COST:::::", ex);
			}
			dashBoardCostAndUsage.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return dashBoardCostAndUsage;
	}
	
	
	
	@Override
	public DashBoardPCData getDashBoardPCData(UsageHistoryRequest usageHistoryRequest) {
		
		DashBoardPCData dashBoardPCData = new DashBoardPCData();
		try{
			List<String> weekNumberList = getDefaultYearWeekNumList(usageHistoryRequest);
			if(null != weekNumberList && weekNumberList.size() > 0){
				String queryName = "PCWeekUsage.findTwoWeeksid";
				String[] paramNames = UsageHistoryUtil.getParamNamesForInput("compTwoWeeksQry");
				Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest,null);
				List<PCWeeklyUsageDO> twoWeeksPCUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
				dashBoardPCData = doashboardEsenseUsageHistoryServiceImplHelper.processDashBoardUsageCompare(twoWeeksPCUsageList,weekNumberList);
				dashBoardPCData.setPreviousStrDateList(UsageHistoryUtil.getWeekDateStrListFromWeekNum(weekNumberList.get(1)));
				dashBoardPCData.setCurrentStrDateList(UsageHistoryUtil.getWeekDateStrListFromWeekNum(weekNumberList.get(0)));
			}
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE DASHBOARD USAGE COMPARE:::::", ex);
			dashBoardPCData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return dashBoardPCData;
	}





	@Override
	public DashBoardBDData getDashBoardBDData(UsageHistoryRequest usageHistoryRequest) {
		
		DashBoardBDData dashBoardBDData = new DashBoardBDData();
		try{
			List<String> weekNumberList = getDefaultYearWeekNumList(usageHistoryRequest);
			usageHistoryRequest.setFromDate(weekNumberList.get(1));
			usageHistoryRequest.setToDate(weekNumberList.get(0));
			String queryName = "BDWeekUsage.findByDateRange";
			String[] paramNames = UsageHistoryUtil.getParamNamesForInput("dateRange");
			Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest,"dateRange");
			List <BDDayWeekUsageDO> bdWeeklyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			dashBoardBDData.setFromDate(usageHistoryRequest.getFromDate());
			dashBoardBDData.setToDate(usageHistoryRequest.getToDate());
			dashBoardBDData = doashboardEsenseUsageHistoryServiceImplHelper.populateBDDashboardUsage(dashBoardBDData,bdWeeklyUsageList);
			dashBoardBDData.setPreviousStrDateList(UsageHistoryUtil.getWeekDateStrListFromWeekNum(weekNumberList.get(1)));
			dashBoardBDData.setCurrentStrDateList(UsageHistoryUtil.getWeekDateStrListFromWeekNum(weekNumberList.get(0)));
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE DASHBOARD BD DATA:::::", ex);
			dashBoardBDData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return dashBoardBDData;
	}



}
