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

/*
 * PeerCompare Daily Usage Entity
 * @author bbachin1
 * 
 */

@Entity
@Table(name = "WP_PC_HR_DAY_USAGE", schema = CommonConstants.SCHEMA_NAME)
@NamedQueries( {
	
	@NamedQuery(name = "PCDailyUsage.findByActualDayPresent", query = "SELECT t FROM PCDailyUsageDO t WHERE t.pcDailyUsagePK.esiid = :esid " +
			"  and t.pcDailyUsagePK.contractAccountNumber = :ca" +
			"  and t.pcDailyUsagePK.contractId = :co and t.pcDailyUsagePK.actualDay = to_date(:actualDate,'MM/DD/YY')"),
	
	@NamedQuery(name = "PCDailyUsage.findByActualDayNotPresent", query = " SELECT t FROM PCDailyUsageDO t WHERE t.pcDailyUsagePK.esiid = :esid " +
				"  and t.pcDailyUsagePK.contractAccountNumber = :ca" +
				"  and t.pcDailyUsagePK.contractId = :co and t.pcDailyUsagePK.actualDay = (SELECT max(pc.pcDailyUsagePK.actualDay) FROM PCDailyUsageDO pc WHERE pc.pcDailyUsagePK.esiid = :esid " +
				"  and pc.pcDailyUsagePK.contractAccountNumber = :ca" +
				"  and pc.pcDailyUsagePK.contractId = :co )"),
				
	@NamedQuery(name = "PCDailyUsagePrev.findByOtherActualDay", query = "SELECT count(*) FROM PCDailyUsageDO t WHERE t.pcDailyUsagePK.esiid = :esid " +
						"  and t.pcDailyUsagePK.contractAccountNumber = :ca" +
						"  and t.pcDailyUsagePK.contractId = :co and t.pcDailyUsagePK.actualDay <= to_date(:actualDate,'MM/DD/YY')"),
	
	@NamedQuery(name = "PCDailyUsageNext.findByOtherActualDay", query = "SELECT count(*) FROM PCDailyUsageDO t WHERE t.pcDailyUsagePK.esiid = :esid " +
						"  and t.pcDailyUsagePK.contractAccountNumber = :ca" +
						"  and t.pcDailyUsagePK.contractId = :co and t.pcDailyUsagePK.actualDay >= to_date(:actualDate,'MM/DD/YY')")

	
})


public class PCDailyUsageDO implements Serializable{
	
	@EmbeddedId
	private PCDailyUsagePK pcDailyUsagePK;
	
