package com.reliant.sm.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.SmartMainDAO;
import com.reliant.sm.dao.SmartMainHibernateDAO;
import com.reliant.sm.dao.hibernate.dataobject.BDDayWeekUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.BDYearUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.DayUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.DayWeeklyUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.PCDailyUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.PCWeeklyUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.PCYearlyUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.YearUsageDO;
import com.reliant.sm.dao.rowmapper.TemperatureDO;
import com.reliant.sm.exception.UsageHistoryRequestException;
import com.reliant.sm.model.BDYearTempData;
import com.reliant.sm.model.EsenseDetailDailyData;
import com.reliant.sm.model.EsenseDetailMonthlyData;
import com.reliant.sm.model.EsenseDetailWeeklyData;
import com.reliant.sm.model.EsenseDetailYearlyData;
import com.reliant.sm.model.EsenseDtlBDWeeklyUsage;
import com.reliant.sm.model.EsenseDtlBDYearlyUsage;
import com.reliant.sm.model.EsenseDtlCompMonthToMonthData;
import com.reliant.sm.model.EsenseDtlCompTwoDaysData;
import com.reliant.sm.model.EsenseDtlCompTwoMonthsData;
import com.reliant.sm.model.EsenseDtlCompTwoWeeksData;
import com.reliant.sm.model.EsenseDtlCompTwoYearsData;
import com.reliant.sm.model.EsenseDtlPCDailyData;
import com.reliant.sm.model.EsenseDtlPCDataAvailableData;
import com.reliant.sm.model.EsenseDtlPCMonthlyData;
import com.reliant.sm.model.EsenseDtlPCWeeklyData;
import com.reliant.sm.model.EsenseDtlPCYearlyData;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.service.EsenseDetailUsageHistoryService;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;


/**
 * @author bbachin1
 * 
 */
@SuppressWarnings({ "unchecked", "serial" })
@Component
public class EsenseDetailUsageHistoryServiceImpl extends CommonUsageHistoryHelper implements EsenseDetailUsageHistoryService {
	
	
	private static LoggerUtil logger = LoggerUtil.getInstance(EsenseDetailUsageHistoryServiceImpl.class);
	
	@Autowired
	private SmartMainDAO smartMainDAO;
	
	@Autowired
	private SmartMainHibernateDAO smartMainHibernateDAO;
	
	@Autowired
	private EsenseDetailUsageHistoryServiceImplHelper esenseDetailUsageHistoryServiceImplHelper;
	
