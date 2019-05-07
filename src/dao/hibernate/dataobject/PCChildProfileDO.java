package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "PC_CHILD_PROFILE_DATA" , schema = CommonConstants.SCHEMA_NAME)
@NamedQueries({
	@NamedQuery(name = "PCChildProfile.getData", query = "SELECT t FROM PCChildProfileDO t WHERE t.pcChildProfileId.masterID = :pcid "),
	
	@NamedQuery(name = "PCChildProfile.updateData", query = "UPDATE PCChildProfileDO t SET t.pcChildProfileId.paramValue = :paramVal "
			+ "WHERE t.pcChildProfileId.paramName = :paramName  AND t.pcChildProfileId.masterID = :pcid ")
})

public class PCChildProfileDO implements Serializable{

	@EmbeddedId
	private PCChildProfilePK pcChildProfileId;
	@Column (name = "CREATE_DATE")
	private Date createDate;
	@Column (name = "UPDATE_DATE")
	private Date updateDate;
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
	/**
	 * @return the pcChildProfileId
	 */
	public PCChildProfilePK getPcChildProfileId() {
		return pcChildProfileId;
	}
	/**
	 * @param pcChildProfileId the pcChildProfileId to set
	 */
	public void setPcChildProfileId(PCChildProfilePK pcChildProfileId) {
		this.pcChildProfileId = pcChildProfileId;
	}
	
}
