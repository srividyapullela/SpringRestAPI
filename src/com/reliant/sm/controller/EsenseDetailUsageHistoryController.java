package com.reliant.sm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;

/**
 * @author bbachin1
 * 
 */
@RestController
@RequestMapping(value = "/esensedtl")
public class EsenseDetailUsageHistoryController implements CommonConstants{
	
	private static final long serialVersionUID = 1L;

	private static LoggerUtil logger = LoggerUtil.getInstance(EsenseDetailUsageHistoryController.class);
	
	@Autowired
	private EsenseDetailUsageHistoryService esenseDetailUsageHistoryService;
	
	@RequestMapping(value = DAILY_USAGE, method = RequestMethod.POST)
    public EsenseDetailDailyData getDailyUsage(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDetailDailyData esenseDetailDailyData = esenseDetailUsageHistoryService.getDailyUsageData(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_DAILY_USAGE, ESENSE_DETAIL+DAILY_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDetailDailyData);
		return esenseDetailDailyData;
    }
	
	
	@RequestMapping(value = WEEKLY_USAGE, method = RequestMethod.POST)
    public EsenseDetailWeeklyData getWeeklyUsage(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDetailWeeklyData esenseDetailWeeklyData = esenseDetailUsageHistoryService.getWeeklyUsageData(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_WEEKLY_USAGE, ESENSE_DETAIL+WEEKLY_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDetailWeeklyData);
		return esenseDetailWeeklyData;
    }
	
	
	@RequestMapping(value = MONTHLY_USAGE, method = RequestMethod.POST)
    public EsenseDetailMonthlyData getMonthlyUsage(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDetailMonthlyData esenseDetailMonthlyData = esenseDetailUsageHistoryService.getMonthlyUsageData(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_MONTHLY_USAGE, ESENSE_DETAIL+MONTHLY_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDetailMonthlyData);
		return esenseDetailMonthlyData;
    }
	
	
	@RequestMapping(value = YEARLY_USAGE, method = RequestMethod.POST)
    public EsenseDetailYearlyData getYearlyUsage(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDetailYearlyData esenseDetailYearlyData = esenseDetailUsageHistoryService.getYearlyUsageData(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_YEARLY_USAGE, ESENSE_DETAIL+YEARLY_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDetailYearlyData);
		return esenseDetailYearlyData;
    }
	
	@RequestMapping(value = COMPARE_TWOWEEKS_USAGE, method = RequestMethod.POST)
    public EsenseDtlCompTwoWeeksData getTwoWeeksUsageForCompare(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlCompTwoWeeksData esenseDtlCompTwoWeeksData = esenseDetailUsageHistoryService.getTwoWeeksDataForCompare(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_COMPARE_TWOWEEKS_USAGE, ESENSE_DETAIL+COMPARE_TWOWEEKS_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDtlCompTwoWeeksData);
		return esenseDtlCompTwoWeeksData;
    }
	
	@RequestMapping(value = COMPARE_TWODAYS_USAGE, method = RequestMethod.POST)
    public EsenseDtlCompTwoDaysData getTwoDaysUsageForCompare(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlCompTwoDaysData esenseDtlCompTwoDaysData = esenseDetailUsageHistoryService.getTwoDaysDataForCompare(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_COMPARE_TWODAYS_USAGE, ESENSE_DETAIL+COMPARE_TWODAYS_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDtlCompTwoDaysData);
		return esenseDtlCompTwoDaysData;
    }
	
	
	@RequestMapping(value = COMPARE_TWOYEARS_USAGE, method = RequestMethod.POST)
    public EsenseDtlCompTwoYearsData getTwoYearsUsageForCompare(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlCompTwoYearsData esenseDtlCompTwoYearsData = esenseDetailUsageHistoryService.getTwoYearsDataForCompare(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_COMPARE_TWOYEARS_USAGE, ESENSE_DETAIL+COMPARE_TWOYEARS_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDtlCompTwoYearsData);
		return esenseDtlCompTwoYearsData;
    }
	
	
	@RequestMapping(value = COMPARE_TWOMONTHS_USAGE, method = RequestMethod.POST)
    public EsenseDtlCompTwoMonthsData getTwoMonthsUsageForCompare(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlCompTwoMonthsData esenseDtlCompTwoMonthsData = esenseDetailUsageHistoryService.getTwoMonthsDataForCompare(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_COMPARE_TWOMONTHS_USAGE, ESENSE_DETAIL+COMPARE_TWOMONTHS_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDtlCompTwoMonthsData);
		return esenseDtlCompTwoMonthsData;
    }
	
