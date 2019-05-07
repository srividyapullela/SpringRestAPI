package com.reliant.sm.dao;

import org.springframework.stereotype.Component;

import com.reliant.sm.dao.response.ClassicConsumptionResponse;


/**
 * @author bbachin1
 * 
 */
@Component
public interface CPDBMainDAO {
	
	public  ClassicConsumptionResponse getConsumptionUsageData(String contractId);
	
	public  ClassicConsumptionResponse getDemandUsageData(String contractId);
	
	public  ClassicConsumptionResponse getClassicDetailData(String contractId);

}
