package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PCChildProfilePK implements Serializable {

	@Column (name = "PC_ID")
	private long masterID;
	@Column (name = "PARAM_NAME")
	private String paramName;
	@Column (name = "PARAM_VALUE")
	private String paramValue;
	/**
	 * @return the masterID
	 */
	public long getMasterID() {
		return masterID;
	}
	/**
	 * @param masterID the masterID to set
	 */
	public void setMasterID(long masterID) {
		this.masterID = masterID;
	}
	/**
	 * @return the paramName
	 */
	public String getParamName() {
		return paramName;
	}
	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	/**
	 * @return the paramValue
	 */
	public String getParamValue() {
		return paramValue;
	}
	/**
	 * @param paramValue the paramValue to set
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
}
