package com.reliant.sm.model;

import java.util.List;

public class BDYearTempData extends GenericResponse{
	
	private List<Temperature> tempList;

	public List<Temperature> getTempList() {
		return tempList;
	}

	public void setTempList(List<Temperature> tempList) {
		this.tempList = tempList;
	}
	
}
