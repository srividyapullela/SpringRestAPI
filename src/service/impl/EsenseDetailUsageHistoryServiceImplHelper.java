package com.reliant.sm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.hibernate.dataobject.BDDayWeekUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.BDYearUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.DayUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.DayWeeklyUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.PCDailyUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.PCWeeklyUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.PCYearlyUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.YearUsageDO;
import com.reliant.sm.dao.rowmapper.TemperatureDO;
import com.reliant.sm.model.BDSlice;
import com.reliant.sm.model.BDUsage;
import com.reliant.sm.model.BDYearTempData;
import com.reliant.sm.model.Day;
import com.reliant.sm.model.DayUsage;
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
import com.reliant.sm.model.EsenseDtlPCMonthlyData;
import com.reliant.sm.model.EsenseDtlPCWeeklyData;
import com.reliant.sm.model.EsenseDtlPCYearlyData;
import com.reliant.sm.model.Hour;
import com.reliant.sm.model.Month;
import com.reliant.sm.model.MonthlyUsage;
import com.reliant.sm.model.Temperature;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.model.WeeklyData;
import com.reliant.sm.model.YearlyUsage;
import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;


/**
 * @author bbachin1
 * 
 */
@Component
public class EsenseDetailUsageHistoryServiceImplHelper {
	
	private static LoggerUtil logger = LoggerUtil.getInstance(EsenseDetailUsageHistoryServiceImplHelper.class);
	
	
	public EsenseDetailWeeklyData populateWeeklyUsageData(EsenseDetailWeeklyData weeklyUsageData, List<DayWeeklyUsageDO> weekUsageList, UsageHistoryRequest usageHistoryRequest){
		
		List<Day> dayUsageList = new ArrayList<Day>();
		if(weekUsageList != null && weekUsageList.size() > 0) {
			populateWeeklyUsageList(weekUsageList,dayUsageList,weeklyUsageData);
			weeklyUsageData.setDataAvailable(true);
			weeklyUsageData.setDailyDataList(dayUsageList);
			weeklyUsageData.calculateAvgTempHigh();
			weeklyUsageData.calculateAvgTempLow();
			weeklyUsageData.fillGapIfAllDataNotAvailable();
		}else{
			logger.info("DATA NOT FOUND FOR THE YEAR WEEK NUMBER:::::"+usageHistoryRequest.getYearWeekNumber());
			weeklyUsageData.setErrorMessage("NO WEEKLY DATE FOUND FOR THE WEEK NUMBER::::"+usageHistoryRequest.getYearWeekNumber());
		}
		return weeklyUsageData;
	}
	
	
	private void populateWeeklyUsageList(List<DayWeeklyUsageDO> weekUsageList,List<Day> dayUsageList,EsenseDetailWeeklyData weeklyUsageData){
		
		for(DayWeeklyUsageDO usageDO : weekUsageList){
			Day dayUsage = new Day();
			populateDayUsageForDay(dayUsage,usageDO,weeklyUsageData);
			dayUsageList.add(dayUsage);
		}
	}
	
	private void populateDayUsageForDay(Day dayUsage,DayWeeklyUsageDO usageDO,EsenseDetailWeeklyData weeklyUsageData){
		
		dayUsage.setActualDay(UsageHistoryUtil.getStringValue(usageDO.getDayWeeklyUsageId().getActualDay()));
		dayUsage.setCost(UsageHistoryUtil.getStringValue(usageDO.getTotalDayCost()));
		dayUsage.setDay(UsageHistoryUtil.getDayLabelsForWeek(usageDO.getDayOfWeek()));
		dayUsage.setTempHigh(UsageHistoryUtil.getStringValue(usageDO.getDayTempHigh()));
		dayUsage.setTempLow(UsageHistoryUtil.getStringValue(usageDO.getDayTempLow()));
		dayUsage.setStrDate(UsageHistoryUtil.getStrDateForDashboard(UsageHistoryUtil.getStringValue(usageDO.getDayWeeklyUsageId().getActualDay())));
		dayUsage.setUsage(UsageHistoryUtil.getStringValue(usageDO.getTotalDayUsage()));
		weeklyUsageData.addCostToTotalCost(dayUsage.getCost());
		weeklyUsageData.addUsageToTotalUsage(dayUsage.getUsage());
		
	}
	
