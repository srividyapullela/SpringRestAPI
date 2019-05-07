package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.UsageHistoryUtil;

/**
 * PeerCompare DayWeekly Usage Entity
 * @author bbachin1
 * 
 */

@Entity
@Table(name = "WP_PC_DAY_WEEK_USAGE", schema = CommonConstants.SCHEMA_NAME)
@NamedQueries( {
	
	@NamedQuery(name = "PCWeekUsage.findBylatestid", query = "SELECT pc FROM PCWeeklyUsageDO pc WHERE pc.pcWeeklyUsagePK.esiid = :esid "
		+ "  and pc.pcWeeklyUsagePK.contractAccountNumber = :ca"
		+ "  and pc.pcWeeklyUsagePK.contractId = :co and pc.yearWeekNo = :weekNo order by pc.pcWeeklyUsagePK.actualDay"),
		
	@NamedQuery(name = "PCWeekUsage.findTwoWeeksid", query = "SELECT pc FROM PCWeeklyUsageDO pc WHERE pc.pcWeeklyUsagePK.esiid = :esid "
			+ "  and pc.pcWeeklyUsagePK.contractAccountNumber = :ca"
			+ "  and pc.pcWeeklyUsagePK.contractId = :co and pc.yearWeekNo BETWEEN :startYrWkNo AND :endYrWkNo order by pc.pcWeeklyUsagePK.actualDay"),
		
	@NamedQuery(name = "PCPrevWeekUsage.findByOtherWeekNo", query = "SELECT count(*) FROM PCWeeklyUsageDO pc WHERE pc.pcWeeklyUsagePK.esiid = :esid "
			+ "  and pc.pcWeeklyUsagePK.contractAccountNumber = :ca"
			+ "  and pc.pcWeeklyUsagePK.contractId = :co and pc.yearWeekNo <= :weekNo "),
	
	@NamedQuery(name = "PCNextWeekUsage.findByOtherWeekNo", query = "SELECT count(*) FROM PCWeeklyUsageDO pc WHERE pc.pcWeeklyUsagePK.esiid = :esid "
			+ "  and pc.pcWeeklyUsagePK.contractAccountNumber = :ca"
			+ "  and pc.pcWeeklyUsagePK.contractId = :co and pc.yearWeekNo >= :weekNo "),

	@NamedQuery(name = "PCWeekUsage.findByNoyearWeekNo", query = "SELECT pc FROM PCWeeklyUsageDO pc WHERE pc.pcWeeklyUsagePK.esiid = :esid "
			+ "  and pc.pcWeeklyUsagePK.contractAccountNumber = :ca"
			+ "  and pc.pcWeeklyUsagePK.contractId = :co and pc.yearWeekNo = (SELECT max(t.yearWeekNo) FROM PCWeeklyUsageDO t WHERE t.pcWeeklyUsagePK.esiid = :esid "
			+ "  and t.pcWeeklyUsagePK.contractAccountNumber = :ca"
			+ "  and t.pcWeeklyUsagePK.contractId = :co) order by  pc.pcWeeklyUsagePK.actualDay"),
	
	@NamedQuery(name = "PCMonthlyUsage.findByDateRange", query = "SELECT pc FROM PCWeeklyUsageDO pc WHERE pc.pcWeeklyUsagePK.esiid = :esid "
			+ "  and pc.pcWeeklyUsagePK.contractAccountNumber = :ca"
			+ "  and pc.pcWeeklyUsagePK.contractId = :co and pc.pcWeeklyUsagePK.actualDay >= to_date(:fromDate,'MM/DD/YY') "
			+ "and pc.pcWeeklyUsagePK.actualDay <= to_date(:toDate,'MM/DD/YY') and (pc.loadId IS NULL OR pc.loadId NOT IN ('GAPF'))"),
	
	@NamedQuery(name = "PCMonthlyUsage.findPrevMonthId", query = "SELECT count(*) FROM PCWeeklyUsageDO t WHERE t.pcWeeklyUsagePK.esiid = :esid and t.pcWeeklyUsagePK.contractId=:co "
			+ "and t.pcWeeklyUsagePK.contractAccountNumber=:ca and t.pcWeeklyUsagePK.actualDay < to_date(:fromDate,'MM/DD/YY') "
			+ "and (t.loadId IS NULL OR t.loadId NOT IN ('GAPF'))"),
	
	@NamedQuery(name = "PCMonthlyUsage.findNextMonthId", query = "SELECT count(*) FROM PCWeeklyUsageDO t WHERE t.pcWeeklyUsagePK.esiid = :esid and t.pcWeeklyUsagePK.contractId=:co "
			+ "and t.pcWeeklyUsagePK.contractAccountNumber=:ca "
			+ "and t.pcWeeklyUsagePK.actualDay > to_date(:toDate,'MM/DD/YY') and (t.loadId IS NULL OR t.loadId NOT IN ('GAPF'))")

})