	@Qualifier("sqlQuerySource") 
	@Autowired
	private ReloadableResourceBundleMessageSource sqlMessage;

	
	@Override
	public EsenseDetailWeeklyData getWeeklyUsageData(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDetailWeeklyData weeklyUsageData = new EsenseDetailWeeklyData();
		try{
			populateYearWeekNumberForWeeklyUsage(usageHistoryRequest,false);
			String queryName = GET_WEEK_DATA_FOR_WEEKNUM_QRY;
			List<DayWeeklyUsageDO> weekUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, UsageHistoryUtil.getParamNamesForInputVal(true,false),
						UsageHistoryUtil.getParamValuesForInputVal(usageHistoryRequest, true,false));
			populatePrevAndNextWeekNumbers(weeklyUsageData,usageHistoryRequest);
			esenseDetailUsageHistoryServiceImplHelper.populateWeeklyUsageData(weeklyUsageData, weekUsageList,usageHistoryRequest);
			checkForPreviousAndNextDataAvaialbleForWeeklyUsage(usageHistoryRequest,weeklyUsageData);
			populateLabelsIfAnyPrevOrNextDataAvailable(weeklyUsageData,usageHistoryRequest);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(weeklyUsageData,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE WEEKLY USAGE:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE WEEKLY USAGE:::::", ex);
			}
			weeklyUsageData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return weeklyUsageData;
	}
	
	
	@Override
	public EsenseDetailDailyData getDailyUsageData(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDetailDailyData esenseDetailDailyData = new EsenseDetailDailyData();
		try{
			populateActualDayForDailyUsage(usageHistoryRequest,false);
			String queryName = GET_DAILY_DATA_FOR_ACTUALDAY_QRY;
			String[] paramNames = UsageHistoryUtil.getParamNamesForInputVal(false,true);
			Object[] paramValues = UsageHistoryUtil.getParamValuesForInputVal(usageHistoryRequest, false,true);
			List<DayUsageDO> dailyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			
			populatePrevAndNextWeekNumbers(esenseDetailDailyData,usageHistoryRequest);
			esenseDetailUsageHistoryServiceImplHelper.populateDailyUsageData(esenseDetailDailyData, dailyUsageList);
			checkForPreviousAndNextDataAvaialbleForDailyUsage(usageHistoryRequest,esenseDetailDailyData);
			populateLabelsIfAnyPrevOrNextDataAvailable(esenseDetailDailyData,usageHistoryRequest);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(esenseDetailDailyData,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE DAILY USAGE:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE DAILY USAGE:::::", ex);
			}
			esenseDetailDailyData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return esenseDetailDailyData;
	}
	
	
	@Override
	public EsenseDetailMonthlyData getMonthlyUsageData(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDetailMonthlyData monthlyUsageData = new EsenseDetailMonthlyData();
		try{
			populateFirstDayAndLastDayFromActualDay(usageHistoryRequest,false);
			String queryName = GET_MONTHLY_DATA_FOR_MONTHRANGE_QRY;
			List<DayWeeklyUsageDO> weekUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, UsageHistoryUtil.getParamNamesForMonthlyUsage(),
						UsageHistoryUtil.getParamValuesForMonthlyUsage(usageHistoryRequest));
			populatePrevAndNextWeekNumbers(monthlyUsageData,usageHistoryRequest);
			esenseDetailUsageHistoryServiceImplHelper.populateMonthlyUsageData(monthlyUsageData, weekUsageList);
			checkForPreviousAndNextDataAvaialbleForMonthlyUsage(usageHistoryRequest,monthlyUsageData);
			populateLabelsIfAnyPrevOrNextDataAvailable(monthlyUsageData,usageHistoryRequest);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(monthlyUsageData,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE MONTHLY USAGE:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE MONTHLY USAGE:::::", ex);
			}
			monthlyUsageData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return monthlyUsageData;
	}
	
	
	@Override
	public EsenseDetailYearlyData getYearlyUsageData(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDetailYearlyData yearlyUsageData = new EsenseDetailYearlyData();
		try{
			populateYearForYearlyUsage(usageHistoryRequest,false);
			String queryName = GET_YEARLY_DATA_FOR_YEAR_QRY;
			String[] paramNames = UsageHistoryUtil.getParamNamesForYearlyUsage();
			Object[] paramValues = UsageHistoryUtil.getParamValuesForYearlyUsage(usageHistoryRequest);
			List<YearUsageDO> yearlyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			
			esenseDetailUsageHistoryServiceImplHelper.populateYearlyUsageData(yearlyUsageData,yearlyUsageList,usageHistoryRequest);
			populatePrevAndNextWeekNumbers(yearlyUsageData,usageHistoryRequest);
			checkForPreviousAndNextDataAvaialbleForYearlyUsage(usageHistoryRequest,yearlyUsageData);
			populateLabelsIfAnyPrevOrNextDataAvailable(yearlyUsageData,usageHistoryRequest);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(yearlyUsageData,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE YEARLY USAGE DATA:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE YEARLY USAGE DATA:::::", ex);
			}
			yearlyUsageData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return yearlyUsageData;
	}
	
	
	@Override
	public EsenseDtlCompTwoWeeksData getTwoWeeksDataForCompare(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDtlCompTwoWeeksData compTwoWeeksData = new EsenseDtlCompTwoWeeksData();
		try{
			populateYearWeekNumberForWeeklyUsage(usageHistoryRequest,true);
			String queryName = GET_TWO_WEEKS_DATA_FOR_WEEKNUM_QRY;
			String[] paramNames = UsageHistoryUtil.getParamNamesForInput("compTwoWeeksQry");
			Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest,null);
			List<DayWeeklyUsageDO> twoWeeksUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			populatePrevAndNextWeekNumbers(compTwoWeeksData,usageHistoryRequest);
			esenseDetailUsageHistoryServiceImplHelper.populateCompareTwoWeeksData(compTwoWeeksData, twoWeeksUsageList,usageHistoryRequest);
			checkForPreviousAndNextDataAvaialbleForTwoWeeksData(usageHistoryRequest,compTwoWeeksData);
			populateLabelsIfAnyPrevOrNextDataAvailable(compTwoWeeksData,usageHistoryRequest);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(compTwoWeeksData,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE YEARLY USAGE DATA:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE YEARLY USAGE DATA:::::", ex);
			}
			compTwoWeeksData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return compTwoWeeksData;
	}

	
	@Override
	public EsenseDtlCompTwoDaysData getTwoDaysDataForCompare(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDtlCompTwoDaysData twoDaysData = new EsenseDtlCompTwoDaysData();
		try{
			populateActualDayForDailyUsage(usageHistoryRequest,true);
			String queryName = GET_TWO_DAYS_DATA_FOR_ACTUALDAY_QRY;
			String[] paramNames = UsageHistoryUtil.getParamNamesForInput("compTwoDaysQry");
			Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest,null);
			List<DayUsageDO> dailyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			
			populatePrevAndNextWeekNumbers(twoDaysData,usageHistoryRequest);
			esenseDetailUsageHistoryServiceImplHelper.populateCompareTwoDaysData(twoDaysData, dailyUsageList,usageHistoryRequest);
			checkForPreviousAndNextDataAvaialbleForTwoDaysData(usageHistoryRequest,twoDaysData);
			populateLabelsIfAnyPrevOrNextDataAvailable(twoDaysData,usageHistoryRequest);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(twoDaysData,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE YEARLY USAGE DATA:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE YEARLY USAGE DATA:::::", ex);
			}
			twoDaysData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return twoDaysData;
	}
	
	
	@Override
	public EsenseDtlCompTwoMonthsData getTwoMonthsDataForCompare(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDtlCompTwoMonthsData twoMonthsData = new EsenseDtlCompTwoMonthsData();
		try{
			populateFirstDayAndLastDayFromActualDay(usageHistoryRequest,true);
			String queryName = GET_MONTHLY_DATA_FOR_MONTHRANGE_QRY;
			List<DayWeeklyUsageDO> monthlyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, UsageHistoryUtil.getParamNamesForMonthlyUsage(),
						UsageHistoryUtil.getParamValuesForMonthlyUsage(usageHistoryRequest));
			
			populatePrevAndNextWeekNumbers(twoMonthsData,usageHistoryRequest);
			esenseDetailUsageHistoryServiceImplHelper.populateCompareTwoMonthlyUsage(twoMonthsData, monthlyUsageList,usageHistoryRequest);
			checkForPreviousAndNextDataAvaialbleForTwoMonthsData(usageHistoryRequest,twoMonthsData);
			populateLabelsIfAnyPrevOrNextDataAvailable(twoMonthsData,usageHistoryRequest);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(twoMonthsData,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE YEARLY USAGE DATA:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE YEARLY USAGE DATA:::::", ex);
			}
			twoMonthsData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return twoMonthsData;
	}
	
	
	@Override
	public EsenseDtlCompTwoYearsData getTwoYearsDataForCompare(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDtlCompTwoYearsData twoYearsData = new EsenseDtlCompTwoYearsData();
		try{
			populateYearForYearlyUsage(usageHistoryRequest,true);
			String queryName = GET_TWO_YEARS_DATA_FOR_YEAR_QRY;
			String[] paramNames = UsageHistoryUtil.getParamNamesForInput("compTwoYearsQry");
			Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest,null);
			List<YearUsageDO> yearlyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			
			esenseDetailUsageHistoryServiceImplHelper.populateCompareTwoYearlyUsage(twoYearsData, yearlyUsageList,usageHistoryRequest);
			populatePrevAndNextWeekNumbers(twoYearsData,usageHistoryRequest);
			checkForPreviousAndNextDataAvaialbleForTwoMonthsData(usageHistoryRequest,twoYearsData);
			populateLabelsIfAnyPrevOrNextDataAvailable(twoYearsData,usageHistoryRequest);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(twoYearsData,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE YEARLY USAGE DATA:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE YEARLY USAGE DATA:::::", ex);
			}
			twoYearsData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return twoYearsData;
	}
	
	
	@Override
	public EsenseDtlPCDailyData getPCDailyUsageData(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDtlPCDailyData pcDailyData = new EsenseDtlPCDailyData();
		try{
			populateActualDayForPCDailyUsage(usageHistoryRequest);
			String queryName = GET_DAILY_DATA_FOR_PCACTUAL_DAY_QRY;
			String[] paramNames = UsageHistoryUtil.getParamNamesForInput("pcDailyUsageQry");
			Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest,"pcDailyUsageQry");
			List <PCDailyUsageDO> pcDailyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			
			populatePrevAndNextWeekNumbers(pcDailyData,usageHistoryRequest);
			esenseDetailUsageHistoryServiceImplHelper.populatePCDailyUsage(pcDailyData, pcDailyUsageList,usageHistoryRequest);
			checkForPreviousAndNextDataAvaialbleForPCDailyUsage(usageHistoryRequest,pcDailyData);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(pcDailyData,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE PC DAILY USAGE DATA:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE PC DAILY USAGE DATA:::::", ex);
			}
			pcDailyData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return pcDailyData;
	}


	@Override
	public EsenseDtlPCWeeklyData getPCWeeklyUsageData(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDtlPCWeeklyData pcWeeklyData = new EsenseDtlPCWeeklyData();
		try{
			populateYearWeekNumberForPCWeeklyUsage(usageHistoryRequest);
			String queryName = GET_PCWEEKLY_USAGE_FOR_WEEKNUM_QRY;
			String[] paramNames = UsageHistoryUtil.getParamNamesForInput("pcWeeklyUsageQry");
			Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest,"pcWeeklyUsageQry");
			List <PCWeeklyUsageDO> pcWeeklyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			
			populatePrevAndNextWeekNumbers(pcWeeklyData,usageHistoryRequest);
			esenseDetailUsageHistoryServiceImplHelper.populatePCWeeklyUsage(pcWeeklyData, pcWeeklyUsageList,usageHistoryRequest);
			checkForPreviousAndNextDataAvaialbleForPCWeeklyUsage(usageHistoryRequest,pcWeeklyData);
			populateLabelsIfAnyPrevOrNextDataAvailable(pcWeeklyData,usageHistoryRequest);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(pcWeeklyData,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE PC WEEKLY USAGE DATA:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE PC WEEKLY USAGE DATA:::::", ex);
			}
			
			pcWeeklyData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return pcWeeklyData;
	}


	@Override
	public EsenseDtlPCMonthlyData getPCMonthlyUsageData(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDtlPCMonthlyData pcMonthlyData = new EsenseDtlPCMonthlyData();
		try{
			populateFirstDayAndLastDayFromPCActualDay(usageHistoryRequest);
			String queryName = GET_PCMONTHLY_USAGE_FOR_MONTHRANGE_QRY;
			String[] paramNames = UsageHistoryUtil.getParamNamesForInput("dateRange");
			Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest,null);
			List <PCWeeklyUsageDO> pcMonthlyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			
			populatePrevAndNextWeekNumbers(pcMonthlyData,usageHistoryRequest);
			esenseDetailUsageHistoryServiceImplHelper.populatePCMonthlyUsage(pcMonthlyData, pcMonthlyUsageList);
			checkForPreviousAndNextDataAvaialbleForPCMonthlyUsage(usageHistoryRequest,pcMonthlyData);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(pcMonthlyData,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE PC MONTHLY USAGE DATA:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE PC MONTHLY USAGE DATA:::::", ex);
			}
			
			pcMonthlyData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return pcMonthlyData;
	}


	@Override
	public EsenseDtlPCYearlyData getPCYearlyUsageData(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDtlPCYearlyData pcYearlyData = new EsenseDtlPCYearlyData();
		try{
			populateYearForPCYearlyUsage(usageHistoryRequest);
			String queryName = GET_PCYEARLY_USAGE_FOR_YEAR_QRY;
			String[] paramNames = UsageHistoryUtil.getParamNamesForInput("pcYearlyUsageQry");
			Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest,"pcYearlyUsageQry");
			List <PCYearlyUsageDO> pcYearlyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			
			populatePrevAndNextWeekNumbers(pcYearlyData,usageHistoryRequest);
			esenseDetailUsageHistoryServiceImplHelper.populatePCYearlyUsage(pcYearlyData, pcYearlyUsageList);
			checkForPreviousAndNextDataAvaialbleForPCYearlyUsage(usageHistoryRequest,pcYearlyData);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(pcYearlyData,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE PC YEARLY USAGE DATA:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE PC YEARLY USAGE DATA:::::", ex);
			}
			pcYearlyData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return pcYearlyData;
	}


	@Override
	public EsenseDtlBDYearlyUsage getBDMonthlyUsageData(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDtlBDYearlyUsage BDYearUsage = new EsenseDtlBDYearlyUsage();
		try{
			populateYearWeekNumberForBDYearlyUsage(usageHistoryRequest);
			String queryName = "BDYearUsage.findByMonthNo";
			String[] paramNames = UsageHistoryUtil.getParamNamesForInput("bdYearlyUsageQry");
			Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest,"bdYearlyUsageQry");
			List <BDYearUsageDO> bdYearlyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			
			populatePrevAndNextWeekNumbers(BDYearUsage,usageHistoryRequest);  
			esenseDetailUsageHistoryServiceImplHelper.populateBDYearlyUsage(BDYearUsage, bdYearlyUsageList);
			checkForPreviousAndNextDataAvaialbleForBDYearlyUsage(usageHistoryRequest,BDYearUsage);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(BDYearUsage,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE BD WEEKLY USAGE DATA:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE BD WEEKLY USAGE DATA:::::", ex);
			}
			BDYearUsage.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return BDYearUsage;
	}

	@Override
	public EsenseDtlBDWeeklyUsage getBDWeeklyUsageData(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDtlBDWeeklyUsage bdWeekUsage = new EsenseDtlBDWeeklyUsage();
		try{
			populateYearWeekNumberForBDWeeklyUsage(usageHistoryRequest);
			String queryName = "BDWeekUsage.findByid";
			String[] paramNames = UsageHistoryUtil.getParamNamesForInput("bdWeeklyUsageQry");
			Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest,"bdWeeklyUsageQry");
			List <BDDayWeekUsageDO> bdWeeklyUsageList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNames,paramValues);
			
			populatePrevAndNextWeekNumbers(bdWeekUsage,usageHistoryRequest);
			esenseDetailUsageHistoryServiceImplHelper.populateBDWeeklyUsage(bdWeekUsage, bdWeeklyUsageList);
			checkForPreviousAndNextDataAvaialbleForBDWeeklyUsage(usageHistoryRequest,bdWeekUsage);
			populateLabelsIfAnyPrevOrNextDataAvailable(bdWeekUsage,usageHistoryRequest);
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				populatePrevAndNextWeekNumbers(bdWeekUsage,usageHistoryRequest);
				logger.info("ERROR OCCURED WHILE GETTING THE BD WEEKLY USAGE DATA:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE BD WEEKLY USAGE DATA:::::", ex);
			}
			bdWeekUsage.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return bdWeekUsage;
	}


