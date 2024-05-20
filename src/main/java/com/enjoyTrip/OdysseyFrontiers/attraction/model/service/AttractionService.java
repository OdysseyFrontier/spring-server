package com.enjoyTrip.OdysseyFrontiers.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.AttractionDetail;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.AttractionInfo;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Gugun;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Sido;

public interface AttractionService {
    List<Sido> listSidos() throws Exception;

	List<Gugun> listGuguns(int sidoCode) throws Exception;
	
	List<AttractionInfo> listAttr(int contentTypeId, int sidoCode, int gugunCode, String keyword, double nowLocLng, double nowLocLat ) throws Exception;

    List<AttractionInfo> listAttr(int contentTypeId) throws SQLException;

    AttractionInfo getAttraction(long contentId);
}
