package com.enjoyTrip.OdysseyFrontiers.hotplace.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.Gugun2;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.HotPlaceDto;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.HotPlaceHitDto;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.Sido2;

public interface HotPlaceService {
	List<Sido2> listSidos() throws Exception;

    List<Gugun2> listGuguns(int sidoCode) throws Exception;

    int writeHotPlace(HotPlaceDto hotPlaceDto) throws Exception;
    
    List<HotPlaceDto> selectAttr(Map<String, Object> map) throws Exception;
    HotPlaceDto getHotPlace(Map<String, Object> map) throws Exception;
    
    int createOrUpdateHit(HotPlaceHitDto hotplaceHitDto) throws Exception;
    
    int modifyHotPlcae(HotPlaceDto hotPlaceDto) throws Exception;
    
    void createLike(Map<String, String> map) throws Exception;
    void deleteLike(Map<String, String> map) throws Exception;
    
    void deleteHotPlace(int contentId) throws Exception;
}
