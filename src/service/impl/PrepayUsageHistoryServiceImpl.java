package com.reliant.sm.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.SmartMainDAO;
import com.reliant.sm.dao.SmartMainHibernateDAO;
import com.reliant.sm.dao.hibernate.dataobject.DayUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.DayWeeklyUsageDO;
import com.reliant.sm.model.DailyTemperatureData;
import com.reliant.sm.model.PrepayDailyUsageData;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.model.prepay.PrepayDates;
import com.reliant.sm.service.PrepayUsageHistoryService;
import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;


/**
 * @author bbachin1
 * 
 */
@SuppressWarnings("unchecked")
@Component
public class PrepayUsageHistoryServiceImpl implements PrepayUsageHistoryService{
	
	private static LoggerUtil logger = LoggerUtil.getInstance(PrepayUsageHistoryServiceImpl.class);
	
	@Autowired
	private SmartMainDAO smartMainDAO;
	
	@Autowired
	private SmartMainHibernateDAO smartMainHibernateDAO;
	
	@Autowired
	private PrepayUsageHistoryServiceImplHelper prepayUsageHistoryServiceImplHelper;

	@Override
	public PrepayDailyUsageData getDailyUsageData(UsageHistoryRequest usageHistoryRequest) {
		
		PrepayDailyUsageData prepayDailyData = new PrepayDailyUsageData();
		try{
			String actualDay = usageHistoryRequest.getActualDay();
			String queryName = "";
			String[] paramNames;
			Object[] paramValues;
			if(StringUtils.isNotBlank(actualDay)){
				queryName = "DailyUsage.findByid";
				paramNames = UsageHistoryUtil.getParamNamesForInputVal(false,true);
				paramValues = UsageHistoryUtil.getParamValuesForInputVal(usageHistoryRequest, false,true);
			}else{
				queryName = "DailyUsageDayView.findByid";
				paramNames = UsageHistoryUtil.getParamNames();
				paramValues = UsageHistoryUtil.getParamValues(usageHistoryRequest);
			}
			List<DayUsageDO> dailyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			prepayUsageHistoryServiceImplHelper.populateDailyUsageData(prepayDailyData, dailyUsageList);
			actualDay = StringUtils.isNotBlank(actualDay)?actualDay:prepayDailyData.getActualDay();
			String previousDay = UsageHistoryUtil.getPrevActualDay(actualDay);
			String nextDay = UsageHistoryUtil.getNextActualDay(actualDay);
			logger.debug("ACTUAL DAY::::::::::"+actualDay);
			logger.debug("PREVIOUS DAY::::::::"+previousDay);
			logger.debug("NEXT DAY::::::::::::"+nextDay);
			queryName = "DailyUsageNext.findByid";
			usageHistoryRequest.setActualDay(nextDay);
			Number dailyUsageNextCount = smartMainHibernateDAO.singleResultQuery(usageHistoryRequest, queryName,UsageHistoryUtil.getParamNamesForInputVal(false,true),
					UsageHistoryUtil.getParamValuesForInputVal(usageHistoryRequest, false,true));
			queryName = "DailyUsagePrev.findByid";
			usageHistoryRequest.setActualDay(previousDay);
			Number dailyUsagePrevCount = smartMainHibernateDAO.singleResultQuery(usageHistoryRequest, queryName,UsageHistoryUtil.getParamNamesForInputVal(false,true),
					UsageHistoryUtil.getParamValuesForInputVal(usageHistoryRequest, false,true));
			logger.debug("PREVIOUS DATA AVAILABLE:::::::::::"+ dailyUsagePrevCount.intValue());
			logger.debug("NEXT DATA AVAILABLE:::::::::::::::"+ dailyUsageNextCount.intValue());
			prepayDailyData.setPrevDataAvailable(0 != dailyUsagePrevCount.intValue());
			prepayDailyData.setNextDataAvailable(0 != dailyUsageNextCount.intValue());
			prepayDailyData.setPrevDay(previousDay);
			prepayDailyData.setNextDay(nextDay);
			
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE DASHBOARD USAGE AND COST:::::", ex);
		}
		return prepayDailyData;
	}

	@Override
	public DailyTemperatureData getTemperatureData(UsageHistoryRequest usageHistoryRequest) {
		
		DailyTemperatureData temperatureData = new DailyTemperatureData();
		try{
			String queryName = "GetTempDetails.forDateRange";
			String[] paramNames = UsageHistoryUtil.getParamNamesForTempData();
			Object[] paramValues = UsageHistoryUtil.getParamValuesForTempData(usageHistoryRequest);
			logger.info("paramValues:::::"+paramValues);
			List<DayWeeklyUsageDO> tempList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			prepayUsageHistoryServiceImplHelper.populateDailyTemperatureData(tempList, temperatureData);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE DAILY TEMPERATURE DATA:::::", ex);
		}
		return temperatureData;
	}

	@Override
	public PrepayDates getPrepayDateRange() {
		return UsageHistoryUtil.getPrepayDates();
	}

}
