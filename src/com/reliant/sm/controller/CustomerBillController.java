package com.reliant.sm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reliant.sm.model.ProjectedBill;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.service.CustomerPrefService;
import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;


/**
 * @author bbachin1
 * 
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerBillController implements CommonConstants{
	
	private static final long serialVersionUID = 1L;

	private static LoggerUtil logger = LoggerUtil.getInstance(CustomerBillController.class);
	
	@Autowired
	private CustomerPrefService customerPrefService;
	
	@RequestMapping(value = PROJECTED_BILL, method = RequestMethod.POST)
    public ProjectedBill getDashBoardUsageAndCost(@RequestBody UsageHistoryRequest usageHistoryRequest) {
		long startTime = System.currentTimeMillis();
		ProjectedBill projectedBill = customerPrefService.getProjectBillForContractAccount(usageHistoryRequest);
		logger.logTransaction(CustomerBillController.class.getName(), TX_CUSTOMER_PROJECTED_BILL, CUSTOMER+PROJECTED_BILL, 
				UsageHistoryUtil.getElapsedTime(startTime), usageHistoryRequest, projectedBill);
		return projectedBill;
    }

}
