package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DayUsagePK implements Serializable {
	
	@Column (name = "ESIID")
	private String esid;
	@Column (name = "CONTRACT_ACCT_ID")
	private String ca;
	@Column (name = "CONTRACT_ID")
	private String co;
	@Column (name = "ACTUAL_DAY")
	private Date actualDate;
	
	
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
	public Date getActualDate() {
		return actualDate;
	}
	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}
	
	

}
