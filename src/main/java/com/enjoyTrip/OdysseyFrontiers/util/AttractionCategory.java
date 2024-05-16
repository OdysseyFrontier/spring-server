package com.enjoyTrip.OdysseyFrontiers.util;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum AttractionCategory {
    ALL(0, "모든 관광지 유형"),
    TOURIST_SPOT(12, "관광지"),
    CULTURAL_FACILITY(14, "문화시설"),
    FESTIVAL_EVENT(15, "축제 / 공연 / 행사"),
    TRAVEL_COURSE(25, "여행코스"),
    LEISURE_SPORTS(28, "레포츠"),
    ACCOMMODATION(32, "숙박"),
    SHOPPING(38, "쇼핑"),
    RESTAURANT(39, "음식점");

    private final int code;
    private final String name;

    AttractionCategory(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static int fromCode(int code) {
        for (AttractionCategory category : values()) {
            if (category.code == code) {
                return category.getCode();
            }
        }
        return ALL.getCode();
    }

}
