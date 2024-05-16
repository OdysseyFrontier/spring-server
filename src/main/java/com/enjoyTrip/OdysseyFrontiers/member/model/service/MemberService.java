package com.enjoyTrip.OdysseyFrontiers.member.model.service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;

public interface MemberService {
    // 아이디 중복 체크
    int idCheck(Map<String, String> map) throws Exception;

    // 회원 가입
    int joinMember(MemberDto memberDto) throws Exception;

    List<MemberDto> findAllMembers() throws Exception;

    // 로그인
    MemberDto loginMember(MemberDto memberDto) throws Exception;

    // 회원 정보 수정
    void updateMember(MemberDto memberDto) throws Exception;

    // 비밀변호 변경
    void updatePassword(MemberDto memberDto) throws Exception;

    //비밀번호찾기
    String findPassword(Long userId) throws Exception;

    // 회원 탈퇴
    void deleteMember(Long userId) throws Exception;
    
    // 회원 정보 조회
    MemberDto memberInfo(long memberId) throws Exception;
    
    
    void saveRefreshToken(long memberId, String refreshToken) throws Exception;
	Object getRefreshToken(long memberId) throws Exception;
	void deleRefreshToken(long memberId) throws Exception;


    Optional<MemberDto> findByMemberId(Long memberId) throws Exception;

    Optional<MemberDto> findByName(String name) throws Exception;
}