	@RequestMapping(value = COMPARE_MONTHTOMONTH_USAGE, method = RequestMethod.POST)
    public EsenseDtlCompMonthToMonthData compareMonthToMonthUsage(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlCompMonthToMonthData esenseDtlCompMonthToMonthData = esenseDetailUsageHistoryService.compareMonthToMonthUsage(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_COMPARE_MONTHTOMONTH_USAGE, ESENSE_DETAIL+COMPARE_MONTHTOMONTH_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDtlCompMonthToMonthData);
		return esenseDtlCompMonthToMonthData;
    }
	
	
	@RequestMapping(value = PC_DAILY_USAGE, method = RequestMethod.POST)
    public EsenseDtlPCDailyData getPCDailyUsageData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlPCDailyData esenseDtlPCDailyData = esenseDetailUsageHistoryService.getPCDailyUsageData(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_PC_DAILY_USAGE, ESENSE_DETAIL+PC_DAILY_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDtlPCDailyData);
		return esenseDtlPCDailyData;
    }
	
	@RequestMapping(value = PC_WEEKLY_USAGE, method = RequestMethod.POST)
    public EsenseDtlPCWeeklyData getPCWeeklyUsageData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlPCWeeklyData esenseDtlPCWeeklyData = esenseDetailUsageHistoryService.getPCWeeklyUsageData(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_PC_WEEKLY_USAGE, ESENSE_DETAIL+PC_WEEKLY_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDtlPCWeeklyData);
		return esenseDtlPCWeeklyData;
    }
	
	
	@RequestMapping(value = PC_MONTHLY_USAGE, method = RequestMethod.POST)
    public EsenseDtlPCMonthlyData getPCMonthlyUsageData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlPCMonthlyData esenseDtlPCMonthlyData = esenseDetailUsageHistoryService.getPCMonthlyUsageData(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_PC_MONTHLY_USAGE, ESENSE_DETAIL+PC_MONTHLY_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDtlPCMonthlyData);
		return esenseDtlPCMonthlyData;
    }
	
	
	@RequestMapping(value = PC_YEARLY_USAGE, method = RequestMethod.POST)
    public EsenseDtlPCYearlyData getPCYearlyUsageData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlPCYearlyData esenseDtlPCYearlyData = esenseDetailUsageHistoryService.getPCYearlyUsageData(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_PC_YEARLY_USAGE, ESENSE_DETAIL+PC_YEARLY_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDtlPCYearlyData);
		return esenseDtlPCYearlyData;
    }
	
	
	@RequestMapping(value = BD_MONTHLY_USAGE, method = RequestMethod.POST)
    public EsenseDtlBDYearlyUsage getBDMonthlyUsageData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlBDYearlyUsage esenseDtlBDYearlyUsage = esenseDetailUsageHistoryService.getBDMonthlyUsageData(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_BD_MONTHLY_USAGE, ESENSE_DETAIL+BD_MONTHLY_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDtlBDYearlyUsage);
		return esenseDtlBDYearlyUsage;
    }
	
	
	@RequestMapping(value = BD_WEEKLY_USAGE, method = RequestMethod.POST)
    public EsenseDtlBDWeeklyUsage getBDWeeklyUsageData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlBDWeeklyUsage esenseDtlBDWeeklyUsage = esenseDetailUsageHistoryService.getBDWeeklyUsageData(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_BD_WEEKLY_USAGE, ESENSE_DETAIL+BD_WEEKLY_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, esenseDtlBDWeeklyUsage);
		return esenseDtlBDWeeklyUsage;
    }
	
	@RequestMapping(value = BD_YEARTEMP_USAGE, method = RequestMethod.POST)
    public BDYearTempData getBDYearTemperatureData(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		BDYearTempData bdYearTempData = esenseDetailUsageHistoryService.getBDYearTemperatureData(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_BD_YEARTEMP_USAGE, ESENSE_DETAIL+BD_YEARTEMP_USAGE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, bdYearTempData);
		return bdYearTempData;
    }
	
	
	@RequestMapping(value = PC_DATA_AVAILABLE, method = RequestMethod.POST)
    public EsenseDtlPCDataAvailableData getPCDataAvailableOrNot(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		EsenseDtlPCDataAvailableData pcDataAvailable = esenseDetailUsageHistoryService.getPCDataAvailable(usageHistoryRequest);
		logger.logTransaction(EsenseDetailUsageHistoryController.class.getName(), TX_PC_DATA_AVAILABLE, ESENSE_DETAIL+PC_DATA_AVAILABLE, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, pcDataAvailable);
		return pcDataAvailable;
    }
	
}
