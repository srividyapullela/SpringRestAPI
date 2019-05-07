package com.reliant.sm.model;


public class ProjectedBill extends GenericResponse{
	
	private String projBillAmount;
	private String billPeriodStart;
	private String projBillAmountLow;
	private String projBillAmountHigh;
	private String billPeriodEnd;
	private String actualBillAmount;
	private String contractAccountId;
	private String actualDay;
	private String billPeriodEndsOnStr;
	private String currentDateStr;
	
	
	public String getProjBillAmount() {
		return projBillAmount;
	}
	public void setProjBillAmount(String projBillAmount) {
		this.projBillAmount = projBillAmount;
	}
	public String getBillPeriodStart() {
		return billPeriodStart;
	}
	public void setBillPeriodStart(String billPeriodStart) {
		this.billPeriodStart = billPeriodStart;
	}
	public String getProjBillAmountLow() {
		return projBillAmountLow;
	}
	public void setProjBillAmountLow(String projBillAmountLow) {
		this.projBillAmountLow = projBillAmountLow;
	}
	public String getProjBillAmountHigh() {
		return projBillAmountHigh;
	}
	public void setProjBillAmountHigh(String projBillAmountHigh) {
		this.projBillAmountHigh = projBillAmountHigh;
	}
	public String getBillPeriodEnd() {
		return billPeriodEnd;
	}
	public void setBillPeriodEnd(String billPeriodEnd) {
		this.billPeriodEnd = billPeriodEnd;
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
	public String getContractAccountId() {
		return contractAccountId;
	}
	public void setContractAccountId(String contractAccountId) {
		this.contractAccountId = contractAccountId;
	}
	public String getBillPeriodEndsOnStr() {
		return billPeriodEndsOnStr;
	}
	public void setBillPeriodEndsOnStr(String billPeriodEndsOnStr) {
		this.billPeriodEndsOnStr = billPeriodEndsOnStr;
	}
	public String getCurrentDateStr() {
		return currentDateStr;
	}
	public void setCurrentDateStr(String currentDateStr) {
		this.currentDateStr = currentDateStr;
	}
	
}
