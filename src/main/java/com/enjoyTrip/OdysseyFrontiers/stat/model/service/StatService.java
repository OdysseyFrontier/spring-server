package com.enjoyTrip.OdysseyFrontiers.stat.model.service;

import java.util.List;

import com.enjoyTrip.OdysseyFrontiers.stat.model.dto.StatDto;

public interface StatService {
	List<StatDto> getPopularAttractions() throws Exception;
	List<StatDto> getMemberRegistrationStats() throws Exception;
	List<StatDto> getAttractionCountByRegion() throws Exception;
	List<StatDto> getActivityByTime() throws Exception;
}
