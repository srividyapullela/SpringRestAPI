package com.reliant.sm.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.SmartMainDAO;
import com.reliant.sm.dao.SmartMainHibernateDAO;
import com.reliant.sm.dao.constants.SmartDaoConstants;
import com.reliant.sm.dao.request.DateRange;
import com.reliant.sm.exception.SmartMainDAOException;
import com.reliant.sm.exception.UsageHistoryRequestException;
import com.reliant.sm.model.EsenseDetailDailyData;
import com.reliant.sm.model.EsenseDetailMonthlyData;
import com.reliant.sm.model.EsenseDetailWeeklyData;
import com.reliant.sm.model.EsenseDetailYearlyData;
import com.reliant.sm.model.EsenseDtlBDWeeklyUsage;
import com.reliant.sm.model.EsenseDtlBDYearlyUsage;
import com.reliant.sm.model.EsenseDtlCompTwoDaysData;
import com.reliant.sm.model.EsenseDtlCompTwoMonthsData;
import com.reliant.sm.model.EsenseDtlCompTwoWeeksData;
import com.reliant.sm.model.EsenseDtlCompTwoYearsData;
import com.reliant.sm.model.EsenseDtlPCDailyData;
import com.reliant.sm.model.EsenseDtlPCMonthlyData;
import com.reliant.sm.model.EsenseDtlPCWeeklyData;
import com.reliant.sm.model.EsenseDtlPCYearlyData;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;


/**
 * @author bbachin1
 * 
 */
@Component
public class CommonUsageHistoryHelper implements CommonConstants, SmartDaoConstants{
	
	private static final long serialVersionUID = 3289867361514176031L;

	private static LoggerUtil logger = LoggerUtil.getInstance(CommonUsageHistoryHelper.class);
	
	@Autowired
	private SmartMainDAO smartMainDAO;
	
	@Autowired
	private SmartMainHibernateDAO smartMainHibernateDAO;
	
