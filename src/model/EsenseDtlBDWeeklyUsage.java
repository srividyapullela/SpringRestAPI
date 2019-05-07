package com.reliant.sm.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.util.DateUtil;
import com.reliant.sm.util.UsageHistoryUtil;


public class EsenseDtlBDWeeklyUsage extends UsageHistoryResponse{
	
	private BDUsage bdUsage;
	private String totalCost;
	private String totalUsage;
	private String weekOfMessage;
	private List<String> highTempList;
	private List<String> lowTempList;
	private int month;
	private String dayAndYear;
	
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
				bd = bd.add(new BigDecimal(("null" != bdSlice.getCost() && StringUtils.isNotBlank(bdSlice.getCost()))?bdSlice.getCost():"0.0"));
			}
		}
		this.totalCost = UsageHistoryUtil.getRequiredScaleForInputString(bd.toString(),2);
	}
	
	public String getTotalUsage() {
		return totalUsage;
	}
	
	public void setTotalUsage() {
		BigDecimal bd = new BigDecimal("0.0");
		if(null != this.bdUsage.sliceList && this.bdUsage.sliceList.size() >0){
			for(BDSlice bdSlice : this.bdUsage.sliceList){
				bd = bd.add(new BigDecimal(("null" != bdSlice.getUsage() && StringUtils.isNotBlank(bdSlice.getUsage()))?bdSlice.getUsage():"0.0"));
			}
		}
		this.totalUsage = UsageHistoryUtil.getRequiredScaleForInputString(bd.toString(),1);
	}
	
	public String getWeekOfMessage() {
		return weekOfMessage;
	}
	
	public void setWeekOfMessage(String weekOfMessage) {
		StringBuffer weekMessage = new StringBuffer("");
		Date date = DateUtil.getDate(weekOfMessage, "MM/dd/yyyy");
		weekMessage.append(DateUtil.getFormatedDate(date, "MMMM dd, yyyy"));
		this.weekOfMessage = weekMessage.toString();
		this.month = DateUtil.getMonthInt(date);
		this.dayAndYear = DateUtil.getFormatedDate(date, "dd, yyyy");
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
	
	public List<String> getHighTempList() {
		return highTempList;
	}
	public void setHighTempList(List<String> highTempList) {
		this.highTempList = highTempList;
	}
	public List<String> getLowTempList() {
		return lowTempList;
	}
	public void setLowTempList(List<String> lowTempList) {
		this.lowTempList = lowTempList;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getDayAndYear() {
		return dayAndYear;
	}
	public void setDayAndYear(String dayAndYear) {
		this.dayAndYear = dayAndYear;
	}
	
	
}
