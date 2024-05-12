package com.enjoyTrip.OdysseyFrontiers.util;

public enum BoardType {
    NOTICE("공지사항", "notice"),
    COMMUNITY("커뮤니티", "community");

    private final String korean;
    private final String english;

    BoardType(String korean, String english) {
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
