package com.reliant.sm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.reliant.sm.dao.hibernate.dataobject.DayUsageDO;
import com.reliant.sm.model.GreenButtonUsageData;
import com.reliant.sm.model.Hour;
import com.reliant.sm.util.UsageHistoryUtil;


/**
 * @author bbachin1
 * 
 */
@Component
public class GreenButtonDataUsageServiceImplHelper {
	
	
	public GreenButtonUsageData populateGreenButtonUsageUsageData(GreenButtonUsageData greenBtnData, List<DayUsageDO> dailyUsageList){
			
		List<Hour> hourList = new ArrayList<Hour>();
		if(null != dailyUsageList && dailyUsageList.size() >0){
			for(DayUsageDO dayDO : dailyUsageList){
				Hour hour = new Hour();
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
				System.out.println(dayDO.getDailyUsageId().getActualDate());
				hour.setActualDay(UsageHistoryUtil.getStringValue(dayDO.getDailyUsageId().getActualDate()));
				hour.setActualDateInMS(dayDO.getDailyUsageId().getActualDate());
				hourList.add(hour);
			}
			greenBtnData.setDataAvailable(true);
			greenBtnData.setHourlyDataList(hourList);
				
			}
		
		  return greenBtnData;
		}

}
