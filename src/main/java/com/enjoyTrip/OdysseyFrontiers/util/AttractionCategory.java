package com.enjoyTrip.OdysseyFrontiers.util;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final int id;
    private final String name;

    AttractionCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static int fromCode(int id) {
        for (AttractionCategory category : values()) {
            if (category.id == id) {
                return category.getId();
            }
        }
        return ALL.getId();
    }

    public static List<Map<String, Object>> getAllCategoriesWithCodes() {
        List<Map<String, Object>> categoriesWithCodes = new ArrayList<>();
        for (AttractionCategory category : AttractionCategory.values()) {
            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put("name", category.getName());
            categoryMap.put("code", category.getId());
            categoriesWithCodes.add(categoryMap);
        }
        return categoriesWithCodes;
    }

}
