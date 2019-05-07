package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.reliant.sm.util.CommonConstants;

/**
 * @author bbachin1
 */

@Entity
@Table(name = "WP_DA_MONTH" , schema = CommonConstants.SCHEMA_NAME)
@NamedQueries( {
	
	@NamedQuery(name = "BDYearUsage.findByMonthNo", query = "SELECT t FROM BDYearUsageDO t WHERE t.yearlyUsageId.esid = :esid " +
			"  and t.yearlyUsageId.ca = :ca" +
			"  and t.yearlyUsageId.co = :co and t.yearlyUsageId.yearMonthNo = :yearMonthNum order by t.yearlyUsageId.yearMonthNo"),
			
	@NamedQuery(name = "BDPrevUsage.findById", query = "SELECT count(*) FROM BDYearUsageDO t WHERE t.yearlyUsageId.esid = :esid " +
			"  and t.yearlyUsageId.ca = :ca" +
			"  and t.yearlyUsageId.co = :co and t.yearlyUsageId.yearMonthNo <= :yearMonthNum"),
	
	@NamedQuery(name = "BDNextUsage.findById", query = "SELECT count(*) FROM BDYearUsageDO t WHERE t.yearlyUsageId.esid = :esid " +
			"  and t.yearlyUsageId.ca = :ca" +
			"  and t.yearlyUsageId.co = :co and t.yearlyUsageId.yearMonthNo >= :yearMonthNum"),
			
	@NamedQuery(name = "BDYearUsage.findMaxMonthNum", query = "SELECT max(t.yearlyUsageId.yearMonthNo) FROM BDYearUsageDO t WHERE t.yearlyUsageId.esid = :esid " +
		"  and t.yearlyUsageId.ca = :ca" +
		"  and t.yearlyUsageId.co = :co and t.yearlyUsageId.yearNo = :yearMonthNum")
	
	})


public class BDYearUsageDO implements Serializable{
	
	@EmbeddedId
	private BDYearUsagePK yearlyUsageId;
	
	@Column (name = "SLICE_USG")
	private String sliceUsg;
	@Column (name = "SLICE_COST")
	private String sliceCost;
	@Column (name = "TOTAL_MONTH_USG")
	private String totalMonthUsg;
	@Column (name = "TOTAL_MONTH_COST")	
	private String totalMonthCost;		
	@Column (name = "MON_TEMP_HIGH")	
	private String monTempHigh;	
	@Column (name = "MON_TEMP_LOW")	
	private String monTempLow;	
	@Column (name = "ACTUAL_DAY")
	private String actualDay;
	@Column (name = "LOAD_ID")
	private String loadID;
	@Column (name = "CODE_ID")
	private String codeId;
	
	
	@ManyToOne
    @JoinColumn(name="SLICE_ID",
                insertable=false, updatable=false, 
                nullable=false)
    private BDSlices BDSlices;
	
	
	public BDYearUsagePK getYearlyUsageId() {
		return yearlyUsageId;
	}
	public void setYearlyUsageId(BDYearUsagePK yearlyUsageId) {
		this.yearlyUsageId = yearlyUsageId;
	}
	public String getSliceUsg() {
		return sliceUsg;
	}
	public void setSliceUsg(String sliceUsg) {
		this.sliceUsg = sliceUsg;
	}
	public String getSliceCost() {
		return sliceCost;
	}
	public void setSliceCost(String sliceCost) {
		this.sliceCost = sliceCost;
	}
	public String getTotalMonthUsg() {
		return totalMonthUsg;
	}
	public void setTotalMonthUsg(String totalMonthUsg) {
		this.totalMonthUsg = totalMonthUsg;
	}
	public String getTotalMonthCost() {
		return totalMonthCost;
	}
	public void setTotalMonthCost(String totalMonthCost) {
		this.totalMonthCost = totalMonthCost;
	}
	public String getLoadID() {
		return loadID;
	}
	public void setLoadID(String loadID) {
		this.loadID = loadID;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	public String getMonTempHigh() {
		return monTempHigh;
	}
	public void setMonTempHigh(String monTempHigh) {
		this.monTempHigh = monTempHigh;
	}
	public String getMonTempLow() {
		return monTempLow;
	}
	public void setMonTempLow(String monTempLow) {
		this.monTempLow = monTempLow;
	}
	public String getActualDay() {
		return actualDay;
	}
	public void setActualDay(String actualDay) {
		this.actualDay = actualDay;
	}
	
	public BDSlices getBDSlices() {
		return BDSlices;
	}
	public void setBDSlices(BDSlices bDSlices) {
		BDSlices = bDSlices;
	}
	
	
}
