package com.enjoyTrip.OdysseyFrontiers.util;

import lombok.Getter;

@Getter
public enum Season {
    SPRING("봄","spring"),
    SUMMER("여름","summer"),
    FALL("가을","fall"),
    WINTER("겨울","winter");

    private final String koreanName;
    private final String englishName;

    Season(String koreanName, String englishName) {
        this.koreanName = koreanName;
        this.englishName = englishName;
    }

    @Override
    public String toString() {
        return "Season{" +
                "koreanName='" + koreanName + '\'' +
                ", englishName='" + englishName + '\'' +
                "} " + super.toString();
    }
}
