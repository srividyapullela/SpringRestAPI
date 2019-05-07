package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.reliant.sm.util.CommonConstants;

@Entity
@Table(name = "WP_DA_DAY" , schema = CommonConstants.SCHEMA_NAME)
@NamedQueries({			
			@NamedQuery(name = "BDWeekUsagePrev.findByid", query = "SELECT count(*) FROM BDDayWeekUsageDO t WHERE t.bdDayWeekUsageId.esiid = :esid " +
				"  and t.bdDayWeekUsageId.contractAccountNumber = :ca" +
				"  and t.bdDayWeekUsageId.contractId = :co and t.bdDayWeekUsageId.yearWeekNo <= :weekNo and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF'))"),
				
			@NamedQuery(name = "BDWeekUsageCurr.findByid", query = "SELECT count(*) FROM BDDayWeekUsageDO t WHERE t.bdDayWeekUsageId.esiid = :esid " +
				"  and t.bdDayWeekUsageId.contractAccountNumber = :ca" +
				"  and t.bdDayWeekUsageId.contractId = :co and t.bdDayWeekUsageId.yearWeekNo >= :weekNo and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF'))"),
				
			@NamedQuery(name = "BDWeekUsage.findByid", query = "SELECT t FROM BDDayWeekUsageDO t WHERE t.bdDayWeekUsageId.esiid = :esid " +
					"  and t.bdDayWeekUsageId.contractAccountNumber = :ca" +
					"  and t.bdDayWeekUsageId.contractId = :co and t.bdDayWeekUsageId.yearWeekNo = :weekNo and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF')) order by t.bdDayWeekUsageId.yearWeekNo"),
					
			@NamedQuery(name = "BDWeekUsage.findByDateRange", query = "SELECT t FROM BDDayWeekUsageDO t WHERE t.bdDayWeekUsageId.esiid = :esid " +
					"  and t.bdDayWeekUsageId.contractAccountNumber = :ca" +
					"  and t.bdDayWeekUsageId.contractId = :co and t.bdDayWeekUsageId.yearWeekNo >= :fromDate "
				+ "and t.bdDayWeekUsageId.yearWeekNo <= :toDate and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF')) order by t.bdDayWeekUsageId.yearWeekNo"),
			
			@NamedQuery(name = "BDWeekUsage.findMaxWeekNum", query = "SELECT max(t.bdDayWeekUsageId.yearWeekNo) FROM BDDayWeekUsageDO t WHERE t.bdDayWeekUsageId.esiid = :esid " +
					"  and t.bdDayWeekUsageId.contractAccountNumber = :ca" +
					"  and t.bdDayWeekUsageId.contractId = :co")
				
})

public class BDDayWeekUsageDO implements Serializable {

	@EmbeddedId
	private BDDayWeeklyUsagePK bdDayWeekUsageId;
	
	@Column (name = "SLICE_USG")
	private String sliceUsg;
	@Column (name = "SLICE_COST")
	
	private String sliceCost;
	@Column (name = "TOTAL_DAY_USG")
	private String totalDayUsg;
	@Column (name = "TOTAL_DAY_COST")
	private String totalDayCost;
	@Column (name = "DAY_TEMP_HIGH")
	private String dayTempHigh;
	@Column (name = "DAY_TEMP_LOW")
	private String dayTempLow;
	@Column (name = "LOAD_ID")
	private String loadID;
	@Column (name = "CODE_ID")
	private String codeId;
	
	@OneToOne
    @JoinColumn(name="SLICE_ID",
                insertable=false, updatable=false, 
                nullable=false)
    private BDSlices BDSlices;

	
	public void setLoadID(String loadID) {
		this.loadID = loadID;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public BDDayWeeklyUsagePK getBdDayWeekUsageId() {
		return bdDayWeekUsageId;
	}

	public void setBdDayWeekUsageId(BDDayWeeklyUsagePK bdDayWeekUsageId) {
		this.bdDayWeekUsageId = bdDayWeekUsageId;
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

	public String getTotalDayUsg() {
		return totalDayUsg;
	}

	public void setTotalDayUsg(String totalDayUsg) {
		this.totalDayUsg = totalDayUsg;
	}

	public String getTotalDayCost() {
		return totalDayCost;
	}

	public void setTotalDayCost(String totalDayCost) {
		this.totalDayCost = totalDayCost;
	}

	public String getDayTempHigh() {
		return dayTempHigh;
	}

	public void setDayTempHigh(String dayTempHigh) {
		this.dayTempHigh = dayTempHigh;
	}

	public String getDayTempLow() {
		return dayTempLow;
	}

	public void setDayTempLow(String dayTempLow) {
		this.dayTempLow = dayTempLow;
	}

	public BDSlices getBDSlices() {
		return BDSlices;
	}

	public void setBDSlices(BDSlices bDSlices) {
		BDSlices = bDSlices;
	}

	public String getLoadID() {
		return loadID;
	}
	
	
}