@SqlResultSetMapping(name = "PCWeeklyUsageDO", entities = {

@EntityResult(entityClass = PCWeeklyUsageDO.class, fields = {

		@FieldResult(name = "pcWeeklyUsagePK.esiid", column = "ESIID"),
		@FieldResult(name = "pcWeeklyUsagePK.contractAccountNumber", column = "CONTRACT_ACCT_ID"),
		@FieldResult(name = "pcWeeklyUsagePK.contractId", column = "CONTRACT_ID"),
		@FieldResult(name = "pcWeeklyUsagePK.actualDay", column = "ACTUAL_DAY"),
		@FieldResult(name = "yearWeekNo", column = "YEAR_WEEK_NO"),
		@FieldResult(name = "peerCount", column = "PEER_COUNT"),
		@FieldResult(name = "dayAvgUsage", column = "DAY_AVG_USAGE"),
		@FieldResult(name = "dayEffUsage", column = "DAY_EFF_USAGE"),
		@FieldResult(name = "daySelfUsage", column = "DAY_SLF_USAGE"),
		@FieldResult(name = "weekAvgUsage", column = "WEEK_AVG_USAGE"),
		@FieldResult(name = "weekEffUsage", column = "WEEK_EFF_USAGE"),
		@FieldResult(name = "weekSelfUsage", column = "WEEK_SLF_USAGE"),
		@FieldResult(name = "dayTempHigh", column = "DAY_TEMP_HIGH"),
		@FieldResult(name = "dayTempLow", column = "DAY_TEMP_LOW"),
		@FieldResult(name = "weekAvgTempHigh", column = "WEEK_AVE_TEMP_HIGH"),
		@FieldResult(name = "weekAvgTempLow", column = "WEEK_AVE_TEMP_LOW"),
		@FieldResult(name = "codeId", column = "CODE_ID"),
		@FieldResult(name = "loadId", column = "LOAD_ID"),
		@FieldResult(name = "createDate", column = "CREATE_DATE"),
		@FieldResult(name = "updateDate", column = "UPDATE_DATE")
		
})

}

)

public class PCWeeklyUsageDO implements Serializable{
	
	@EmbeddedId
	private PCWeeklyUsagePK pcWeeklyUsagePK;
	
