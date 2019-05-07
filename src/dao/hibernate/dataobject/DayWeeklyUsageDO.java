package com.reliant.sm.dao.hibernate.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.UsageHistoryUtil;

import javax.persistence.*;

/**
 * @author bbachin1
 *
 */

@Entity
@Table(name = "WP_DAY_WEEK" , schema = CommonConstants.SCHEMA_NAME)
@NamedQueries({
		@NamedQuery(name = "WeekUsage.findByid", query = "SELECT t FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esid " +
		"  and t.dayWeeklyUsageId.contractAccountNumber = :ca" +
		"  and t.dayWeeklyUsageId.contractId = :co and t.yearWeekNum = :weekNo and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF')) order by t.dayWeeklyUsageId.actualDay"),
		
		@NamedQuery(name = "WeekUsage.findByBetweenid", query = "SELECT t FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esid " +
				"  and t.dayWeeklyUsageId.contractAccountNumber = :ca" +
				"  and t.dayWeeklyUsageId.contractId = :co and t.yearWeekNum BETWEEN :startYrWkNo AND :endYrWkNo and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF')) order by t.dayWeeklyUsageId.actualDay"),
		
		@NamedQuery(name = "WeekUsage.findBylatestid", query = "  SELECT t FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esid " +
		"  and t.dayWeeklyUsageId.contractAccountNumber = :ca" +
		"  and t.dayWeeklyUsageId.contractId = :co and t.yearWeekNum = :weekNo and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF'))"),


		@NamedQuery(name = "WeekUsagePrev.findByid", query = "SELECT count(*) FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esid " +
		"  and t.dayWeeklyUsageId.contractAccountNumber = :ca" +
		"  and t.dayWeeklyUsageId.contractId = :co and t.yearWeekNum <= :weekNo and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF'))"),

		@NamedQuery(name = "WeekUsageCurr.findByid", query = "SELECT count(*) FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esid " +
		"  and t.dayWeeklyUsageId.contractAccountNumber = :ca" +
		"  and t.dayWeeklyUsageId.contractId = :co and t.yearWeekNum >= :weekNo and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF'))"),			
				
		@NamedQuery(name = "WeekCurrentUsage.findByid", query = "SELECT t FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esid " +
		"  and t.dayWeeklyUsageId.contractAccountNumber = :ca" +
		"  and t.dayWeeklyUsageId.contractId = :co and t.yearWeekNum = (SELECT max(t.yearWeekNum) FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esid " +
		"  and t.dayWeeklyUsageId.contractAccountNumber = :ca" +
		"  and t.dayWeeklyUsageId.contractId = :co) and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF'))"),

		
		@NamedQuery(name = "WeekUsage.findDashBoardWeekData", query = "SELECT t FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esid " +
		"  and t.dayWeeklyUsageId.contractAccountNumber = :ca" +
		"  and t.dayWeeklyUsageId.contractId = :co   and t.yearWeekNum in (:weekNoList) and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF'))"),
		
		
		@NamedQuery(name = "CompareWeekUsage.findByid", query = "SELECT t FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esid " +
		"  and t.dayWeeklyUsageId.contractAccountNumber = :ca" +
		"  and t.dayWeeklyUsageId.contractId = :co   and t.yearWeekNum in (:currentYearweek,:compareToPreviousYearWeek) and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF')) order by t.dayWeeklyUsageId.actualDay"),
		
		
		@NamedQuery(name = "GetTempDetails.forDateRange", query = "SELECT t FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esiid and t.dayWeeklyUsageId.contractId=:co "
			+ "and t.dayWeeklyUsageId.contractAccountNumber=:ca and t.dayWeeklyUsageId.actualDay >= to_date(:fromYear,'MM/DD/YY') "
			+ "and t.dayWeeklyUsageId.actualDay <= to_date(:toYear,'MM/DD/YY') and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF'))") ,

		@NamedQuery(name = "MonthlyUsage.findByDateRange", query = "SELECT t FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esid and t.dayWeeklyUsageId.contractId=:co "
				+ "and t.dayWeeklyUsageId.contractAccountNumber=:ca and t.dayWeeklyUsageId.actualDay >= to_date(:fromDate,'MM/DD/YY') "
				+ "and t.dayWeeklyUsageId.actualDay <= to_date(:toDate,'MM/DD/YY') and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF'))"),
		
		@NamedQuery(name = "MonthlyUsage.findPrevMonthId", query = "SELECT count(*) FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esid and t.dayWeeklyUsageId.contractId=:co "
				+ "and t.dayWeeklyUsageId.contractAccountNumber=:ca and t.dayWeeklyUsageId.actualDay < to_date(:fromDate,'MM/DD/YY') "
				+ "and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF'))"),
		
		@NamedQuery(name = "MonthlyUsage.findNextMonthId", query = "SELECT count(*) FROM DayWeeklyUsageDO t WHERE t.dayWeeklyUsageId.esiid = :esid and t.dayWeeklyUsageId.contractId=:co "
				+ "and t.dayWeeklyUsageId.contractAccountNumber=:ca "
				+ "and t.dayWeeklyUsageId.actualDay > to_date(:toDate,'MM/DD/YY') and (t.loadID IS NULL OR t.loadID NOT IN ('GAPF'))")
		

})


