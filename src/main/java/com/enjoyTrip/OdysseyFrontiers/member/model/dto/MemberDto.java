package com.enjoyTrip.OdysseyFrontiers.member.model.dto;

import java.util.Date;

import com.enjoyTrip.OdysseyFrontiers.util.MemberStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class MemberDto {

	private long memberId;
	private String name;
	private String emailId;
	private String emailDomain;
	private String password;
	private String phone;
	private String status;
	private String address;
	private String birthday;
	private String joinDate;
	private String token;
	private String image;
}