	public EsenseDetailDailyData populateDailyUsageData(EsenseDetailDailyData dailyUsageData, List<DayUsageDO> dailyUsageList){
		
		Hour hour = new Hour();
		if(null != dailyUsageList && dailyUsageList.size() >0){
			DayUsageDO dayDO = dailyUsageList.get(0);
			populateHourlyData(hour,dayDO);
			dailyUsageData.setDataAvailable(true);
			dailyUsageData.setHourlyData(hour);
			dailyUsageData.setActualDay(DateUtil.getFormatedDate(dayDO.getDailyUsageId().getActualDate(), "MM/dd/yyyy"));
			dailyUsageData.setDayOfMessage(UsageHistoryUtil.getStringValue(dayDO.getDailyUsageId().getActualDate()));
		}else{
			dailyUsageData.setErrorMessage("NO DATA FOUND FOR THE ACTUAL DAY:::::"+dailyUsageData.getActualDay());
		}
		return dailyUsageData;
	}
	
	
	private void populateHourlyData(Hour hour, DayUsageDO dayDO){
		
		hour.setCost1(UsageHistoryUtil.getStringValue(dayDO.getCostHour01()));
		hour.setCost2(UsageHistoryUtil.getStringValue(dayDO.getCostHour02()));
		hour.setCost3(UsageHistoryUtil.getStringValue(dayDO.getCostHour03()));
		hour.setCost4(UsageHistoryUtil.getStringValue(dayDO.getCostHour04()));
		hour.setCost5(UsageHistoryUtil.getStringValue(dayDO.getCostHour05()));
		hour.setCost6(UsageHistoryUtil.getStringValue(dayDO.getCostHour06()));
		hour.setCost7(UsageHistoryUtil.getStringValue(dayDO.getCostHour07()));
		hour.setCost8(UsageHistoryUtil.getStringValue(dayDO.getCostHour08()));
		hour.setCost9(UsageHistoryUtil.getStringValue(dayDO.getCostHour09()));
		hour.setCost10(UsageHistoryUtil.getStringValue(dayDO.getCostHour10()));
		hour.setCost11(UsageHistoryUtil.getStringValue(dayDO.getCostHour11()));
		hour.setCost12(UsageHistoryUtil.getStringValue(dayDO.getCostHour12()));
		hour.setCost13(UsageHistoryUtil.getStringValue(dayDO.getCostHour13()));
		hour.setCost14(UsageHistoryUtil.getStringValue(dayDO.getCostHour14()));
		hour.setCost15(UsageHistoryUtil.getStringValue(dayDO.getCostHour15()));
		hour.setCost16(UsageHistoryUtil.getStringValue(dayDO.getCostHour16()));
		hour.setCost17(UsageHistoryUtil.getStringValue(dayDO.getCostHour17()));
		hour.setCost18(UsageHistoryUtil.getStringValue(dayDO.getCostHour18()));
		hour.setCost19(UsageHistoryUtil.getStringValue(dayDO.getCostHour19()));
		hour.setCost20(UsageHistoryUtil.getStringValue(dayDO.getCostHour20()));
		hour.setCost21(UsageHistoryUtil.getStringValue(dayDO.getCostHour21()));
		hour.setCost22(UsageHistoryUtil.getStringValue(dayDO.getCostHour22()));
		hour.setCost23(UsageHistoryUtil.getStringValue(dayDO.getCostHour23()));
		hour.setCost24(UsageHistoryUtil.getStringValue(dayDO.getCostHour24()));
		
		hour.setUsage1(UsageHistoryUtil.getStringValue(dayDO.getUsageHour01()));
		hour.setUsage2(UsageHistoryUtil.getStringValue(dayDO.getUsageHour02()));
		hour.setUsage3(UsageHistoryUtil.getStringValue(dayDO.getUsageHour03()));
		hour.setUsage4(UsageHistoryUtil.getStringValue(dayDO.getUsageHour04()));
		hour.setUsage5(UsageHistoryUtil.getStringValue(dayDO.getUsageHour05()));
		hour.setUsage6(UsageHistoryUtil.getStringValue(dayDO.getUsageHour06()));
		hour.setUsage7(UsageHistoryUtil.getStringValue(dayDO.getUsageHour07()));
		hour.setUsage8(UsageHistoryUtil.getStringValue(dayDO.getUsageHour08()));
		hour.setUsage9(UsageHistoryUtil.getStringValue(dayDO.getUsageHour09()));
		hour.setUsage10(UsageHistoryUtil.getStringValue(dayDO.getUsageHour10()));
		hour.setUsage11(UsageHistoryUtil.getStringValue(dayDO.getUsageHour11()));
		hour.setUsage12(UsageHistoryUtil.getStringValue(dayDO.getUsageHour12()));
		hour.setUsage13(UsageHistoryUtil.getStringValue(dayDO.getUsageHour13()));
		hour.setUsage14(UsageHistoryUtil.getStringValue(dayDO.getUsageHour14()));
		hour.setUsage15(UsageHistoryUtil.getStringValue(dayDO.getUsageHour15()));
		hour.setUsage16(UsageHistoryUtil.getStringValue(dayDO.getUsageHour16()));
		hour.setUsage17(UsageHistoryUtil.getStringValue(dayDO.getUsageHour17()));
		hour.setUsage18(UsageHistoryUtil.getStringValue(dayDO.getUsageHour18()));
		hour.setUsage19(UsageHistoryUtil.getStringValue(dayDO.getUsageHour19()));
		hour.setUsage20(UsageHistoryUtil.getStringValue(dayDO.getUsageHour20()));
		hour.setUsage21(UsageHistoryUtil.getStringValue(dayDO.getUsageHour21()));
		hour.setUsage22(UsageHistoryUtil.getStringValue(dayDO.getUsageHour22()));
		hour.setUsage23(UsageHistoryUtil.getStringValue(dayDO.getUsageHour23()));
		hour.setUsage24(UsageHistoryUtil.getStringValue(dayDO.getUsageHour24()));
		
		hour.setTempHigh(UsageHistoryUtil.getStringValue(dayDO.getDayTemHI()));
		hour.setTempLow(UsageHistoryUtil.getStringValue(dayDO.getDayTemLo()));
		hour.setTotalCost(UsageHistoryUtil.getStringValue(dayDO.getTotalCostDay()));
		hour.setTotalUsage(UsageHistoryUtil.getStringValue(dayDO.getTotalUsageDay()));
		hour.setActualDay(DateUtil.getFormatedDate(dayDO.getDailyUsageId().getActualDate(), "MM/dd/yyyy"));
	}
	
	
	public EsenseDetailMonthlyData populateMonthlyUsageData(EsenseDetailMonthlyData monthlyUsageData, List<DayWeeklyUsageDO> weekUsageList){
		
		List<Day> dayUsageList = new ArrayList<Day>();
		Integer i =1;
		if(weekUsageList != null && weekUsageList.size() > 0) {
			for(DayWeeklyUsageDO usageDO : weekUsageList){
				Day dayUsage = new Day();
				dayUsage.setActualDay(UsageHistoryUtil.getStringValue(usageDO.getDayWeeklyUsageId().getActualDay()));
				dayUsage.setCost(UsageHistoryUtil.getStringValue(usageDO.getTotalDayCost()));
				dayUsage.setDay(i.toString());
				dayUsage.setTempHigh(UsageHistoryUtil.getStringValue(usageDO.getDayTempHigh()));
				dayUsage.setTempLow(UsageHistoryUtil.getStringValue(usageDO.getDayTempLow()));
				dayUsage.setStrDate(UsageHistoryUtil.getStrDateForDashboard(UsageHistoryUtil.getStringValue(usageDO.getDayWeeklyUsageId().getActualDay())));
				dayUsage.setUsage(UsageHistoryUtil.getStringValue(usageDO.getTotalDayUsage()));
				monthlyUsageData.addCostToTotalCost(dayUsage.getCost());
				monthlyUsageData.addUsageToTotalUsage(dayUsage.getUsage());
				dayUsageList.add(dayUsage);
				i++;
			}
			monthlyUsageData.setActualDay(DateUtil.getFormatedDate(weekUsageList.get(0).getDayWeeklyUsageId().getActualDay(), "MM/dd/yyyy"));
			monthlyUsageData.setCurrentYearWeekNum(weekUsageList.get(0).getYearWeekNum());
			monthlyUsageData.setDataAvailable(true);
			monthlyUsageData.setDailyDataList(dayUsageList);
			monthlyUsageData.calAverageTempHigh();
			monthlyUsageData.calAverageTempLow();
			monthlyUsageData.fillGapIfAllDataNotAvailable();
		}
		monthlyUsageData.setMonth(UsageHistoryUtil.getMonthLabelForMonthlyUsage(monthlyUsageData.getActualDay()));
		return monthlyUsageData;
	}
	
	
	public EsenseDetailYearlyData populateYearlyUsageData(EsenseDetailYearlyData yearlyUsageData,List<YearUsageDO> yearlyUsageList, UsageHistoryRequest usageRequest){
		
		if(null != yearlyUsageList && yearlyUsageList.size() >0){
			List<Month> monthlyDataList = new ArrayList<Month>();
			populateYearUsageList(yearlyUsageData,yearlyUsageList,monthlyDataList);
			yearlyUsageData.setMonthlyDataList(monthlyDataList);
			yearlyUsageData.setDataAvailable(true);
			yearlyUsageData.calculateAvgTempHigh();
			yearlyUsageData.calculateAvgTempLow();
			yearlyUsageData.fillGapIfAllDataNotAvailable();
		}else{
			yearlyUsageData.setErrorMessage("NO DATA FOUND FOR THE YEAR:::"+usageRequest.getYearMonthNum());
		}
		yearlyUsageData.setCurrentYear(usageRequest.getYearMonthNum());
		yearlyUsageData.setYear(usageRequest.getYearMonthNum());
		return yearlyUsageData;
	}
	
	
	private void populateYearUsageList(EsenseDetailYearlyData yearlyUsageData,List<YearUsageDO> yearlyUsageList,List<Month> monthlyDataList){
		
		for(YearUsageDO data : yearlyUsageList){
			Month month = new Month();
			populateMonthData(month,yearlyUsageData,data);
			monthlyDataList.add(month);
		}
	}
	
