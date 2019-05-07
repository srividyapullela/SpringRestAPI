package com.reliant.sm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.SmartMainDAO;
import com.reliant.sm.dao.SmartMainHibernateDAO;
import com.reliant.sm.dao.hibernate.dataobject.DayUsageDO;
import com.reliant.sm.dao.request.DateRange;
import com.reliant.sm.model.GreenButtonDateRange;
import com.reliant.sm.model.GreenButtonUsageData;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.service.GreenButtonDataUsageService;
import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;

/**
 * @author bbachin1
 * 
 */
@Component
public class GreenButtonDataUsageServiceImpl extends CommonUsageHistoryHelper implements GreenButtonDataUsageService{
	
	private static LoggerUtil logger = LoggerUtil.getInstance(GreenButtonDataUsageServiceImpl.class);
	
	@Autowired
	private SmartMainHibernateDAO smartMainHibernateDAO;
	
	@Autowired
	private SmartMainDAO smartMainDAO;
	
	@Autowired
	private GreenButtonDataUsageServiceImplHelper greenButtonDataUsageServiceImplHelper;

	@Override
	public GreenButtonUsageData getGreenButtonData(UsageHistoryRequest usageHistoryRequest) {
		
		GreenButtonUsageData greenButtonData = new GreenButtonUsageData();
		try{
			if(StringUtils.isNotBlank(usageHistoryRequest.getFromDate()) && StringUtils.isNotBlank(usageHistoryRequest.getToDate())){
				String queryName = "GreenButtonUsage.findByActualDayPresent";
				String[] paramNames = UsageHistoryUtil.getParamNamesForGreenButton();
				Object[] paramValues = UsageHistoryUtil.getParamValuesForGreenButton(usageHistoryRequest);
				List<DayUsageDO> dailyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
				greenButtonDataUsageServiceImplHelper.populateGreenButtonUsageUsageData(greenButtonData, dailyUsageList);
			}else{
				greenButtonData.setDataAvailable(false);
				greenButtonData.setErrorMessage("NO START DATE AND END DATE IN THE REQUEST");
			}
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE GREEN BUTTON DATA:::::", ex);
			greenButtonData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return greenButtonData;
	}

	@Override
	public GreenButtonDateRange getGreenButtonDateRange(UsageHistoryRequest usageHistoryRequest) {
		
		GreenButtonDateRange greenButtonDateRange = new GreenButtonDateRange();
		try{
			DateRange dateRange = getDateRangeFromHourlyData(usageHistoryRequest);
			if(StringUtils.isNotBlank(dateRange.getMinDate()) && StringUtils.isNotBlank(dateRange.getMaxDate())){
				greenButtonDateRange.setDataAvailable(true);
				greenButtonDateRange.setEndDate(DateUtil.getFormattedDate("MMddyyyy", "yyyy-MM-dd", dateRange.getMaxDate()));
				greenButtonDateRange.setStartDate(DateUtil.getFormattedDate("MMddyyyy", "yyyy-MM-dd", dateRange.getMinDate()));
			}else{
				logger.info("NO DATE RANGE FOUND FOR THE ESIID:::"+usageHistoryRequest.getEsiid());
				greenButtonDateRange.setErrorMessage("NO DATE RANGE FOUND FOR THE ESIID:::"+usageHistoryRequest.getEsiid());
			}
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE GREEN BUTTON DATE RANGE:::::", ex);
			greenButtonDateRange.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return greenButtonDateRange;
	}

}
