package com.reliant.sm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.SmartMainHibernateDAO;
import com.reliant.sm.dao.hibernate.dataobject.CustomerBillPeriodDO;
import com.reliant.sm.model.SMSProcessorRestResponse;
import com.reliant.sm.model.Usage;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.service.SMSProcessorService;
import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.LoggerUtil;

/**
 * @author bbachin1
 * 
 */
@Component
public class SMSProcessorServiceImpl extends CommonUsageHistoryHelper implements SMSProcessorService{
	
	private static LoggerUtil logger = LoggerUtil.getInstance(SMSProcessorServiceImpl.class);
	
	@Autowired
	private SmartMainHibernateDAO smartMainHibernateDAO;

	@Override
	public SMSProcessorRestResponse getCADetailsForDashBoard(UsageHistoryRequest usageHistoryRequest){
		
		SMSProcessorRestResponse response = new SMSProcessorRestResponse();
		try{
			String queryName = GET_CUSTOMER_PROJECTED_BILL_QRY;
			String[] paramNameAry = {"ca"};
			Object[] paramValAry = {usageHistoryRequest.getContractAccNumber()};
			List<CustomerBillPeriodDO> customerBillList = smartMainHibernateDAO.listQuery(null, queryName, paramNameAry,paramValAry);
			if(null != customerBillList && customerBillList.size() >0){
				populateCostAndBillForDashboardResponse(response,customerBillList);
				populateUsageForDashboardResponse(response,customerBillList);
				response.setDataAvailable(true);
			}else{
				logger.info("NO DATA AVILABLE FOR THE CA::::::"+usageHistoryRequest.getContractAccNumber());
			}
		}catch(Exception ex){
			logger.error("EXCEPTION OCCURED WHILE GETTING DASHBOARD CA RESPONSE:::::",ex);
		}
		return response;
	}
	
	
	
	private void populateUsageForDashboardResponse(SMSProcessorRestResponse response, List<CustomerBillPeriodDO> customerBillList){
		
		List<Usage> usageList = new ArrayList<Usage>();
		for(CustomerBillPeriodDO custBill : customerBillList){
			Usage usage = new Usage();
			usage.setActualDay(DateUtil.getFormattedDate("MM/dd/yyyy", "yyyy-MM-dd", custBill.getActualDay()));
			usage.setActualUsage(custBill.getActualUsage());
			usage.setContractId(custBill.getCustomerBillPeriodId().getContractId());
			usage.setEsiid(custBill.getCustomerBillPeriodId().getEsiid());
			usageList.add(usage);
		}
		response.setUsageList(usageList);
	}
	
	
	private void populateCostAndBillForDashboardResponse(SMSProcessorRestResponse response, List<CustomerBillPeriodDO> customerBillList){
		
		BigDecimal projecedBillAmt = new BigDecimal("0.0");
		BigDecimal actualBillAmt = new BigDecimal("0.0");
		for(CustomerBillPeriodDO custBill : customerBillList){
			projecedBillAmt = projecedBillAmt.add(new BigDecimal(null != custBill.getProjectedBillAmt()?custBill.getProjectedBillAmt():"0.00"));
			actualBillAmt = actualBillAmt.add(new BigDecimal(null != custBill.getActualBillAmount()?custBill.getActualBillAmount():"0.00"));
		}
		response.setProjectedBillAmount(projecedBillAmt.toString());
		response.setActualBillAmount(actualBillAmt.toString());
		response.setActualDay(null != customerBillList.get(0)?DateUtil.getFormattedDate("MM/dd/yyyy", "yyyy-MM-dd", customerBillList.get(0).getActualDay()):"");
		response.setBillEndPeriod(null != customerBillList.get(0)?DateUtil.getFormattedDate("MM/dd/yyyy", "yyyy-MM-dd", customerBillList.get(0).getBillPeriodEnd()):"");
		response.setBillStartPeriod(null != customerBillList.get(0)?DateUtil.getFormattedDate("MM/dd/yyyy", "yyyy-MM-dd", customerBillList.get(0).getBillPeriodStart()):"");
		response.setCaNumber(null != customerBillList.get(0)?customerBillList.get(0).getCustomerBillPeriodId().getContractAccountNumber():"");
	}

}