@SqlResultSetMapping(name = "DayWeeklyUsageDO", entities = {

@EntityResult(entityClass = DayWeeklyUsageDO.class, fields = {

		@FieldResult(name = "dayWeeklyUsageId.esiid", column = "ESIID"),
		@FieldResult(name = "dayWeeklyUsageId.contractAccountNumber", column = "CONTRACT_ACCT_ID"),
		@FieldResult(name = "dayWeeklyUsageId.contractId", column = "CONTRACT_ID"),
		@FieldResult(name = "dayWeeklyUsageId.actualDay", column = "ACTUAL_DAY"),
		@FieldResult(name = "dayOfWeek", column = "DAY_OF_WEEK"),
		@FieldResult(name = "timeBlkType", column = "TIME_BLK_TYPE"),
		@FieldResult(name = "totalDayUsage", column = "TOTAL_USAGE_DAY"),
		@FieldResult(name = "totalDayCost", column = "TOTAL_COST_DAY"),
		@FieldResult(name = "dayTempHigh", column = "DAY_TEMP_HIGH"),
		@FieldResult(name = "dayTempLow", column = "DAY_TEMP_LOW"),
		@FieldResult(name = "yearWeekNum", column = "YEAR_WEEK_NO"),
		@FieldResult(name = "weekTotalUsage", column = "TOTAL_USAGE_WEEK"),
		@FieldResult(name = "weekTotalCost", column = "TOTAL_COST_WEEK"),
		@FieldResult(name = "weekAveTempHigh", column = "WEEK_AVE_TEMP_HIGH"),
		@FieldResult(name = "weekAveTempLow", column = "WEEK_AVE_TEMP_LOW"),
		@FieldResult(name = "code", column = "CODE_ID"),
		@FieldResult(name = "veeFlag", column = "VEE_FLAG"),
		@FieldResult(name = "veeFlagVersion", column = "VEE_FLAG_VERSION"),
		@FieldResult(name = "createDate", column = "CREATE_DATE"),
		@FieldResult(name = "updateDate", column = "UPDATE_DATE"),
		@FieldResult(name = "loadID", column = "LOAD_ID"),
		@FieldResult(name = "touBlkCost1", column = "TOU_BLK1_COST"),
		@FieldResult(name = "touBlkUsage1", column = "TOU_BLK1_USAGE"),
		@FieldResult(name = "touBlkCost2", column = "TOU_BLK2_COST"),
		@FieldResult(name = "touBlkUsage2", column = "TOU_BLK2_USAGE"),
		@FieldResult(name = "touBlkCost3", column = "TOU_BLK3_COST"),
		@FieldResult(name = "touBlkUsage3", column = "TOU_BLK3_USAGE"),
		@FieldResult(name = "touBlkCost4", column = "TOU_BLK4_COST"),
		@FieldResult(name = "touBlkUsage4", column = "TOU_BLK4_USAGE"),
		@FieldResult(name = "touBlkCost5", column = "TOU_BLK5_COST"),
		@FieldResult(name = "touBlkUsage5", column = "TOU_BLK5_USAGE"),
		@FieldResult(name = "touBlkCost6", column = "TOU_BLK6_COST"),
		@FieldResult(name = "touBlkUsage6", column = "TOU_BLK6_USAGE"),
		@FieldResult(name = "touType", column = "TOU_GROUP")
})

}

)
public class DayWeeklyUsageDO implements Serializable {

