package com.enjoyTrip.OdysseyFrontiers.member.model.service;


import java.util.Map;

import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;

public interface MemberService {
	// 아이디 중복 체크
		int idCheck(String userId) throws Exception;
		
		// 회원 가입
		int joinMember(MemberDto memberDto) throws Exception;
		
		// 로그인
		MemberDto loginMember(Map<String, String> map) throws Exception;
		
		// 회원 정보 수정
		void updateMember(MemberDto memberDto) throws Exception;
		
		// 비밀변호 변경
		void updatePassword(MemberDto memberDto) throws Exception;
		
		//비밀번호찾기
		String findPassword(String userId) throws Exception;
		
		// 회원 탈퇴
		void deleteMember(String userId) throws Exception;
}