	private void populateMonthData(Month month,EsenseDetailYearlyData yearlyUsageData,YearUsageDO data){
		
		month.setCost(UsageHistoryUtil.getStringValue(data.getTotalMonthCost()));
		month.setUsage(UsageHistoryUtil.getStringValue(data.getMonthUsage()));
		yearlyUsageData.addCostToTotalCost(month.getCost());
		yearlyUsageData.addUsageToTotalUsage(month.getUsage());
		month.setMonth(DateUtil.getMonthLabel(data.getYearlyUsageId().getActualDate(),true));
		month.setActualDay(UsageHistoryUtil.getStringValue(data.getYearlyUsageId().getActualDate()));
		month.setTempHigh(data.getMonthAveTempHi());
		month.setTempLow(data.getMonthAveTempLo());
		month.setYear(data.getYear());
	}
	
	
	
	
	public EsenseDtlCompMonthToMonthData populateCompareMonthToMonthData(EsenseDtlCompMonthToMonthData monthToMonthUsage,
			List<YearUsageDO> yearlyUsageList, UsageHistoryRequest usageRequest){
		
		Month currMonth = new Month();
		Month compMonth = new Month();
		compMonth.setMonth(DateUtil.getMonthLabel(UsageHistoryUtil.getActualDateFromYearMonthNumber(usageRequest.getFromDate()),true));
		compMonth.setYear(StringUtils.substring(usageRequest.getFromDate(), 0, 4));
		currMonth.setMonth(DateUtil.getMonthLabel(UsageHistoryUtil.getActualDateFromYearMonthNumber(usageRequest.getToDate()),true));
		currMonth.setYear(StringUtils.substring(usageRequest.getToDate(), 0, 4));
		if(null != yearlyUsageList && yearlyUsageList.size() >0){
			for(YearUsageDO data : yearlyUsageList){
				if(StringUtils.equalsIgnoreCase(data.getYearMonthNum(), usageRequest.getFromDate())){
					compMonth.setCost(UsageHistoryUtil.getStringValue(data.getTotalMonthCost()));
					compMonth.setUsage(UsageHistoryUtil.getStringValue(data.getMonthUsage()));
					compMonth.setTempHigh(data.getMonthAveTempHi());
					compMonth.setTempLow(data.getMonthAveTempLo());
					monthToMonthUsage.setPrevDataAvailable(true);
				}else if(StringUtils.equalsIgnoreCase(data.getYearMonthNum(), usageRequest.getToDate())){
					currMonth.setCost(UsageHistoryUtil.getStringValue(data.getTotalMonthCost()));
					currMonth.setUsage(UsageHistoryUtil.getStringValue(data.getMonthUsage()));
					currMonth.setTempHigh(data.getMonthAveTempHi());
					currMonth.setTempLow(data.getMonthAveTempLo());
					monthToMonthUsage.setNextDataAvailable(true);
				}
			}
			monthToMonthUsage.setDataAvailable(true);
		}else{
			monthToMonthUsage.setErrorMessage("NO YEARLY DATA LIST FROM THE QUERY");
		}
		monthToMonthUsage.setCurrentMonth(currMonth);
		monthToMonthUsage.setComparedMonth(compMonth);
		return monthToMonthUsage;
	}
	
	
	public EsenseDtlCompTwoWeeksData populateCompareTwoWeeksData(EsenseDtlCompTwoWeeksData twoWeeksData, List<DayWeeklyUsageDO> twoWeeksUsageList,UsageHistoryRequest usageHistoryRequest){
		
		WeeklyData previousWeekData = new WeeklyData();
		WeeklyData currentWeekData = new WeeklyData();
		if(null != twoWeeksUsageList && twoWeeksUsageList.size() >0){
			populateTwoWeeksUsageList(twoWeeksData,twoWeeksUsageList,previousWeekData,currentWeekData,usageHistoryRequest);
			twoWeeksData.setDataAvailable(true);
		}else{
			twoWeeksData.setErrorMessage("NO TWO WEEKS DATA LIST FOR THE WEEK NUMBER::::"+usageHistoryRequest.getFromDate()+"::AND::"+usageHistoryRequest.getToDate());
		}
		previousWeekData.setYearWeekNumber(twoWeeksData.getPrevYearWeekNumber());
		previousWeekData.setWeekOfMessage(twoWeeksData.getPrevYearWeekNumber());
		previousWeekData.setMonth();
		previousWeekData.setDateRange(UsageHistoryUtil.getWeekRangeFromActualDay(usageHistoryRequest.getFromDate()));
		currentWeekData.setYearWeekNumber(twoWeeksData.getCurrentYearWeekNum());
		currentWeekData.setWeekOfMessage(twoWeeksData.getCurrentYearWeekNum());
		currentWeekData.setMonth();
		currentWeekData.setDateRange(UsageHistoryUtil.getWeekRangeFromActualDay(usageHistoryRequest.getToDate()));
		currentWeekData.setDayAndYear(twoWeeksData.getCurrentYearWeekNum());
		previousWeekData.setDayAndYear(twoWeeksData.getPrevYearWeekNumber());
		twoWeeksData.setPreviousWeekUsage(previousWeekData);
		twoWeeksData.setCurrentWeekUsage(currentWeekData);
		twoWeeksData.fillGapIfAllDataNotAvailable();
		return twoWeeksData;
	}
	
	
	private void populateTwoWeeksUsageList(EsenseDtlCompTwoWeeksData twoWeeksData, List<DayWeeklyUsageDO> twoWeeksUsageList,
			WeeklyData previousWeekData,WeeklyData currentWeekData,UsageHistoryRequest usageHistoryRequest){
		
		List<Day> prevDayUsageList = new ArrayList<Day>();
		List<Day> currDayUsageList = new ArrayList<Day>();
		for(DayWeeklyUsageDO usageDO : twoWeeksUsageList){
			Day dayUsage = new Day();
			dayUsage.setActualDay(UsageHistoryUtil.getStringValue(usageDO.getDayWeeklyUsageId().getActualDay()));
			dayUsage.setCost(UsageHistoryUtil.getStringValue(usageDO.getTotalDayCost()));
			dayUsage.setDay(UsageHistoryUtil.getDayLabelsForWeek(usageDO.getDayOfWeek()));
			dayUsage.setTempHigh(UsageHistoryUtil.getStringValue(usageDO.getDayTempHigh()));
			dayUsage.setTempLow(UsageHistoryUtil.getStringValue(usageDO.getDayTempLow()));
			dayUsage.setStrDate(UsageHistoryUtil.getStrDateForDashboard(UsageHistoryUtil.getStringValue(usageDO.getDayWeeklyUsageId().getActualDay())));
			dayUsage.setUsage(UsageHistoryUtil.getStringValue(usageDO.getTotalDayUsage()));
			if(StringUtils.equalsIgnoreCase(usageHistoryRequest.getFromDate(), usageDO.getYearWeekNum())){
				previousWeekData.addCostToTotalCost(dayUsage.getCost());
				previousWeekData.addUsageToTotalUsage(dayUsage.getUsage());
				prevDayUsageList.add(dayUsage);
			}else if(StringUtils.equalsIgnoreCase(usageHistoryRequest.getToDate(), usageDO.getYearWeekNum())){
				currentWeekData.addCostToTotalCost(dayUsage.getCost());
				currentWeekData.addUsageToTotalUsage(dayUsage.getUsage());
				currDayUsageList.add(dayUsage);
			}
		}
		previousWeekData.setDailyDataList(prevDayUsageList);
		currentWeekData.setDailyDataList(currDayUsageList);
	}
	
	
	public EsenseDtlCompTwoDaysData populateCompareTwoDaysData(EsenseDtlCompTwoDaysData twoDaysData,List<DayUsageDO> dailyUsageList,UsageHistoryRequest usageRequest){
		
		DayUsage previousDayUsage = new DayUsage();
		DayUsage currentDayUsage = new DayUsage();
		if(null != dailyUsageList && dailyUsageList.size() >0){
			for(DayUsageDO dayDO : dailyUsageList){
				Hour hour = new Hour();
				if(StringUtils.equalsIgnoreCase(DateUtil.getFormatedDate(dayDO.getDailyUsageId().getActualDate(), "MM/dd/yyyy"), usageRequest.getFromDate())){
					populateHourlyData(hour,dayDO);
					previousDayUsage.setHourlyData(hour);
				}else if(StringUtils.equalsIgnoreCase(DateUtil.getFormatedDate(dayDO.getDailyUsageId().getActualDate(), "MM/dd/yyyy"), usageRequest.getToDate())){
					populateHourlyData(hour,dayDO);
					currentDayUsage.setHourlyData(hour);
				}
			}
			twoDaysData.setDataAvailable(true);
		}else{
			twoDaysData.setErrorMessage("NO TWO DAYS DATA FOUND FOR THE DAY NUMBERS:::"+usageRequest.getFromDate()+"::AND::"+usageRequest.getToDate());
		}
		previousDayUsage.setDayOfMessage(usageRequest.getFromDate());
		currentDayUsage.setDayOfMessage(usageRequest.getToDate());
		twoDaysData.setPreviousDayUsage(previousDayUsage);
		twoDaysData.setCurrentDayUsage(currentDayUsage);
		return twoDaysData;
	}
	
	
	public EsenseDtlCompTwoMonthsData populateCompareTwoMonthlyUsage(EsenseDtlCompTwoMonthsData twoMonthsData, List<DayWeeklyUsageDO> monthlyUsageList, UsageHistoryRequest usageRequest){
		
		MonthlyUsage currentMonthUsage = new MonthlyUsage();
		MonthlyUsage previousMonthUsage = new MonthlyUsage();
		if(null != monthlyUsageList && monthlyUsageList.size() >0){
			List<Day> currDayUsageList = new ArrayList<Day>();
			List<Day> prevDayUsageList = new ArrayList<Day>();
			Integer i =1;
			Integer j =1;
			for(DayWeeklyUsageDO usageDO : monthlyUsageList){
				Day dayUsage = new Day();
				dayUsage.setActualDay(UsageHistoryUtil.getStringValue(usageDO.getDayWeeklyUsageId().getActualDay()));
				dayUsage.setCost(UsageHistoryUtil.getStringValue(usageDO.getTotalDayCost()));
				dayUsage.setTempHigh(UsageHistoryUtil.getStringValue(usageDO.getDayTempHigh()));
				dayUsage.setTempLow(UsageHistoryUtil.getStringValue(usageDO.getDayTempLow()));
				dayUsage.setStrDate(UsageHistoryUtil.getStrDateForDashboard(UsageHistoryUtil.getStringValue(usageDO.getDayWeeklyUsageId().getActualDay())));
				dayUsage.setUsage(UsageHistoryUtil.getStringValue(usageDO.getTotalDayUsage()));
				if(isPreviousMonth(usageDO.getDayWeeklyUsageId().getActualDay(), usageRequest.getFromDate())){
					dayUsage.setDay(j.toString());
					previousMonthUsage.addCostToTotalCost(dayUsage.getCost());
					previousMonthUsage.addUsageToTotalUsage(dayUsage.getUsage());
					prevDayUsageList.add(dayUsage);
					j++;
				}else{
					dayUsage.setDay(i.toString());
					currentMonthUsage.addCostToTotalCost(dayUsage.getCost());
					currentMonthUsage.addUsageToTotalUsage(dayUsage.getUsage());
					currDayUsageList.add(dayUsage);
					i++;
				}
			}
			currentMonthUsage.setDailyDataList(currDayUsageList);
			currentMonthUsage.calAverageTempHigh();
			currentMonthUsage.calAverageTempLow();
			previousMonthUsage.setDailyDataList(prevDayUsageList);
			previousMonthUsage.calAverageTempHigh();
			previousMonthUsage.calAverageTempLow();
			twoMonthsData.setDataAvailable(true);
		}else{
			twoMonthsData.setErrorMessage("NO TWO WEEKS DATA LIST FROM THE QUERY");
		}
		currentMonthUsage.setMonth(UsageHistoryUtil.getMonthLabelForMonthlyUsage(usageRequest.getToDate()));
		previousMonthUsage.setMonth(UsageHistoryUtil.getMonthLabelForMonthlyUsage(usageRequest.getFromDate()));
		twoMonthsData.setCurrentMonthUsage(currentMonthUsage);
		twoMonthsData.setPreviousMonthUsage(previousMonthUsage);
		twoMonthsData.fillGapIfAllDataNotAvailable();
		return twoMonthsData;
	}
	
	
	private boolean isPreviousMonth(Date date, String FromDate){
		
		String fromMonth = DateUtil.getMonthLabel(DateUtil.getDate(FromDate, "MM/dd/yyyy"),false);
		String month = DateUtil.getMonthLabel(date,false);
		if(StringUtils.equalsIgnoreCase(fromMonth, month)){
			return true;
		}
		return false;
	}
	
	
	public EsenseDtlCompTwoYearsData populateCompareTwoYearlyUsage(EsenseDtlCompTwoYearsData twoYearsData,List<YearUsageDO> yearlyUsageList,UsageHistoryRequest usageRequest){
		
		YearlyUsage currentYearUsage = new YearlyUsage();
		YearlyUsage previousYearUsage = new YearlyUsage();
		if(null != yearlyUsageList && yearlyUsageList.size() >0){
			List<Month> currMonthlyList = new ArrayList<Month>();
			List<Month> prevMonthlyList = new ArrayList<Month>();
			for(YearUsageDO data : yearlyUsageList){
				Month month = new Month();
				month.setCost(UsageHistoryUtil.getStringValue(data.getTotalMonthCost()));
				month.setUsage(UsageHistoryUtil.getStringValue(data.getMonthUsage()));
				month.setMonth(DateUtil.getMonthLabel(data.getYearlyUsageId().getActualDate(),true));
				month.setTempHigh(data.getMonthAveTempHi());
				month.setActualDay(UsageHistoryUtil.getStringValue(data.getYearlyUsageId().getActualDate()));
				month.setTempLow(data.getMonthAveTempLo());
				month.setYear(data.getYear());
				if(StringUtils.equalsIgnoreCase(data.getYear(), usageRequest.getFromDate())){
					previousYearUsage.addCostToTotalCost(month.getCost());
					previousYearUsage.addUsageToTotalUsage(month.getUsage());
					prevMonthlyList.add(month);
				}else if(StringUtils.equalsIgnoreCase(data.getYear(), usageRequest.getToDate())){
					currentYearUsage.addCostToTotalCost(month.getCost());
					currentYearUsage.addUsageToTotalUsage(month.getUsage());
					currMonthlyList.add(month);
				}
			}
			currentYearUsage.setMonthlyDataList(currMonthlyList);
			previousYearUsage.setMonthlyDataList(prevMonthlyList);
			twoYearsData.setDataAvailable(true);
		}else{
			twoYearsData.setErrorMessage("NO DATA FOUND FOR THE YEAR NUMBERS::::"+usageRequest.getFromDate()+"::AND::"+usageRequest.getToDate());
		}
		currentYearUsage.setYear(usageRequest.getToDate());
		
		previousYearUsage.setYear(usageRequest.getFromDate());
		twoYearsData.setCurrentYearUsage(currentYearUsage);
		twoYearsData.setPreviousYearUsage(previousYearUsage);
		twoYearsData.fillGapIfAllDataNotAvailable();
		return twoYearsData;
		
	}
	
	
	public EsenseDtlPCDailyData populatePCDailyUsage(EsenseDtlPCDailyData dailyUsageData, List<PCDailyUsageDO> pcDailyUsageList,UsageHistoryRequest usageRequest){
		
		if(null != pcDailyUsageList && pcDailyUsageList.size() >0){
			Hour selfHourlyData = new Hour();
			Hour effHourlyData = new Hour();
			Hour avgHourlyData = new Hour();
			PCDailyUsageDO dayDO = pcDailyUsageList.get(0);
			selfHourlyData.setUsage1(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr01()));
			selfHourlyData.setUsage2(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr02()));
			selfHourlyData.setUsage3(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr03()));
			selfHourlyData.setUsage4(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr04()));
			selfHourlyData.setUsage5(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr05()));
			selfHourlyData.setUsage6(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr06()));
			selfHourlyData.setUsage7(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr07()));
			selfHourlyData.setUsage8(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr08()));
			selfHourlyData.setUsage9(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr09()));
			selfHourlyData.setUsage10(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr10()));
			selfHourlyData.setUsage11(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr11()));
			selfHourlyData.setUsage12(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr12()));
			selfHourlyData.setUsage13(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr13()));
			selfHourlyData.setUsage14(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr14()));
			selfHourlyData.setUsage15(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr15()));
			selfHourlyData.setUsage16(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr16()));
			selfHourlyData.setUsage17(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr17()));
			selfHourlyData.setUsage18(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr18()));
			selfHourlyData.setUsage19(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr19()));
			selfHourlyData.setUsage20(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr20()));
			selfHourlyData.setUsage21(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr21()));
			selfHourlyData.setUsage22(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr22()));
			selfHourlyData.setUsage23(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr23()));
			selfHourlyData.setUsage24(UsageHistoryUtil.getStringValue(dayDO.getSelfUsageHr24()));
			
			selfHourlyData.setTempHigh(UsageHistoryUtil.getStringValue(dayDO.getDayTempHigh()));
			selfHourlyData.setTempLow(UsageHistoryUtil.getStringValue(dayDO.getDayTempLow()));
			selfHourlyData.setTotalUsage(UsageHistoryUtil.getStringValue(dayDO.getDaySelfUsage()));
			selfHourlyData.setActualDay(usageRequest.getActualDay());
			
			effHourlyData.setUsage1(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr01()));
			effHourlyData.setUsage2(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr02()));
			effHourlyData.setUsage3(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr03()));
			effHourlyData.setUsage4(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr04()));
			effHourlyData.setUsage5(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr05()));
			effHourlyData.setUsage6(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr06()));
			effHourlyData.setUsage7(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr07()));
			effHourlyData.setUsage8(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr08()));
			effHourlyData.setUsage9(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr09()));
			effHourlyData.setUsage10(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr10()));
			effHourlyData.setUsage11(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr11()));
			effHourlyData.setUsage12(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr12()));
			effHourlyData.setUsage13(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr13()));
			effHourlyData.setUsage14(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr14()));
			effHourlyData.setUsage15(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr15()));
			effHourlyData.setUsage16(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr16()));
			effHourlyData.setUsage17(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr17()));
			effHourlyData.setUsage18(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr18()));
			effHourlyData.setUsage19(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr19()));
			effHourlyData.setUsage20(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr20()));
			effHourlyData.setUsage21(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr21()));
			effHourlyData.setUsage22(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr22()));
			effHourlyData.setUsage23(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr23()));
			effHourlyData.setUsage24(UsageHistoryUtil.getStringValue(dayDO.getEffUsageHr24()));
			
			effHourlyData.setTempHigh(UsageHistoryUtil.getStringValue(dayDO.getDayTempHigh()));
			effHourlyData.setTempLow(UsageHistoryUtil.getStringValue(dayDO.getDayTempLow()));
			effHourlyData.setTotalUsage(UsageHistoryUtil.getStringValue(dayDO.getDayEffUsage()));
			effHourlyData.setActualDay(usageRequest.getActualDay());
			
			avgHourlyData.setUsage1(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr01()));
			avgHourlyData.setUsage2(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr02()));
			avgHourlyData.setUsage3(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr03()));
			avgHourlyData.setUsage4(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr04()));
			avgHourlyData.setUsage5(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr05()));
			avgHourlyData.setUsage6(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr06()));
			avgHourlyData.setUsage7(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr07()));
			avgHourlyData.setUsage8(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr08()));
			avgHourlyData.setUsage9(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr09()));
			avgHourlyData.setUsage10(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr10()));
			avgHourlyData.setUsage11(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr11()));
			avgHourlyData.setUsage12(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr12()));
			avgHourlyData.setUsage13(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr13()));
			avgHourlyData.setUsage14(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr14()));
			avgHourlyData.setUsage15(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr15()));
			avgHourlyData.setUsage16(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr16()));
			avgHourlyData.setUsage17(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr17()));
			avgHourlyData.setUsage18(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr18()));
			avgHourlyData.setUsage19(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr19()));
			avgHourlyData.setUsage20(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr20()));
			avgHourlyData.setUsage21(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr21()));
			avgHourlyData.setUsage22(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr22()));
			avgHourlyData.setUsage23(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr23()));
			avgHourlyData.setUsage24(UsageHistoryUtil.getStringValue(dayDO.getAvgUsageHr24()));
			avgHourlyData.setTempHigh(UsageHistoryUtil.getStringValue(dayDO.getDayTempHigh()));
			avgHourlyData.setTempLow(UsageHistoryUtil.getStringValue(dayDO.getDayTempLow()));
			avgHourlyData.setTotalUsage(UsageHistoryUtil.getStringValue(dayDO.getDayAvgUsage()));
			avgHourlyData.setActualDay(usageRequest.getActualDay());
			
			dailyUsageData.setAvgHourlyData(avgHourlyData);
			dailyUsageData.setEffHourlyData(effHourlyData);
			dailyUsageData.setSelfHourlyData(selfHourlyData);
			dailyUsageData.setDataAvailable(true);
		}else{
			dailyUsageData.setErrorMessage("NO PC DAILY DATA LIST FROM THE QUERY");
		}
		dailyUsageData.setDayOfMessage(usageRequest.getActualDay());
		return dailyUsageData;
	}
	
	public EsenseDtlPCWeeklyData populatePCWeeklyUsage(EsenseDtlPCWeeklyData weeklyUsageData, List<PCWeeklyUsageDO> pcWeeklyUsageList,UsageHistoryRequest usageHistoryRequest){
		
		if(null != pcWeeklyUsageList && pcWeeklyUsageList.size() >0){
			List<Day> effDayList = new ArrayList<Day>();
			List<Day> avgDayList = new ArrayList<Day>();
			List<Day> selfDayList = new ArrayList<Day>();
			WeeklyData selfWeekUsage = new WeeklyData();
			WeeklyData effWeekUsage = new WeeklyData();
			WeeklyData avgWeekUsage = new WeeklyData();
			
			for(PCWeeklyUsageDO dayUsage: pcWeeklyUsageList){
				Day day = new Day();
				day.setActualDay(UsageHistoryUtil.getStringValue(dayUsage.getpcWeeklyUsagePK().getActualDay()));
				//day.setDay(UsageHistoryUtil.getDayLabelsForWeek(dayUsage.getpcWeeklyUsagePK().getActualDay()));
				day.setTempHigh(UsageHistoryUtil.getStringValue(dayUsage.getDayTempHigh()));
				day.setTempLow(UsageHistoryUtil.getStringValue(dayUsage.getDayTempLow()));
				day.setStrDate(UsageHistoryUtil.getStrDateForDashboard(UsageHistoryUtil.getStringValue(dayUsage.getpcWeeklyUsagePK().getActualDay())));
				day.setUsage(UsageHistoryUtil.getStringValue(dayUsage.getDayAvgUsage()));
				avgWeekUsage.addUsageToTotalUsage(day.getUsage());
				avgDayList.add(day);
			}
			
			for(PCWeeklyUsageDO dayUsage: pcWeeklyUsageList){
				Day day = new Day();
				day.setActualDay(UsageHistoryUtil.getStringValue(dayUsage.getpcWeeklyUsagePK().getActualDay()));
				//day.setDay(UsageHistoryUtil.getDayLabelsForWeek(dayUsage.getpcWeeklyUsagePK().getActualDay()));
				day.setTempHigh(UsageHistoryUtil.getStringValue(dayUsage.getDayTempHigh()));
				day.setTempLow(UsageHistoryUtil.getStringValue(dayUsage.getDayTempLow()));
				day.setStrDate(UsageHistoryUtil.getStrDateForDashboard(UsageHistoryUtil.getStringValue(dayUsage.getpcWeeklyUsagePK().getActualDay())));
				day.setUsage(UsageHistoryUtil.getStringValue(dayUsage.getDayEffUsage()));
				effWeekUsage.addUsageToTotalUsage(day.getUsage());
				effDayList.add(day);
			}
			
			for(PCWeeklyUsageDO dayUsage: pcWeeklyUsageList){
				Day day = new Day();
				day.setActualDay(UsageHistoryUtil.getStringValue(dayUsage.getpcWeeklyUsagePK().getActualDay()));
				//day.setDay(UsageHistoryUtil.getDayLabelsForWeek(dayUsage.getpcWeeklyUsagePK().getActualDay()));
				day.setTempHigh(UsageHistoryUtil.getStringValue(dayUsage.getDayTempHigh()));
				day.setTempLow(UsageHistoryUtil.getStringValue(dayUsage.getDayTempLow()));
				day.setStrDate(UsageHistoryUtil.getStrDateForDashboard(UsageHistoryUtil.getStringValue(dayUsage.getpcWeeklyUsagePK().getActualDay())));
				day.setUsage(UsageHistoryUtil.getStringValue(dayUsage.getDaySelfUsage()));
				selfWeekUsage.addUsageToTotalUsage(day.getUsage());
				selfDayList.add(day);
			}
			selfWeekUsage.setDailyDataList(selfDayList);
			effWeekUsage.setDailyDataList(effDayList);
			avgWeekUsage.setDailyDataList(avgDayList);
			weeklyUsageData.setEffWeekUsage(effWeekUsage);
			weeklyUsageData.setAvgWeekUsage(avgWeekUsage);
			weeklyUsageData.setSelfWeekUsage(selfWeekUsage);
			weeklyUsageData.setDataAvailable(true);
			weeklyUsageData.calculateAvgTempHigh();
			weeklyUsageData.calculateAvgTempLow();
			weeklyUsageData.fillGapIfAllDataNotAvailable();
		}else{
			weeklyUsageData.setErrorMessage("NO PC WEEKLY DATA LIST FROM THE QUERY");
		}
		return weeklyUsageData;
	}
	
	
	public EsenseDtlPCMonthlyData populatePCMonthlyUsage(EsenseDtlPCMonthlyData monthlyUsageData, List<PCWeeklyUsageDO> pcMonthlyUsageList){
		
		if(null != pcMonthlyUsageList && pcMonthlyUsageList.size() >0){
			List<Day> effDayList = new ArrayList<Day>();
			List<Day> avgDayList = new ArrayList<Day>();
			List<Day> selfDayList = new ArrayList<Day>();
			
			MonthlyUsage selfMonthlyUsage = new MonthlyUsage();
			MonthlyUsage effMonthlyUsage = new MonthlyUsage();
			MonthlyUsage avgMonthlyUsage = new MonthlyUsage();
			
			for(PCWeeklyUsageDO dayUsage: pcMonthlyUsageList){
				Day day = new Day();
				day.setActualDay(UsageHistoryUtil.getStringValue(dayUsage.getpcWeeklyUsagePK().getActualDay()));
				//day.setDay(UsageHistoryUtil.getDayLabelsForWeek(dayUsage.getpcWeeklyUsagePK().getActualDay()));
				day.setTempHigh(UsageHistoryUtil.getStringValue(dayUsage.getDayTempHigh()));
				day.setTempLow(UsageHistoryUtil.getStringValue(dayUsage.getDayTempLow()));
				day.setStrDate(UsageHistoryUtil.getStrDateForDashboard(UsageHistoryUtil.getStringValue(dayUsage.getpcWeeklyUsagePK().getActualDay())));
				day.setUsage(UsageHistoryUtil.getStringValue(dayUsage.getDayAvgUsage()));
				avgMonthlyUsage.addUsageToTotalUsage(day.getUsage());
				avgDayList.add(day);
			}
			
			for(PCWeeklyUsageDO dayUsage: pcMonthlyUsageList){
				Day day = new Day();
				day.setActualDay(UsageHistoryUtil.getStringValue(dayUsage.getpcWeeklyUsagePK().getActualDay()));
				//day.setDay(UsageHistoryUtil.getDayLabelsForWeek(dayUsage.getpcWeeklyUsagePK().getActualDay()));
				day.setTempHigh(UsageHistoryUtil.getStringValue(dayUsage.getDayTempHigh()));
				day.setTempLow(UsageHistoryUtil.getStringValue(dayUsage.getDayTempLow()));
				day.setStrDate(UsageHistoryUtil.getStrDateForDashboard(UsageHistoryUtil.getStringValue(dayUsage.getpcWeeklyUsagePK().getActualDay())));
				day.setUsage(UsageHistoryUtil.getStringValue(dayUsage.getDayEffUsage()));
				effMonthlyUsage.addUsageToTotalUsage(day.getUsage());
				effDayList.add(day);
			}
			
			for(PCWeeklyUsageDO dayUsage: pcMonthlyUsageList){
				Day day = new Day();
				day.setActualDay(UsageHistoryUtil.getStringValue(dayUsage.getpcWeeklyUsagePK().getActualDay()));
				//day.setDay(UsageHistoryUtil.getDayLabelsForWeek(dayUsage.getpcWeeklyUsagePK().getActualDay()));
				day.setTempHigh(UsageHistoryUtil.getStringValue(dayUsage.getDayTempHigh()));
				day.setTempLow(UsageHistoryUtil.getStringValue(dayUsage.getDayTempLow()));
				day.setStrDate(UsageHistoryUtil.getStrDateForDashboard(UsageHistoryUtil.getStringValue(dayUsage.getpcWeeklyUsagePK().getActualDay())));
				day.setUsage(UsageHistoryUtil.getStringValue(dayUsage.getDaySelfUsage()));
				selfMonthlyUsage.addUsageToTotalUsage(day.getUsage());
				selfDayList.add(day);
			}
			
			selfMonthlyUsage.setDailyDataList(selfDayList);
			effMonthlyUsage.setDailyDataList(effDayList);
			avgMonthlyUsage.setDailyDataList(avgDayList);
			monthlyUsageData.setEffMonthUsage(effMonthlyUsage);
			monthlyUsageData.setAvgMonthUsage(avgMonthlyUsage);
			monthlyUsageData.setSelfMonthUsage(selfMonthlyUsage);
			monthlyUsageData.setDataAvailable(true);
			monthlyUsageData.fillGapIfAllDataNotAvailable();
		}else{
			monthlyUsageData.setErrorMessage("NO PC MONTHLY DATA LIST FROM THE QUERY");
		}
		return monthlyUsageData;
	}
	
	public EsenseDtlPCYearlyData populatePCYearlyUsage(EsenseDtlPCYearlyData yearlyUsageData, List<PCYearlyUsageDO> pcYearlyUsageList){
		
		YearlyUsage selfYearlyUsage = new YearlyUsage();
		YearlyUsage effYearlyUsage = new YearlyUsage();
		YearlyUsage avgYearlyUsage = new YearlyUsage();
		if(null != pcYearlyUsageList && pcYearlyUsageList.size() >0){
			List<Month> effMonthList = new ArrayList<Month>();
			List<Month> avgMonthList = new ArrayList<Month>();
			List<Month> selfMonthList = new ArrayList<Month>();
			
			for(PCYearlyUsageDO data : pcYearlyUsageList){
				Month month = new Month();
				month.setUsage(UsageHistoryUtil.getStringValue(data.getMonthSelfUsage()));
				selfYearlyUsage.addUsageToTotalUsage(month.getUsage());
				month.setMonth(DateUtil.getMonthLabel(data.getPcYearlyUsagePK().getActualDay(),true));
				month.setTempHigh(UsageHistoryUtil.getStringValue(data.getMonthAvgTempHigh()));
				month.setTempLow(UsageHistoryUtil.getStringValue(data.getMonthAvgTempLow()));
				month.setActualDay(UsageHistoryUtil.getStringValue(data.getPcYearlyUsagePK().getActualDay()));
				month.setYear(data.getYearDate());
				selfMonthList.add(month);
			}
			
			for(PCYearlyUsageDO data : pcYearlyUsageList){
				Month month = new Month();
				month.setUsage(UsageHistoryUtil.getStringValue(data.getMonthAvgUsage()));
				avgYearlyUsage.addUsageToTotalUsage(month.getUsage());
				month.setMonth(DateUtil.getMonthLabel(data.getPcYearlyUsagePK().getActualDay(),true));
				month.setTempHigh(UsageHistoryUtil.getStringValue(data.getMonthAvgTempHigh()));
				month.setTempLow(UsageHistoryUtil.getStringValue(data.getMonthAvgTempLow()));
				month.setActualDay(UsageHistoryUtil.getStringValue(data.getPcYearlyUsagePK().getActualDay()));
				month.setYear(data.getYearDate());
				avgMonthList.add(month);
			}
			
			for(PCYearlyUsageDO data : pcYearlyUsageList){
				Month month = new Month();
				month.setUsage(UsageHistoryUtil.getStringValue(data.getMonthEffUsage()));
				effYearlyUsage.addUsageToTotalUsage(month.getUsage());
				month.setMonth(DateUtil.getMonthLabel(data.getPcYearlyUsagePK().getActualDay(),true));
				month.setTempHigh(UsageHistoryUtil.getStringValue(data.getMonthAvgTempHigh()));
				month.setTempLow(UsageHistoryUtil.getStringValue(data.getMonthAvgTempLow()));
				month.setActualDay(UsageHistoryUtil.getStringValue(data.getPcYearlyUsagePK().getActualDay()));
				month.setYear(data.getYearDate());
				effMonthList.add(month);
			}
			
			selfYearlyUsage.setMonthlyDataList(selfMonthList);
			effYearlyUsage.setMonthlyDataList(effMonthList);
			avgYearlyUsage.setMonthlyDataList(avgMonthList);
			yearlyUsageData.setDataAvailable(true);
			yearlyUsageData.calculateAvgTempHigh();
			yearlyUsageData.calculateAvgTempLow();
		}else{
			yearlyUsageData.setErrorMessage("NO PC YEARLY DATA LIST FROM THE QUERY");
		}
		selfYearlyUsage.setYear(UsageHistoryUtil.getYearLabelForPCUsage(yearlyUsageData.getCurrentYear()));
		effYearlyUsage.setYear(UsageHistoryUtil.getYearLabelForPCUsage(yearlyUsageData.getCurrentYear()));
		avgYearlyUsage.setYear(UsageHistoryUtil.getYearLabelForPCUsage(yearlyUsageData.getCurrentYear()));
		yearlyUsageData.setEffYearUsage(effYearlyUsage);
		yearlyUsageData.setAvgYearUsage(avgYearlyUsage);
		yearlyUsageData.setSelfYearUsage(selfYearlyUsage);
		yearlyUsageData.fillGapIfAllDataNotAvailable();
		yearlyUsageData.setDateRange(UsageHistoryUtil.getYearLabelForPCUsage(yearlyUsageData.getCurrentYear()));
		return yearlyUsageData;
	}
	
	
	public EsenseDtlBDYearlyUsage populateBDYearlyUsage(EsenseDtlBDYearlyUsage bdYearlyUsageData, List<BDYearUsageDO> bdYearlyUsageList){
		
		if(null != bdYearlyUsageList && bdYearlyUsageList.size() >0){
			List<BDSlice> bdSliceList = new ArrayList<BDSlice>();
			for(BDYearUsageDO bdYearUsage: bdYearlyUsageList){
				BDSlice bdSlice = new BDSlice();
				bdSlice.setCost(UsageHistoryUtil.getRequiredScaleForInputString(bdYearUsage.getSliceCost(),2));
				bdSlice.setDescription(bdYearUsage.getBDSlices().getSliceDesc());
				bdSlice.setName(bdYearUsage.getBDSlices().getSliceName());
				bdSlice.setUsage(UsageHistoryUtil.getRequiredScaleForInputString(bdYearUsage.getSliceUsg(),1));
				bdSlice.setId(bdYearUsage.getBDSlices().getSliceId());
				bdSliceList.add(bdSlice);
			}
			BDYearUsageDO bdUsageDO = bdYearlyUsageList.get(0);
			BDUsage bdUsage = new BDUsage();
			bdUsage.setSliceList(bdSliceList);
			bdUsage.setTempHigh(bdUsageDO.getMonTempHigh());
			bdUsage.setTempLow(bdUsageDO.getMonTempLow());
			bdYearlyUsageData.setBdUsage(bdUsage);
			bdYearlyUsageData.setTotalCost(UsageHistoryUtil.getRequiredScaleForInputString(bdUsageDO.getTotalMonthCost(),2));
			bdYearlyUsageData.setTotalUsage(UsageHistoryUtil.getRequiredScaleForInputString(bdUsageDO.getTotalMonthUsg(),1));
			Date date = DateUtil.getDate(bdUsageDO.getActualDay(), "yyyy-MM-dd");
			bdYearlyUsageData.setActualDay(DateUtil.getFormatedDate(date, "MM/dd/yyyy"));
			bdYearlyUsageData.setCurrentMonthNum(bdUsageDO.getYearlyUsageId().getYearMonthNo());
			bdYearlyUsageData.setDataAvailable(true);
			bdYearlyUsageData.calculatePercentage();
			bdYearlyUsageData.setMonthNum(DateUtil.getMonthInt(date));
			bdYearlyUsageData.setYear(DateUtil.getYearInt(date));
		}else{
			bdYearlyUsageData.setErrorMessage("NO BD YEARLY DATA LIST FROM THE QUERY");
		}
		bdYearlyUsageData.setMonth(UsageHistoryUtil.getMonthLabelForMonthlyUsage(bdYearlyUsageData.getActualDay()));
		return bdYearlyUsageData;
	}
	
	
	
	
	
	public EsenseDtlBDWeeklyUsage populateBDWeeklyUsage(EsenseDtlBDWeeklyUsage bdWeekUsageData, List<BDDayWeekUsageDO> bdWeeklyUsageList){
		
		BDUsage bdUsage = new BDUsage();
		if(null != bdWeeklyUsageList && bdWeeklyUsageList.size() >0){
			List<BDSlice> tempBDSliceList = new ArrayList<BDSlice>();
			List<BDSlice> bdSliceList = new ArrayList<BDSlice>();
			for(BDDayWeekUsageDO bdWeekUsage: bdWeeklyUsageList){
				BDSlice bdSlice = new BDSlice();
				bdSlice.setDescription(bdWeekUsage.getBDSlices().getSliceDesc());
				bdSlice.setName(bdWeekUsage.getBDSlices().getSliceName());
				bdSlice.setId(bdWeekUsage.getBDSlices().getSliceId());
				bdSlice.setCost(bdWeekUsage.getSliceCost());
				bdSlice.setUsage(bdWeekUsage.getSliceUsg());
				tempBDSliceList.add(bdSlice);
			}
			for(int i=1; i<=5; i++){
				BDSlice bdSlice1 = new BDSlice();
				for(BDSlice bdSlice : tempBDSliceList){
					if(StringUtils.equalsIgnoreCase(String.valueOf(i), bdSlice.getId())){
						bdSlice1.setDescription(bdSlice.getDescription());
						bdSlice1.setName(bdSlice.getName());
						bdSlice1.setId(bdSlice.getId());
						bdSlice1.addCostToSliceCost(bdSlice.getCost());
						bdSlice1.addUsageToSliceUsage(bdSlice.getUsage());
					}
				}
				if(StringUtils.isNotBlank(bdSlice1.getId())){
					bdSliceList.add(bdSlice1);
				}
			}
			bdUsage.setSliceList(bdSliceList);
			bdWeekUsageData.setBdUsage(bdUsage);
			bdWeekUsageData.setTotalCost();
			bdWeekUsageData.setTotalUsage();
			bdWeekUsageData.setDataAvailable(true);
			bdWeekUsageData.calculatePercentage();
			populateTemperatureList(bdWeekUsageData,bdWeeklyUsageList);
		}else{
			bdWeekUsageData.setErrorMessage("NO BD WEEKLY DATA LIST FROM THE QUERY");
		}
		bdWeekUsageData.setWeekOfMessage(bdWeekUsageData.getActualDay());
		return bdWeekUsageData;
	}
	
	
	private void populateTemperatureList(EsenseDtlBDWeeklyUsage bdWeekUsageData, List<BDDayWeekUsageDO> bdWeeklyUsageList){
		
		String actualDay = bdWeeklyUsageList.get(0).getBdDayWeekUsageId().getActualDay();
		List<Date> dateList = UsageHistoryUtil.getWeekDateListFromAnyDay(DateUtil.getDate(actualDay, "yyyy-MM-dd"));
		List<String> highTempList = new ArrayList<String>();
		List<String> lowTempList = new ArrayList<String>();
		for(Date date: dateList){
			highTempList.add(getHighTempOrLowTempValue(true,date,bdWeeklyUsageList));
			lowTempList.add(getHighTempOrLowTempValue(false,date,bdWeeklyUsageList));
		}
		bdWeekUsageData.setHighTempList(highTempList);
		bdWeekUsageData.setLowTempList(lowTempList);
	}
	
	
	private String getHighTempOrLowTempValue(boolean isHigh,Date dateIn,List<BDDayWeekUsageDO> bdWeeklyUsageList){
		
		for(BDDayWeekUsageDO bdWeekUsage: bdWeeklyUsageList){
			Date date = DateUtil.getDate(bdWeekUsage.getBdDayWeekUsageId().getActualDay(), "yyyy-MM-dd");
			if(date.equals(dateIn)){
				if(isHigh){return bdWeekUsage.getDayTempHigh();}else{return bdWeekUsage.getDayTempLow();}
			}
		}
		return null;
	}
	
	
	public void populateBDYearTemperatureData(BDYearTempData bdYearTempData,List<TemperatureDO> tempList){
		
		if(null != tempList && tempList.size() >0){
			List<Temperature> returnTempList = new ArrayList<Temperature>();
			for(TemperatureDO temp: tempList){
				Temperature temperature = new Temperature();
				temperature.setTempHigh(temp.getTempHigh());
				temperature.setTempLow(temp.getTempLow());
				temperature.setYearMonthNum(temp.getYearMonthNum());
				returnTempList.add(temperature);
			}
			bdYearTempData.setTempList(returnTempList);
			bdYearTempData.setDataAvailable(true);
		}else{
			logger.info("NO TEMPERATURE DATA FOUND FOR THIS QUERY::");
		}
	}
	
}
