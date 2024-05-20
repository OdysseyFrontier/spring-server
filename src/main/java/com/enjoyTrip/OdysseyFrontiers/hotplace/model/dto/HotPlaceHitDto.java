package com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HotPlaceHitDto {
	private int contentId;
    private long memberId;
    private String createTime;
    
    public HotPlaceHitDto(int contentId, Long memberId) {
        this.contentId = contentId;
        this.memberId = memberId;
    }
}
