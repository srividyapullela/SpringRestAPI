package com.reliant.sm.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.reliant.sm.util.UsageHistoryUtil;


public class BDSlice {
	
	private String id;
	private String name;
	private String description;
	private String usage = "0.0";
	private String cost = "0.0";
	private String usagePercentage;
	private String costPercentage;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	public void addCostToSliceCost(String cost){
		if("null" != cost && StringUtils.isNotBlank(cost)){
			BigDecimal bd = new BigDecimal(this.cost);
			this.cost = UsageHistoryUtil.getRequiredScaleForInputString(bd.add(new BigDecimal(cost)).toString(),2);
		}
	}
	
	public void addUsageToSliceUsage(String usage){
		if("null" != usage && StringUtils.isNotBlank(usage)){
			BigDecimal bd = new BigDecimal(this.usage);
			this.usage = UsageHistoryUtil.getRequiredScaleForInputString(bd.add(new BigDecimal(usage)).toString(),1);
		}
	}
	public String getUsagePercentage() {
		return usagePercentage;
	}
	public void setUsagePercentage(String usagePercentage) {
		this.usagePercentage = usagePercentage;
	}
	public String getCostPercentage() {
		return costPercentage;
	}
	public void setCostPercentage(String costPercentage) {
		this.costPercentage = costPercentage;
	}
	
}
