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
import com.reliant.sm.util.UsageHistoryUtil;


@Entity
@Table(name = "WP_HR_DAY", schema = CommonConstants.SCHEMA_NAME)
@NamedQueries( {

		@NamedQuery(name = "DailyUsage.findByid", query = "SELECT t FROM DayUsageDO t WHERE t.dailyUsageId.esid = :esid "
				+ "  and t.dailyUsageId.ca = :ca"
				+ "  and t.dailyUsageId.co = :co and t.dailyUsageId.actualDate = to_date(:actualDate,'MM/DD/YY') and (t.loadId IS NULL OR t.loadId NOT IN ('GAPF'))"),

		@NamedQuery(name = "DailyUsageNext.findByid", query = "SELECT count(*) FROM DayUsageDO t WHERE t.dailyUsageId.esid = :esid "
				+ "  and t.dailyUsageId.ca = :ca"
				+ "  and t.dailyUsageId.co = :co and t.dailyUsageId.actualDate >= to_date(:actualDate,'MM/DD/YY') and (t.loadId IS NULL OR t.loadId NOT IN ('GAPF'))"),

		@NamedQuery(name = "DailyUsagePrev.findByid", query = "SELECT count(*) FROM DayUsageDO t WHERE t.dailyUsageId.esid = :esid "
				+ "  and t.dailyUsageId.ca = :ca"
				+ "  and t.dailyUsageId.co = :co and t.dailyUsageId.actualDate <= to_date(:actualDate,'MM/DD/YY') and (t.loadId IS NULL OR t.loadId NOT IN ('GAPF'))"),

		@NamedQuery(name = "DailyUsageMoActualDay.findByid", query = "SELECT t FROM DayUsageDO t WHERE t.dailyUsageId.esid = :esid "
				+ "  and t.dailyUsageId.ca = :ca"
				+ " and t.dailyUsageId.co = :co and t.dailyUsageId.actualDate BETWEEN to_date(:startDate,'MM/DD/YY') AND to_date(:endDate,'MM/DD/YY') and (t.loadId IS NULL OR t.loadId NOT IN ('GAPF')) order by t.dailyUsageId.actualDate"),

		@NamedQuery(name = "DailyUsageDayView.findByid", query = " SELECT t FROM DayUsageDO t WHERE t.dailyUsageId.esid = :esid "
				+ "  and t.dailyUsageId.ca = :ca"
				+ "  and t.dailyUsageId.co = :co and t.dailyUsageId.actualDate = (SELECT max(t.dailyUsageId.actualDate) FROM DayUsageDO t WHERE t.dailyUsageId.esid = :esid "
				+ "  and t.dailyUsageId.ca = :ca"
				+ "  and t.dailyUsageId.co = :co ) and (t.loadId IS NULL OR t.loadId NOT IN ('GAPF'))"),
				
		@NamedQuery(name = "DailyUsageMoDefaultDay.findByid", query = " SELECT t FROM DayUsageDO t WHERE t.dailyUsageId.esid = :esid "
					+ "  and t.dailyUsageId.ca = :ca"
					+ "  and t.dailyUsageId.co = :co and (t.loadId IS NULL OR t.loadId NOT IN ('GAPF')) order by t.dailyUsageId.actualDate desc"),

		@NamedQuery(name = "CompareDayUsage.findByid", query = "SELECT t FROM DayUsageDO t WHERE t.dailyUsageId.esid = :esid "
				+ "  and t.dailyUsageId.ca = :ca"
				+ "  and t.dailyUsageId.co = :co and t.dailyUsageId.actualDate in (to_date(:currentDay,'MM/DD/YY'),to_date(:compareToDay,'MM/DD/YY')) and (t.loadId IS NULL OR t.loadId NOT IN ('GAPF')) order by t.dailyUsageId.actualDate)"),
				
		@NamedQuery(name = "GreenButtonUsage.findByActualDayPresent", query = "SELECT t FROM DayUsageDO t WHERE t.dailyUsageId.esid = :esid "
				+ "  and t.dailyUsageId.ca = :ca"
				+ "  and t.dailyUsageId.co = :co and t.dailyUsageId.actualDate >= to_date(:monthBeginDate,'MM/DD/YY') and t.dailyUsageId.actualDate <= to_date(:monthEndDate,'MM/DD/YY') and (t.loadId IS NULL OR t.loadId NOT IN ('GAPF'))")

})
public class DayUsageDO implements Serializable {

