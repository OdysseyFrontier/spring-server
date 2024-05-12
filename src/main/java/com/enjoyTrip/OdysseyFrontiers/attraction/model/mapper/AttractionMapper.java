package com.enjoyTrip.OdysseyFrontiers.attraction.model.mapper;

import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.AttractionInfo;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Gugun;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Sido;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AttractionMapper {
    List<Sido> listSidos() throws SQLException;

    List<Gugun> listGuguns(int sidoCode) throws SQLException;

    List<AttractionInfo> selectAttr(int contentTypeId, int sidoCode, int gugunCode, String keyword) throws SQLException;

    List<AttractionInfo> selectAttrOnlyKeyword(String keyword) throws SQLException;

    List<AttractionInfo> selectAttrNoKeyword(int contentTypeId, int sidoCode, int gugunCode) throws SQLException;
}