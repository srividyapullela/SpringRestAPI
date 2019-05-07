package com.reliant.sm.model;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.util.DateUtil;

public class BreakDownWeekData {
	
	private BDUsage bdUsage;
	private String totalCost;
	private String totalUsage;
	private String yearWeekNum;
	private String weekOfMessage;
	private List<Temperature> tempList;
	
	
	
	public List<Temperature> getTempList() {
		return tempList;
	}
	public void setTempList(List<Temperature> tempList) {
		this.tempList = tempList;
	}
	public String getYearWeekNum() {
		return yearWeekNum;
	}
	public void setYearWeekNum(String yearWeekNum) {
		this.yearWeekNum = yearWeekNum;
	}
	public BDUsage getBdUsage() {
		return bdUsage;
	}
	public void setBdUsage(BDUsage bdUsage) {
		this.bdUsage = bdUsage;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost() {
		BigDecimal bd = new BigDecimal("0.0");
		if(null != this.bdUsage.sliceList && this.bdUsage.sliceList.size() >0){
			for(BDSlice bdSlice : this.bdUsage.sliceList){
				bd = bd.add(new BigDecimal(("null" != bdSlice.getCost() && StringUtils.isNotBlank(bdSlice.getCost()))?bdSlice.getCost():"0.00"));
			}
		}
		this.totalCost = bd.toString();
	}
	
	public String getTotalUsage() {
		return totalUsage;
	}
	
	public void setTotalUsage() {
		BigDecimal bd = new BigDecimal("0.0");
		if(null != this.bdUsage.sliceList && this.bdUsage.sliceList.size() >0){
			for(BDSlice bdSlice : this.bdUsage.sliceList){
				bd = bd.add(new BigDecimal(("null" != bdSlice.getUsage() && StringUtils.isNotBlank(bdSlice.getUsage()))?bdSlice.getUsage():"0.00"));
			}
		}
		this.totalUsage = bd.toString();
	}
	
	public String getWeekOfMessage() {
		return weekOfMessage;
	}
	
	public void setWeekOfMessage(String weekOfMessage) {
		StringBuffer weekMessage = new StringBuffer("Week Of ");
		weekMessage.append(DateUtil.getFormatedDate(DateUtil.getDate(weekOfMessage, "yyyy-MM-dd"), "MMMM dd, yyyy"));
		this.weekOfMessage = weekMessage.toString();
	}
	
	
	public void calculatePercentage(){
		
		for(BDSlice bdSlice : this.bdUsage.sliceList){
			if("null" != bdSlice.getUsage() && StringUtils.isNotBlank(bdSlice.getUsage())){
				BigDecimal bd1 = new BigDecimal(bdSlice.getUsage());
				bd1 = bd1.multiply(new BigDecimal("100"));
				float usgPercentage = (bd1.floatValue())/((new BigDecimal(this.totalUsage)).floatValue());
				bdSlice.setUsagePercentage(String.valueOf(usgPercentage));
			}
			
			if("null" != bdSlice.getCost() && StringUtils.isNotBlank(bdSlice.getCost())){
				BigDecimal bd2 = new BigDecimal(bdSlice.getCost());
				bd2 = bd2.multiply(new BigDecimal("100"));
				float costPercentage = (bd2.floatValue())/((new BigDecimal(this.totalCost)).floatValue());
				bdSlice.setCostPercentage(String.valueOf(costPercentage));
			}
		}
	}

}
