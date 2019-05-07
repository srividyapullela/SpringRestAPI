package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BDDayWeeklyUsagePK implements Serializable {

	@Column (name = "ESIID")
	private String esiid;
	//@Column (name = "BUS_PARTNER")
	//private String busPartner;
	@Column (name = "CONTRACT_ACCT_ID")
	private String contractAccountNumber;
	@Column (name = "CONTRACT_ID")
	private String contractId;
	@Column (name = "ACTUAL_DAY")
	private String actualDay;
	@Column (name = "YEAR_WEEK_NO")
	private String yearWeekNo;	
	@Column (name = "SLICE_ID")
	//@OneToOne(cascade = CascadeType.ALL)
	private int sliceId;
	
	public String getEsiid() {
		return esiid;
	}
	public void setEsiid(String esiid) {
		this.esiid = esiid;
	}
	/*public String getBusPartner() {
		return busPartner;
	}
	public void setBusPartner(String busPartner) {
		this.busPartner = busPartner;
	}*/
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
	public String getActualDay() {
		return actualDay;
	}
	public void setActualDay(String actualDay) {
		this.actualDay = actualDay;
	}
	public String getYearWeekNo() {
		return yearWeekNo;
	}
	public void setYearWeekNo(String yearWeekNo) {
		this.yearWeekNo = yearWeekNo;
	}
	public int getSliceId() {
		return sliceId;
	}
	public void setSliceId(int sliceId) {
		this.sliceId = sliceId;
	}	
	
}
