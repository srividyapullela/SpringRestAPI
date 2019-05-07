package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BDYearUsagePK implements Serializable {

	@Column (name = "ESIID")
	private String esid;
	@Column (name = "CONTRACT_ACCT_ID")
	private String ca;
	@Column (name = "CONTRACT_ID")
	private String co;
	@Column (name = "BUS_PARTNER")
	private String busPartner;
	@Column (name = "YEAR_MONTH_NO")
	private String yearMonthNo;
	@Column (name = "YEAR_NO")
	private String yearNo;
	@Column (name = "SLICE_ID")
	private String sliceId;
	
	
	
	public String getEsid() {
		return esid;
	}
	public void setEsid(String esid) {
		this.esid = esid;
	}
	public String getCa() {
		return ca;
	}
	public void setCa(String ca) {
		this.ca = ca;
	}
	public String getCo() {
		return co;
	}
	public void setCo(String co) {
		this.co = co;
	}
	public String getBusPartner() {
		return busPartner;
	}
	public void setBusPartner(String busPartner) {
		this.busPartner = busPartner;
	}
	public String getYearMonthNo() {
		return yearMonthNo;
	}
	public void setYearMonthNo(String yearMonthNo) {
		this.yearMonthNo = yearMonthNo;
	}
	public String getYearNo() {
		return yearNo;
	}
	public void setYearNo(String yearNo) {
		this.yearNo = yearNo;
	}
	public String getSliceId() {
		return sliceId;
	}
	public void setSliceId(String sliceId) {
		this.sliceId = sliceId;
	}
	
	
	
}
