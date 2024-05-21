package com.enjoyTrip.OdysseyFrontiers.attraction.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AttractionInfo {
	private int contentId;
    private int contentTypeId;
    private String contentTypeName;
    private String title;
    private String description;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String firstImage;
    private String firstImage2;
    private int readCount;
    private int sidoCode;
    private int gugunCode;
    private String gugunName;
    private String sidoName;
    private double latitude;
    private double longitude;
    private String mlevel;
    private boolean isScrap;
    private long memberId;
    private String accessLevel;

}
