package com.enjoyTrip.OdysseyFrontiers.util.jwt;

import lombok.Builder;

@Builder
public record JWToken(
    String grantType,
    String accessToken,
    String refreshToken){

}
