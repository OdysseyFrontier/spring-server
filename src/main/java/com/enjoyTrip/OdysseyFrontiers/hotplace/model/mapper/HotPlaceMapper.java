package com.enjoyTrip.OdysseyFrontiers.hotplace.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Gugun;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Sido;
import org.apache.ibatis.annotations.Mapper;

import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.HotPlaceDto;


@Mapper
public interface HotPlaceMapper {
    List<Sido> listSidos() throws SQLException;

    List<Gugun> listGuguns(int sidoCode) throws SQLException;

    int writeHotPlaceInfo(HotPlaceDto hotPlaceDto) throws SQLException;
    void writeHotPlaceDetail(HotPlaceDto hotPlaceDto) throws SQLException;
    void writeHotPlaceDescription(HotPlaceDto hotPlaceDto) throws SQLException;
    
    // 사진 저장 어떻게 할지 고민하기
    void writeHotPlaceAttachments(HotPlaceDto hotPlaceDto) throws SQLException;
    
    List<HotPlaceDto> selectAttr(Map<String, Object> map) throws SQLException;
    HotPlaceDto getHotPlace(Map<String, Object> map) throws SQLException;
    
    Optional<Integer> getRecentMemberHit(int contentId, Long memberId) throws SQLException;
    int createHit(int contentId, Long memberId) throws SQLException;
    int updateHit(int contentId, Long memberId) throws SQLException;
    
//    int modifyHotPlcae(HotPlaceDto hotPlaceDto) throws SQLException;
    int modifyHotPlcaeInfo(HotPlaceDto hotPlaceDto) throws SQLException;
    int modifyHotPlcaeDetail(HotPlaceDto hotPlaceDto) throws SQLException;
    int modifyHotPlcaeDescription(HotPlaceDto hotPlaceDto) throws SQLException;
    
    // 사진 어떻게 바꿀지 고민
    int modifyHotPlcaeAttachments(HotPlaceDto hotPlaceDto) throws SQLException;
    
    void createLike(Map<String, String> map) throws SQLException;
    void deleteLike(Map<String, String> map) throws SQLException;
    
//    int deleteHotPlace(int contentId) throws SQLException;
    void deleteHotPlaceInfo(int contentId) throws SQLException;
    void deleteHotPlaceDetail(int contentId) throws SQLException;
    void deleteHotPlaceDescription(int contentId) throws SQLException;
    void deleteHotPlaceAttachments(int contentId) throws SQLException;
    void deleteHotPlaceHit(int contentId) throws SQLException;
    void deleteHotPlaceLike(int contentId) throws SQLException;
    
}
