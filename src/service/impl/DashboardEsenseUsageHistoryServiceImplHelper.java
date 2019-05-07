package com.reliant.sm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.hibernate.dataobject.BDDayWeekUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.DayWeeklyUsageDO;
import com.reliant.sm.dao.hibernate.dataobject.PCWeeklyUsageDO;
import com.reliant.sm.model.BDSlice;
import com.reliant.sm.model.BDUsage;
import com.reliant.sm.model.BreakDownWeekData;
import com.reliant.sm.model.DashBoardBDData;
import com.reliant.sm.model.DashBoardPCData;
import com.reliant.sm.model.DashboardUsageAndCost;
import com.reliant.sm.model.Day;
import com.reliant.sm.model.PCUsage;
import com.reliant.sm.model.Temperature;
import com.reliant.sm.model.WeeklyData;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;
/**
 * @author bbachin1
 * 
 */
@Component
public class DashboardEsenseUsageHistoryServiceImplHelper {
	
	private static LoggerUtil logger = LoggerUtil.getInstance(DashboardEsenseUsageHistoryServiceImplHelper.class);
	

	public DashboardUsageAndCost processDashBoardCostAndUsage(List<DayWeeklyUsageDO> twoWeeksUsageList, List<String> weekNumberList){
		logger.debug("START:::DashboardUsageHistoryServiceImplHelper::::processDashBoardCostAndUsage()>>>>>>>");
		DashboardUsageAndCost dashboardUsageAndCost = new DashboardUsageAndCost();
		if(null != twoWeeksUsageList && twoWeeksUsageList.size() > 0){
			populateWeekUsageList(twoWeeksUsageList,weekNumberList,dashboardUsageAndCost);
			dashboardUsageAndCost.setDataAvailable(true);
			dashboardUsageAndCost.fillGapIfAllDataNotAvailable();
		}else{
			logger.info("NO USAGE FOUND FOR THE WEEK NUMBER LIST::::::::"+weekNumberList);
			dashboardUsageAndCost.setErrorMessage("NO USAGE FOUND FOR THE WEEK NUMBER LIST::::::::"+weekNumberList);
		}
		logger.debug("END:::DashboardUsageHistoryServiceImplHelper::::processDashBoardCostAndUsage()<<<<<<<");
		return dashboardUsageAndCost;
	}
	
	
	private void populateWeekUsageList(List<DayWeeklyUsageDO> twoWeeksUsageList,List<String> weekNumberList,
			DashboardUsageAndCost dashboardUsageAndCost){
		
		WeeklyData previousCostAndUsage = new WeeklyData();
		WeeklyData currentCostAndUsage = new WeeklyData();
		String previousWeekNumber = getPreviousWeekNumber(weekNumberList);
		String currentWeekNumber = getCurrentWeekNumber(weekNumberList);
		logger.debug("CURRENT WEEK NUMBER::::::::"+currentWeekNumber+":::AND PREVIOUS WEEK NUMBER::::::::"+previousWeekNumber);
		for(DayWeeklyUsageDO dayDO : twoWeeksUsageList){
			Day dayUsage = new Day();
			dayUsage.setCost(UsageHistoryUtil.getRequiredScaleForInputString(UsageHistoryUtil.getStringValue(dayDO.getTotalDayCost()),2));
			dayUsage.setStrDate(UsageHistoryUtil.getStrDateForDashboard(dayDO.getDayWeeklyUsageId().getActualDay()));
			dayUsage.setDay(UsageHistoryUtil.getDayLabelsForWeek(dayDO.getDayOfWeek()));
			dayUsage.setActualDay(UsageHistoryUtil.getStringValue(dayDO.getDayWeeklyUsageId().getActualDay()));
			dayUsage.setTempHigh(UsageHistoryUtil.getStringValue(dayDO.getDayTempHigh()));
			dayUsage.setTempLow(UsageHistoryUtil.getStringValue(dayDO.getDayTempLow()));
			dayUsage.setUsage(UsageHistoryUtil.getRequiredScaleForInputString(UsageHistoryUtil.getStringValue(dayDO.getTotalDayUsage()),1));
			if(StringUtils.equalsIgnoreCase(previousWeekNumber, dayDO.getYearWeekNum())){
				previousCostAndUsage.addDayUsageList().add(dayUsage);
				previousCostAndUsage.addCostToTotalCost(dayUsage.getCost());
				previousCostAndUsage.addUsageToTotalUsage(dayUsage.getUsage());
				previousCostAndUsage.setYearWeekNumber(dayDO.getYearWeekNum());
			}else if(StringUtils.equalsIgnoreCase(currentWeekNumber, dayDO.getYearWeekNum())){
				currentCostAndUsage.addDayUsageList().add(dayUsage);
				currentCostAndUsage.addCostToTotalCost(dayUsage.getCost());
				currentCostAndUsage.addUsageToTotalUsage(dayUsage.getUsage());
				currentCostAndUsage.setYearWeekNumber(dayDO.getYearWeekNum());
			}
		}
		previousCostAndUsage.setDayAndYear(previousCostAndUsage.getYearWeekNumber());
		currentCostAndUsage.setDayAndYear(currentCostAndUsage.getYearWeekNumber());
		previousCostAndUsage.setWeekOfMessage(previousCostAndUsage.getYearWeekNumber());
		currentCostAndUsage.setWeekOfMessage(currentCostAndUsage.getYearWeekNumber());
		currentCostAndUsage.setMonth();
		previousCostAndUsage.setMonth();
		dashboardUsageAndCost.setPreviousWeekUsage(previousCostAndUsage);
		dashboardUsageAndCost.setCurrentWeekUsage(currentCostAndUsage);
	}
	
	
	
	
	public DashBoardPCData processDashBoardUsageCompare(List<PCWeeklyUsageDO> twoWeeksPCUsageList, List<String> weekNumberList){
		logger.debug("START:::DashboardUsageHistoryServiceImplHelper::::processDashBoardUsageCompare()>>>>>>>");
		DashBoardPCData dashBoardPCData = new DashBoardPCData();
		
		String previousWeekNumber = getPreviousWeekNumber(weekNumberList);
		String currentWeekNumber = getCurrentWeekNumber(weekNumberList);
		
		logger.debug("CURRENT WEEK NUMBER::::::::"+currentWeekNumber);
		logger.debug("PREVIOUS WEEK NUMBER::::::::"+previousWeekNumber);
		
		List<Date> previousWeekDateList = new ArrayList<Date>();
		List<Date> currentWeekDateList = new ArrayList<Date>();
		
		if(null != twoWeeksPCUsageList && twoWeeksPCUsageList.size() > 0){
			for(PCWeeklyUsageDO pcDO: twoWeeksPCUsageList){
				PCUsage pcUsage = new PCUsage();
				
				pcUsage.setEffUsage(UsageHistoryUtil.getRequiredScaleForInputString(UsageHistoryUtil.getStringValue(pcDO.getWeekEffUsage()),1));
				pcUsage.setTempHigh(UsageHistoryUtil.getStringValue(pcDO.getWeekAvgTempHigh()));
				pcUsage.setTempLow(UsageHistoryUtil.getStringValue(pcDO.getWeekAvgTempLow()));
				pcUsage.setAvgUsage(UsageHistoryUtil.getRequiredScaleForInputString(UsageHistoryUtil.getStringValue(pcDO.getWeekAvgUsage()),1));
				//pcUsage.setUsageComparitionMessage(UsageHistoryUtil.getStringValue(pcDO.get));
				pcUsage.setSelfUsage(UsageHistoryUtil.getRequiredScaleForInputString(UsageHistoryUtil.getStringValue(pcDO.getWeekSelfUsage()),1));
				String yearWeekNum = pcDO.getYearWeekNo();
				pcUsage.setYearWeekNum(yearWeekNum);
				if(StringUtils.equalsIgnoreCase(previousWeekNumber, yearWeekNum)){
					previousWeekDateList.add(pcDO.getpcWeeklyUsagePK().getActualDay());
					dashBoardPCData.setPreviousWeekPCData(pcUsage);
					dashBoardPCData.setPrevDataAvailable(true);
				}else{
					currentWeekDateList.add(pcDO.getpcWeeklyUsagePK().getActualDay());
					dashBoardPCData.setCurrentWeekPCData(pcUsage);
					dashBoardPCData.setDataAvailable(true);
				}
			}
			dashBoardPCData.getCurrentWeekPCData().setDateRange(UsageHistoryUtil.getWeekRangeFromActualDay(currentWeekNumber));
			dashBoardPCData.getPreviousWeekPCData().setDateRange(UsageHistoryUtil.getWeekRangeFromActualDay(previousWeekNumber));
			dashBoardPCData.setDataAvailable(true);
		}else{
			logger.info("NO USAGE FOUND FOR THE WEEK NUMBER LIST::::::::"+weekNumberList);
			dashBoardPCData.setDataAvailable(false);
			dashBoardPCData.setPrevDataAvailable(false);
			dashBoardPCData.setDataAvailable(false);
		}
		logger.info("previousWeekDateList::::::"+previousWeekDateList);
		logger.info("currentWeekDateList::::::"+currentWeekDateList);
		logger.debug("END:::DashboardUsageHistoryServiceImplHelper::::processDashBoardUsageCompare()<<<<<<<");
		return dashBoardPCData;
	}
	
	
	public DashBoardBDData populateBDDashboardUsage(DashBoardBDData dashBoardBDData, List <BDDayWeekUsageDO> bdWeeklyUsageList){
		
		if(null != bdWeeklyUsageList && bdWeeklyUsageList.size() >0){
			logger.info("SIZE FROM THE DB QUERY:::::::"+bdWeeklyUsageList.size());
			dashBoardBDData.setDataAvailable(true);
			dashBoardBDData.setCurrentBDData(getBDUsageBasedOnWeekNumber(bdWeeklyUsageList,dashBoardBDData.getToDate(),bdWeeklyUsageList.size()-1));
			dashBoardBDData.setPreviousBDData(getBDUsageBasedOnWeekNumber(bdWeeklyUsageList,dashBoardBDData.getFromDate(),0));
		}else{
			dashBoardBDData.setErrorMessage("NO BD WEEKLY DATA LIST FROM THE QUERY");
		}
		return dashBoardBDData;
	}
	
	
	private BreakDownWeekData getBDUsageBasedOnWeekNumber(List <BDDayWeekUsageDO> bdWeeklyUsageList, String weekNumber,int indexNo){
		
		BreakDownWeekData breakDownWeekData = new BreakDownWeekData();
		BDUsage bdUsage = new BDUsage();
		List<BDSlice> tempBDSliceList = new ArrayList<BDSlice>();
		List<BDSlice> bdSliceList = new ArrayList<BDSlice>();
		
		List<Temperature> tempList = new ArrayList<Temperature>();
		List<String> tempDatesList = new ArrayList<String>();
		
		int index=0;
		for(BDDayWeekUsageDO bdWeekUsage: bdWeeklyUsageList){
			BDSlice bdSlice = new BDSlice();
			bdSlice.setDescription(bdWeekUsage.getBDSlices().getSliceDesc());
			bdSlice.setName(bdWeekUsage.getBDSlices().getSliceName());
			bdSlice.setId(bdWeekUsage.getBDSlices().getSliceId());
			bdSlice.setCost(bdWeekUsage.getSliceCost());
			bdSlice.setUsage(bdWeekUsage.getSliceUsg());
			if(StringUtils.equalsIgnoreCase(weekNumber, bdWeeklyUsageList.get(index).getBdDayWeekUsageId().getYearWeekNo())){
				Temperature temp = new Temperature();
				temp.setTempHigh(bdWeekUsage.getDayTempHigh());
				temp.setTempLow(bdWeekUsage.getDayTempLow());
				temp.setDay(UsageHistoryUtil.getStrDateForDashboard(bdWeekUsage.getBdDayWeekUsageId().getActualDay()));
				if(!tempDatesList.contains(bdWeekUsage.getBdDayWeekUsageId().getActualDay())){
					tempList.add(temp);
				}
				tempDatesList.add(bdWeekUsage.getBdDayWeekUsageId().getActualDay());
				tempBDSliceList.add(bdSlice);
			}
			index++;
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
			if(StringUtils.isNotBlank(bdSlice1.getName())){
				bdSliceList.add(bdSlice1);
			}
		}
		
		bdUsage.setSliceList(bdSliceList);
		breakDownWeekData.setTempList(tempList);
		breakDownWeekData.setBdUsage(bdUsage);
		breakDownWeekData.setYearWeekNum(bdWeeklyUsageList.get(indexNo).getBdDayWeekUsageId().getYearWeekNo());
		breakDownWeekData.setWeekOfMessage(bdWeeklyUsageList.get(indexNo).getBdDayWeekUsageId().getActualDay());
		breakDownWeekData.setTotalCost();
		breakDownWeekData.setTotalUsage();
		breakDownWeekData.calculatePercentage();
		
		return breakDownWeekData;
	}
	
	
	private String getCurrentWeekNumber(List<String> weekNumberList){
		
		String currentWeekNumber = "";
		if(Integer.valueOf(weekNumberList.get(0)) > Integer.valueOf(weekNumberList.get(1))){
			currentWeekNumber =  weekNumberList.get(0);
		}else{
			currentWeekNumber = weekNumberList.get(1);
		}
		return currentWeekNumber;
	}
	
	
	private String getPreviousWeekNumber(List<String> weekNumberList){
		
		String previousWeekNumber = "";
		if(Integer.valueOf(weekNumberList.get(0)) < Integer.valueOf(weekNumberList.get(1))){
			previousWeekNumber =  weekNumberList.get(0);
		}else{
			previousWeekNumber = weekNumberList.get(1);
		}
		return previousWeekNumber;
	}
	
	
	private String getStringFromMap(Map<String,Object> keyValPair, String keyValue){
		
		if(keyValPair.get(keyValue) instanceof String){
			return (String) keyValPair.get(keyValue);
		}else if(keyValPair.get(keyValue) instanceof BigDecimal){
			return ((BigDecimal) keyValPair.get(keyValue)).toString();
		}else if(keyValPair.get(keyValue) instanceof Date){
			return ((Date) keyValPair.get(keyValue)).toString();
		}	
		return "";
	}
	
}
