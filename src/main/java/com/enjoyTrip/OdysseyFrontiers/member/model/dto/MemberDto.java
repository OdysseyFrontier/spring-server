package com.enjoyTrip.OdysseyFrontiers.member.model.dto;

import com.enjoyTrip.OdysseyFrontiers.util.MemberStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDto {

	private String memberId;
	private String memberName;
	private String memberPassword;
	private String emailId;
	private String emailDomain;
	private String memberPhone;
	private String memberAddress;
	private String joinDate;
	private MemberStatus status;

    @Override
	public String toString() {
		return "MemberDto [memberId=" + memberId + ", memberName=" + memberName + ", memberPassword=" + memberPassword
				+ ", emailId=" + emailId + ", emailDomain=" + emailDomain + ", memberPhone=" + memberPhone
				+ ", memberAddress=" + memberAddress + ", joinDate=" + joinDate + ", status=" + status + "]";
	}
}
