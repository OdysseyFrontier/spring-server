package com.enjoyTrip.OdysseyFrontiers.hotplace.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Gugun;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Sido;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.HotPlaceDto;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.HotPlaceHitDto;

public interface HotPlaceService {
	List<Sido> listSidos() throws Exception;

    List<Gugun> listGuguns(int sidoCode) throws Exception;

    int getSidoCode(String sidocode) throws Exception;
    int writeHotPlace(HotPlaceDto hotPlaceDto) throws Exception;
    void setFilePath(HotPlaceDto hotPlaceDto) throws Exception;
    
    List<HotPlaceDto> selectAttr(Map<String, Object> map) throws Exception;
    HotPlaceDto getHotPlace(Map<String, Object> map) throws Exception;
    
    int createOrUpdateHit(HotPlaceHitDto hotplaceHitDto) throws Exception;
    
    HotPlaceDto getModifyHotPlace(int contentId) throws Exception;
    int modifyHotPlcae(HotPlaceDto hotPlaceDto) throws Exception;
    
    void createLike(Map<String, String> map) throws Exception;
    void deleteLike(Map<String, String> map) throws Exception;
    
    void deleteHotPlace(int contentId) throws Exception;
}