	@EmbeddedId
	private DayUsagePK dailyUsageId;

	@Column(name = "USAGE_HR01")
	private Double usageHour01;
	@Column(name = "USAGE_HR02")
	private Double usageHour02;
	@Column(name = "USAGE_HR03")
	private Double usageHour03;
	@Column(name = "USAGE_HR04")
	private Double usageHour04;
	@Column(name = "USAGE_HR05")
	private Double usageHour05;
	@Column(name = "USAGE_HR06")
	private Double usageHour06;
	@Column(name = "USAGE_HR07")
	private Double usageHour07;
	@Column(name = "USAGE_HR08")
	private Double usageHour08;
	@Column(name = "USAGE_HR09")
	private Double usageHour09;
	@Column(name = "USAGE_HR10")
	private Double usageHour10;
	@Column(name = "USAGE_HR11")
	private Double usageHour11;
	@Column(name = "USAGE_HR12")
	private Double usageHour12;
	@Column(name = "USAGE_HR13")
	private Double usageHour13;
	@Column(name = "USAGE_HR14")
	private Double usageHour14;
	@Column(name = "USAGE_HR15")
	private Double usageHour15;
	@Column(name = "USAGE_HR16")
	private Double usageHour16;
	@Column(name = "USAGE_HR17")
	private Double usageHour17;
	@Column(name = "USAGE_HR18")
	private Double usageHour18;
	@Column(name = "USAGE_HR19")
	private Double usageHour19;
	@Column(name = "USAGE_HR20")
	private Double usageHour20;
	@Column(name = "USAGE_HR21")
	private Double usageHour21;
	@Column(name = "USAGE_HR22")
	private Double usageHour22;
	@Column(name = "USAGE_HR23")
	private Double usageHour23;
	@Column(name = "USAGE_HR24")
	private Double usageHour24;
	@Column(name = "COST_HR01")
	private Double costHour01;
	@Column(name = "COST_HR02")
	private Double costHour02;
	@Column(name = "COST_HR03")
	private Double costHour03;
	@Column(name = "COST_HR04")
	private Double costHour04;
	@Column(name = "COST_HR05")
	private Double costHour05;
	@Column(name = "COST_HR06")
	private Double costHour06;
	@Column(name = "COST_HR07")
	private Double costHour07;
	@Column(name = "COST_HR08")
	private Double costHour08;
	@Column(name = "COST_HR09")
	private Double costHour09;
	@Column(name = "COST_HR10")
	private Double costHour10;
	@Column(name = "COST_HR11")
	private Double costHour11;
	@Column(name = "COST_HR12")
	private Double costHour12;
	@Column(name = "COST_HR13")
	private Double costHour13;
	@Column(name = "COST_HR14")
	private Double costHour14;
	@Column(name = "COST_HR15")
	private Double costHour15;
	@Column(name = "COST_HR16")
	private Double costHour16;
	@Column(name = "COST_HR17")
	private Double costHour17;
	@Column(name = "COST_HR18")
	private Double costHour18;
	@Column(name = "COST_HR19")
	private Double costHour19;
	@Column(name = "COST_HR20")
	private Double costHour20;
	@Column(name = "COST_HR21")
	private Double costHour21;
	@Column(name = "COST_HR22")
	private Double costHour22;
	@Column(name = "COST_HR23")
	private Double costHour23;
	@Column(name = "COST_HR24")
	private Double costHour24;
	@Column(name = "TIME_BLK_TYPE")
	private String timeBlkType;
	@Column(name = "TOTAL_USAGE_DAY")
	private Double totalUsageDay;
	@Column(name = "TOTAL_COST_DAY")
	private Double totalCostDay;
	@Column(name = "CODE_ID")
	private String code;
	@Column(name = "LOAD_ID")
	private String loadId;
	@Column(name = "VEE_FLAG")
	private String veeFlag;
	@Column(name = "VEE_FLAG_VERSION")
	private String veeFlagVer;
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	@Column(name = "DAY_TEMP_HIGH")
	private Integer dayTemHI;
	@Column(name = "DAY_TEMP_LOW")
	private Integer dayTemLo;

	
	
