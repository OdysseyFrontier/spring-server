package com.enjoyTrip.OdysseyFrontiers.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;

@Mapper
public interface MemberMapper {
	// 아이디 중복 체크
	int idCheck(Long memberId) throws SQLException;
	
	// 회원 가입
	int joinMember(MemberDto memberDto) throws SQLException;
	
	// 로그인
	MemberDto loginMember(Map<String, String> map) throws SQLException;
	
	// 회원 정보 수정
	void updateMember(MemberDto memberDto) throws SQLException;
	
	// 비밀변호 변경
	void updatePassword(MemberDto memberDto) throws SQLException;
	
	//비밀번호찾기
	String findPassword(Long memberId) throws SQLException;
	
	// 회원 탈퇴
	void deleteMember(Long memberId) throws SQLException;

	Optional<MemberDto> findByName(String name) throws SQLException;

	Optional<MemberDto> findByMemberId(Long memberId) throws SQLException;

    List<MemberDto> findAllMembers() throws SQLException;
}
