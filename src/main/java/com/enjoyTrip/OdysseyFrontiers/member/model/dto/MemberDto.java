package com.enjoyTrip.OdysseyFrontiers.member.model.dto;

import com.enjoyTrip.OdysseyFrontiers.util.MemberStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class MemberDto {

	private long memberId;
	private String name;
	private String emailId;
	private String emailDomain;
	private String password;
	private String phone;
	private MemberStatus status;
	private String address;
	private Date birthday;
	private String joinDate;
}