	@Column (name = "TOU_GROUP")
	private String touGroup;
	
	
	/*@OneToMany(targetEntity=DayTouGroup.class,cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER) 
	@JoinTable(name = "WP_TOU_GROUP", joinColumns ={@JoinColumn(name = "ESIID", unique = true)}, inverseJoinColumns = {@JoinColumn(name = "TOU_GROUP") })
	
	public Set<DayTouGroup> getTouGroupBlock() {
		return touGroupBlock;
	}	
	
	public void setTouGroupBlock(Set<DayTouGroup> touGroupBlock) {
		this.touGroupBlock = touGroupBlock;
	}*/	
	
	/**
	 * @return the dailyUsageId
	 */
	public DayUsagePK getDailyUsageId() {
		return dailyUsageId;
	}

	/**
	 * @param dailyUsageId
	 *            the dailyUsageId to set
	 */
	public void setDailyUsageId(DayUsagePK dailyUsageId) {
		this.dailyUsageId = dailyUsageId;
	}

	/**
	 * @return the usageHour01
	 */
	public Double getUsageHour01() {
		return (UsageHistoryUtil.getOneDecimal(usageHour01));
	}

	/**
	 * @param usageHour01
	 *            the usageHour01 to set
	 */
	public void setUsageHour01(Double usageHour01) {
		this.usageHour01 = usageHour01;
	}

	/**
	 * @return the usageHour02
	 */
	public Double getUsageHour02() {
		return (UsageHistoryUtil.getOneDecimal(usageHour02));
	}

	/**
	 * @param usageHour02
	 *            the usageHour02 to set
	 */
	public void setUsageHour02(Double usageHour02) {
		this.usageHour02 = usageHour02;
	}

	/**
	 * @return the usageHour03
	 */
	public Double getUsageHour03() {
		return (UsageHistoryUtil.getOneDecimal(usageHour03));
	}

	/**
	 * @param usageHour03
	 *            the usageHour03 to set
	 */
	public void setUsageHour03(Double usageHour03) {
		this.usageHour03 = usageHour03;
	}

	/**
	 * @return the usageHour04
	 */
	public Double getUsageHour04() {
		return (UsageHistoryUtil.getOneDecimal(usageHour04));
	}

	/**
	 * @param usageHour04
	 *            the usageHour04 to set
	 */
	public void setUsageHour04(Double usageHour04) {
		this.usageHour04 = usageHour04;
	}

	/**
	 * @return the usageHour05
	 */
	public Double getUsageHour05() {
		return (UsageHistoryUtil.getOneDecimal(usageHour05));
	}

	/**
	 * @param usageHour05
	 *            the usageHour05 to set
	 */
	public void setUsageHour05(Double usageHour05) {
		this.usageHour05 = usageHour05;
	}

	/**
	 * @return the usageHour06
	 */
	public Double getUsageHour06() {
		return (UsageHistoryUtil.getOneDecimal(usageHour06));
	}

	/**
	 * @param usageHour06
	 *            the usageHour06 to set
	 */
	public void setUsageHour06(Double usageHour06) {
		this.usageHour06 = usageHour06;
	}

	/**
	 * @return the usageHour07
	 */
	public Double getUsageHour07() {
		return (UsageHistoryUtil.getOneDecimal(usageHour07));
	}

