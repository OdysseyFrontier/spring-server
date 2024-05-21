package com.enjoyTrip.OdysseyFrontiers.stat.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.enjoyTrip.OdysseyFrontiers.stat.model.dto.StatDto;

@Mapper
public interface StatMapper {
	List<StatDto> getPopularAttractions() throws SQLException;
	List<StatDto> getMemberRegistrationStats() throws SQLException;
	List<StatDto> getAttractionCountByRegion() throws SQLException;
	List<StatDto> getActivityByTime() throws SQLException;
}
