package com.enjoyTrip.OdysseyFrontiers.util;

public enum MemberStatus {
    ACTIVE("회원 이용 중","active"),
    INACTIVE("회원 탈퇴","inactive"),
    ADMIN("마스터 회원","admin");

    private final String status;
    private final String englishName;

    MemberStatus(String status, String englishName) {
        this.status = status;
        this.englishName = englishName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getStatus() {
        return status;
    }
}
