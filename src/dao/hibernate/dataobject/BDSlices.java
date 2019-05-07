package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.reliant.sm.util.CommonConstants;

@Entity
@Table(name = CommonConstants.BD_TABLE_SLICES , schema = CommonConstants.SCHEMA_NAME)
public class BDSlices implements Serializable{
	
	private static final long serialVersionUID = 8940551255976571734L;
	
	@Id
	@Column (name = "SLICE_ID")
	private String sliceId;
	@Column (name = "SLICE_NAME")
	private String sliceName;
	@Column (name = "SLICE_DESC")
	private String sliceDesc;
	@Column (name = "ACTIVE_FLAG")
	private String activeFlag;
	@Column (name = "SLICE_TRSHLD_PCT")
	private String sliceThresPct;
	
	public String getSliceId() {
		return sliceId;
	}
	public void setSliceId(String sliceId) {
		this.sliceId = sliceId;
	}
	public String getSliceName() {
		return sliceName;
	}
	public void setSliceName(String sliceName) {
		this.sliceName = sliceName;
	}
	public String getSliceDesc() {
		return sliceDesc;
	}
	public void setSliceDesc(String sliceDesc) {
		this.sliceDesc = sliceDesc;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getSliceThresPct() {
		return sliceThresPct;
	}
	public void setSliceThresPct(String sliceThresPct) {
		this.sliceThresPct = sliceThresPct;
	}
	
	

}
