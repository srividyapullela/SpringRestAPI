package com.reliant.sm.model;

import java.util.ArrayList;
import java.util.List;

public class NeighbourhoodSettings extends UsageHistoryResponse{
	
	private String squareFootage;
	private String residence;
	private String ageOfHome;
	private String heatingUsed;
	private String typeOfResidence;
	private String haveAPool;
	private String processStatus;
	private List<String> sqFootList;
	private List<String> ageHomeList;
	
	
	public List<String> getSqFootList() {
		return sqFootList;
	}
	public void setSqFootList(List<String> sqFootList) {
		this.sqFootList = sqFootList;
	}
	public List<String> getAgeHomeList() {
		return ageHomeList;
	}
	public void setAgeHomeList(List<String> ageHomeList) {
		this.ageHomeList = ageHomeList;
	}
	public String getSquareFootage() {
		return squareFootage;
	}
	public void setSquareFootage(String squareFootage) {
		this.squareFootage = squareFootage;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public String getAgeOfHome() {
		return ageOfHome;
	}
	public void setAgeOfHome(String ageOfHome) {
		this.ageOfHome = ageOfHome;
	}
	public String getHeatingUsed() {
		return heatingUsed;
	}
	public void setHeatingUsed(String heatingUsed) {
		this.heatingUsed = heatingUsed;
	}
	public String getTypeOfResidence() {
		return typeOfResidence;
	}
	public void setTypeOfResidence(String typeOfResidence) {
		this.typeOfResidence = typeOfResidence;
	}
	public String getHaveAPool() {
		return haveAPool;
	}
	public void setHaveAPool(String haveAPool) {
		this.haveAPool = haveAPool;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	
	public List<String> addSqFootList(){
	    if (this.sqFootList == null) {
	      this.sqFootList = new ArrayList<String>();
	    }
	    return this.sqFootList;
	}
	public List<String> addAgeHomeList(){
	    if (this.ageHomeList == null) {
	      this.ageHomeList = new ArrayList<String>();
	    }
	    return this.ageHomeList;
	}
	

}
