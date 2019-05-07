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
 * @author BBACHIN1
 *
 */

@Entity
@Table(name = "WP_PC_HOME_AGE_BANDS" , schema = CommonConstants.SCHEMA_NAME)
@NamedQueries( {
	
	@NamedQuery(name = "PCHomeAgeBand.findAll", query = "SELECT t FROM PCHomeAgeBandDO t WHERE t.homeAgeBandID>0 ORDER BY t.homeAgeStart ASC"),

})

public class PCHomeAgeBandDO implements Serializable{
	
	@Id
	@Column (name = "HOME_AGE_BAND_ID")
	private String homeAgeBandID;
	@Column (name = "HOME_AGE_START")
	private String homeAgeStart;
	@Column (name = "HOME_AGE_END")
	private String homeAgeEnd;
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	/**
	 * @return the homeAgeBandID
	 */
	public String getHomeAgeBandID() {
		return homeAgeBandID;
	}
	/**
	 * @param homeAgeBandID the homeAgeBandID to set
	 */
	public void setHomeAgeBandID(String homeAgeBandID) {
		this.homeAgeBandID = homeAgeBandID;
	}
	/**
	 * @return the homeAgeStart
	 */
	public String getHomeAgeStart() {
		return homeAgeStart;
	}
	/**
	 * @param homeAgeStart the homeAgeStart to set
	 */
	public void setHomeAgeStart(String homeAgeStart) {
		this.homeAgeStart = homeAgeStart;
	}
	/**
	 * @return the homeAgeEnd
	 */
	public String getHomeAgeEnd() {
		return homeAgeEnd;
	}
	/**
	 * @param homeAgeEnd the homeAgeEnd to set
	 */
	public void setHomeAgeEnd(String homeAgeEnd) {
		this.homeAgeEnd = homeAgeEnd;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
