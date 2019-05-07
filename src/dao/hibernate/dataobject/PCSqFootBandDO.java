package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.reliant.sm.util.CommonConstants;

/**
 * 
 * @author bbachin1
 *
 */

@Entity
@Table(name = "WP_PC_SQFT_BANDS" , schema = CommonConstants.SCHEMA_NAME)
@NamedQueries( {
	
	@NamedQuery(name = "PCSqFootBand.findAll", query = "SELECT t FROM PCSqFootBandDO t WHERE t.sqFtBandID >0 AND t.widerBandFlag ='N' ORDER BY SQFT_START ASC"),

})

public class PCSqFootBandDO implements Serializable{
	
	@Id
	@Column (name = "SQFT_BAND_ID")
	private String sqFtBandID;
	@Column (name = "SQFT_START")
	private String sqFtStart;
	@Column (name = "SQFT_END")
	private String sqFtEnd;
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	@Column (name = "WIDER_BAND_FLAG")
	private String widerBandFlag;
	
	public String getSqFtBandID() {
		return sqFtBandID;
	}
	
	public void setSqFtBandID(String sqFtBandID) {
		this.sqFtBandID = sqFtBandID;
	}
	
	public String getSqFtStart() {
		return sqFtStart;
	}
	
	public void setSqFtStart(String sqFtStart) {
		this.sqFtStart = sqFtStart;
	}
	
	public String getSqFtEnd() {
		return sqFtEnd;
	}
	
	public void setSqFtEnd(String sqFtEnd) {
		this.sqFtEnd = sqFtEnd;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getWiderBandFlag() {
		return widerBandFlag;
	}
	
	public void setWiderBandFlag(String widerBandFlag) {
		this.widerBandFlag = widerBandFlag;
	}
	
}
