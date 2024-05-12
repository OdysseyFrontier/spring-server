package com.enjoyTrip.OdysseyFrontiers.util;

public enum AccessType {
    PUBLIC("공개","public"),
    PRIVATE("비공개","private")
    ;

    private final String korean;
    private final String english;

    AccessType(String korean, String english) {
        this.korean = korean;
        this.english = english;
    }


    public String getKorean() {
        return korean;
    }

    public String getEnglish() {
        return english;
    }
}
