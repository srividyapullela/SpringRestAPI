package com.reliant.sm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.SmartMainHibernateDAO;
import com.reliant.sm.dao.hibernate.dataobject.CustomerBillPeriodDO;
import com.reliant.sm.model.ProjectedBill;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.service.CustomerPrefService;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;

/**
 * @author bbachin1
 * 
 */
@Component
public class CustomerPrefServiceImpl extends CommonUsageHistoryHelper implements CustomerPrefService{
	
	private static LoggerUtil logger = LoggerUtil.getInstance(CustomerPrefServiceImpl.class);
	
	@Autowired
	private SmartMainHibernateDAO smartMainHibernateDAO;
	
	@Autowired
	private CustomerPrefServiceImplHelper customerPrefServiceImplHelper;

	@SuppressWarnings("unchecked")
	@Override
	public ProjectedBill getProjectBillForContractAccount(UsageHistoryRequest usageHistoryRequest) {
		
		ProjectedBill projectedBill = new ProjectedBill();
		try{
			String queryName = GET_CUSTOMER_PROJECTED_BILL_QRY;
			String[] paramNameAry = {"ca"};
			Object[] paramValAry = {usageHistoryRequest.getContractAccNumber()};
			List<CustomerBillPeriodDO> customerBillData = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, paramNameAry,paramValAry);
			customerPrefServiceImplHelper.processProjectedBillData(projectedBill,customerBillData,usageHistoryRequest);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE CUSTOMER PROJECTED BILL DATA:::::", ex);
			projectedBill.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return projectedBill;
	}

}