	/**
	 * @param usageHour07
	 *            the usageHour07 to set
	 */
	public void setUsageHour07(Double usageHour07) {
		this.usageHour07 = usageHour07;
	}

	/**
	 * @return the usageHour08
	 */
	public Double getUsageHour08() {
		return (UsageHistoryUtil.getOneDecimal(usageHour08));
	}

	/**
	 * @param usageHour08
	 *            the usageHour08 to set
	 */
	public void setUsageHour08(Double usageHour08) {
		this.usageHour08 = usageHour08;
	}

	/**
	 * @return the usageHour09
	 */
	public Double getUsageHour09() {
		return (UsageHistoryUtil.getOneDecimal(usageHour09));
	}

	/**
	 * @param usageHour09
	 *            the usageHour09 to set
	 */
	public void setUsageHour09(Double usageHour09) {
		this.usageHour09 = usageHour09;
	}

	/**
	 * @return the usageHour10
	 */
	public Double getUsageHour10() {
		return (UsageHistoryUtil.getOneDecimal(usageHour10));
	}

	/**
	 * @param usageHour10
	 *            the usageHour10 to set
	 */
	public void setUsageHour10(Double usageHour10) {
		this.usageHour10 = usageHour10;
	}

	/**
	 * @return the usageHour11
	 */
	public Double getUsageHour11() {
		return (UsageHistoryUtil.getOneDecimal(usageHour11));
	}

	/**
	 * @param usageHour11
	 *            the usageHour11 to set
	 */
	public void setUsageHour11(Double usageHour11) {
		this.usageHour11 = usageHour11;
	}

	/**
	 * @return the usageHour12
	 */
	public Double getUsageHour12() {
		return (UsageHistoryUtil.getOneDecimal(usageHour12));
	}

	/**
	 * @param usageHour12
	 *            the usageHour12 to set
	 */
	public void setUsageHour12(Double usageHour12) {
		this.usageHour12 = usageHour12;
	}

	/**
	 * @return the usageHour13
	 */
	public Double getUsageHour13() {
		return (UsageHistoryUtil.getOneDecimal(usageHour13));
	}

	/**
	 * @param usageHour13
	 *            the usageHour13 to set
	 */
	public void setUsageHour13(Double usageHour13) {
		this.usageHour13 = usageHour13;
	}

	/**
	 * @return the usageHour14
	 */
	public Double getUsageHour14() {
		return (UsageHistoryUtil.getOneDecimal(usageHour14));
	}

	/**
	 * @param usageHour14
	 *            the usageHour14 to set
	 */
	public void setUsageHour14(Double usageHour14) {
		this.usageHour14 = usageHour14;
	}

	/**
	 * @return the usageHour15
	 */
	public Double getUsageHour15() {
		return (UsageHistoryUtil.getOneDecimal(usageHour15));
	}

	/**
	 * @param usageHour15
	 *            the usageHour15 to set
	 */
	public void setUsageHour15(Double usageHour15) {
		this.usageHour15 = usageHour15;
	}

	/**
	 * @return the usageHour16
	 */
	public Double getUsageHour16() {
		return (UsageHistoryUtil.getOneDecimal(usageHour16));
	}

	/**
	 * @param usageHour16
	 *            the usageHour16 to set
	 */
	public void setUsageHour16(Double usageHour16) {
		this.usageHour16 = usageHour16;
	}

	/**
	 * @return the usageHour17
	 */
	public Double getUsageHour17() {
		return (UsageHistoryUtil.getOneDecimal(usageHour17));
	}

	/**
	 * @param usageHour17
	 *            the usageHour17 to set
	 */
	public void setUsageHour17(Double usageHour17) {
		this.usageHour17 = usageHour17;
	}

	/**
	 * @return the usageHour18
	 */
	public Double getUsageHour18() {
		return (UsageHistoryUtil.getOneDecimal(usageHour18));
	}

