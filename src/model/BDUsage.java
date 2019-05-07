package com.reliant.sm.model;

import java.util.ArrayList;
import java.util.List;


public class BDUsage extends Temperature{
	
	protected List<BDSlice> sliceList;
	

	public List<BDSlice> getSliceList() {
		if(this.sliceList == null){
			return new ArrayList<BDSlice>();
		}
		return sliceList;
	}

	public void setSliceList(List<BDSlice> sliceList) {
		this.sliceList = sliceList;
	}
	
	public List<BDSlice> addSliceToList(){
	    if (this.sliceList == null) {
	      this.sliceList = new ArrayList<BDSlice>();
	    }
	    return this.sliceList;
	}
	
}