	@Qualifier("sqlQuerySource") 
	@Autowired
	private ReloadableResourceBundleMessageSource sqlMessage;
	
	
	public void populatePrevAndNextWeekNumbers(Object obj,UsageHistoryRequest usageHistoryRequest){
		
		if(obj instanceof EsenseDetailWeeklyData){
			String yearWeekNum = usageHistoryRequest.getYearWeekNumber();
			String actualDay = UsageHistoryUtil.getActualDayFromYearWeekNum(yearWeekNum);
			
			((EsenseDetailWeeklyData) obj).setActualDay(actualDay);
			((EsenseDetailWeeklyData) obj).setPrevDay(UsageHistoryUtil.getPrevActualDay(actualDay));
			((EsenseDetailWeeklyData) obj).setNextDay(UsageHistoryUtil.getNextActualDay(actualDay));
			
			((EsenseDetailWeeklyData) obj).setCurrentYear(UsageHistoryUtil.getCurrentYear(actualDay));
			((EsenseDetailWeeklyData) obj).setPreviousYear(UsageHistoryUtil.getPreviousYear(actualDay, 1));
			((EsenseDetailWeeklyData) obj).setNextYear(UsageHistoryUtil.getNextYear(actualDay, 1));
			
			((EsenseDetailWeeklyData) obj).setYearWeekNum(yearWeekNum);
			((EsenseDetailWeeklyData) obj).setCurrentYearWeekNum(yearWeekNum);
			((EsenseDetailWeeklyData) obj).setPrevYearWeekNumber(UsageHistoryUtil.getPreviousYearWeek(yearWeekNum));
			((EsenseDetailWeeklyData) obj).setNextYearWeekNumber(UsageHistoryUtil.getNextYearWeek(yearWeekNum));
		}else if(obj instanceof EsenseDetailDailyData){
			String actualDay = usageHistoryRequest.getActualDay();
			
			((EsenseDetailDailyData) obj).setPrevDay(UsageHistoryUtil.getPrevActualDay(actualDay));
			((EsenseDetailDailyData) obj).setNextDay(UsageHistoryUtil.getNextActualDay(actualDay));
			((EsenseDetailDailyData) obj).setActualDay(actualDay);
			
			((EsenseDetailDailyData) obj).setCurrentYear(UsageHistoryUtil.getCurrentYear(actualDay));
			((EsenseDetailDailyData) obj).setPreviousYear(UsageHistoryUtil.getPreviousYear(actualDay, 1));
			((EsenseDetailDailyData) obj).setNextYear(UsageHistoryUtil.getNextYear(actualDay, 1));
			
			String yearWeekNum = UsageHistoryUtil.getYearWeekNumFromActualDay(actualDay);
			((EsenseDetailDailyData) obj).setCurrentYearWeekNum(yearWeekNum);
			((EsenseDetailDailyData) obj).setPrevYearWeekNumber(UsageHistoryUtil.getPreviousYearWeek(yearWeekNum));
			((EsenseDetailDailyData) obj).setNextYearWeekNumber(UsageHistoryUtil.getNextYearWeek(yearWeekNum));
			
		}else if(obj instanceof EsenseDetailMonthlyData){
			String actualDay = usageHistoryRequest.getActualDay();
			String yearWeekNum = UsageHistoryUtil.getYearWeekNumFromActualDay(actualDay);
			
			((EsenseDetailMonthlyData) obj).setActualDay(actualDay);
			((EsenseDetailMonthlyData) obj).setPrevDay(UsageHistoryUtil.getPrevActualDayForMonthlyUsage(actualDay));
			((EsenseDetailMonthlyData) obj).setNextDay(UsageHistoryUtil.getNextActualDayForMonthlyUsage(actualDay));
			
			((EsenseDetailMonthlyData) obj).setCurrentYear(UsageHistoryUtil.getCurrentYear(actualDay));
			((EsenseDetailMonthlyData) obj).setPreviousYear(UsageHistoryUtil.getPreviousYear(actualDay, 1));
			((EsenseDetailMonthlyData) obj).setNextYear(UsageHistoryUtil.getNextYear(actualDay, 1));
			
			((EsenseDetailMonthlyData) obj).setCurrentYearWeekNum(yearWeekNum);
			((EsenseDetailMonthlyData) obj).setPrevYearWeekNumber(UsageHistoryUtil.getPreviousYearWeek(yearWeekNum));
			((EsenseDetailMonthlyData) obj).setNextYearWeekNumber(UsageHistoryUtil.getNextYearWeek(yearWeekNum));
		
		}else if(obj instanceof EsenseDetailYearlyData){
			
			String year = usageHistoryRequest.getYearMonthNum();
			
			((EsenseDetailYearlyData) obj).setPreviousYear(String.valueOf(Integer.parseInt(year)-1));
			((EsenseDetailYearlyData) obj).setNextYear(String.valueOf(Integer.parseInt(year)+1));
			((EsenseDetailYearlyData) obj).setCurrentYear(year);
			
			String defaultYearWeekNum = getDefaultYearWeekNumFromDB(usageHistoryRequest);
			((EsenseDetailYearlyData) obj).setCurrentYearWeekNum(defaultYearWeekNum);
			((EsenseDetailYearlyData) obj).setPrevYearWeekNumber(UsageHistoryUtil.getPreviousYearWeek(defaultYearWeekNum));
			((EsenseDetailYearlyData) obj).setNextYearWeekNumber(UsageHistoryUtil.getNextYearWeek(defaultYearWeekNum));
			
			
			String actualDay = getDefaultActualDayFromDB(usageHistoryRequest);
			((EsenseDetailYearlyData) obj).setActualDay(actualDay);
			((EsenseDetailYearlyData) obj).setPrevDay(UsageHistoryUtil.getPrevActualDay(actualDay));
			((EsenseDetailYearlyData) obj).setNextDay(UsageHistoryUtil.getNextActualDay(actualDay));
		
		}else if(obj instanceof EsenseDtlCompTwoWeeksData){
			String yearWeekNum = usageHistoryRequest.getYearWeekNumber();
			String actualDay = UsageHistoryUtil.getActualDayFromYearWeekNum(yearWeekNum);
			
			((EsenseDtlCompTwoWeeksData) obj).setActualDay(actualDay);
			((EsenseDtlCompTwoWeeksData) obj).setPrevDay(UsageHistoryUtil.getPrevActualDay(actualDay));
			((EsenseDtlCompTwoWeeksData) obj).setNextDay(UsageHistoryUtil.getNextActualDay(actualDay));
			
			((EsenseDtlCompTwoWeeksData) obj).setCurrentYear(UsageHistoryUtil.getCurrentYear(actualDay));
			((EsenseDtlCompTwoWeeksData) obj).setPreviousYear(UsageHistoryUtil.getPreviousYear(actualDay, 1));
			((EsenseDtlCompTwoWeeksData) obj).setNextYear(UsageHistoryUtil.getNextYear(actualDay, 1));
			
			((EsenseDtlCompTwoWeeksData) obj).setCurrentYearWeekNum(yearWeekNum);
			((EsenseDtlCompTwoWeeksData) obj).setNextYearWeekNumber(UsageHistoryUtil.getNextYearWeek(yearWeekNum));
			((EsenseDtlCompTwoWeeksData) obj).setPrevYearWeekNumber(UsageHistoryUtil.getPreviousYearWeek(yearWeekNum));
		}else if(obj instanceof EsenseDtlCompTwoDaysData){
			String actualDay = usageHistoryRequest.getActualDay();
			
			((EsenseDtlCompTwoDaysData) obj).setPrevDay(UsageHistoryUtil.getPrevActualDay(actualDay));
			((EsenseDtlCompTwoDaysData) obj).setNextDay(UsageHistoryUtil.getNextActualDay(actualDay));
			((EsenseDtlCompTwoDaysData) obj).setActualDay(actualDay);
			
			((EsenseDtlCompTwoDaysData) obj).setCurrentYear(UsageHistoryUtil.getCurrentYear(actualDay));
			((EsenseDtlCompTwoDaysData) obj).setPreviousYear(UsageHistoryUtil.getPreviousYear(actualDay, 1));
			((EsenseDtlCompTwoDaysData) obj).setNextYear(UsageHistoryUtil.getNextYear(actualDay, 1));
			
			String yearWeekNum = UsageHistoryUtil.getYearWeekNumFromActualDay(actualDay);
			((EsenseDtlCompTwoDaysData) obj).setCurrentYearWeekNum(yearWeekNum);
			((EsenseDtlCompTwoDaysData) obj).setPrevYearWeekNumber(yearWeekNum);
			((EsenseDtlCompTwoDaysData) obj).setNextYearWeekNumber(yearWeekNum);
			
		}else if(obj instanceof EsenseDtlCompTwoMonthsData){
			String actualDay = usageHistoryRequest.getActualDay();
			String yearWeekNum = UsageHistoryUtil.getYearWeekNumFromActualDay(actualDay);
			
			((EsenseDtlCompTwoMonthsData) obj).setActualDay(actualDay);
			((EsenseDtlCompTwoMonthsData) obj).setPrevDay(UsageHistoryUtil.getPrevActualDayForMonthlyUsage(actualDay));
			((EsenseDtlCompTwoMonthsData) obj).setNextDay(UsageHistoryUtil.getNextActualDayForMonthlyUsage(actualDay));
			
			((EsenseDtlCompTwoMonthsData) obj).setCurrentYear(UsageHistoryUtil.getCurrentYear(actualDay));
			((EsenseDtlCompTwoMonthsData) obj).setPreviousYear(UsageHistoryUtil.getPreviousYear(actualDay, 1));
			((EsenseDtlCompTwoMonthsData) obj).setNextYear(UsageHistoryUtil.getNextYear(actualDay, 1));
			
			((EsenseDtlCompTwoMonthsData) obj).setCurrentYearWeekNum(yearWeekNum);
			((EsenseDtlCompTwoMonthsData) obj).setPrevYearWeekNumber(UsageHistoryUtil.getPreviousYearWeek(yearWeekNum));
			((EsenseDtlCompTwoMonthsData) obj).setNextYearWeekNumber(UsageHistoryUtil.getNextYearWeek(yearWeekNum));
			
		}else if(obj instanceof EsenseDtlCompTwoYearsData){
			String year = usageHistoryRequest.getYearMonthNum();
			
			((EsenseDtlCompTwoYearsData) obj).setPreviousYear(String.valueOf(Integer.parseInt(year)-1));
			((EsenseDtlCompTwoYearsData) obj).setNextYear(String.valueOf(Integer.parseInt(year)+1));
			((EsenseDtlCompTwoYearsData) obj).setCurrentYear(year);
			
			String defaultYearWeekNum = getDefaultYearWeekNumFromDB(usageHistoryRequest);
			((EsenseDtlCompTwoYearsData) obj).setPrevYearWeekNumber(UsageHistoryUtil.getPreviousYearWeek(defaultYearWeekNum));
			((EsenseDtlCompTwoYearsData) obj).setNextYearWeekNumber(UsageHistoryUtil.getNextYearWeek(defaultYearWeekNum));
			((EsenseDtlCompTwoYearsData) obj).setCurrentYearWeekNum(defaultYearWeekNum);
			
			String actualDay = getDefaultActualDayFromDB(usageHistoryRequest);
			((EsenseDtlCompTwoYearsData) obj).setPrevDay(UsageHistoryUtil.getPrevActualDay(actualDay));
			((EsenseDtlCompTwoYearsData) obj).setNextDay(UsageHistoryUtil.getNextActualDay(actualDay));
			((EsenseDtlCompTwoYearsData) obj).setActualDay(actualDay);
		
		}else if(obj instanceof EsenseDtlBDYearlyUsage){
			String actualDay = UsageHistoryUtil.getActualDayFromYearMonthNumber(usageHistoryRequest.getYearMonthNum());
			
			((EsenseDtlBDYearlyUsage) obj).setCurrentMonthNum(usageHistoryRequest.getYearMonthNum());
			((EsenseDtlBDYearlyUsage) obj).setPreviousMonthNum(UsageHistoryUtil.getPreviousYearMonthNumber(actualDay));
			((EsenseDtlBDYearlyUsage) obj).setNextMonthNum(UsageHistoryUtil.getNextYearMonthNumber(actualDay));
			
			String yearWeekNum = UsageHistoryUtil.getYearWeekNumFromActualDay(actualDay);
			((EsenseDtlBDYearlyUsage) obj).setCurrentYearWeekNum(yearWeekNum);
			((EsenseDtlBDYearlyUsage) obj).setPrevYearWeekNumber(UsageHistoryUtil.getPreviousYearWeek(yearWeekNum));
			((EsenseDtlBDYearlyUsage) obj).setNextYearWeekNumber(UsageHistoryUtil.getNextYearWeek(yearWeekNum));
			
			((EsenseDtlBDYearlyUsage) obj).setCurrentYear(UsageHistoryUtil.getCurrentYear(actualDay));
			((EsenseDtlBDYearlyUsage) obj).setPreviousYear(UsageHistoryUtil.getPreviousYear(actualDay, 1));
			((EsenseDtlBDYearlyUsage) obj).setNextYear(UsageHistoryUtil.getNextYear(actualDay, 1));
			
			((EsenseDtlBDYearlyUsage) obj).setActualDay(actualDay);
			((EsenseDtlBDYearlyUsage) obj).setPrevDay(UsageHistoryUtil.getPrevActualDay(actualDay));
			((EsenseDtlBDYearlyUsage) obj).setNextDay(UsageHistoryUtil.getNextActualDay(actualDay));
			
		}else if(obj instanceof EsenseDtlBDWeeklyUsage){
			
			String yearWeekNum = usageHistoryRequest.getYearWeekNumber();
			
			((EsenseDtlBDWeeklyUsage) obj).setCurrentYearWeekNum(yearWeekNum);
			((EsenseDtlBDWeeklyUsage) obj).setPrevYearWeekNumber(UsageHistoryUtil.getPreviousYearWeek(yearWeekNum));
			((EsenseDtlBDWeeklyUsage) obj).setNextYearWeekNumber(UsageHistoryUtil.getNextYearWeek(yearWeekNum));
			
			String actualDay = UsageHistoryUtil.getActualDayFromYearWeekNum(yearWeekNum);
			
			((EsenseDtlBDWeeklyUsage) obj).setPreviousMonthNum(UsageHistoryUtil.getPreviousYearMonthNumber(actualDay));
			((EsenseDtlBDWeeklyUsage) obj).setNextMonthNum(UsageHistoryUtil.getNextYearMonthNumber(actualDay));
			((EsenseDtlBDWeeklyUsage) obj).setCurrentMonthNum((UsageHistoryUtil.getCurrentYearMonthNumber(actualDay)));
			
			((EsenseDtlBDWeeklyUsage) obj).setCurrentYear(UsageHistoryUtil.getCurrentYear(actualDay));
			((EsenseDtlBDWeeklyUsage) obj).setPreviousYear(UsageHistoryUtil.getPreviousYear(actualDay, 1));
			((EsenseDtlBDWeeklyUsage) obj).setNextYear(UsageHistoryUtil.getNextYear(actualDay, 1));
			
			((EsenseDtlBDWeeklyUsage) obj).setActualDay(actualDay);
			((EsenseDtlBDWeeklyUsage) obj).setPrevDay(UsageHistoryUtil.getPrevActualDay(actualDay));
			((EsenseDtlBDWeeklyUsage) obj).setNextDay(UsageHistoryUtil.getNextActualDay(actualDay));
		
		}else if(obj instanceof EsenseDtlPCYearlyData){
			
			String year = usageHistoryRequest.getYearMonthNum();
			
			((EsenseDtlPCYearlyData) obj).setCurrentYear(year);
			((EsenseDtlPCYearlyData) obj).setPreviousYear(String.valueOf(Integer.parseInt(year)-1));
			((EsenseDtlPCYearlyData) obj).setNextYear(String.valueOf(Integer.parseInt(year)+1));
		}else if(obj instanceof EsenseDtlPCWeeklyData){
			
			String yearWeekNum = usageHistoryRequest.getYearWeekNumber();
			String actualDay = UsageHistoryUtil.getActualDayFromYearWeekNum(yearWeekNum);
			((EsenseDtlPCWeeklyData) obj).setActualDay(actualDay);
			((EsenseDtlPCWeeklyData) obj).setPrevDay(UsageHistoryUtil.getPrevActualDay(actualDay));
			((EsenseDtlPCWeeklyData) obj).setNextDay(UsageHistoryUtil.getNextActualDay(actualDay));
			
			((EsenseDtlPCWeeklyData) obj).setCurrentYear(UsageHistoryUtil.getCurrentYear(actualDay));
			((EsenseDtlPCWeeklyData) obj).setPreviousYear(UsageHistoryUtil.getPreviousYear(actualDay, 1));
			((EsenseDtlPCWeeklyData) obj).setNextYear(UsageHistoryUtil.getNextYear(actualDay, 1));
			
			((EsenseDtlPCWeeklyData) obj).setCurrentYearWeekNum(yearWeekNum);
			((EsenseDtlPCWeeklyData) obj).setPrevYearWeekNumber(UsageHistoryUtil.getPreviousYearWeek(yearWeekNum));
			((EsenseDtlPCWeeklyData) obj).setNextYearWeekNumber(UsageHistoryUtil.getNextYearWeek(yearWeekNum));
		}else if(obj instanceof EsenseDtlPCDailyData){
			
			String actualDay = usageHistoryRequest.getActualDay();
			
			((EsenseDtlPCDailyData) obj).setPrevDay(UsageHistoryUtil.getPrevActualDay(actualDay));
			((EsenseDtlPCDailyData) obj).setNextDay(UsageHistoryUtil.getNextActualDay(actualDay));
			((EsenseDtlPCDailyData) obj).setActualDay(actualDay);
			
			((EsenseDtlPCDailyData) obj).setCurrentYear(UsageHistoryUtil.getCurrentYear(actualDay));
			((EsenseDtlPCDailyData) obj).setPreviousYear(UsageHistoryUtil.getPreviousYear(actualDay, 1));
			((EsenseDtlPCDailyData) obj).setNextYear(UsageHistoryUtil.getNextYear(actualDay, 1));
			
			String yearWeekNum = UsageHistoryUtil.getYearWeekNumFromActualDay(actualDay);
			((EsenseDtlPCDailyData) obj).setCurrentYearWeekNum(yearWeekNum);
			((EsenseDtlPCDailyData) obj).setPrevYearWeekNumber(UsageHistoryUtil.getPreviousYearWeek(yearWeekNum));
			((EsenseDtlPCDailyData) obj).setNextYearWeekNumber(UsageHistoryUtil.getNextYearWeek(yearWeekNum));
		}else if(obj instanceof EsenseDtlPCMonthlyData){
			
			String actualDay = usageHistoryRequest.getActualDay();
			((EsenseDtlPCMonthlyData) obj).setPrevDay(UsageHistoryUtil.getPrevActualDay(actualDay));
			((EsenseDtlPCMonthlyData) obj).setNextDay(UsageHistoryUtil.getNextActualDay(actualDay));
			((EsenseDtlPCMonthlyData) obj).setActualDay(actualDay);
			
			((EsenseDtlPCMonthlyData) obj).setCurrentYear(UsageHistoryUtil.getCurrentYear(actualDay));
			((EsenseDtlPCMonthlyData) obj).setPreviousYear(UsageHistoryUtil.getPreviousYear(actualDay, 1));
			((EsenseDtlPCMonthlyData) obj).setNextYear(UsageHistoryUtil.getNextYear(actualDay, 1));
			
			String yearWeekNum = UsageHistoryUtil.getYearWeekNumFromActualDay(actualDay);
			((EsenseDtlPCMonthlyData) obj).setCurrentYearWeekNum(yearWeekNum);
			((EsenseDtlPCMonthlyData) obj).setPrevYearWeekNumber(UsageHistoryUtil.getPreviousYearWeek(yearWeekNum));
			((EsenseDtlPCMonthlyData) obj).setNextYearWeekNumber(UsageHistoryUtil.getNextYearWeek(yearWeekNum));
		}
		
	}
	
	
	private String getDefaultYearWeekNumFromDB(UsageHistoryRequest usageHistoryRequest ){		
		String defaultWeekNum = "";
		try{
			defaultWeekNum = smartMainDAO.getDefaultWeek(usageHistoryRequest.getEsiid());
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING DEFAULT WEEK NUMBER::::",ex);
		}
		return defaultWeekNum;
	}
	
	
	private String getDefaultActualDayFromDB(UsageHistoryRequest usageHistoryRequest ){
		
		String defaultActualDay = "";
		try{
			defaultActualDay = smartMainDAO.getDefaultDay(usageHistoryRequest.getEsiid());
			defaultActualDay= DateUtil.getFormatedDate(DateUtil.getDate(defaultActualDay, "yyyy-MM-dd"), "MM/dd/yyyy");
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING DEFAULT ACTUAL DAY::::",ex);
		}
		return defaultActualDay;
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForWeeklyUsage(UsageHistoryRequest usageHistoryRequest,EsenseDetailWeeklyData weeklyUsageData) throws SmartMainDAOException{
		String queryName = sqlMessage.getMessage(IS_PREV_WEEK_DATA_AVAILABLE_QRY, null, null);
		usageHistoryRequest.setYearWeekNumber(weeklyUsageData.getPrevYearWeekNumber());
		boolean isPrevDataAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getYearWeekNumber());
		queryName = sqlMessage.getMessage(IS_NEXT_WEEK_DATA_AVAILABLE_QRY, null, null);
		usageHistoryRequest.setYearWeekNumber(weeklyUsageData.getNextYearWeekNumber());
		boolean isNextDataAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getYearWeekNumber());
		weeklyUsageData.setPrevDataAvailable(isPrevDataAvail);
		weeklyUsageData.setNextDataAvailable(isNextDataAvail);
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+weeklyUsageData.isPrevDataAvailable()+"::NEXT DATA AVAILABLE::::::::::::"+
				weeklyUsageData.isNextDataAvailable()+":::FOR THE WEEK NUM:::"+weeklyUsageData.getPrevYearWeekNumber());
		if(!weeklyUsageData.isDataAvailable() && (weeklyUsageData.isPrevDataAvailable() || weeklyUsageData.isNextDataAvailable())){
			populateLabelsIfAnyPrevOrNextDataAvailable(weeklyUsageData,usageHistoryRequest);
		}
		usageHistoryRequest.setYearWeekNumber(weeklyUsageData.getCurrentYearWeekNum());
	}
	
	
	protected void populateYearWeekNumberForWeeklyUsage(UsageHistoryRequest usageHistoryRequest,boolean forTwoWeeks) throws UsageHistoryRequestException, SmartMainDAOException{
		
		if(null != usageHistoryRequest && StringUtils.isNotBlank(usageHistoryRequest.getYearWeekNumber())){
			logger.info("YEAR WEEK NUMBER FOUND IN THE REQUEST::::GETTING WEEKLY USAGE FOR THE SPECIFIC WEEK NUMBER:::"+usageHistoryRequest.getYearWeekNumber());
		}else{
			List<String> weekNumberList = getDefaultYearWeekNumList(usageHistoryRequest);
			if(null != weekNumberList && weekNumberList.isEmpty()){
				throw new UsageHistoryRequestException("WEEK NUMBER NOT FOUND FOR ESIID:::"+usageHistoryRequest.getEsiid());
			}
			usageHistoryRequest.setYearWeekNumber(weekNumberList.get(0));
			logger.info("YEAR WEEK NUMBER NOT FOUND IN THE REQUEST::::GETTING WEEKLY USAGE FOR THE DEFAULT WEEK NUMBER:::"+usageHistoryRequest.getYearWeekNumber());
		}
		if(forTwoWeeks){
			String previousWeekNum = usageHistoryRequest.isComparitionWithPrevYear()?
					UsageHistoryUtil.getYearWeekNumberFromPreviousYear(usageHistoryRequest.getYearWeekNumber()):UsageHistoryUtil.getPreviousYearWeek(usageHistoryRequest.getYearWeekNumber());
			usageHistoryRequest.setFromDate(previousWeekNum);
			usageHistoryRequest.setToDate(usageHistoryRequest.getYearWeekNumber());
			logger.info("GETTING TWO WEEKS USAGE FOR THE WEEK NUMBERS::::"+usageHistoryRequest.getYearWeekNumber()+"::AND::"+previousWeekNum);
		}
	}
	
	protected List<String> getDefaultYearWeekNumList(UsageHistoryRequest usageRequest) throws SmartMainDAOException, UsageHistoryRequestException{
		
		String sqlQuery = sqlMessage.getMessage(GET_DEFAULT_WEEK_NUMBERS_LIST_QRY, null, null);
		List<String> weekNumberList = smartMainDAO.getDefaultNumberListForESIID(sqlQuery, usageRequest.getEsiid(), 
				usageRequest.getContractAccNumber(), usageRequest.getContractId());
		if(null != weekNumberList && weekNumberList.isEmpty()){
			throw new UsageHistoryRequestException("WEEK NUMBER LIST NOT FOUND FOR ESIID:::"+usageRequest.getEsiid());
		}
		usageRequest.setToDate(weekNumberList.size() >0?weekNumberList.get(0):"");
		usageRequest.setFromDate(weekNumberList.size() >1?weekNumberList.get(1):"");
		logger.info("YEAR WEEK NUMBER LIST FROM THE DB:::"+weekNumberList);
		return weekNumberList;
	}
	
	
	protected List<String> getPCDefaultYearWeekNumbers(UsageHistoryRequest usageHistoryRequest) throws SmartMainDAOException, UsageHistoryRequestException{
		
		List<String> weekNumberList = smartMainDAO.getDefaultWeekNumbersForPCData(usageHistoryRequest.getEsiid());
		if(null != weekNumberList && weekNumberList.isEmpty()){
			throw new UsageHistoryRequestException("WEEK NUMBER LIST NOT FOUND FOR ESIID:::"+usageHistoryRequest.getEsiid());
		}
		logger.info("YEAR WEEK NUMBER LIST FROM THE DB:::"+weekNumberList);
		
		return weekNumberList;
	}
	
	
	protected List<String> getBDDefaultYearWeekNumbers(UsageHistoryRequest usageHistoryRequest) throws SmartMainDAOException, UsageHistoryRequestException{
		
		List<String> weekNumberList = smartMainDAO.getDefaultWeekNumbersForBDData(usageHistoryRequest.getEsiid());
		if(null != weekNumberList && weekNumberList.isEmpty()){
			throw new UsageHistoryRequestException("WEEK NUMBER LIST NOT FOUND FOR ESIID:::"+usageHistoryRequest.getEsiid());
		}
		logger.info("YEAR WEEK NUMBER LIST FROM THE DB:::"+weekNumberList);
		return weekNumberList;
	}
	
	
	protected void populateActualDayForDailyUsage(UsageHistoryRequest usageHistoryRequest,boolean forTwoDays) throws UsageHistoryRequestException, SmartMainDAOException{
		
		 if(null != usageHistoryRequest && StringUtils.isNotBlank(usageHistoryRequest.getActualDay())){
			logger.info("ACTUAL DAY FOUND IN THE REQUEST::::GETTING DAILY USAGE FOR THE SPECIFIC DAY:::"+usageHistoryRequest.getActualDay());
		}else{
			String actualDayFromDB = smartMainDAO.getDefaultDay(usageHistoryRequest.getEsiid());
			if(null == actualDayFromDB || StringUtils.isBlank(actualDayFromDB)){
				throw new UsageHistoryRequestException("ACTUAL DAY NOT FOUND FOR ESIID:::"+usageHistoryRequest.getEsiid());
			}
			String actualDay = DateUtil.getFormatedDate(DateUtil.getDate(actualDayFromDB, "yyyy-MM-dd"), "MM/dd/yyyy");
			usageHistoryRequest.setActualDay(actualDay);
			logger.info("ACTUAL DAY NOT FOUND IN THE REQUEST::::GETTING DAILY USAGE FOR THE ACTUAL DAY FROM DB:::"+actualDay);
		}
		if(forTwoDays){
			String previousDay = usageHistoryRequest.isComparitionWithPrevYear()?
					UsageHistoryUtil.getActualDayFromPreviousYear(usageHistoryRequest.getActualDay()):UsageHistoryUtil.getPrevActualDay(usageHistoryRequest.getActualDay());
			usageHistoryRequest.setFromDate(previousDay);
			usageHistoryRequest.setToDate(usageHistoryRequest.getActualDay());
			logger.info("GETTING TWO DAYS USAGE FOR THE DAY NUMBERS::::"+usageHistoryRequest.getFromDate()+"::AND::"+usageHistoryRequest.getToDate());
		}
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForDailyUsage(UsageHistoryRequest usageHistoryRequest,EsenseDetailDailyData esenseDetailDailyData) throws SmartMainDAOException{
		
		String queryName = sqlMessage.getMessage(IS_NEXT_DAY_DATA_AVAILABLE_QRY, null, null);
		boolean isNextDataAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getActualDay());
		queryName = sqlMessage.getMessage(IS_PREV_DAY_DATA_AVAILABLE_QRY, null, null);
		boolean isPrevDataAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getActualDay());
		
		esenseDetailDailyData.setPrevDataAvailable(isPrevDataAvail);
		esenseDetailDailyData.setNextDataAvailable(isNextDataAvail);
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+esenseDetailDailyData.isPrevDataAvailable()+":::FOR THE DAY:::"+esenseDetailDailyData.getPrevDay());
		logger.info("NEXT DATA AVAILABLE::::::::::::"+esenseDetailDailyData.isPrevDataAvailable()+":::FOR THE DAY:::"+esenseDetailDailyData.getNextDay());
	}
	
	
	protected void populateFirstDayAndLastDayFromActualDay(UsageHistoryRequest usageHistoryRequest, boolean isForTwoMonths) throws UsageHistoryRequestException, SmartMainDAOException{
		
		populateActualDayForDailyUsage(usageHistoryRequest,false);
		String firstDayMonth = isForTwoMonths?UsageHistoryUtil.getStartDateForPreviousMonth(usageHistoryRequest.getActualDay()):UsageHistoryUtil.getMonthFirstDateFromActualDay(usageHistoryRequest.getActualDay());
		String lastDayMonth = UsageHistoryUtil.getMonthEndDateFromActualDay(usageHistoryRequest.getActualDay());
		
		logger.info("ACTUAL DAY FROM THE REQUEST::::::"+usageHistoryRequest.getActualDay()+"::CALCULATED FIRST DAY OF MONTH::::::"+firstDayMonth
				+"::::CALCULATED FIRST DAY OF MONTH::::::"+lastDayMonth);
		usageHistoryRequest.setFromDate(firstDayMonth);
		usageHistoryRequest.setToDate(lastDayMonth);
	}
	
	protected void checkForPreviousAndNextDataAvaialbleForMonthlyUsage(UsageHistoryRequest usageHistoryRequest, EsenseDetailMonthlyData monthlyUsageData) throws SmartMainDAOException{
		
		String queryName = sqlMessage.getMessage(IS_PREV_MONTH_DATA_AVAILABLE_QRY, null, null);
		boolean isPrevAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getFromDate());
		queryName = sqlMessage.getMessage(IS_NEXT_MONTH_DATA_AVAILABLE_QRY, null, null);
		boolean isNextAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getToDate());
		monthlyUsageData.setPrevDataAvailable(isPrevAvail);
		monthlyUsageData.setNextDataAvailable(isNextAvail);
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+monthlyUsageData.isPrevDataAvailable()+":::BEFORE THE DATE:::"+usageHistoryRequest.getFromDate());
		logger.info("NEXT DATA AVAILABLE::::::::::::"+monthlyUsageData.isPrevDataAvailable()+":::AFTER THE DATE:::"+usageHistoryRequest.getToDate());
	}
	
	
	protected void populateYearForYearlyUsage(UsageHistoryRequest usageHistoryRequest,boolean forTwoYears) throws UsageHistoryRequestException, SmartMainDAOException{
		
		if(null != usageHistoryRequest && StringUtils.isNotBlank(usageHistoryRequest.getYearMonthNum())){
			logger.info("ACTUAL YEAR FOUND IN THE REQUEST::::GETTING YEARLY USAGE FOR THE SPECIFIC YEAR:::"+usageHistoryRequest.getYearMonthNum());
		}else{
			String actualYearFromDB = smartMainDAO.getDefaultYear(usageHistoryRequest.getEsiid());
			if(null == actualYearFromDB || StringUtils.isBlank(actualYearFromDB)){
				throw new UsageHistoryRequestException("DEFAULT YEAR NOT FOUND FOR ESIID:::"+usageHistoryRequest.getEsiid());
			}
			usageHistoryRequest.setYearMonthNum(actualYearFromDB);
			logger.info("YEAR NOT FOUND IN THE REQUEST::::GETTING YEARLY USAGE FOR THE DEFAULT YEAR FROM DB:::"+actualYearFromDB);
		}
		if(forTwoYears){
			String previousYear =  UsageHistoryUtil.getPreviousYearNumber(usageHistoryRequest.getYearMonthNum());
			usageHistoryRequest.setFromDate(previousYear);
			usageHistoryRequest.setToDate(usageHistoryRequest.getYearMonthNum());
			logger.info("GETTING TWO YEARS USAGE FOR THE YEAR NUMBERS::::"+usageHistoryRequest.getFromDate()+"::AND::"+usageHistoryRequest.getToDate());
		}
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForYearlyUsage(UsageHistoryRequest usageHistoryRequest,EsenseDetailYearlyData yearlyUsageData) throws SmartMainDAOException{
		
		String queryName = sqlMessage.getMessage(IS_PREV_YEAR_DATA_AVAILABLE_QRY, null, null);
		usageHistoryRequest.setYearMonthNum(yearlyUsageData.getPreviousYear());
		boolean isPrevAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getYearMonthNum());
		queryName = sqlMessage.getMessage(IS_NEXT_YEAR_DATA_AVAILABLE_QRY, null, null);
		usageHistoryRequest.setYearMonthNum(yearlyUsageData.getNextYear());
		boolean isNextAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getYearMonthNum());
		usageHistoryRequest.setYearMonthNum(yearlyUsageData.getPreviousYear());
		queryName = sqlMessage.getMessage(GET_MAX_YEAR_MONTH_NUM_QRY, null, null);
		usageHistoryRequest.setYearMonthNum(yearlyUsageData.getCurrentYear());
		String maxYearDataMonth = getStringParamValue(usageHistoryRequest,queryName,usageHistoryRequest.getYearMonthNum());
		yearlyUsageData.setPrevDataAvailable(isPrevAvail);
		yearlyUsageData.setNextDataAvailable(isNextAvail);
		yearlyUsageData.setMaxYearDataMonth(maxYearDataMonth);
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+yearlyUsageData.isPrevDataAvailable()+":::FOR THE YEAR:::"+yearlyUsageData.getPreviousYear());
		logger.info("NEXT DATA AVAILABLE::::::::::::"+yearlyUsageData.isPrevDataAvailable()+":::FOR THE YEAR:::"+yearlyUsageData.getNextYear());
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForTwoWeeksData(UsageHistoryRequest usageHistoryRequest,EsenseDtlCompTwoWeeksData compTwoWeeksData) throws SmartMainDAOException{
		
		String PrevYearWeekNum = UsageHistoryUtil.getPreviousYearWeek(compTwoWeeksData.getPrevYearWeekNumber());
		String queryName = sqlMessage.getMessage(IS_PREV_WEEK_DATA_AVAILABLE_QRY, null, null);
		usageHistoryRequest.setYearWeekNumber(PrevYearWeekNum);
		boolean isPrevDataAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getYearWeekNumber());
		String nextYearWeekNum = UsageHistoryUtil.getNextYearWeek(compTwoWeeksData.getCurrentYearWeekNum());
		queryName = sqlMessage.getMessage(IS_NEXT_WEEK_DATA_AVAILABLE_QRY, null, null);
		usageHistoryRequest.setYearWeekNumber(nextYearWeekNum);
		boolean isNextDataAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getYearWeekNumber());
		compTwoWeeksData.setPrevDataAvailable(isPrevDataAvail);
		compTwoWeeksData.setNextDataAvailable(isNextDataAvail);
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+compTwoWeeksData.isPrevDataAvailable()+":::FOR THE WEEK NUM:::"+PrevYearWeekNum);
		logger.info("NEXT DATA AVAILABLE::::::::::::"+compTwoWeeksData.isPrevDataAvailable()+":::FOR THE WEEK NUM:::"+nextYearWeekNum);
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForTwoDaysData(UsageHistoryRequest usageHistoryRequest,EsenseDtlCompTwoDaysData twoDaysData) throws SmartMainDAOException{
		
		String dayBeforePreviousDay = UsageHistoryUtil.getPrevActualDay(usageHistoryRequest.getFromDate());
		String nextDay = UsageHistoryUtil.getNextActualDay(usageHistoryRequest.getToDate());
		String queryName = sqlMessage.getMessage(IS_NEXT_DAY_DATA_AVAILABLE_QRY, null, null);
		boolean isNextDataAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getActualDay());
		queryName = sqlMessage.getMessage(IS_PREV_DAY_DATA_AVAILABLE_QRY, null, null);
		boolean isPrevDataAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getActualDay());
		twoDaysData.setPrevDataAvailable(isPrevDataAvail);
		twoDaysData.setNextDataAvailable(isNextDataAvail);
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+twoDaysData.isPrevDataAvailable()+":::FOR THE ACTUAL DAY:::"+dayBeforePreviousDay);
		logger.info("NEXT DATA AVAILABLE::::::::::::"+twoDaysData.isPrevDataAvailable()+":::FOR THE ACTUAL DAY:::"+nextDay);
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForTwoMonthsData(UsageHistoryRequest usageHistoryRequest,EsenseDtlCompTwoMonthsData twoMonthsData) throws SmartMainDAOException{
		
		String queryName = "MonthlyUsage.findPrevMonthId";
		Number monthlyUsagePrevCount = smartMainHibernateDAO.singleResultQuery(usageHistoryRequest, queryName,UsageHistoryUtil.getParamNamesForMonthlyUsage(true),
				UsageHistoryUtil.getParamValuesForMonthlyUsage(usageHistoryRequest,true));
		queryName = "MonthlyUsage.findNextMonthId";
		Number monthlyUsageNextCount = smartMainHibernateDAO.singleResultQuery(usageHistoryRequest, queryName,UsageHistoryUtil.getParamNamesForMonthlyUsage(false),
				UsageHistoryUtil.getParamValuesForMonthlyUsage(usageHistoryRequest,false));
		twoMonthsData.setPrevDataAvailable(0 != monthlyUsagePrevCount.intValue());
		twoMonthsData.setNextDataAvailable(0 != monthlyUsageNextCount.intValue());
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+twoMonthsData.isPrevDataAvailable()+":::BEFORE THE DATE:::"+usageHistoryRequest.getFromDate());
		logger.info("NEXT DATA AVAILABLE::::::::::::"+twoMonthsData.isPrevDataAvailable()+":::AFTER THE DATE:::"+usageHistoryRequest.getToDate());
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForTwoMonthsData(UsageHistoryRequest usageHistoryRequest,EsenseDtlCompTwoYearsData twoYearsData) throws SmartMainDAOException{
		
		String queryName = sqlMessage.getMessage(IS_PREV_YEAR_DATA_AVAILABLE_QRY, null, null);
		usageHistoryRequest.setYearMonthNum(twoYearsData.getPreviousYear());
		boolean isPrevAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getYearMonthNum());
		queryName = sqlMessage.getMessage(IS_NEXT_YEAR_DATA_AVAILABLE_QRY, null, null);
		usageHistoryRequest.setYearMonthNum(twoYearsData.getNextYear());
		boolean isNextAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,usageHistoryRequest.getYearMonthNum());
		usageHistoryRequest.setYearMonthNum(twoYearsData.getPreviousYear());
		queryName = sqlMessage.getMessage(GET_MAX_YEAR_MONTH_NUM_QRY, null, null);
		usageHistoryRequest.setYearMonthNum(twoYearsData.getCurrentYear());
		String maxYearDataMonth = getStringParamValue(usageHistoryRequest,queryName,usageHistoryRequest.getYearMonthNum());
		twoYearsData.setPrevDataAvailable(isPrevAvail);
		twoYearsData.setNextDataAvailable(isNextAvail);
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+twoYearsData.isPrevDataAvailable()+":::FOR THE YEAR:::"+twoYearsData.getPreviousYear());
		logger.info("NEXT DATA AVAILABLE::::::::::::"+twoYearsData.isPrevDataAvailable()+":::FOR THE YEAR:::"+twoYearsData.getNextYear());
	}
	
	
	protected void populateActualDayForPCDailyUsage(UsageHistoryRequest usageHistoryRequest) throws UsageHistoryRequestException, SmartMainDAOException{
		
		if(null != usageHistoryRequest && StringUtils.isNotBlank(usageHistoryRequest.getActualDay())){
			logger.info("ACTUAL DAY FOUND IN THE REQUEST::::GETTING PC DAILY USAGE FOR THE SPECIFIC DAY:::"+usageHistoryRequest.getActualDay());
		}else{
			String sqlQuery = sqlMessage.getMessage(GET_DEFAULT_DAY_FOR_PC_USAGE, null, null);
			String actualDayFromDB = smartMainDAO.getDefaultNumberForESIID(sqlQuery, usageHistoryRequest.getEsiid(),
					usageHistoryRequest.getContractAccNumber(), usageHistoryRequest.getContractId());
			if(null == actualDayFromDB || StringUtils.isBlank(actualDayFromDB)){
				throw new UsageHistoryRequestException("ACTUAL DAY NOT FOUND FOR ESIID:::"+usageHistoryRequest.getEsiid());
			}
			usageHistoryRequest.setActualDay(DateUtil.getFormattedDate("MM/dd/yyyy", "yyyy-MM-dd", actualDayFromDB));
			logger.info("ACTUAL DAY NOT FOUND IN THE REQUEST::::GETTING PC DAILY USAGE FOR THE ACTUAL DAY FROM DB:::"+usageHistoryRequest.getActualDay());
		}
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForPCDailyUsage(UsageHistoryRequest usageHistoryRequest,EsenseDtlPCDailyData pcDailyData) throws SmartMainDAOException{
		
		String queryName = sqlMessage.getMessage(IS_PREV_DAY_PCDATA_ACTUALDAY_QRY, null, null);
		boolean isPrevAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,pcDailyData.getPrevDay());
		queryName = sqlMessage.getMessage(IS_NEXT_DAY_PCDATA_ACTUALDAY_QRY, null, null);
		boolean isNextAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,pcDailyData.getNextDay());
		pcDailyData.setPrevDataAvailable(isPrevAvail);
		pcDailyData.setNextDataAvailable(isNextAvail);
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+pcDailyData.isPrevDataAvailable()+":::FOR THE DAY:::"+pcDailyData.getPrevDay());
		logger.info("NEXT DATA AVAILABLE::::::::::::"+pcDailyData.isPrevDataAvailable()+":::FOR THE DAY:::"+pcDailyData.getNextDay());
	}
	
	
	protected void populateYearWeekNumberForPCWeeklyUsage(UsageHistoryRequest usageHistoryRequest) throws UsageHistoryRequestException, SmartMainDAOException{
		
		if(null != usageHistoryRequest && StringUtils.isNotBlank(usageHistoryRequest.getYearWeekNumber())){
			logger.info("YEAR WEEK NUMBER FOUND IN THE REQUEST::::GETTING PC WEEKLY USAGE FOR THE SPECIFIC WEEK NUMBER:::"+usageHistoryRequest.getYearWeekNumber());
		}else{
			String sqlQuery = sqlMessage.getMessage(GET_DEFAULT_WEEK_FOR_PC_USAGE, null, null);
			String weekNumFromDB = smartMainDAO.getDefaultNumberForESIID(sqlQuery, usageHistoryRequest.getEsiid(),
					usageHistoryRequest.getContractAccNumber(), usageHistoryRequest.getContractId());
			if(null != weekNumFromDB && StringUtils.isBlank(weekNumFromDB)){
				throw new UsageHistoryRequestException("WEEK NUMBER NOT FOUND FOR ESIID:::"+usageHistoryRequest.getEsiid());
			}
			usageHistoryRequest.setYearWeekNumber(weekNumFromDB);
			logger.info("YEAR WEEK NUMBER NOT FOUND IN THE REQUEST::::GETTING PC WEEKLY USAGE FOR THE DEFAULT WEEK NUMBER:::"+usageHistoryRequest.getYearWeekNumber());
		}
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForPCWeeklyUsage(UsageHistoryRequest usageHistoryRequest,EsenseDtlPCWeeklyData pcWeeklyData) throws SmartMainDAOException{
		
		String queryName = sqlMessage.getMessage(IS_PREV_WEEK_PCDATA_AVAILABLE_QRY, null, null);
		boolean isPrevAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,pcWeeklyData.getPrevYearWeekNumber());
		queryName = sqlMessage.getMessage(IS_NEXT_WEEK_PCDATA_AVAILABLE_QRY, null, null);
		boolean isNextAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,pcWeeklyData.getNextYearWeekNumber());
		pcWeeklyData.setPrevDataAvailable(isPrevAvail);
		pcWeeklyData.setNextDataAvailable(isNextAvail);
	}
	
	
	protected void populateFirstDayAndLastDayFromPCActualDay(UsageHistoryRequest usageHistoryRequest) throws UsageHistoryRequestException, SmartMainDAOException{
		
		String actualDay = usageHistoryRequest.getActualDay();
		String firstDayMonth = UsageHistoryUtil.getMonthFirstDateFromActualDay(actualDay);
		String lastDayMonth = UsageHistoryUtil.getMonthEndDateFromActualDay(actualDay);
		logger.info("ACTUAL DAY FROM THE REQUEST::::::"+actualDay+"::CALCULATED FIRST DAY OF MONTH::::::"+firstDayMonth
				+"::::CALCULATED FIRST DAY OF MONTH::::::"+lastDayMonth);
		usageHistoryRequest.setFromDate(firstDayMonth);
		usageHistoryRequest.setToDate(lastDayMonth);
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForPCMonthlyUsage(UsageHistoryRequest usageHistoryRequest,EsenseDtlPCMonthlyData pcMonthlyData) throws SmartMainDAOException{
		
		String queryName = IS_PREV_MONTH_PCDATA_AVAILABLE_QRY;
		Number monthlyUsagePrevCount = smartMainHibernateDAO.singleResultQuery(usageHistoryRequest, queryName,UsageHistoryUtil.getParamNamesForMonthlyUsage(true),
				UsageHistoryUtil.getParamValuesForMonthlyUsage(usageHistoryRequest,true));
		queryName = IS_NEXT_MONTH_PCDATA_AVAILABLE_QRY;
		Number monthlyUsageNextCount = smartMainHibernateDAO.singleResultQuery(usageHistoryRequest, queryName,UsageHistoryUtil.getParamNamesForMonthlyUsage(false),
				UsageHistoryUtil.getParamValuesForMonthlyUsage(usageHistoryRequest,false));
		pcMonthlyData.setPrevDataAvailable(0 != monthlyUsagePrevCount.intValue());
		pcMonthlyData.setNextDataAvailable(0 != monthlyUsageNextCount.intValue());
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+pcMonthlyData.isPrevDataAvailable()+":::BEFORE THE DATE:::"+usageHistoryRequest.getFromDate());
		logger.info("NEXT DATA AVAILABLE::::::::::::"+pcMonthlyData.isPrevDataAvailable()+":::AFTER THE DATE:::"+usageHistoryRequest.getToDate());
	}
	
	
	protected void populateYearForPCYearlyUsage(UsageHistoryRequest usageHistoryRequest) throws UsageHistoryRequestException, SmartMainDAOException{
		
		if(null != usageHistoryRequest && StringUtils.isNotBlank(usageHistoryRequest.getYearMonthNum())){
			logger.info("ACTUAL YEAR FOUND IN THE REQUEST::::GETTING PC YEARLY USAGE FOR THE SPECIFIC YEAR:::"+usageHistoryRequest.getYearMonthNum());
		}else{
			String sqlQuery = sqlMessage.getMessage(GET_DEFAULT_YEAR_FOR_PC_USAGE, null, null);
			String actualYearFromDB = smartMainDAO.getDefaultNumberForESIID(sqlQuery, usageHistoryRequest.getEsiid(),
					usageHistoryRequest.getContractAccNumber(), usageHistoryRequest.getContractId());
			if(null == actualYearFromDB || StringUtils.isBlank(actualYearFromDB)){
				throw new UsageHistoryRequestException("DEFAULT YEAR NOT FOUND FOR ESIID:::"+usageHistoryRequest.getEsiid());
			}
			usageHistoryRequest.setYearMonthNum(actualYearFromDB);
			logger.info("YEAR NOT FOUND IN THE REQUEST::::GETTING PC YEARLY USAGE FOR THE DEFAULT YEAR FROM DB:::"+usageHistoryRequest.getYearMonthNum());
		}
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForPCYearlyUsage(UsageHistoryRequest usageHistoryRequest,EsenseDtlPCYearlyData pcYearlyData) throws SmartMainDAOException{
		
		String queryName = sqlMessage.getMessage(IS_PREV_PCYEAR_DATA_AVAILABLE_QRY, null, null);
		boolean isPrevAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,pcYearlyData.getPreviousYear());
		queryName = sqlMessage.getMessage(IS_NEXT_PCYEAR_DATA_AVAILABLE_QRY, null, null);
		boolean isNextAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,pcYearlyData.getNextYear());
		pcYearlyData.setPrevDataAvailable(isPrevAvail);
		pcYearlyData.setNextDataAvailable(isNextAvail);
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+pcYearlyData.isPrevDataAvailable()+":::FOR THE YEAR:::"+pcYearlyData.getPreviousYear());
		logger.info("NEXT DATA AVAILABLE::::::::::::"+pcYearlyData.isPrevDataAvailable()+":::FOR THE YEAR:::"+pcYearlyData.getNextYear());
	}
	
	
	protected boolean checkForCurrentPCDataAvaialble(UsageHistoryRequest usageRequest) throws SmartMainDAOException{
		
		boolean pcDataAvailable = false;
		String queryName = "";
		if(StringUtils.isNotBlank(usageRequest.getYearWeekNumber())){
			queryName = sqlMessage.getMessage(IS_CURR_WEEK_PCDATA_AVAILABLE_QRY, null, null);
			pcDataAvailable = isPreviousOrNextDataAvailable(usageRequest,queryName,usageRequest.getYearWeekNumber());
		}else if(StringUtils.isNotBlank(usageRequest.getActualDay())){
			String firstDayMonth = UsageHistoryUtil.getMonthFirstDateFromActualDay(usageRequest.getActualDay());
			queryName = sqlMessage.getMessage(IS_CURR_DAY_PCDATA_ACTUALDAY_QRY, null, null);
			pcDataAvailable = isPreviousOrNextDataAvailable(usageRequest,queryName,firstDayMonth);
		}else if(StringUtils.isNotBlank(usageRequest.getYearMonthNum())){
			queryName = sqlMessage.getMessage(IS_CURR_PCYEAR_DATA_AVAILABLE_QRY, null, null);
			pcDataAvailable = isPreviousOrNextDataAvailable(usageRequest,queryName,usageRequest.getYearMonthNum());
		}else{
			logger.info("NO INPUT VALUE FOR PC DATA AVAILABLE::::");
		}
		return pcDataAvailable;
	}
	
	
	
	protected void populateYearWeekNumberForBDWeeklyUsage(UsageHistoryRequest usageHistoryRequest) throws SmartMainDAOException, UsageHistoryRequestException{
		
		String sqlQuery = sqlMessage.getMessage(GET_DEFAULT_WEEK_NUMBER_BD_QUERY, null, null);
		String actualYearWeekNumFromDB = smartMainDAO.getDefaultNumberForESIID(sqlQuery,usageHistoryRequest.getEsiid(),
				usageHistoryRequest.getContractAccNumber(),usageHistoryRequest.getContractId());
		if((null == actualYearWeekNumFromDB || StringUtils.isBlank(actualYearWeekNumFromDB)) && 
				(null == usageHistoryRequest.getYearWeekNumber() || StringUtils.isBlank(usageHistoryRequest.getYearWeekNumber()))){
			throw new UsageHistoryRequestException("DEFAULT YEAR NOT FOUND FOR ESIID:::"+usageHistoryRequest.getEsiid());
		}
		Date dateFromRequeust = DateUtil.getDate(UsageHistoryUtil.getActualDayFromYearWeekNum(usageHistoryRequest.getYearWeekNumber()), "MM/dd/yyyy");
		Date dateFromDB = DateUtil.getDate(UsageHistoryUtil.getActualDayFromYearWeekNum(actualYearWeekNumFromDB), "MM/dd/yyyy");
		if(dateFromRequeust.compareTo(dateFromDB) > 0){
			usageHistoryRequest.setYearWeekNumber(actualYearWeekNumFromDB);
		}else{
			usageHistoryRequest.setYearWeekNumber(usageHistoryRequest.getYearWeekNumber());
		}
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForBDWeeklyUsage(UsageHistoryRequest usageHistoryRequest,EsenseDtlBDWeeklyUsage bdWeekUsage) throws SmartMainDAOException{
		
		String queryName = sqlMessage.getMessage(IS_PREV_BD_WEEK_DATA_AVILABLE, null, null);
		boolean isPrevAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,bdWeekUsage.getPrevYearWeekNumber());
		queryName = sqlMessage.getMessage(IS_NEXT_BD_WEEK_DATA_AVILABLE, null, null);
		boolean isNextAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,bdWeekUsage.getNextYearWeekNumber());
		bdWeekUsage.setPrevDataAvailable(isPrevAvail);
		bdWeekUsage.setNextDataAvailable(isNextAvail);
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+bdWeekUsage.isPrevDataAvailable()+":::FOR THE YEAR WEEK NUMBER:::"+bdWeekUsage.getPrevYearWeekNumber());
		logger.info("NEXT DATA AVAILABLE::::::::::::"+bdWeekUsage.isPrevDataAvailable()+":::FOR THE YEAR WEEK NUMBER:::"+bdWeekUsage.getNextYearWeekNumber());
	}
	
	
	protected void populateYearWeekNumberForBDYearlyUsage(UsageHistoryRequest usageHistoryRequest) throws SmartMainDAOException, UsageHistoryRequestException{
		
		if(null != usageHistoryRequest && StringUtils.isNotBlank(usageHistoryRequest.getYearMonthNum())){
			usageHistoryRequest.setYearMonthNum(UsageHistoryUtil.prependZeroIfNotThere(usageHistoryRequest.getYearMonthNum()));
			logger.info("ACTUAL YEAR MONTH NUM FOUND IN THE REQUEST::::GETTING YEARLY USAGE FOR THE SPECIFIC YEAR:::"+usageHistoryRequest.getYearMonthNum());
		}else{
			String sqlQuery = sqlMessage.getMessage(GET_DEFAULT_YEAR_MONTH_NUM_BD_QUERY, null, null);
			String defaultBDMonthNum = smartMainDAO.getDefaultNumberForESIID(sqlQuery,usageHistoryRequest.getEsiid(),
					usageHistoryRequest.getContractAccNumber(),usageHistoryRequest.getContractId());
			if(null == defaultBDMonthNum || StringUtils.isBlank(defaultBDMonthNum)){
				throw new UsageHistoryRequestException("DEFAULT YEAR MONTH NUM NOT FOUND FOR ESIID:::"+usageHistoryRequest.getEsiid());
			}
			usageHistoryRequest.setYearMonthNum(defaultBDMonthNum);
			logger.info("YEAR MONTH NUM NOT FOUND IN THE REQUEST::::GETTING YEARLY USAGE FOR THE DEFAULT YEAR FROM DB:::"+defaultBDMonthNum);
		}
	}
	
	
	protected void checkForPreviousAndNextDataAvaialbleForBDYearlyUsage(UsageHistoryRequest usageHistoryRequest,EsenseDtlBDYearlyUsage bdYearUsage) throws SmartMainDAOException{
		
		String queryName = sqlMessage.getMessage(IS_PREV_BD_YEAR_DATA_AVILABLE, null, null);
		boolean isPrevAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,bdYearUsage.getPreviousMonthNum());
		queryName = sqlMessage.getMessage(IS_NEXT_BD_YEAR_DATA_AVILABLE, null, null);
		boolean isNextAvail = isPreviousOrNextDataAvailable(usageHistoryRequest,queryName,bdYearUsage.getNextMonthNum());
		bdYearUsage.setPrevDataAvailable(isPrevAvail);
		bdYearUsage.setNextDataAvailable(isNextAvail);
		logger.info("PREVIOUS DATA AVAILABLE::::::::"+bdYearUsage.isPrevDataAvailable()+":::FOR THE YEAR WEEK NUMBER:::"+bdYearUsage.getPrevYearWeekNumber());
		logger.info("NEXT DATA AVAILABLE::::::::::::"+bdYearUsage.isPrevDataAvailable()+":::FOR THE YEAR WEEK NUMBER:::"+bdYearUsage.getNextYearWeekNumber());
		queryName = sqlMessage.getMessage(GET_MAX_YEAR_MONTH_NUM_BD_FOR_YEAR, null, null);
		String year = StringUtils.isNotBlank(usageHistoryRequest.getYearMonthNum())?usageHistoryRequest.getYearMonthNum().substring(0, 4):"";
		if(logger.isDebugEnabled()){logger.debug("GETTING THE YEAR MAX NUMBER FOR THE YEAR:::::"+year);}
		if(StringUtils.isNotBlank(year)){
			String maxYearMonthNum = smartMainDAO.getStringValue(usageHistoryRequest.getEsiid(), usageHistoryRequest.getContractAccNumber(), 
					usageHistoryRequest.getContractId(), year, queryName);
			bdYearUsage.setMaxYearMonthNum(maxYearMonthNum);
		}else{
			logger.info("NOT ABLE TO FIND MAX YEAR MONTH NUMBER FOR YEAR: EMPTY:::::");
		}
	}
	
	
	
	private boolean isPreviousOrNextDataAvailable(UsageHistoryRequest usageHistoryRequest,String queryName,String paramName) throws SmartMainDAOException{
		
		return smartMainDAO.isPrevAndNextDataAvailable(usageHistoryRequest.getEsiid(), usageHistoryRequest.getContractAccNumber(), 
				usageHistoryRequest.getContractId(), paramName, queryName);
	}
	
	
	private String getStringParamValue(UsageHistoryRequest usageHistoryRequest,String queryName,String paramName) throws SmartMainDAOException{
		
		return smartMainDAO.getStringValue(usageHistoryRequest.getEsiid(), usageHistoryRequest.getContractAccNumber(), 
				usageHistoryRequest.getContractId(), paramName, queryName);
	}
	
	
	protected void populateLabelsIfAnyPrevOrNextDataAvailable(Object obj,UsageHistoryRequest usageHistoryRequest){
		
		logger.debug("DATA NOT AVAILABLE:::ONE OF THE PREV AND NEXT DATA AVAILABLE::::CREATING LABLES");
		if(obj instanceof EsenseDetailWeeklyData){
			((EsenseDetailWeeklyData) obj).setStrDateList(UsageHistoryUtil.getWeekDateStrListFromWeekNum(usageHistoryRequest.getYearWeekNumber()));
			((EsenseDetailWeeklyData) obj).setWeekOfMessage(usageHistoryRequest.getYearWeekNumber());
			((EsenseDetailWeeklyData) obj).setDayAndYear(usageHistoryRequest.getYearWeekNumber());
			((EsenseDetailWeeklyData) obj).setMonth();
			((EsenseDetailWeeklyData) obj).setDateRange(UsageHistoryUtil.getDataRangeFromYearWeekNum(usageHistoryRequest.getYearWeekNumber()));
		}else if(obj instanceof EsenseDetailDailyData){
			((EsenseDetailDailyData) obj).setDayOfMessage(usageHistoryRequest.getActualDay());
		}else if(obj instanceof EsenseDetailMonthlyData){
			((EsenseDetailMonthlyData) obj).setMonth(UsageHistoryUtil.getMonthLabelForMonthlyUsage(usageHistoryRequest.getActualDay()));
		}else if(obj instanceof EsenseDetailYearlyData){
			((EsenseDetailYearlyData) obj).setYear(usageHistoryRequest.getYearMonthNum());
		}else if(obj instanceof EsenseDtlCompTwoWeeksData){
			String currentYearWeekNum = ((EsenseDtlCompTwoWeeksData) obj).getCurrentYearWeekNum();
			((EsenseDtlCompTwoWeeksData) obj).setStrDateList(UsageHistoryUtil.getWeekDateStrListFromWeekNum(currentYearWeekNum));
			((EsenseDtlCompTwoWeeksData) obj).setDateRange(UsageHistoryUtil.getDataRangeFromYearWeekNum(currentYearWeekNum));
		}else if(obj instanceof EsenseDtlPCWeeklyData){
			((EsenseDtlPCWeeklyData) obj).setStrDateList(UsageHistoryUtil.getWeekDateStrListFromWeekNum(usageHistoryRequest.getYearWeekNumber()));
		}else if(obj instanceof EsenseDtlBDWeeklyUsage){
			((EsenseDtlBDWeeklyUsage) obj).setStrDateList(UsageHistoryUtil.getWeekDateStrListFromWeekNum(usageHistoryRequest.getYearWeekNumber()));
		}
		
	}
	
	
	protected DateRange getDateRangeFromHourlyData(UsageHistoryRequest usageHistoryRequest) throws SmartMainDAOException{
		
		String queryName = sqlMessage.getMessage(GET_DATE_RANGE_FROM_HOUR_TABLE_QRY, null, null);
		return smartMainDAO.getDateRangeFromDayHourData(usageHistoryRequest.getEsiid(), usageHistoryRequest.getContractAccNumber(),
				usageHistoryRequest.getContractId(), queryName);
	}

}