	/**
	 * @param usageHour18
	 *            the usageHour18 to set
	 */
	public void setUsageHour18(Double usageHour18) {
		this.usageHour18 = usageHour18;
	}

	/**
	 * @return the usageHour19
	 */
	public Double getUsageHour19() {
		return (UsageHistoryUtil.getOneDecimal(usageHour19));
	}

	/**
	 * @param usageHour19
	 *            the usageHour19 to set
	 */
	public void setUsageHour19(Double usageHour19) {
		this.usageHour19 = usageHour19;
	}

	/**
	 * @return the usageHour20
	 */
	public Double getUsageHour20() {
		return (UsageHistoryUtil.getOneDecimal(usageHour20));
	}

	/**
	 * @param usageHour20
	 *            the usageHour20 to set
	 */
	public void setUsageHour20(Double usageHour20) {
		this.usageHour20 = usageHour20;
	}

	/**
	 * @return the usageHour21
	 */
	public Double getUsageHour21() {
		return (UsageHistoryUtil.getOneDecimal(usageHour21));
	}

	/**
	 * @param usageHour21
	 *            the usageHour21 to set
	 */
	public void setUsageHour21(Double usageHour21) {
		this.usageHour21 = usageHour21;
	}

	/**
	 * @return the usageHour22
	 */
	public Double getUsageHour22() {
		return (UsageHistoryUtil.getOneDecimal(usageHour22));
	}

	/**
	 * @param usageHour22
	 *            the usageHour22 to set
	 */
	public void setUsageHour22(Double usageHour22) {
		this.usageHour22 = usageHour22;
	}

	/**
	 * @return the usageHour23
	 */
	public Double getUsageHour23() {
		return (UsageHistoryUtil.getOneDecimal(usageHour23));
	}

	/**
	 * @param usageHour23
	 *            the usageHour23 to set
	 */
	public void setUsageHour23(Double usageHour23) {
		this.usageHour23 = usageHour23;
	}

	/**
	 * @return the usageHour24
	 */
	public Double getUsageHour24() {
		return (UsageHistoryUtil.getOneDecimal(usageHour24));
	}

	/**
	 * @param usageHour24
	 *            the usageHour24 to set
	 */
	public void setUsageHour24(Double usageHour24) {
		this.usageHour24 = usageHour24;
	}

	/**
	 * @return the costHour01
	 */
	public Double getCostHour01() {
		return costHour01;
	}

	/**
	 * @param costHour01
	 *            the costHour01 to set
	 */
	public void setCostHour01(Double costHour01) {
		this.costHour01 = costHour01;
	}

	/**
	 * @return the costHour02
	 */
	public Double getCostHour02() {
		return costHour02;
	}

	/**
	 * @param costHour02
	 *            the costHour02 to set
	 */
	public void setCostHour02(Double costHour02) {
		this.costHour02 = costHour02;
	}

	/**
	 * @return the costHour03
	 */
	public Double getCostHour03() {
		return costHour03;
	}

	/**
	 * @param costHour03
	 *            the costHour03 to set
	 */
	public void setCostHour03(Double costHour03) {
		this.costHour03 = costHour03;
	}

	/**
	 * @return the costHour04
	 */
	public Double getCostHour04() {
		return costHour04;
	}

	/**
	 * @param costHour04
	 *            the costHour04 to set
	 */
	public void setCostHour04(Double costHour04) {
		this.costHour04 = costHour04;
	}

	/**
	 * @return the costHour05
	 */
	public Double getCostHour05() {
		return costHour05;
	}

	/**
	 * @param costHour05
	 *            the costHour05 to set
	 */
	public void setCostHour05(Double costHour05) {
		this.costHour05 = costHour05;
	}

	/**
	 * @return the costHour06
	 */
	public Double getCostHour06() {
		return costHour06;
	}

	/**
	 * @param costHour06
	 *            the costHour06 to set
	 */
	public void setCostHour06(Double costHour06) {
		this.costHour06 = costHour06;
	}

