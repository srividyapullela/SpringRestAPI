package com.reliant.sm.service;

import org.springframework.stereotype.Component;

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


/**
 * @author bbachin1
 * 
 */
@Component
public interface EsenseDetailUsageHistoryService {
	
	public EsenseDetailDailyData getDailyUsageData(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDetailWeeklyData getWeeklyUsageData(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDetailMonthlyData getMonthlyUsageData(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDetailYearlyData getYearlyUsageData(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDtlCompTwoWeeksData getTwoWeeksDataForCompare(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDtlCompTwoDaysData getTwoDaysDataForCompare(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDtlCompTwoMonthsData getTwoMonthsDataForCompare(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDtlCompMonthToMonthData compareMonthToMonthUsage(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDtlCompTwoYearsData getTwoYearsDataForCompare(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDtlPCDailyData getPCDailyUsageData(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDtlPCWeeklyData getPCWeeklyUsageData(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDtlPCMonthlyData getPCMonthlyUsageData(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDtlPCYearlyData getPCYearlyUsageData(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDtlBDYearlyUsage getBDMonthlyUsageData(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDtlBDWeeklyUsage getBDWeeklyUsageData(UsageHistoryRequest usageHistoryRequest);
	
	public BDYearTempData getBDYearTemperatureData(UsageHistoryRequest usageHistoryRequest);
	
	public EsenseDtlPCDataAvailableData getPCDataAvailable(UsageHistoryRequest usageHistoryRequest);

}
