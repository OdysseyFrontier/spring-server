package com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class HotPlaceDto {
	private long memberId;
	private String name;
	private int contentId;
    private int contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String firstImage;
    private String firstImage2;
    private int hit;
    private int sidoCode;
    private int gugunCode;
    private double latitude;
    private double longitude;
    private String mlevel;
    private int likes;
    private String isLike;
    
    
    private String homepage;
	private String overview;
	private String telname;
	
	
    private String createdTime;
	private String modifiedTime;
}