	@EmbeddedId
	private DayWeeklyUsagePK dayWeeklyUsageId;

	@Column (name = "DAY_OF_WEEK")
	private String dayOfWeek;
	@Column (name = "TIME_BLK_TYPE")
	private String  timeBlkType;
	@Column (name = "TOTAL_USAGE_DAY")
	private Double totalDayUsage;
	@Column (name = "TOTAL_COST_DAY")
	private Double  totalDayCost;
	@Column (name = "DAY_TEMP_HIGH")
	private Integer dayTempHigh;
	@Column (name = "DAY_TEMP_LOW")
	private Integer dayTempLow;
	@Column (name = "YEAR_WEEK_NO")
	private String yearWeekNum;
	@Column (name = "TOTAL_USAGE_WEEK")
	private Double weekTotalUsage;
	@Column (name = "TOTAL_COST_WEEK")
	private Double weekTotalCost;
	@Column (name = "WEEK_AVE_TEMP_HIGH")
	private Integer weekAveTempHigh;
	@Column (name = "WEEK_AVE_TEMP_LOW")
	private Integer weekAveTempLow;
	@Column (name = "CODE_ID")
	private String code;
	@Column (name = "VEE_FLAG")
	private String veeFlag;
	@Column (name = "VEE_FLAG_VERSION")
	private String veeFlagVersion;
	
	@Column (name = "CREATE_DATE")
	private Date createDate;
	@Column (name = "UPDATE_DATE")
	private Date updateDate;
	@Column (name = "LOAD_ID")
	private String loadID;

	/* Start - New TOU Blocks are added */
	@Column(name = "TOU_BLK1_COST")
	private Double touBlkCost1;
	@Column(name = "TOU_BLK2_COST")
	private Double touBlkCost2;
	@Column(name = "TOU_BLK3_COST")
	private Double touBlkCost3;
	@Column(name = "TOU_BLK4_COST")
	private Double touBlkCost4;
	@Column(name = "TOU_BLK5_COST")
	private Double touBlkCost5;
	@Column(name = "TOU_BLK6_COST")
	private Double touBlkCost6;

	@Column(name = "TOU_BLK1_USAGE")
	private Double touBlkUsage1;
	@Column(name = "TOU_BLK2_USAGE")
	private Double touBlkUsage2;
	@Column(name = "TOU_BLK3_USAGE")
	private Double touBlkUsage3;
	@Column(name = "TOU_BLK4_USAGE")
	private Double touBlkUsage4;
	@Column(name = "TOU_BLK5_USAGE")
	private Double touBlkUsage5;
	@Column(name = "TOU_BLK6_USAGE")
	private Double touBlkUsage6;
	@Column(name = "TOU_GROUP")
	private String touType;
	/* End - New TOU Blocks are added */
	
	
	public DayWeeklyUsagePK getDayWeeklyUsageId() {
		return dayWeeklyUsageId;
	}
	
	public void setDayWeeklyUsageId(DayWeeklyUsagePK dayWeeklyUsageId) {
		this.dayWeeklyUsageId = dayWeeklyUsageId;
	}
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public Double getTotalDayUsage() {
		return totalDayUsage!=null?(UsageHistoryUtil.getOneDecimal(totalDayUsage)):null;
	}
	
	public void setTotalDayUsage(Double totalDayUsage) {
		this.totalDayUsage = totalDayUsage;
	}
	
	public Double getTotalDayCost() {
		return totalDayCost;
	}
	
	public void setTotalDayCost(Double totalDayCost) {
		this.totalDayCost = totalDayCost;
	}
	
	public Integer getDayTempHigh() {
		return dayTempHigh;
	}
	
	public void setDayTempHigh(Integer dayTempHigh) {
		this.dayTempHigh = dayTempHigh;
	}
	
	public Integer getDayTempLow() {
		return dayTempLow;
	}
	
	public void setDayTempLow(Integer dayTempLow) {
		this.dayTempLow = dayTempLow;
	}
	
	public String getYearWeekNum() {
		return yearWeekNum;
	}
	
	public void setYearWeekNum(String yearWeekNum) {
		this.yearWeekNum = yearWeekNum;
	}
	
	public Double getWeekTotalUsage() {
		return weekTotalUsage!=null?(UsageHistoryUtil.getOneDecimal(weekTotalUsage)):null;
	}
	