	@Column(name = "YEAR_WEEK_NO")
	private String yearWeekNo;
	@Column(name = "PEER_COUNT")
	private String peerCount;
	@Column(name = "DAY_AVG_USAGE")
	private Double dayAvgUsage;
	@Column(name = "DAY_EFF_USAGE")
	private Double dayEffUsage;
	@Column(name = "DAY_SLF_USAGE")
	private Double daySelfUsage;
	@Column(name = "WEEK_AVG_USAGE")
	private Double weekAvgUsage;
	@Column(name = "WEEK_EFF_USAGE")
	private Double weekEffUsage;
	@Column(name = "WEEK_SLF_USAGE")
	private Double weekSelfUsage;
	@Column(name = "DAY_TEMP_HIGH")
	private Integer dayTempHigh;
	@Column(name = "DAY_TEMP_LOW")
	private Integer dayTempLow;
	@Column(name = "WEEK_AVE_TEMP_HIGH")
	private Integer weekAvgTempHigh;
	@Column(name = "WEEK_AVE_TEMP_LOW")
	private Integer weekAvgTempLow;
	@Column(name = "CODE_ID")
	private String codeId;
	@Column(name = "LOAD_ID")
	private String loadId;
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	
	/**
	 * @return the pcWeeklyUsagePK
	 */
	public PCWeeklyUsagePK getpcWeeklyUsagePK() {
		return pcWeeklyUsagePK;
	}
	/**
	 * @param pcWeeklyUsagePK the pcWeeklyUsagePK to set
	 */
	public void setpcWeeklyUsagePK(PCWeeklyUsagePK pcWeeklyUsagePK) {
		this.pcWeeklyUsagePK = pcWeeklyUsagePK;
	}
	/**
	 * @return the yearWeekNo
	 */
	public String getYearWeekNo() {
		return yearWeekNo;
	}
	/**
	 * @param yearWeekNo the yearWeekNo to set
	 */
	public void setYearWeekNo(String yearWeekNo) {
		this.yearWeekNo = yearWeekNo;
	}
	/**
	 * @return the peerCount
	 */
	public String getPeerCount() {
		return peerCount;
	}
	/**
	 * @param peerCount the peerCount to set
	 */
	public void setPeerCount(String peerCount) {
		this.peerCount = peerCount;
	}
	/**
	 * @return the dayAvgUsage
	 */
	public Double getDayAvgUsage() {
		return dayAvgUsage!= null ? (UsageHistoryUtil.getOneDecimal(dayAvgUsage)) : null;
	}
	/**
	 * @param dayAvgUsage the dayAvgUsage to set
	 */
	public void setDayAvgUsage(Double dayAvgUsage) {
		this.dayAvgUsage = dayAvgUsage;
	}
	/**
	 * @return the dayEffUsage
	 */
	public Double getDayEffUsage() {
		return dayEffUsage!= null ? (UsageHistoryUtil.getOneDecimal(dayEffUsage)) : null;
	}
	/**
	 * @param dayEffUsage the dayEffUsage to set
	 */
	public void setDayEffUsage(Double dayEffUsage) {
		this.dayEffUsage = dayEffUsage;
	}
	/**
	 * @return the daySelfUsage
	 */
	public Double getDaySelfUsage() {
		return daySelfUsage!= null ? (UsageHistoryUtil.getOneDecimal(daySelfUsage)) : null;
	}
	/**
	 * @param daySelfUsage the daySelfUsage to set
	 */
	public void setDaySelfUsage(Double daySelfUsage) {
		this.daySelfUsage = daySelfUsage;
		this.daySelfUsage = this.daySelfUsage.intValue()!=-1 ? this.daySelfUsage:0.0;
	}
	/**
	 * @return the weekAvgUsage
	 */
	public Double getWeekAvgUsage() {
		return weekAvgUsage!= null ? (UsageHistoryUtil.getOneDecimal(weekAvgUsage)) : null;
	}
	/**
	 * @param weekAvgUsage the weekAvgUsage to set
	 */
	public void setWeekAvgUsage(Double weekAvgUsage) {
		this.weekAvgUsage = weekAvgUsage;
	}
	/**
	 * @return the weekEffUsage
	 */
	public Double getWeekEffUsage() {
		return weekEffUsage!= null ? (UsageHistoryUtil.getOneDecimal(weekEffUsage)) : null;
	}
	/**
	 * @param weekEffUsage the weekEffUsage to set
	 */
	public void setWeekEffUsage(Double weekEffUsage) {
		this.weekEffUsage = weekEffUsage;
	}
	/**
	 * @return the weekSelfUsage
	 */
	public Double getWeekSelfUsage() {
		return weekSelfUsage!= null ? (UsageHistoryUtil.getOneDecimal(weekSelfUsage)) : null;
	}
	/**
	 * @param weekSelfUsage the weekSelfUsage to set
	 */
	public void setWeekSelfUsage(Double weekSelfUsage) {
		this.weekSelfUsage = weekSelfUsage;
	}
	/**
	 * @return the dayTempHigh
	 */
	public Integer getDayTempHigh() {
		return dayTempHigh;
	}
	/**
	 * @param dayTempHigh the dayTempHigh to set
	 */
	public void setDayTempHigh(Integer dayTempHigh) {
		this.dayTempHigh = dayTempHigh;
	}
	/**
	 * @return the dayTempLow
	 */
	public Integer getDayTempLow() {
		return dayTempLow;
	}
	/**
	 * @param dayTempLow the dayTempLow to set
	 */
	public void setDayTempLow(Integer dayTempLow) {
		this.dayTempLow = dayTempLow;
	}
	/**
	 * @return the weekAvgTempHigh
	 */
	public Integer getWeekAvgTempHigh() {
		return weekAvgTempHigh;
	}
	/**
	 * @param weekAvgTempHigh the weekAvgTempHigh to set
	 */
	public void setWeekAvgTempHigh(Integer weekAvgTempHigh) {
		this.weekAvgTempHigh = weekAvgTempHigh;
	}
	/**
	 * @return the weekAvgTempLow
	 */
	public Integer getWeekAvgTempLow() {
		return weekAvgTempLow;
	}
	/**
	 * @param weekAvgTempLow the weekAvgTempLow to set
	 */
	public void setWeekAvgTempLow(Integer weekAvgTempLow) {
		this.weekAvgTempLow = weekAvgTempLow;
	}
	/**
	 * @return the codeId
	 */
	public String getCodeId() {
		return codeId;
	}
	/**
	 * @param codeId the codeId to set
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	/**
	 * @return the loadId
	 */
	public String getLoadId() {
		return loadId;
	}
	/**
	 * @param loadId the loadId to set
	 */
	public void setLoadId(String loadId) {
		this.loadId = loadId;
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
