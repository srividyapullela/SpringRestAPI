package com.reliant.sm.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ClassicData {
	
	private List<MonthAndUsage> currentMonthAndUsage;
	private List<MonthAndUsage> previousMonthAndUsage;
	private List<MonthAndUsage> oldMonthAndUsage;
	private boolean currUsageAvailable = false;
	private boolean prevUsageAvailable = false;
	private boolean dataAvailable = false;
	private String errorMessage;
	
	public List<MonthAndUsage> getCurrentMonthAndUsage() {
		return currentMonthAndUsage;
	}
	public void setCurrentMonthAndUsage(List<MonthAndUsage> currentMonthAndUsage) {
		this.currentMonthAndUsage = currentMonthAndUsage;
	}
	public List<MonthAndUsage> getPreviousMonthAndUsage() {
		return previousMonthAndUsage;
	}
	public void setPreviousMonthAndUsage(List<MonthAndUsage> previousMonthAndUsage) {
		this.previousMonthAndUsage = previousMonthAndUsage;
	}
	public List<MonthAndUsage> getOldMonthAndUsage() {
		return oldMonthAndUsage;
	}
	public void setOldMonthAndUsage(List<MonthAndUsage> oldMonthAndUsage) {
		this.oldMonthAndUsage = oldMonthAndUsage;
	}
	
	
	public boolean isDataAvailable() {
		return dataAvailable;
	}
	public void setDataAvailable(boolean dataAvailable) {
		this.dataAvailable = dataAvailable;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<MonthAndUsage> addCurrentMonthAndUsageList(){
	    if (this.currentMonthAndUsage == null) {
	      this.currentMonthAndUsage = new ArrayList<MonthAndUsage>();
	    }
	    return this.currentMonthAndUsage;
	}
	
	public List<MonthAndUsage> addPreviousMonthAndUsageList(){
	    if (this.previousMonthAndUsage == null) {
	      this.previousMonthAndUsage = new ArrayList<MonthAndUsage>();
	    }
	    return this.previousMonthAndUsage;
	}
	
	public List<MonthAndUsage> addOldMonthAndUsageList(){
	    if (this.oldMonthAndUsage == null) {
	      this.oldMonthAndUsage = new ArrayList<MonthAndUsage>();
	    }
	    return this.oldMonthAndUsage;
	}
	
	public boolean isCurrUsageAvailable() {
		return currUsageAvailable;
	}
	
	public void setCurrUsageAvailable() {
		if(null != this.currentMonthAndUsage){
			for(MonthAndUsage month : this.currentMonthAndUsage){
				if(StringUtils.isNotBlank(month.getUsage())){
					this.currUsageAvailable = true;
					break;
				}
			}
		}
	}
	
	public boolean isPrevUsageAvailable() {
		return prevUsageAvailable;
	}
	
	public void setPrevUsageAvailable() {
		if(null != this.previousMonthAndUsage){
			for(MonthAndUsage month : this.previousMonthAndUsage){
				if(StringUtils.isNotBlank(month.getUsage())){
					this.prevUsageAvailable = true;
					break;
				}
			}
		}
	}
	

}
