package com.reliant.sm.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.reliant.sm.dao.hibernate.dataobject.CustomerBillPeriodDO;
import com.reliant.sm.model.ProjectedBill;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;


/**
 * @author bbachin1
 * 
 */
@Component
public class CustomerPrefServiceImplHelper {
	
	private static LoggerUtil logger = LoggerUtil.getInstance(CustomerPrefServiceImplHelper.class);
	
	public ProjectedBill processProjectedBillData(ProjectedBill projectedBill, List<CustomerBillPeriodDO> customerBillData,
			UsageHistoryRequest usageHistoryRequest){
		
		if(null != customerBillData && customerBillData.size() >0){
			projectedBill.setActualBillAmount(customerBillData.get(0).getActualBillAmount());
			projectedBill.setActualDay(customerBillData.get(0).getActualDay());
			projectedBill.setBillPeriodEnd(customerBillData.get(0).getBillPeriodEnd());
			projectedBill.setBillPeriodStart(customerBillData.get(0).getBillPeriodStart());
			projectedBill.setProjBillAmount(UsageHistoryUtil.getIntegerValForInputString(customerBillData.get(0).getProjectedBillAmt()));
			projectedBill.setProjBillAmountHigh(UsageHistoryUtil.getIntegerValForInputString(customerBillData.get(0).getProjectedBillAmtHigh()));
			projectedBill.setProjBillAmountLow(UsageHistoryUtil.getIntegerValForInputString(customerBillData.get(0).getProjectedBillAmtLow()));
			projectedBill.setContractAccountId(customerBillData.get(0).getCustomerBillPeriodId().getContractAccountNumber());
			projectedBill.setCurrentDateStr(DateUtil.getFormatedDate(DateUtil.getDate(projectedBill.getActualDay(), "yyyy-MM-dd"), "MM/dd"));
			projectedBill.setBillPeriodEndsOnStr(DateUtil.getFormatedDate(DateUtil.getDate(projectedBill.getBillPeriodEnd(), "yyyy-MM-dd"), "MM/dd"));
			
			projectedBill.setDataAvailable(true);
			logger.info("PROJECTED BILL DATA FOUND FOR:::"+usageHistoryRequest.getContractAccNumber());
		}else{
			logger.info("NO PROJECTED BILL DATA FOUND FOR:::"+usageHistoryRequest.getContractAccNumber());
			projectedBill.setErrorMessage("NO PROJECTED BILL DATA FOUND FOR:::"+usageHistoryRequest.getContractAccNumber());
		}
		return projectedBill;

	}
}
