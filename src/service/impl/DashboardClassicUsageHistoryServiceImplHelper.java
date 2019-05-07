package com.reliant.sm.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.response.ClassicConsumptionResponse;
import com.reliant.sm.dao.rowmapper.ContractUsageDO;
import com.reliant.sm.model.ClassicData;
import com.reliant.sm.model.ClassicDetailData;
import com.reliant.sm.model.MonthAndUsage;
import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;


/**
 * @author bbachin1
 * 
 */
@Component
public class DashboardClassicUsageHistoryServiceImplHelper implements CommonConstants{
	
	private static final long serialVersionUID = -5441233696466130324L;
	private static LoggerUtil logger = LoggerUtil.getInstance(DashboardClassicUsageHistoryServiceImplHelper.class);
	
	public ClassicData processClassicConsumptionData(ClassicConsumptionResponse response, ClassicData consumptionData){
		
		if(null != response && null != response.getContractUsageList() && response.getContractUsageList().size() >0){
			logger.info("LIST SIZE FROM THE RESPONSE:::::::::"+response.getContractUsageList().size());
			for(int i=1;i<13;i++){
				getMonthUsageBasedOnMonthNumber(response,i,consumptionData);
			}
			consumptionData.setDataAvailable(true);
			consumptionData.setPrevUsageAvailable();
			consumptionData.setCurrUsageAvailable();
		}else{
			logger.info("NO RESPONSE FROM THE DATABASE::::::::");
		}
		return consumptionData;
	}
	
	
	private void getMonthUsageBasedOnMonthNumber(ClassicConsumptionResponse response,int monthNum,ClassicData consumptionData){
		
		MonthAndUsage currConsumpData = new MonthAndUsage();
		MonthAndUsage prevConsumpData = new MonthAndUsage();
		for(ContractUsageDO contractDO : response.getContractUsageList()){
			if(null != contractDO.getYear() && StringUtils.equalsIgnoreCase(contractDO.getYear(), String.valueOf(DateUtil.getCurrentYear()))){
				if(null != contractDO.getMonth() && monthNum == Integer.parseInt(contractDO.getMonth())){
					currConsumpData.setMonth(contractDO.getMonth());
					currConsumpData.setUsage(UsageHistoryUtil.getStringValue(contractDO.getQuantity()));
					currConsumpData.setYear(contractDO.getYear());
				}else{
					currConsumpData.setMonth(String.valueOf(monthNum));
				}
			}else{
				if(null != contractDO.getMonth() && monthNum == Integer.parseInt(contractDO.getMonth())){
					prevConsumpData.setMonth(contractDO.getMonth());
					prevConsumpData.setUsage(UsageHistoryUtil.getStringValue(contractDO.getQuantity()));
					prevConsumpData.setYear(contractDO.getYear());
				}else{
					prevConsumpData.setMonth(String.valueOf(monthNum));
				}
			}
		}
		consumptionData.addCurrentMonthAndUsageList().add(currConsumpData);
		consumptionData.addPreviousMonthAndUsageList().add(prevConsumpData);
	}
	
	
	public ClassicDetailData processClassicDetailData(ClassicConsumptionResponse response, ClassicDetailData classicDetailData){
		
		if(null != response && null != response.getContractUsageList() && response.getContractUsageList().size() >0){
			logger.info("LIST SIZE FROM THE RESPONSE:::::::::"+response.getContractUsageList().size());
			ClassicData consumptionData = new ClassicData();
			ClassicData demandData = new ClassicData();
			
			for(int i=1;i<13;i++){
				processConsumptionAndDemandData(response,classicDetailData,consumptionData,CURRENT_YEAR_C,i,false);
				processConsumptionAndDemandData(response,classicDetailData,demandData,CURRENT_YEAR_C,i,true);
			}	
			consumptionData.setCurrUsageAvailable();
			demandData.setCurrUsageAvailable();
			for(int i=1;i<13;i++){
				processConsumptionAndDemandData(response,classicDetailData,consumptionData,PREVIOUS_YEAR_P,i,false);
				processConsumptionAndDemandData(response,classicDetailData,demandData,PREVIOUS_YEAR_P,i,true);
			}
			consumptionData.setPrevUsageAvailable();
			demandData.setPrevUsageAvailable();
			for(int i=1;i<13;i++){
				processConsumptionAndDemandData(response,classicDetailData,consumptionData,OLD_YEAR_O,i,false);
				processConsumptionAndDemandData(response,classicDetailData,demandData,OLD_YEAR_O,i,true);
			}
			classicDetailData.setCurrentYear(String.valueOf(DateUtil.getCurrentYear()));
			classicDetailData.setOldYear(DateUtil.getYear(DateUtil.addYears(new Date(), -2)));
			classicDetailData.setPreviousYear(DateUtil.getYear(DateUtil.addYears(new Date(), -1)));
			classicDetailData.setConsumptionData(consumptionData);
			classicDetailData.setDemandData(demandData);
			classicDetailData.setDataAvailable(true);
		}else{
			logger.info("NO CONSUMPTION AND DEMAND DATA AVAILABLE::");
		}
		return classicDetailData;
	}
	
	//year = C-current year P-previous year O - old year
	private void processConsumptionAndDemandData(ClassicConsumptionResponse response, ClassicDetailData classicDetailData, ClassicData data,String year,int monthNum,boolean isDemand){
		
		MonthAndUsage consumpData = new MonthAndUsage();
		String whichYear = "";
		if(StringUtils.equalsIgnoreCase(year, CURRENT_YEAR_C)){
			whichYear = String.valueOf(DateUtil.getCurrentYear());
		}else if(StringUtils.equalsIgnoreCase(year, PREVIOUS_YEAR_P)){
			whichYear = DateUtil.getYear(DateUtil.addYears(new Date(), -1));
		}else{
			whichYear = DateUtil.getYear(DateUtil.addYears(new Date(), -2));
		}
		for(ContractUsageDO contractDO : response.getContractUsageList()){
			if(null != contractDO.getYear() && StringUtils.equalsIgnoreCase(contractDO.getYear(), whichYear)){
				if(null != contractDO.getMonth() && monthNum == Integer.parseInt(contractDO.getMonth())){
					consumpData.setCost(isDemand?UsageHistoryUtil.getRequiredScaleForInputInt(contractDO.getConsumCost(),2):
						UsageHistoryUtil.getRequiredScaleForInputInt(contractDO.getConsumCost(),2));
					consumpData.setMonth(contractDO.getMonth());
					consumpData.setUsage(isDemand?UsageHistoryUtil.getRequiredScaleForInputInt(contractDO.getDemandData(),1):
						UsageHistoryUtil.getRequiredScaleForInputInt(contractDO.getConsumUsage(), 1));
					consumpData.setYear(contractDO.getYear());
				}else{
					consumpData.setMonth(String.valueOf(monthNum));
				}
			}
		}
		if(StringUtils.equalsIgnoreCase(year, CURRENT_YEAR_C)){
			data.addCurrentMonthAndUsageList().add(consumpData);
		}else if(StringUtils.equalsIgnoreCase(year, PREVIOUS_YEAR_P)){
			data.addPreviousMonthAndUsageList().add(consumpData);
		}else{
			data.addOldMonthAndUsageList().add(consumpData);
		}
	}
}
