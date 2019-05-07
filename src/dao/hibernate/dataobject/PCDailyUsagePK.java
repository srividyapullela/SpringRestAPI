package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PeerCompare DailyUsage Primary Key(Required for Composite Key in the Table WP_PC_HR_DAY_USAGE)
 * @author Sandeep Mettugari
 * 
 */

@Embeddable
public class PCDailyUsagePK implements Serializable{
	
	@Column (name = "ESIID")
	private String esiid;
	@Column (name = "CONTRACT_ACCT_ID")
	private String contractAccountNumber;
	@Column (name = "CONTRACT_ID")
	private String contractId;
	@Column (name = "ACTUAL_DAY")
	private Date actualDay;
	
	
	/**
	 * @return the esiid
	 */
	public String getEsiid() {
		return esiid;
	}
	/**
	 * @param esiid the esiid to set
	 */
	public void setEsiid(String esiid) {
		this.esiid = esiid;
	}
	/**
	 * @return the contractAccountNumber
	 */
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}
	/**
	 * @param contractAccountNumber the contractAccountNumber to set
	 */
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}
	/**
	 * @return the contractId
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return the actualDay
	 */
	public Date getActualDay() {
		return actualDay;
	}
	/**
	 * @param actualDay the actualDay to set
	 */
	public void setActualDay(Date actualDay) {
		this.actualDay = actualDay;
	}
	
}
