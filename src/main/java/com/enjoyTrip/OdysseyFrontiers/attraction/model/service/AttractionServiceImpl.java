package com.enjoyTrip.OdysseyFrontiers.attraction.model.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.AttractionInfo;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Gugun;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Sido;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {

    private final AttractionMapper attractionMapper;

    private AttractionServiceImpl(AttractionMapper attractionMapper) {
        this.attractionMapper = attractionMapper;
    }

    @Override
    public List<Sido> listSidos() throws Exception {
        // TODO Auto-generated method stub
        return attractionMapper.listSidos();
    }

    @Override
    public List<Gugun> listGuguns(int sidoCode) throws Exception {
        // TODO Auto-generated method stub
        return attractionMapper.listGuguns(sidoCode);
    }

    @Override
    public List<AttractionInfo> listAttr(int contentTypeId, int sidoCode, int gugunCode,
                                         String keyword, double nowLocLat, double nowLocLng) throws Exception {
        // 싸피 위도 경도
        double[] nowloc = initLoc(nowLocLat, nowLocLng);

        System.out.println(nowLocLat);
        System.out.println(nowLocLng);
        System.out.println(nowloc[0] + " " + nowloc[1]);
        List<AttractionInfo> list = null;

        if (keyword != null && contentTypeId == 0 && sidoCode == 0 && gugunCode == 0) {
            list = attractionMapper.selectAttrOnlyKeyword(keyword);
        } else if (Objects.equals(keyword, "no")) {
            list = attractionMapper.selectAttrNoKeyword(contentTypeId, sidoCode, gugunCode);
        } else {
            list = attractionMapper.selectAttr(contentTypeId, sidoCode, gugunCode, keyword);
        }


        list.sort(Comparator.comparingDouble(o -> (
                Math.abs(o.getLatitude() - nowloc[0]) +
                        Math.abs(o.getLongitude() - nowloc[1])
        )));

        return list;

    }

    private double[] initLoc(double nowLocLat, double nowLocLng) {
        if (nowLocLat == 0) {
            nowLocLat = 36.110336;
        }
        if (nowLocLng == 0) {
            nowLocLng = 128.4112384;
        }
        return new double[]{nowLocLat, nowLocLng};
    }
}