package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DayWeeklyUsagePK implements Serializable{
	
	@Column (name = "ESIID")
	private String esiid;
	@Column (name = "CONTRACT_ACCT_ID")
	private String contractAccountNumber;
	@Column (name = "CONTRACT_ID")
	private String contractId;
	@Column (name = "ACTUAL_DAY")
	private Date actualDay;
	
	
	public String getEsiid() {
		return esiid;
	}
	public void setEsiid(String esiid) {
		this.esiid = esiid;
	}
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public Date getActualDay() {
		return actualDay;
	}
	public void setActualDay(Date actualDay) {
		this.actualDay = actualDay;
	}
	
	
	

}
