package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.reliant.sm.util.CommonConstants;

/**
 * 
 * @author BBACHIN1
 *
 */

@Entity
@Table(name = "WP_CUST_BILL_PERIOD" , schema = CommonConstants.SCHEMA_NAME)
@NamedQueries({
	@NamedQuery(name = "customer.projectedbillId", query = "SELECT t FROM CustomerBillPeriodDO t WHERE t.customerBillPeriodId.contractAccountNumber = :ca "
			+ " AND t.custStat = 'AC' ORDER BY t.actualUsage ASC "),
})

public class CustomerBillPeriodDO implements Serializable{
	
	@EmbeddedId
	private CustomerBillPeriodPK customerBillPeriodId;
	
	@Column (name = "ACTUAL_BILL_AMT")
	private String actualBillAmount;
	@Column (name = "ACTUAL_DAY")
	private String actualDay;
	@Column (name = "PROJ_BILL_AMT")
	private String projectedBillAmt;
	@Column (name = "BILL_PERIOD_START")
	private String billPeriodStart;
	@Column (name = "BILL_PERIOD_END")
	private String billPeriodEnd;
	@Column (name = "PROJ_BILL_AMT_LOW")
	private String projectedBillAmtLow;
	@Column (name = "PROJ_BILL_AMT_HIGH")
	private String projectedBillAmtHigh;
	@Column (name = "CUST_STAT")
	private String custStat;
	@Column (name = "ACTUAL_USAGE")
	private String actualUsage;
	
	
	public CustomerBillPeriodPK getCustomerBillPeriodId() {
		return customerBillPeriodId;
	}
	public void setCustomerBillPeriodId(CustomerBillPeriodPK customerBillPeriodId) {
		this.customerBillPeriodId = customerBillPeriodId;
	}
	public String getActualBillAmount() {
		return actualBillAmount;
	}
	public void setActualBillAmount(String actualBillAmount) {
		this.actualBillAmount = actualBillAmount;
	}
	public String getActualDay() {
		return actualDay;
	}
	public void setActualDay(String actualDay) {
		this.actualDay = actualDay;
	}
	public String getProjectedBillAmt() {
		return projectedBillAmt;
	}
	public void setProjectedBillAmt(String projectedBillAmt) {
		this.projectedBillAmt = projectedBillAmt;
	}
	public String getBillPeriodStart() {
		return billPeriodStart;
	}
	public void setBillPeriodStart(String billPeriodStart) {
		this.billPeriodStart = billPeriodStart;
	}
	public String getBillPeriodEnd() {
		return billPeriodEnd;
	}
	public void setBillPeriodEnd(String billPeriodEnd) {
		this.billPeriodEnd = billPeriodEnd;
	}
	public String getProjectedBillAmtLow() {
		return projectedBillAmtLow;
	}
	public void setProjectedBillAmtLow(String projectedBillAmtLow) {
		this.projectedBillAmtLow = projectedBillAmtLow;
	}
	public String getProjectedBillAmtHigh() {
		return projectedBillAmtHigh;
	}
	public void setProjectedBillAmtHigh(String projectedBillAmtHigh) {
		this.projectedBillAmtHigh = projectedBillAmtHigh;
	}
	public String getCustStat() {
		return custStat;
	}
	public void setCustStat(String custStat) {
		this.custStat = custStat;
	}
	public String getActualUsage() {
		return actualUsage;
	}
	public void setActualUsage(String actualUsage) {
		this.actualUsage = actualUsage;
	}
	
	
	
}
