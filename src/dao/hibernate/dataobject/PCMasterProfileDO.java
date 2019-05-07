package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.reliant.sm.util.CommonConstants;
/**
 * 
 * @author BBACHIN1
 *
 */

@Entity
@Table(name = "PC_MASTER_PROFILE_DATA" , schema = CommonConstants.SCHEMA_NAME)
@NamedQueries({
		
	@NamedQuery(name = "PCMasterProfile.checkAvailableData", query = "SELECT t FROM PCMasterProfileDO t WHERE t.bp = :bp "
			+ "  and t.ca = :ca"
			+ "  and t.co = :co and t.esiid = :esid"),
	
	@NamedQuery(name = "PCMasterProfile.getPCID", query = "SELECT t.id FROM PCMasterProfileDO t WHERE t.bp = :bp "
			+ "  and t.ca = :ca"
			+ "  and t.co = :co and t.esiid = :esid"),
			
	@NamedQuery(name = "PCMasterProfile.updateByWeb", query = "UPDATE PCMasterProfileDO t SET t.processStatus = 'U'"
			+ ", t.updateBy = 'WEB'"
			+ "where t.id = :pcid")
			
})


public class PCMasterProfileDO implements Serializable{

	@Id
	@Column (name = "PC_ID")
	private long id;
	@Column (name = "ESIID")
	private String esiid;
	@Column (name = "BUS_PARTNER")
	private String bp;
	@Column (name = "CONTRACT_ACCT_ID")
	private String ca;
	@Column (name = "CONTRACT_ID")
	private String co;
	@Column (name = "PROCESS_STATUS")
	private String processStatus;
	@Column (name = "UPDATED_BY")
	private String updateBy;
	@Column (name = "CREATE_DATE")
	private Date createDate;
	@Column (name = "UPDATE_DATE")
	private Date updateDate;
	@Column (name = "CODE_ID")
	private String codeID;
	@Column (name = "LOAD_ID")
	private String loadID;
	@Transient
	List<PCChildProfileDO> pcChildProfileList;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEsiid() {
		return esiid;
	}
	
	public void setEsiid(String esiid) {
		this.esiid = esiid;
	}
	
	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
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

	public String getProcessStatus() {
		return processStatus;
	}
	
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}
	
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
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
	
	public String getCodeID() {
		return codeID;
	}
	
	public void setCodeID(String codeID) {
		this.codeID = codeID;
	}
	
	public String getLoadID() {
		return loadID;
	}
	
	public void setLoadID(String loadID) {
		this.loadID = loadID;
	}
	
	public List<PCChildProfileDO> getPcChildProfileList() {
		return pcChildProfileList;
	}
	
	public void setPcChildProfileList(List<PCChildProfileDO> pcChildProfileList) {
		this.pcChildProfileList = pcChildProfileList;
	}
	
	
	
}
