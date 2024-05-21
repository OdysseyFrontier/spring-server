package com.enjoyTrip.OdysseyFrontiers.stat.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enjoyTrip.OdysseyFrontiers.stat.model.dto.StatDto;
import com.enjoyTrip.OdysseyFrontiers.stat.model.mapper.StatMapper;

@Service
public class StatServiceImpl implements StatService {
	private final StatMapper statMapper;


    private StatServiceImpl(StatMapper statMapper) {
        this.statMapper = statMapper;
    }
	

	@Override
	public List<StatDto> getPopularAttractions() throws Exception {
        return statMapper.getPopularAttractions();
    }
	
	@Override
	public List<StatDto> getMemberRegistrationStats() throws Exception {
        return statMapper.getMemberRegistrationStats();
    }
	
	@Override
	public List<StatDto> getAttractionCountByRegion() throws Exception {
        return statMapper.getAttractionCountByRegion();
    }
	
	@Override
	public List<StatDto> getActivityByTime() throws Exception {
        return statMapper.getActivityByTime();
    }

}
