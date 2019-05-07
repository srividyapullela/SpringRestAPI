package com.reliant.sm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.hibernate.dataobject.PCChildProfileDO;
import com.reliant.sm.dao.hibernate.dataobject.PCHomeAgeBandDO;
import com.reliant.sm.dao.hibernate.dataobject.PCSqFootBandDO;
import com.reliant.sm.model.NeighbourhoodSettings;
import com.reliant.sm.util.CommonConstants;
import com.reliant.sm.util.LoggerUtil;

/**
 * @author bbachin1
 * 
 */
@Component
public class NeighbourhoodSettingsServiceImplHelper implements CommonConstants{
	
	private static LoggerUtil logger = LoggerUtil.getInstance(NeighbourhoodSettingsServiceImplHelper.class);
	
	public NeighbourhoodSettings processNeighbourhoodSettings(List<PCHomeAgeBandDO> pcHomeAgeBandList,List<PCSqFootBandDO> pcSqFootBandList,
			List<PCChildProfileDO> pcChildProfList, NeighbourhoodSettings neighbourhoodSettings){
		
		if(null != pcChildProfList && pcChildProfList.size() > 0){
			
			for(PCChildProfileDO childProfDO : pcChildProfList){
				if(null != childProfDO && null != childProfDO.getPcChildProfileId()){
					if(StringUtils.equalsIgnoreCase(childProfDO.getPcChildProfileId().getParamName(), PC_SETTINGS_PARAM_NAME_POOL)){
						neighbourhoodSettings.setHaveAPool(childProfDO.getPcChildProfileId().getParamValue());
					}	
					if(StringUtils.equalsIgnoreCase(childProfDO.getPcChildProfileId().getParamName(), PC_SETTINGS_PARAM_NAME_HEAT_TYPE)){
						neighbourhoodSettings.setHeatingUsed(childProfDO.getPcChildProfileId().getParamValue());
					}
					if(StringUtils.equalsIgnoreCase(childProfDO.getPcChildProfileId().getParamName(), PC_SETTINGS_PARAM_NAME_RENTOWN_TYPE)){
						neighbourhoodSettings.setResidence(childProfDO.getPcChildProfileId().getParamValue());
					}
					if(StringUtils.equalsIgnoreCase(childProfDO.getPcChildProfileId().getParamName(), PC_SETTINGS_PARAM_NAME_SQFT)){
						String sqFootRange = getSqFootRangeFromPCSqFootBandList(pcSqFootBandList,childProfDO.getPcChildProfileId().getParamValue());
						neighbourhoodSettings.setSquareFootage(sqFootRange);
					}
					if(StringUtils.equalsIgnoreCase(childProfDO.getPcChildProfileId().getParamName(), PC_SETTINGS_PARAM_NAME_HOME_AGE)){
						String homeAge = getAgeHomeRangeFromPCHomeAgeBandList(pcHomeAgeBandList,childProfDO.getPcChildProfileId().getParamValue());
						neighbourhoodSettings.setAgeOfHome(homeAge);
					}
					if(StringUtils.equalsIgnoreCase(childProfDO.getPcChildProfileId().getParamName(), PC_SETTINGS_PARAM_NAME_RES_TYPE)){
						neighbourhoodSettings.setTypeOfResidence(childProfDO.getPcChildProfileId().getParamValue());
					}
				}
			}
			populateAgeHomeAndSqFootList(neighbourhoodSettings,pcHomeAgeBandList,pcSqFootBandList);
			neighbourhoodSettings.setDataAvailable(true);
		}else{
			logger.info("NO CHILD PROFILE LIST FOUND");
		}
		return neighbourhoodSettings;
	}
	
	
	private String getSqFootRangeFromPCSqFootBandList(List<PCSqFootBandDO> pcSqFootBandList, String sqFootageId){
		
		if(null != pcSqFootBandList && pcSqFootBandList.size() >0){
			for(PCSqFootBandDO band : pcSqFootBandList){
				if(StringUtils.equalsIgnoreCase(sqFootageId, band.getSqFtBandID())){
					return band.getSqFtBandID()+":"+band.getSqFtStart()+" - "+band.getSqFtEnd();
				}
			}
		}
		return sqFootageId;
	}
	
	
	private String getAgeHomeRangeFromPCHomeAgeBandList(List<PCHomeAgeBandDO> pcHomeAgeBandList, String homeAgeId){
		
		if(null != pcHomeAgeBandList && pcHomeAgeBandList.size() >0){
			for(PCHomeAgeBandDO band : pcHomeAgeBandList){
				if(StringUtils.equalsIgnoreCase(homeAgeId, band.getHomeAgeBandID())){
					return band.getHomeAgeBandID()+":"+band.getHomeAgeStart()+" - "+band.getHomeAgeEnd();
				}
			}
		}
		return homeAgeId;
	}
	
	
	private void populateAgeHomeAndSqFootList(NeighbourhoodSettings neighbourhoodSettings,
			List<PCHomeAgeBandDO> pcHomeAgeBandList,List<PCSqFootBandDO> pcSqFootBandList){
		
		if(null != pcSqFootBandList && pcSqFootBandList.size() >0){
			for(PCSqFootBandDO band : pcSqFootBandList){
				neighbourhoodSettings.addSqFootList().add(band.getSqFtBandID()+":"+band.getSqFtStart()+" - "+band.getSqFtEnd());
			}
		}
		if(null != pcHomeAgeBandList && pcHomeAgeBandList.size() >0){
			for(PCHomeAgeBandDO band : pcHomeAgeBandList){
				neighbourhoodSettings.addAgeHomeList().add(band.getHomeAgeBandID()+":"+band.getHomeAgeStart()+" - "+band.getHomeAgeEnd());
			}
		}
	}

}