	/**
	 * @return the costHour07
	 */
	public Double getCostHour07() {
		return costHour07;
	}

	/**
	 * @param costHour07
	 *            the costHour07 to set
	 */
	public void setCostHour07(Double costHour07) {
		this.costHour07 = costHour07;
	}

	/**
	 * @return the costHour08
	 */
	public Double getCostHour08() {
		return costHour08;
	}

	/**
	 * @param costHour08
	 *            the costHour08 to set
	 */
	public void setCostHour08(Double costHour08) {
		this.costHour08 = costHour08;
	}

	/**
	 * @return the costHour09
	 */
	public Double getCostHour09() {
		return costHour09;
	}

	/**
	 * @param costHour09
	 *            the costHour09 to set
	 */
	public void setCostHour09(Double costHour09) {
		this.costHour09 = costHour09;
	}

	/**
	 * @return the costHour10
	 */
	public Double getCostHour10() {
		return costHour10;
	}

	/**
	 * @param costHour10
	 *            the costHour10 to set
	 */
	public void setCostHour10(Double costHour10) {
		this.costHour10 = costHour10;
	}

	/**
	 * @return the costHour11
	 */
	public Double getCostHour11() {
		return costHour11;
	}

	/**
	 * @param costHour11
	 *            the costHour11 to set
	 */
	public void setCostHour11(Double costHour11) {
		this.costHour11 = costHour11;
	}

	/**
	 * @return the costHour12
	 */
	public Double getCostHour12() {
		return costHour12;
	}

	/**
	 * @param costHour12
	 *            the costHour12 to set
	 */
	public void setCostHour12(Double costHour12) {
		this.costHour12 = costHour12;
	}

	/**
	 * @return the costHour13
	 */
	public Double getCostHour13() {
		return costHour13;
	}

	/**
	 * @param costHour13
	 *            the costHour13 to set
	 */
	public void setCostHour13(Double costHour13) {
		this.costHour13 = costHour13;
	}

	/**
	 * @return the costHour14
	 */
	public Double getCostHour14() {
		return costHour14;
	}

	/**
	 * @param costHour14
	 *            the costHour14 to set
	 */
	public void setCostHour14(Double costHour14) {
		this.costHour14 = costHour14;
	}

	/**
	 * @return the costHour15
	 */
	public Double getCostHour15() {
		return costHour15;
	}

	/**
	 * @param costHour15
	 *            the costHour15 to set
	 */
	public void setCostHour15(Double costHour15) {
		this.costHour15 = costHour15;
	}

	/**
	 * @return the costHour16
	 */
	public Double getCostHour16() {
		return costHour16;
	}

	/**
	 * @param costHour16
	 *            the costHour16 to set
	 */
	public void setCostHour16(Double costHour16) {
		this.costHour16 = costHour16;
	}

	/**
	 * @return the costHour17
	 */
	public Double getCostHour17() {
		return costHour17;
	}

	/**
	 * @param costHour17
	 *            the costHour17 to set
	 */
	public void setCostHour17(Double costHour17) {
		this.costHour17 = costHour17;
	}

	/**
	 * @return the costHour18
	 */
	public Double getCostHour18() {
		return costHour18;
	}

	/**
	 * @param costHour18
	 *            the costHour18 to set
	 */
	public void setCostHour18(Double costHour18) {
		this.costHour18 = costHour18;
	}

	/**
	 * @return the costHour19
	 */
	public Double getCostHour19() {
		return costHour19;
	}

	/**
	 * @param costHour19
	 *            the costHour19 to set
	 */
	public void setCostHour19(Double costHour19) {
		this.costHour19 = costHour19;
	}

	/**
	 * @return the costHour20
	 */
	public Double getCostHour20() {
		return costHour20;
	}

	/**
	 * @param costHour20
	 *            the costHour20 to set
	 */
	public void setCostHour20(Double costHour20) {
		this.costHour20 = costHour20;
	}

