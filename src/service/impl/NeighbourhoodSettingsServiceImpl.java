package com.reliant.sm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reliant.sm.dao.SmartMainHibernateDAO;
import com.reliant.sm.dao.hibernate.dataobject.PCChildProfileDO;
import com.reliant.sm.dao.hibernate.dataobject.PCHomeAgeBandDO;
import com.reliant.sm.dao.hibernate.dataobject.PCMasterProfileDO;
import com.reliant.sm.dao.hibernate.dataobject.PCSqFootBandDO;
import com.reliant.sm.exception.SmartMainDAOException;
import com.reliant.sm.model.NeighbourhoodSettings;
import com.reliant.sm.model.UsageHistoryRequest;
import com.reliant.sm.model.UsageHistoryResponse;
import com.reliant.sm.service.NeighbourhoodSettingsService;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;

/**
 * @author bbachin1
 * 
 */

@Component
public class NeighbourhoodSettingsServiceImpl extends CommonUsageHistoryHelper implements NeighbourhoodSettingsService{
	
	private static LoggerUtil logger = LoggerUtil.getInstance(NeighbourhoodSettingsServiceImpl.class);
	
	@Autowired
	private SmartMainHibernateDAO smartMainHibernateDAO;
	
	@Autowired
	private NeighbourhoodSettingsServiceImplHelper neighbourhoodSettingsServiceImplHelper;

	@SuppressWarnings("unchecked")
	@Override
	public NeighbourhoodSettings getNieghbourhoodSettings(UsageHistoryRequest usageHistoryRequest) {
		
		NeighbourhoodSettings neighbourhoodSettings = new NeighbourhoodSettings();
		try{
			String queryName = GET_ALL_HOME_AGE_BANDS_QRY;
			List<PCHomeAgeBandDO> pcHomeAgeBandList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, new String[0],new Object[0]);
			queryName = GET_ALL_SQ_FOOT_BANDS_QRY;
			List<PCSqFootBandDO> pcSqFootBandList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName, new String[0],new Object[0]);
			