	public void setWeekTotalUsage(Double weekTotalUsage) {
		this.weekTotalUsage = weekTotalUsage;
	}
	
	public Double getWeekTotalCost() {
		return weekTotalCost;
	}
	
	public void setWeekTotalCost(Double weekTotalCost) {
		this.weekTotalCost = weekTotalCost;
	}
	
	public Integer getWeekAveTempHigh() {
		return weekAveTempHigh;
	}
	
	public void setWeekAveTempHigh(Integer weekAveTempHigh) {
		this.weekAveTempHigh = weekAveTempHigh;
	}
	
	public Integer getWeekAveTempLow() {
		return weekAveTempLow;
	}
	
	public void setWeekAveTempLow(Integer weekAveTempLow) {
		this.weekAveTempLow = weekAveTempLow;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getVeeFlag() {
		return veeFlag;
	}
	
	public void setVeeFlag(String veeFlag) {
		this.veeFlag = veeFlag;
	}
	
	
	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getLoadID() {
		return loadID;
	}
	
	public void setLoadID(String loadID) {
		this.loadID = loadID;
	}
	
	public String getTimeBlkType() {
		return timeBlkType;
	}
	
	public void setTimeBlkType(String timeBlkType) {
		this.timeBlkType = timeBlkType;
	}
	
	public String getVeeFlagVersion() {
		return veeFlagVersion;
	}
	
	public void setVeeFlagVersion(String veeFlagVersion) {
		this.veeFlagVersion = veeFlagVersion;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	public Double getTouBlkCost1() {
		return touBlkCost1;
	}

	
	public void setTouBlkCost1(Double touBlkCost1) {
		this.touBlkCost1 = touBlkCost1;
	}

	
	public Double getTouBlkCost2() {
		return touBlkCost2;
	}

	
	public void setTouBlkCost2(Double touBlkCost2) {
		this.touBlkCost2 = touBlkCost2;
	}

	
	public Double getTouBlkCost3() {
		return touBlkCost3;
	}

	
	public void setTouBlkCost3(Double touBlkCost3) {
		this.touBlkCost3 = touBlkCost3;
	}

	public Double getTouBlkCost4() {
		return touBlkCost4;
	}

	public void setTouBlkCost4(Double touBlkCost4) {
		this.touBlkCost4 = touBlkCost4;
	}

	
	public Double getTouBlkCost5() {
		return touBlkCost5;
	}

	
	public void setTouBlkCost5(Double touBlkCost5) {
		this.touBlkCost5 = touBlkCost5;
	}

	
	public Double getTouBlkCost6() {
		return touBlkCost6;
	}

	
	public void setTouBlkCost6(Double touBlkCost6) {
		this.touBlkCost6 = touBlkCost6;
	}

	
	public Double getTouBlkUsage1() {
		return touBlkUsage1;
	}

	
	public void setTouBlkUsage1(Double touBlkUsage1) {
		this.touBlkUsage1 = touBlkUsage1;
		
	}

	public Double getTouBlkUsage2() {
		return touBlkUsage2;
	}

	
	public void setTouBlkUsage2(Double touBlkUsage2) {
		this.touBlkUsage2 = touBlkUsage2;
		
	}

	
	public Double getTouBlkUsage3() {
		return touBlkUsage3;
	}

	
	public void setTouBlkUsage3(Double touBlkUsage3) {
		this.touBlkUsage3 = touBlkUsage3;
		
	}

	
	public Double getTouBlkUsage4() {
		return touBlkUsage4;
	}

	
	public void setTouBlkUsage4(Double touBlkUsage4) {
		this.touBlkUsage4 = touBlkUsage4;
		
	}

	
	public Double getTouBlkUsage5() {
		return touBlkUsage5;
	}

	
	public void setTouBlkUsage5(Double touBlkUsage5) {
		this.touBlkUsage5 = touBlkUsage5;
		
	}

	
	public Double getTouBlkUsage6() {
		return touBlkUsage6;
	}

	public void setTouBlkUsage6(Double touBlkUsage6) {
		this.touBlkUsage6 = touBlkUsage6;
		
	}

	public String getTouType() {
		//touType = "AC";
		return touType;
	}

	public void setTouType(String touType) {
		this.touType = touType;
	}

}