	/**
	 * @return the costHour21
	 */
	public Double getCostHour21() {
		return costHour21;
	}

	/**
	 * @param costHour21
	 *            the costHour21 to set
	 */
	public void setCostHour21(Double costHour21) {
		this.costHour21 = costHour21;
	}

	/**
	 * @return the costHour22
	 */
	public Double getCostHour22() {
		return costHour22;
	}

	/**
	 * @param costHour22
	 *            the costHour22 to set
	 */
	public void setCostHour22(Double costHour22) {
		this.costHour22 = costHour22;
	}

	/**
	 * @return the costHour23
	 */
	public Double getCostHour23() {
		return costHour23;
	}

	/**
	 * @param costHour23
	 *            the costHour23 to set
	 */
	public void setCostHour23(Double costHour23) {
		this.costHour23 = costHour23;
	}

	/**
	 * @return the costHour24
	 */
	public Double getCostHour24() {
		return costHour24;
	}

	/**
	 * @param costHour24
	 *            the costHour24 to set
	 */
	public void setCostHour24(Double costHour24) {
		this.costHour24 = costHour24;
	}

	/**
	 * @return the timeBlkType
	 */
	public String getTimeBlkType() {
		return timeBlkType;
	}

	/**
	 * @param timeBlkType
	 *            the timeBlkType to set
	 */
	public void setTimeBlkType(String timeBlkType) {
		this.timeBlkType = timeBlkType;
	}

	/**
	 * @return the totalUsageDay
	 */
	public Double getTotalUsageDay() {
		return (UsageHistoryUtil.getOneDecimal(totalUsageDay));
	}

	/**
	 * @param totalUsageDay
	 *            the totalUsageDay to set
	 */
	public void setTotalUsageDay(Double totalUsageDay) {
		this.totalUsageDay = totalUsageDay;
	}

	/**
	 * @return the totalCostDay
	 */
	public Double getTotalCostDay() {
		return totalCostDay;
	}

	/**
	 * @param totalCostDay
	 *            the totalCostDay to set
	 */
	public void setTotalCostDay(Double totalCostDay) {
		this.totalCostDay = totalCostDay;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the loadId
	 */
	public String getLoadId() {
		return loadId;
	}

	/**
	 * @param loadId
	 *            the loadId to set
	 */
	public void setLoadId(String loadId) {
		this.loadId = loadId;
	}

	/**
	 * @return the veeFlag
	 */
	public String getVeeFlag() {
		return veeFlag;
	}

	/**
	 * @param veeFlag
	 *            the veeFlag to set
	 */
	public void setVeeFlag(String veeFlag) {
		this.veeFlag = veeFlag;
	}

	/**
	 * @return the veeFlagVer
	 */
	public String getVeeFlagVer() {

		return veeFlagVer;
	}

	/**
	 * @param veeFlagVer
	 *            the veeFlagVer to set
	 */
	public void setVeeFlagVer(String veeFlagVer) {
		this.veeFlagVer = veeFlagVer;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
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
	 * @param updateDate
	 *            the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the dayTemHI
	 */
	public Integer getDayTemHI() {
		return dayTemHI;
	}

	/**
	 * @param dayTemHI
	 *            the dayTemHI to set
	 */
	public void setDayTemHI(Integer dayTemHI) {
		this.dayTemHI = dayTemHI;
	}

	/**
	 * @return the dayTemLo
	 */
	public Integer getDayTemLo() {
		return dayTemLo;
	}

	/**
	 * @param dayTemLo
	 *            the dayTemLo to set
	 */
	public void setDayTemLo(Integer dayTemLo) {
		this.dayTemLo = dayTemLo;
	}
	
	/**
	 * @return the touGroup
	 */
	public String getTouGroup() {
		return touGroup;
	}
	/**
	 * @param touGroup the touGroup to set
	 */
	public void setTouGroup(String touGroup) {
		this.touGroup = touGroup;
	}	
		
	
		
}