	@Override
	public EsenseDtlCompMonthToMonthData compareMonthToMonthUsage(UsageHistoryRequest usageRequest) {
		
	
		EsenseDtlCompMonthToMonthData monthToMonthUsage = new EsenseDtlCompMonthToMonthData();
		try{
			String fromMonthNum = usageRequest.getFromDate();
			String toMonthNum = usageRequest.getToDate();
			if(StringUtils.isBlank(fromMonthNum) || StringUtils.isBlank(toMonthNum)){
				throw new UsageHistoryRequestException("ONE OF THE MONTH NUMBERS IS BLANK IN REQUEST:::FROM DATE::"
						+usageRequest.getFromDate()+"::TO DATE::"+usageRequest.getToDate());
			}else{
				String queryName = COMPARE_MONTH_TO_MONTH_QRY;
				String[] paramNames = UsageHistoryUtil.getParamNamesForInput("compMonthToMonthQry");
				Object[] paramValues = UsageHistoryUtil.getParamValuesForInput(usageRequest,"dateRange");
				List<YearUsageDO> MonthlyUsageList = smartMainHibernateDAO.listQuery(usageRequest, queryName, paramNames,paramValues);
				esenseDetailUsageHistoryServiceImplHelper.populateCompareMonthToMonthData(monthToMonthUsage, MonthlyUsageList,usageRequest);
			}
		}catch(Exception ex){
			if(ex instanceof UsageHistoryRequestException){
				logger.info("ERROR OCCURED WHILE GETTING THE BD WEEKLY USAGE DATA:::::"+ex.getMessage());
			}else{
				logger.error("ERROR OCCURED WHILE GETTING THE BD WEEKLY USAGE DATA:::::", ex);
			}
			monthToMonthUsage.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return monthToMonthUsage;
	}


	@Override
	public BDYearTempData getBDYearTemperatureData(UsageHistoryRequest usageHistoryRequest) {
		
		BDYearTempData bdYearTempData = new BDYearTempData();
		try{
			List <TemperatureDO> tempList = smartMainDAO.getBDYearTemperatureData(usageHistoryRequest.getEsiid(), usageHistoryRequest.getContractAccNumber(),
					usageHistoryRequest.getContractId(), usageHistoryRequest.getYearMonthNum(), sqlMessage.getMessage(GET_BD_YEAR_TEMP_DATA_QRY, null, null));
			esenseDetailUsageHistoryServiceImplHelper.populateBDYearTemperatureData(bdYearTempData,tempList);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE BD WEEKLY USAGE DATA:::::", ex);
			bdYearTempData.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return bdYearTempData;
	}


	@Override
	public EsenseDtlPCDataAvailableData getPCDataAvailable(UsageHistoryRequest usageHistoryRequest) {
		
		EsenseDtlPCDataAvailableData pcDataAvailable = new EsenseDtlPCDataAvailableData();
		try{
			boolean pcDataAvail = checkForCurrentPCDataAvaialble(usageHistoryRequest);
			pcDataAvailable.setDataAvailable(pcDataAvail);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE PC DATA AVAILABLE DATA:::::", ex);
			pcDataAvailable.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return pcDataAvailable;
	}
	
	
}

