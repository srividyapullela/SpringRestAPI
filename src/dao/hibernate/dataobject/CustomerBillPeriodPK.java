package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerBillPeriodPK implements Serializable{
	
	private static final long serialVersionUID = -3365070693017190054L;
	
	@Column (name = "ESIID")
	private String esiid;
	@Column (name = "CONTRACT_ACCT_ID")
	private String contractAccountNumber;
	@Column (name = "CONTRACT_ID")
	private String contractId;
	@Column (name = "BUS_PARTNER")
	private String bpNumber;
	
	
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
	public String getBpNumber() {
		return bpNumber;
	}
	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}
	
	
	

}
