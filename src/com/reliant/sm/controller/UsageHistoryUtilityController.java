package com.reliant.sm.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reliant.sm.model.EsenseDetailDailyData;
import com.reliant.sm.util.LoggerUtil;
import com.reliant.sm.util.UsageHistoryUtil;

/**
 * @author bbachin1
 * 
 */
@RestController
@RequestMapping(value = "/utility")
public class UsageHistoryUtilityController {

	private static LoggerUtil logger = LoggerUtil.getInstance(UsageHistoryUtilityController.class);
	
	@RequestMapping(value = "/userDetails/{userLoginId}", method = RequestMethod.GET)
    public EsenseDetailDailyData getUserDetails(@PathVariable String userLoginId) {
		//EsenseDetailDailyData esenseDetailDailyData = esenseDetailUsageHistoryService.getDailyUsageData(usageHistoryRequest);
		
		return null;
    }
	
	@RequestMapping(value = "/prevYearWeekNum/{previousWeek}", method = RequestMethod.GET)
    public String getPreviousYearWeekNum(@PathVariable String previousWeek) {
		logger.info("YEAR WEEK NUMBER FROM INPUT::::::"+previousWeek);
		return UsageHistoryUtil.getPreviousYearWeek(previousWeek);
    }
	
	@RequestMapping(value = "/nextYearWeekNum/{nextWeek}", method = RequestMethod.GET)
    public String getNextYearWeekNum(@PathVariable String nextWeek) {
		logger.info("YEAR WEEK NUMBER FROM INPUT::::::"+nextWeek);
		return UsageHistoryUtil.getNextYearWeek(nextWeek);
    }
	
	@RequestMapping(value = "/prevDay/{prevDay}", method = RequestMethod.GET)
    public String getPreviousDay(@PathVariable String prevDay) {
		logger.info("ACTUAL DAY NUMBER FROM INPUT::::::"+prevDay);
		return UsageHistoryUtil.getPrevActualDay(getDayWithSlashes(prevDay));
    }
	
	
	@RequestMapping(value = "/nextDay/{nextDay}", method = RequestMethod.GET)
    public String getNextDay(@PathVariable String nextDay) {
		logger.info("ACTUAL DAY NUMBER FROM INPUT::::::"+nextDay);
		return UsageHistoryUtil.getNextActualDay(getDayWithSlashes(nextDay));
    }
	
	@RequestMapping(value = "/yearWeekNum/{actualDay}", method = RequestMethod.GET)
    public String getYearWeekNumFromActualDay(@PathVariable String actualDay) {
		logger.info("ACTUAL DAY NUMBER FROM INPUT::::::"+actualDay);
		return UsageHistoryUtil.getYearWeekNumFromActualDay(getDayWithSlashes(actualDay));
    }
	
	private String getDayWithSlashes(String day){
		
		if(StringUtils.isNotBlank(day)){
			return StringUtils.substring(day, 0, 2)+"/"+StringUtils.substring(day, 2, 4)+"/"+
					StringUtils.substring(day, 4, 8);
		}
		return "";
	}
}