			queryName = IS_DATA_AVAIL_FOR_PC_MASTER_PROFILE_QRY;
			List<PCMasterProfileDO> pcMasterProfList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName,UsageHistoryUtil.getParamNamesForInput("pcmasterprofileqry"),
					UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest, "pcmasterprofileqry"));
			if(null != pcMasterProfList && pcMasterProfList.size() >0){
				logger.info("MASTER PROFILE FOUND FOR THE ESIID:::"+usageHistoryRequest.getEsiid()+"::AND PC MASTER PC ID::"+pcMasterProfList.get(0).getId());
				neighbourhoodSettings.setProcessStatus(pcMasterProfList.get(0).getProcessStatus());
				queryName = GET_PC_CHILD_PROFILE_DATA_QRY;
				Object[] paramValAry = {pcMasterProfList.get(0).getId()}; 
				List<PCChildProfileDO> pcChildProfList = smartMainHibernateDAO.listQuery(usageHistoryRequest, queryName,UsageHistoryUtil.getParamNamesForInput("pcchildprofileqry"),
						paramValAry);
				logger.info("CHILD PROFILE FOUND FOR THE ESIID:::"+usageHistoryRequest.getEsiid()+"::AND LIST SIZE::"+pcChildProfList.size());
				neighbourhoodSettingsServiceImplHelper.processNeighbourhoodSettings(pcHomeAgeBandList,pcSqFootBandList,pcChildProfList,neighbourhoodSettings);
			}else{
				neighbourhoodSettings.setErrorMessage("NO MASTER PROFILE DATA FOUND FOR THE ESIID:::::::"+usageHistoryRequest.getEsiid());
			}
			
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE NEIGHBOURHOOD SETTINGS:::::", ex);
			neighbourhoodSettings.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return neighbourhoodSettings;
	}

	@Override
	public UsageHistoryResponse updateNeighbourhoodSettings(UsageHistoryRequest usageHistoryRequest) {
		
		UsageHistoryResponse response = new UsageHistoryResponse();
		try{
			String queryName = GET_PC_MASTER_PROFILE_PC_ID_QRY;
			Number masterProfilePCID = smartMainHibernateDAO.singleResultQuery(usageHistoryRequest, queryName,UsageHistoryUtil.getParamNamesForInput("pcmasterprofileqry"),
					UsageHistoryUtil.getParamValuesForInput(usageHistoryRequest, "pcmasterprofileqry"));
			logger.info("PC MASTER PROFILE PC ID:::::::::"+masterProfilePCID+"::FOR THE ESIID:::"+usageHistoryRequest.getEsiid());
			if(null != masterProfilePCID && masterProfilePCID.intValue() > 0){
				queryName = UPDATE_PC_MASTER_PROFILE_PC_ID_QRY;
				String[] paramNameAry = {"pcid"};
				Object[] paramValAry = {masterProfilePCID};
				boolean updateVal = smartMainHibernateDAO.updateQuery(usageHistoryRequest, queryName,paramNameAry,paramValAry);
				logger.info("UPDATED THE MASTER PROFILE PC ID:::"+updateVal+":::FOR PC ID::::"+masterProfilePCID);
				if(updateVal){
					boolean updchildVal = updateChildProfileData(usageHistoryRequest,masterProfilePCID);
					logger.info("UPDATED CHILD PROFILE AND MASTER PROFILE DATA:::"+updchildVal);
				}else{
					logger.info("NOT ABLE TO UPDATE FOR THE PC ID::"+masterProfilePCID+"::ESIID:::"+usageHistoryRequest.getEsiid());
					response.setErrorMessage("NOT ABLE TO UPDATE FOR THE PC ID::"+masterProfilePCID+"::ESIID:::"+usageHistoryRequest.getEsiid());
				}
			}else{
				logger.info("PC MASTER PROFILE PC ID NOT FOUND FOR THE:::::"+usageHistoryRequest.getEsiid());
				response.setErrorMessage("PC MASTER PROFILE PC ID NOT FOUND FOR THE:::::"+usageHistoryRequest.getEsiid());
			}
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE UPDATING THE NEIGHBOURHOOD SETTINGS:::::", ex);
			response.setErrorMessage(UsageHistoryUtil.getErrorMessage(ex));
		}
		return response;
	}
	
	
	private boolean updateChildProfileData(UsageHistoryRequest usageHistoryRequest, Number masterProfilePCID) throws SmartMainDAOException{
		
		String queryName = UPDATE_PC_CHILD_PROFILE_PC_ID_QRY;
		String[] childParamNameAry = {"paramVal","paramName","pcid"};
		Object[] childParamValSqFtAry = {usageHistoryRequest.getSqFootVal(),PC_SETTINGS_PARAM_NAME_SQFT,masterProfilePCID};
		boolean updateVal = smartMainHibernateDAO.updateQuery(usageHistoryRequest, queryName,childParamNameAry,childParamValSqFtAry);
		
		Object[] childParamValHomeAgeAry = {usageHistoryRequest.getHomeAgeVal(),PC_SETTINGS_PARAM_NAME_HOME_AGE,masterProfilePCID};
		updateVal = smartMainHibernateDAO.updateQuery(usageHistoryRequest, queryName,childParamNameAry,childParamValHomeAgeAry);
		
		Object[] childParamValHeatTypeAry = {usageHistoryRequest.getHeatingUsedVal(),PC_SETTINGS_PARAM_NAME_HEAT_TYPE,masterProfilePCID};
		updateVal = smartMainHibernateDAO.updateQuery(usageHistoryRequest, queryName,childParamNameAry,childParamValHeatTypeAry);
		
		Object[] childParamValResTypeAry = {usageHistoryRequest.getResidenseTypeVal(),PC_SETTINGS_PARAM_NAME_RES_TYPE,masterProfilePCID};
		updateVal = smartMainHibernateDAO.updateQuery(usageHistoryRequest, queryName,childParamNameAry,childParamValResTypeAry);
		
		Object[] childParamValRentAry = {usageHistoryRequest.getResidenseVal(),PC_SETTINGS_PARAM_NAME_RENTOWN_TYPE,masterProfilePCID};
		updateVal = smartMainHibernateDAO.updateQuery(usageHistoryRequest, queryName,childParamNameAry,childParamValRentAry);
		
		Object[] childParamValPoolAry = {usageHistoryRequest.getPoolTypeVal(),PC_SETTINGS_PARAM_NAME_POOL,masterProfilePCID};
		updateVal = smartMainHibernateDAO.updateQuery(usageHistoryRequest, queryName,childParamNameAry,childParamValPoolAry);
		
		return updateVal;
	}

}