	@Column (name = "PEER_COUNT")	
	private String peerCount;
	@Column (name = "DAY_AVG_USAGE")	
	private Double dayAvgUsage;
	@Column (name = "DAY_EFF_USAGE")
	private Double dayEffUsage;
	@Column (name = "DAY_SLF_USAGE")
	private Double daySelfUsage;
	@Column (name = "HR01_AVG_USAGE")
	private Double avgUsageHr01;
	@Column (name = "HR01_EFF_USAGE")
	private Double effUsageHr01;
	@Column (name = "HR01_SLF_USAGE")
	private Double selfUsageHr01;
	@Column (name = "HR02_AVG_USAGE")
	private Double avgUsageHr02;
	@Column (name = "HR02_EFF_USAGE")
	private Double effUsageHr02;
	@Column (name = "HR02_SLF_USAGE")
	private Double selfUsageHr02;
	@Column (name = "HR03_AVG_USAGE")
	private Double avgUsageHr03;
	@Column (name = "HR03_EFF_USAGE")
	private Double effUsageHr03;
	@Column (name = "HR03_SLF_USAGE")
	private Double selfUsageHr03;
	@Column (name = "HR04_AVG_USAGE")
	private Double avgUsageHr04;
	@Column (name = "HR04_EFF_USAGE")
	private Double effUsageHr04;
	@Column (name = "HR04_SLF_USAGE")
	private Double selfUsageHr04;
	@Column (name = "HR05_AVG_USAGE")
	private Double avgUsageHr05;
	@Column (name = "HR05_EFF_USAGE")
	private Double effUsageHr05;
	@Column (name = "HR05_SLF_USAGE")
	private Double selfUsageHr05;
	@Column (name = "HR06_AVG_USAGE")
	private Double avgUsageHr06;
	@Column (name = "HR06_EFF_USAGE")
	private Double effUsageHr06;
	@Column (name = "HR06_SLF_USAGE")
	private Double selfUsageHr06;
	@Column (name = "HR07_AVG_USAGE")
	private Double avgUsageHr07;
	@Column (name = "HR07_EFF_USAGE")
	private Double effUsageHr07;
	@Column (name = "HR07_SLF_USAGE")
	private Double selfUsageHr07;
	@Column (name = "HR08_AVG_USAGE")
	private Double avgUsageHr08;
	@Column (name = "HR08_EFF_USAGE")
	private Double effUsageHr08;
	@Column (name = "HR08_SLF_USAGE")
	private Double selfUsageHr08;
	@Column (name = "HR09_AVG_USAGE")
	private Double avgUsageHr09;
	@Column (name = "HR09_EFF_USAGE")
	private Double effUsageHr09;
	@Column (name = "HR09_SLF_USAGE")
	private Double selfUsageHr09;
	@Column (name = "HR10_AVG_USAGE")
	private Double avgUsageHr10;
	@Column (name = "HR10_EFF_USAGE")
	private Double effUsageHr10;
	@Column (name = "HR10_SLF_USAGE")
	private Double selfUsageHr10;
	@Column (name = "HR11_AVG_USAGE")
	private Double avgUsageHr11;
	@Column (name = "HR11_EFF_USAGE")
	private Double effUsageHr11;
	@Column (name = "HR11_SLF_USAGE")
	private Double selfUsageHr11;
	@Column (name = "HR12_AVG_USAGE")
	private Double avgUsageHr12;
	@Column (name = "HR12_EFF_USAGE")
	private Double effUsageHr12;
	@Column (name = "HR12_SLF_USAGE")
	private Double selfUsageHr12;
	@Column (name = "HR13_AVG_USAGE")
	private Double avgUsageHr13;
	@Column (name = "HR13_EFF_USAGE")
	private Double effUsageHr13;
	@Column (name = "HR13_SLF_USAGE")
	private Double selfUsageHr13;
	@Column (name = "HR14_AVG_USAGE")
	private Double avgUsageHr14;
	@Column (name = "HR14_EFF_USAGE")
	private Double effUsageHr14;
	@Column (name = "HR14_SLF_USAGE")
	private Double selfUsageHr14;
	@Column (name = "HR15_AVG_USAGE")
	private Double avgUsageHr15;
	@Column (name = "HR15_EFF_USAGE")
	private Double effUsageHr15;
	@Column (name = "HR15_SLF_USAGE")
	private Double selfUsageHr15;
	@Column (name = "HR16_AVG_USAGE")
	private Double avgUsageHr16;
	@Column (name = "HR16_EFF_USAGE")
	private Double effUsageHr16;
	@Column (name = "HR16_SLF_USAGE")
	private Double selfUsageHr16;
	@Column (name = "HR17_AVG_USAGE")
	private Double avgUsageHr17;
	@Column (name = "HR17_EFF_USAGE")
	private Double effUsageHr17;
	@Column (name = "HR17_SLF_USAGE")
	private Double selfUsageHr17;
	@Column (name = "HR18_AVG_USAGE")
	private Double avgUsageHr18;
	@Column (name = "HR18_EFF_USAGE")
	private Double effUsageHr18;
	@Column (name = "HR18_SLF_USAGE")
	private Double selfUsageHr18;
	@Column (name = "HR19_AVG_USAGE")
	private Double avgUsageHr19;
	@Column (name = "HR19_EFF_USAGE")
	private Double effUsageHr19;
	@Column (name = "HR19_SLF_USAGE")
	private Double selfUsageHr19;
	@Column (name = "HR20_AVG_USAGE")
	private Double avgUsageHr20;
	@Column (name = "HR20_EFF_USAGE")
	private Double effUsageHr20;
	@Column (name = "HR20_SLF_USAGE")
	private Double selfUsageHr20;
	@Column (name = "HR21_AVG_USAGE")
	private Double avgUsageHr21;
	@Column (name = "HR21_EFF_USAGE")
	private Double effUsageHr21;
	@Column (name = "HR21_SLF_USAGE")
	private Double selfUsageHr21;
	@Column (name = "HR22_AVG_USAGE")
	private Double avgUsageHr22;
	@Column (name = "HR22_EFF_USAGE")
	private Double effUsageHr22;
	@Column (name = "HR22_SLF_USAGE")
	private Double selfUsageHr22;
	@Column (name = "HR23_AVG_USAGE")
	private Double avgUsageHr23;
	@Column (name = "HR23_EFF_USAGE")
	private Double effUsageHr23;
	@Column (name = "HR23_SLF_USAGE")
	private Double selfUsageHr23;
	@Column (name = "HR24_AVG_USAGE")
	private Double avgUsageHr24;
	@Column (name = "HR24_EFF_USAGE")
	private Double effUsageHr24;
	@Column (name = "HR24_SLF_USAGE")
	private Double selfUsageHr24;
	@Column(name = "DAY_TEMP_HIGH")
	private Integer dayTempHigh;
	@Column(name = "DAY_TEMP_LOW")
	private Integer dayTempLow;
	@Column(name = "CODE_ID")
	private String codeId;
	@Column(name = "LOAD_ID")
	private String loadId;
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	/**
	 * @return the pcDailyUsagePK
	 */
	public PCDailyUsagePK getPcDailyUsagePK() {
		return pcDailyUsagePK;
	}
	/**
	 * @param pcDailyUsagePK the pcDailyUsagePK to set
	 */
	public void setPcDailyUsagePK(PCDailyUsagePK pcDailyUsagePK) {
		this.pcDailyUsagePK = pcDailyUsagePK;
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
		return dayAvgUsage;
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
		return dayEffUsage;
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
		return daySelfUsage;
	}
	/**
	 * @param daySelfUsage the daySelfUsage to set
	 */
	public void setDaySelfUsage(Double daySelfUsage) {
		this.daySelfUsage = daySelfUsage;
	}
	/**
	 * @return the avgUsageHr01
	 */
	public Double getAvgUsageHr01() {
		return avgUsageHr01;
	}
	/**
	 * @param avgUsageHr01 the avgUsageHr01 to set
	 */
	public void setAvgUsageHr01(Double avgUsageHr01) {
		this.avgUsageHr01 = avgUsageHr01;
	}
	/**
	 * @return the effUsageHr01
	 */
	public Double getEffUsageHr01() {
		return effUsageHr01;
	}
	/**
	 * @param effUsageHr01 the effUsageHr01 to set
	 */
	public void setEffUsageHr01(Double effUsageHr01) {
		this.effUsageHr01 = effUsageHr01;
	}
	/**
	 * @return the selfUsageHr01
	 */
	public Double getSelfUsageHr01() {
		return selfUsageHr01;
	}
	/**
	 * @param selfUsageHr01 the selfUsageHr01 to set
	 */
	public void setSelfUsageHr01(Double selfUsageHr01) {
		this.selfUsageHr01 = selfUsageHr01;
	}
	/**
	 * @return the avgUsageHr02
	 */
	public Double getAvgUsageHr02() {
		return avgUsageHr02;
	}
	/**
	 * @param avgUsageHr02 the avgUsageHr02 to set
	 */
	public void setAvgUsageHr02(Double avgUsageHr02) {
		this.avgUsageHr02 = avgUsageHr02;
	}
	/**
	 * @return the effUsageHr02
	 */
	public Double getEffUsageHr02() {
		return effUsageHr02;
	}
	/**
	 * @param effUsageHr02 the effUsageHr02 to set
	 */
	public void setEffUsageHr02(Double effUsageHr02) {
		this.effUsageHr02 = effUsageHr02;
	}
	/**
	 * @return the selfUsageHr02
	 */
	public Double getSelfUsageHr02() {
		return selfUsageHr02;
	}
	/**
	 * @param selfUsageHr02 the selfUsageHr02 to set
	 */
	public void setSelfUsageHr02(Double selfUsageHr02) {
		this.selfUsageHr02 = selfUsageHr02;
	}
	/**
	 * @return the avgUsageHr03
	 */
	public Double getAvgUsageHr03() {
		return avgUsageHr03;
	}
	/**
	 * @param avgUsageHr03 the avgUsageHr03 to set
	 */
	public void setAvgUsageHr03(Double avgUsageHr03) {
		this.avgUsageHr03 = avgUsageHr03;
	}
	/**
	 * @return the effUsageHr03
	 */
	public Double getEffUsageHr03() {
		return effUsageHr03;
	}
	/**
	 * @param effUsageHr03 the effUsageHr03 to set
	 */
	public void setEffUsageHr03(Double effUsageHr03) {
		this.effUsageHr03 = effUsageHr03;
	}
	/**
	 * @return the selfUsageHr03
	 */
	public Double getSelfUsageHr03() {
		return selfUsageHr03;
	}
	/**
	 * @param selfUsageHr03 the selfUsageHr03 to set
	 */
	public void setSelfUsageHr03(Double selfUsageHr03) {
		this.selfUsageHr03 = selfUsageHr03;
	}
	/**
	 * @return the avgUsageHr04
	 */
	public Double getAvgUsageHr04() {
		return avgUsageHr04;
	}
	/**
	 * @param avgUsageHr04 the avgUsageHr04 to set
	 */
	public void setAvgUsageHr04(Double avgUsageHr04) {
		this.avgUsageHr04 = avgUsageHr04;
	}
	/**
	 * @return the effUsageHr04
	 */
	public Double getEffUsageHr04() {
		return effUsageHr04;
	}
	/**
	 * @param effUsageHr04 the effUsageHr04 to set
	 */
	public void setEffUsageHr04(Double effUsageHr04) {
		this.effUsageHr04 = effUsageHr04;
	}
	/**
	 * @return the selfUsageHr04
	 */
	public Double getSelfUsageHr04() {
		return selfUsageHr04;
	}
	/**
	 * @param selfUsageHr04 the selfUsageHr04 to set
	 */
	public void setSelfUsageHr04(Double selfUsageHr04) {
		this.selfUsageHr04 = selfUsageHr04;
	}
	/**
	 * @return the avgUsageHr05
	 */
	public Double getAvgUsageHr05() {
		return avgUsageHr05;
	}
	/**
	 * @param avgUsageHr05 the avgUsageHr05 to set
	 */
	public void setAvgUsageHr05(Double avgUsageHr05) {
		this.avgUsageHr05 = avgUsageHr05;
	}
	/**
	 * @return the effUsageHr05
	 */
	public Double getEffUsageHr05() {
		return effUsageHr05;
	}
	/**
	 * @param effUsageHr05 the effUsageHr05 to set
	 */
	public void setEffUsageHr05(Double effUsageHr05) {
		this.effUsageHr05 = effUsageHr05;
	}
	/**
	 * @return the selfUsageHr05
	 */
	public Double getSelfUsageHr05() {
		return selfUsageHr05;
	}
	/**
	 * @param selfUsageHr05 the selfUsageHr05 to set
	 */
	public void setSelfUsageHr05(Double selfUsageHr05) {
		this.selfUsageHr05 = selfUsageHr05;
	}
	/**
	 * @return the avgUsageHr06
	 */
	public Double getAvgUsageHr06() {
		return avgUsageHr06;
	}
	/**
	 * @param avgUsageHr06 the avgUsageHr06 to set
	 */
	public void setAvgUsageHr06(Double avgUsageHr06) {
		this.avgUsageHr06 = avgUsageHr06;
	}
	/**
	 * @return the effUsageHr06
	 */
	public Double getEffUsageHr06() {
		return effUsageHr06;
	}
	/**
	 * @param effUsageHr06 the effUsageHr06 to set
	 */
	public void setEffUsageHr06(Double effUsageHr06) {
		this.effUsageHr06 = effUsageHr06;
	}
	/**
	 * @return the selfUsageHr06
	 */
	public Double getSelfUsageHr06() {
		return selfUsageHr06;
	}
	/**
	 * @param selfUsageHr06 the selfUsageHr06 to set
	 */
	public void setSelfUsageHr06(Double selfUsageHr06) {
		this.selfUsageHr06 = selfUsageHr06;
	}
	/**
	 * @return the avgUsageHr07
	 */
	public Double getAvgUsageHr07() {
		return avgUsageHr07;
	}
	/**
	 * @param avgUsageHr07 the avgUsageHr07 to set
	 */
	public void setAvgUsageHr07(Double avgUsageHr07) {
		this.avgUsageHr07 = avgUsageHr07;
	}
	/**
	 * @return the effUsageHr07
	 */
	public Double getEffUsageHr07() {
		return effUsageHr07;
	}
	/**
	 * @param effUsageHr07 the effUsageHr07 to set
	 */
	public void setEffUsageHr07(Double effUsageHr07) {
		this.effUsageHr07 = effUsageHr07;
	}
	/**
	 * @return the selfUsageHr07
	 */
	public Double getSelfUsageHr07() {
		return selfUsageHr07;
	}
	/**
	 * @param selfUsageHr07 the selfUsageHr07 to set
	 */
	public void setSelfUsageHr07(Double selfUsageHr07) {
		this.selfUsageHr07 = selfUsageHr07;
	}
	/**
	 * @return the avgUsageHr08
	 */
	public Double getAvgUsageHr08() {
		return avgUsageHr08;
	}
	/**
	 * @param avgUsageHr08 the avgUsageHr08 to set
	 */
	public void setAvgUsageHr08(Double avgUsageHr08) {
		this.avgUsageHr08 = avgUsageHr08;
	}
	/**
	 * @return the effUsageHr08
	 */
	public Double getEffUsageHr08() {
		return effUsageHr08;
	}
	/**
	 * @param effUsageHr08 the effUsageHr08 to set
	 */
	public void setEffUsageHr08(Double effUsageHr08) {
		this.effUsageHr08 = effUsageHr08;
	}
	/**
	 * @return the selfUsageHr08
	 */
	public Double getSelfUsageHr08() {
		return selfUsageHr08;
	}
	/**
	 * @param selfUsageHr08 the selfUsageHr08 to set
	 */
	public void setSelfUsageHr08(Double selfUsageHr08) {
		this.selfUsageHr08 = selfUsageHr08;
	}
	/**
	 * @return the avgUsageHr09
	 */
	public Double getAvgUsageHr09() {
		return avgUsageHr09;
	}
	/**
	 * @param avgUsageHr09 the avgUsageHr09 to set
	 */
	public void setAvgUsageHr09(Double avgUsageHr09) {
		this.avgUsageHr09 = avgUsageHr09;
	}
	/**
	 * @return the effUsageHr09
	 */
	public Double getEffUsageHr09() {
		return effUsageHr09;
	}
	/**
	 * @param effUsageHr09 the effUsageHr09 to set
	 */
	public void setEffUsageHr09(Double effUsageHr09) {
		this.effUsageHr09 = effUsageHr09;
	}
	/**
	 * @return the selfUsageHr09
	 */
	public Double getSelfUsageHr09() {
		return selfUsageHr09;
	}
	/**
	 * @param selfUsageHr09 the selfUsageHr09 to set
	 */
	public void setSelfUsageHr09(Double selfUsageHr09) {
		this.selfUsageHr09 = selfUsageHr09;
	}
	/**
	 * @return the avgUsageHr10
	 */
	public Double getAvgUsageHr10() {
		return avgUsageHr10;
	}
	/**
	 * @param avgUsageHr10 the avgUsageHr10 to set
	 */
	public void setAvgUsageHr10(Double avgUsageHr10) {
		this.avgUsageHr10 = avgUsageHr10;
	}
	/**
	 * @return the effUsageHr10
	 */
	public Double getEffUsageHr10() {
		return effUsageHr10;
	}
	/**
	 * @param effUsageHr10 the effUsageHr10 to set
	 */
	public void setEffUsageHr10(Double effUsageHr10) {
		this.effUsageHr10 = effUsageHr10;
	}
	/**
	 * @return the selfUsageHr10
	 */
	public Double getSelfUsageHr10() {
		return selfUsageHr10;
	}
	/**
	 * @param selfUsageHr10 the selfUsageHr10 to set
	 */
	public void setSelfUsageHr10(Double selfUsageHr10) {
		this.selfUsageHr10 = selfUsageHr10;
	}
	/**
	 * @return the avgUsageHr11
	 */
	public Double getAvgUsageHr11() {
		return avgUsageHr11;
	}
	/**
	 * @param avgUsageHr11 the avgUsageHr11 to set
	 */
	public void setAvgUsageHr11(Double avgUsageHr11) {
		this.avgUsageHr11 = avgUsageHr11;
	}
	/**
	 * @return the effUsageHr11
	 */
	public Double getEffUsageHr11() {
		return effUsageHr11;
	}
	/**
	 * @param effUsageHr11 the effUsageHr11 to set
	 */
	public void setEffUsageHr11(Double effUsageHr11) {
		this.effUsageHr11 = effUsageHr11;
	}
	/**
	 * @return the selfUsageHr11
	 */
	public Double getSelfUsageHr11() {
		return selfUsageHr11;
	}
	/**
	 * @param selfUsageHr11 the selfUsageHr11 to set
	 */
	public void setSelfUsageHr11(Double selfUsageHr11) {
		this.selfUsageHr11 = selfUsageHr11;
	}
	/**
	 * @return the avgUsageHr12
	 */
	public Double getAvgUsageHr12() {
		return avgUsageHr12;
	}
	/**
	 * @param avgUsageHr12 the avgUsageHr12 to set
	 */
	public void setAvgUsageHr12(Double avgUsageHr12) {
		this.avgUsageHr12 = avgUsageHr12;
	}
	/**
	 * @return the effUsageHr12
	 */
	public Double getEffUsageHr12() {
		return effUsageHr12;
	}
	/**
	 * @param effUsageHr12 the effUsageHr12 to set
	 */
	public void setEffUsageHr12(Double effUsageHr12) {
		this.effUsageHr12 = effUsageHr12;
	}
	/**
	 * @return the selfUsageHr12
	 */
	public Double getSelfUsageHr12() {
		return selfUsageHr12;
	}
	/**
	 * @param selfUsageHr12 the selfUsageHr12 to set
	 */
	public void setSelfUsageHr12(Double selfUsageHr12) {
		this.selfUsageHr12 = selfUsageHr12;
	}
	/**
	 * @return the avgUsageHr13
	 */
	public Double getAvgUsageHr13() {
		return avgUsageHr13;
	}
	/**
	 * @param avgUsageHr13 the avgUsageHr13 to set
	 */
	public void setAvgUsageHr13(Double avgUsageHr13) {
		this.avgUsageHr13 = avgUsageHr13;
	}
	/**
	 * @return the effUsageHr13
	 */
	public Double getEffUsageHr13() {
		return effUsageHr13;
	}
	/**
	 * @param effUsageHr13 the effUsageHr13 to set
	 */
	public void setEffUsageHr13(Double effUsageHr13) {
		this.effUsageHr13 = effUsageHr13;
	}
	/**
	 * @return the selfUsageHr13
	 */
	public Double getSelfUsageHr13() {
		return selfUsageHr13;
	}
	/**
	 * @param selfUsageHr13 the selfUsageHr13 to set
	 */
	public void setSelfUsageHr13(Double selfUsageHr13) {
		this.selfUsageHr13 = selfUsageHr13;
	}
	/**
	 * @return the avgUsageHr14
	 */
	public Double getAvgUsageHr14() {
		return avgUsageHr14;
	}
	/**
	 * @param avgUsageHr14 the avgUsageHr14 to set
	 */
	public void setAvgUsageHr14(Double avgUsageHr14) {
		this.avgUsageHr14 = avgUsageHr14;
	}
	/**
	 * @return the effUsageHr14
	 */
	public Double getEffUsageHr14() {
		return effUsageHr14;
	}
	/**
	 * @param effUsageHr14 the effUsageHr14 to set
	 */
	public void setEffUsageHr14(Double effUsageHr14) {
		this.effUsageHr14 = effUsageHr14;
	}
	/**
	 * @return the selfUsageHr14
	 */
	public Double getSelfUsageHr14() {
		return selfUsageHr14;
	}
	/**
	 * @param selfUsageHr14 the selfUsageHr14 to set
	 */
	public void setSelfUsageHr14(Double selfUsageHr14) {
		this.selfUsageHr14 = selfUsageHr14;
	}
	/**
	 * @return the avgUsageHr15
	 */
	public Double getAvgUsageHr15() {
		return avgUsageHr15;
	}
	/**
	 * @param avgUsageHr15 the avgUsageHr15 to set
	 */
	public void setAvgUsageHr15(Double avgUsageHr15) {
		this.avgUsageHr15 = avgUsageHr15;
	}
	/**
	 * @return the effUsageHr15
	 */
	public Double getEffUsageHr15() {
		return effUsageHr15;
	}
	/**
	 * @param effUsageHr15 the effUsageHr15 to set
	 */
	public void setEffUsageHr15(Double effUsageHr15) {
		this.effUsageHr15 = effUsageHr15;
	}
	/**
	 * @return the selfUsageHr15
	 */
	public Double getSelfUsageHr15() {
		return selfUsageHr15;
	}
	/**
	 * @param selfUsageHr15 the selfUsageHr15 to set
	 */
	public void setSelfUsageHr15(Double selfUsageHr15) {
		this.selfUsageHr15 = selfUsageHr15;
	}
	/**
	 * @return the avgUsageHr16
	 */
	public Double getAvgUsageHr16() {
		return avgUsageHr16;
	}
	/**
	 * @param avgUsageHr16 the avgUsageHr16 to set
	 */
	public void setAvgUsageHr16(Double avgUsageHr16) {
		this.avgUsageHr16 = avgUsageHr16;
	}
	/**
	 * @return the effUsageHr16
	 */
	public Double getEffUsageHr16() {
		return effUsageHr16;
	}
	/**
	 * @param effUsageHr16 the effUsageHr16 to set
	 */
	public void setEffUsageHr16(Double effUsageHr16) {
		this.effUsageHr16 = effUsageHr16;
	}
	/**
	 * @return the selfUsageHr16
	 */
	public Double getSelfUsageHr16() {
		return selfUsageHr16;
	}
	/**
	 * @param selfUsageHr16 the selfUsageHr16 to set
	 */
	public void setSelfUsageHr16(Double selfUsageHr16) {
		this.selfUsageHr16 = selfUsageHr16;
	}
	/**
	 * @return the avgUsageHr17
	 */
	public Double getAvgUsageHr17() {
		return avgUsageHr17;
	}
	/**
	 * @param avgUsageHr17 the avgUsageHr17 to set
	 */
	public void setAvgUsageHr17(Double avgUsageHr17) {
		this.avgUsageHr17 = avgUsageHr17;
	}
	/**
	 * @return the effUsageHr17
	 */
	public Double getEffUsageHr17() {
		return effUsageHr17;
	}
	/**
	 * @param effUsageHr17 the effUsageHr17 to set
	 */
	public void setEffUsageHr17(Double effUsageHr17) {
		this.effUsageHr17 = effUsageHr17;
	}
	/**
	 * @return the selfUsageHr17
	 */
	public Double getSelfUsageHr17() {
		return selfUsageHr17;
	}
	/**
	 * @param selfUsageHr17 the selfUsageHr17 to set
	 */
	public void setSelfUsageHr17(Double selfUsageHr17) {
		this.selfUsageHr17 = selfUsageHr17;
	}
	/**
	 * @return the avgUsageHr18
	 */
	public Double getAvgUsageHr18() {
		return avgUsageHr18;
	}
	/**
	 * @param avgUsageHr18 the avgUsageHr18 to set
	 */
	public void setAvgUsageHr18(Double avgUsageHr18) {
		this.avgUsageHr18 = avgUsageHr18;
	}
	/**
	 * @return the effUsageHr18
	 */
	public Double getEffUsageHr18() {
		return effUsageHr18;
	}
	/**
	 * @param effUsageHr18 the effUsageHr18 to set
	 */
	public void setEffUsageHr18(Double effUsageHr18) {
		this.effUsageHr18 = effUsageHr18;
	}
	/**
	 * @return the selfUsageHr18
	 */
	public Double getSelfUsageHr18() {
		return selfUsageHr18;
	}
	/**
	 * @param selfUsageHr18 the selfUsageHr18 to set
	 */
	public void setSelfUsageHr18(Double selfUsageHr18) {
		this.selfUsageHr18 = selfUsageHr18;
	}
	/**
	 * @return the avgUsageHr19
	 */
	public Double getAvgUsageHr19() {
		return avgUsageHr19;
	}
	/**
	 * @param avgUsageHr19 the avgUsageHr19 to set
	 */
	public void setAvgUsageHr19(Double avgUsageHr19) {
		this.avgUsageHr19 = avgUsageHr19;
	}
	/**
	 * @return the effUsageHr19
	 */
	public Double getEffUsageHr19() {
		return effUsageHr19;
	}
	/**
	 * @param effUsageHr19 the effUsageHr19 to set
	 */
	public void setEffUsageHr19(Double effUsageHr19) {
		this.effUsageHr19 = effUsageHr19;
	}
	/**
	 * @return the selfUsageHr19
	 */
	public Double getSelfUsageHr19() {
		return selfUsageHr19;
	}
	/**
	 * @param selfUsageHr19 the selfUsageHr19 to set
	 */
	public void setSelfUsageHr19(Double selfUsageHr19) {
		this.selfUsageHr19 = selfUsageHr19;
	}
	/**
	 * @return the avgUsageHr20
	 */
	public Double getAvgUsageHr20() {
		return avgUsageHr20;
	}
	/**
	 * @param avgUsageHr20 the avgUsageHr20 to set
	 */
	public void setAvgUsageHr20(Double avgUsageHr20) {
		this.avgUsageHr20 = avgUsageHr20;
	}
	/**
	 * @return the effUsageHr20
	 */
	public Double getEffUsageHr20() {
		return effUsageHr20;
	}
	/**
	 * @param effUsageHr20 the effUsageHr20 to set
	 */
	public void setEffUsageHr20(Double effUsageHr20) {
		this.effUsageHr20 = effUsageHr20;
	}
	/**
	 * @return the selfUsageHr20
	 */
	public Double getSelfUsageHr20() {
		return selfUsageHr20;
	}
	/**
	 * @param selfUsageHr20 the selfUsageHr20 to set
	 */
	public void setSelfUsageHr20(Double selfUsageHr20) {
		this.selfUsageHr20 = selfUsageHr20;
	}
	/**
	 * @return the avgUsageHr21
	 */
	public Double getAvgUsageHr21() {
		return avgUsageHr21;
	}
	/**
	 * @param avgUsageHr21 the avgUsageHr21 to set
	 */
	public void setAvgUsageHr21(Double avgUsageHr21) {
		this.avgUsageHr21 = avgUsageHr21;
	}
	/**
	 * @return the effUsageHr21
	 */
	public Double getEffUsageHr21() {
		return effUsageHr21;
	}
	/**
	 * @param effUsageHr21 the effUsageHr21 to set
	 */
	public void setEffUsageHr21(Double effUsageHr21) {
		this.effUsageHr21 = effUsageHr21;
	}
	/**
	 * @return the selfUsageHr21
	 */
	public Double getSelfUsageHr21() {
		return selfUsageHr21;
	}
	/**
	 * @param selfUsageHr21 the selfUsageHr21 to set
	 */
	public void setSelfUsageHr21(Double selfUsageHr21) {
		this.selfUsageHr21 = selfUsageHr21;
	}
	/**
	 * @return the avgUsageHr22
	 */
	public Double getAvgUsageHr22() {
		return avgUsageHr22;
	}
	/**
	 * @param avgUsageHr22 the avgUsageHr22 to set
	 */
	public void setAvgUsageHr22(Double avgUsageHr22) {
		this.avgUsageHr22 = avgUsageHr22;
	}
	/**
	 * @return the effUsageHr22
	 */
	public Double getEffUsageHr22() {
		return effUsageHr22;
	}
	/**
	 * @param effUsageHr22 the effUsageHr22 to set
	 */
	public void setEffUsageHr22(Double effUsageHr22) {
		this.effUsageHr22 = effUsageHr22;
	}
	/**
	 * @return the selfUsageHr22
	 */
	public Double getSelfUsageHr22() {
		return selfUsageHr22;
	}
	/**
	 * @param selfUsageHr22 the selfUsageHr22 to set
	 */
	public void setSelfUsageHr22(Double selfUsageHr22) {
		this.selfUsageHr22 = selfUsageHr22;
	}
	/**
	 * @return the avgUsageHr23
	 */
	public Double getAvgUsageHr23() {
		return avgUsageHr23;
	}
	/**
	 * @param avgUsageHr23 the avgUsageHr23 to set
	 */
	public void setAvgUsageHr23(Double avgUsageHr23) {
		this.avgUsageHr23 = avgUsageHr23;
	}
	/**
	 * @return the effUsageHr23
	 */
	public Double getEffUsageHr23() {
		return effUsageHr23;
	}
	/**
	 * @param effUsageHr23 the effUsageHr23 to set
	 */
	public void setEffUsageHr23(Double effUsageHr23) {
		this.effUsageHr23 = effUsageHr23;
	}
	/**
	 * @return the selfUsageHr23
	 */
	public Double getSelfUsageHr23() {
		return selfUsageHr23;
	}
	/**
	 * @param selfUsageHr23 the selfUsageHr23 to set
	 */
	public void setSelfUsageHr23(Double selfUsageHr23) {
		this.selfUsageHr23 = selfUsageHr23;
	}
	/**
	 * @return the avgUsageHr24
	 */
	public Double getAvgUsageHr24() {
		return avgUsageHr24;
	}
	/**
	 * @param avgUsageHr24 the avgUsageHr24 to set
	 */
	public void setAvgUsageHr24(Double avgUsageHr24) {
		this.avgUsageHr24 = avgUsageHr24;
	}
	/**
	 * @return the effUsageHr24
	 */
	public Double getEffUsageHr24() {
		return effUsageHr24;
	}
	/**
	 * @param effUsageHr24 the effUsageHr24 to set
	 */
	public void setEffUsageHr24(Double effUsageHr24) {
		this.effUsageHr24 = effUsageHr24;
	}
	/**
	 * @return the selfUsageHr24
	 */
	public Double getSelfUsageHr24() {
		return selfUsageHr24;
	}
	/**
	 * @param selfUsageHr24 the selfUsageHr24 to set
	 */
	public void setSelfUsageHr24(Double selfUsageHr24) {
		this.selfUsageHr24 = selfUsageHr24;
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
