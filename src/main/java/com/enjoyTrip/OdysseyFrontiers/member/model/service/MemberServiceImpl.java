package com.enjoyTrip.OdysseyFrontiers.member.model.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import com.enjoyTrip.OdysseyFrontiers.member.model.mapper.MemberMapper;
import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private String salt;

	private MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
		salt = getSalt();
	}

	@Override
	public int idCheck(String memberId) throws Exception {
		return memberMapper.idCheck(memberId);
	}

	public String getSalt() {
		
		//1. Random, byte 객체 생성
		//SecureRandom  r = new SecureRandom ();
		byte[] salt = new byte[20];
		
		//2. 난수 생성
		//r.nextBytes(salt);
		
		//3. byte To String (10진수의 문자열로 변경)
		StringBuffer sb = new StringBuffer();
		for(byte b : salt) {
			sb.append(String.format("%02x", b));
		};
		
		return sb.toString();
	}
	
	public String getEncrypt(String memberPassword) {
		
		String result = "";
		try {
			//1. SHA256 알고리즘 객체 생성
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//2. pwd와 salt 합친 문자열에 SHA 256 적용
			System.out.println("pwd + salt 적용 전 : " + memberPassword+salt);
			md.update((memberPassword+salt).getBytes());
			byte[] pwdsalt = md.digest();
			
			//3. byte To String (10진수의 문자열로 변경)
			StringBuffer sb = new StringBuffer();
			for (byte b : pwdsalt) {
				sb.append(String.format("%02x", b));
			}
			
			result=sb.substring(0,10);
			System.out.println("pwd + salt 적용 후 : " + result);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int joinMember(MemberDto memberDto) throws Exception {
		memberDto.setMemberPassword(getEncrypt(memberDto.getMemberPassword()));
		return memberMapper.joinMember(memberDto);
	}

	@Override
	public List<MemberDto> findAllMembers() throws Exception {
		System.out.println("11");
		return memberMapper.findAllMembers();
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) throws Exception {
		map.put("memberPassword", getEncrypt(map.get("memberPassword")));
		return memberMapper.loginMember(map);
	}

	@Override
	public void updateMember(MemberDto memberDto) throws Exception {
//		memberDto.setUserPwd(getEncrypt(memberDto.getUserPwd()));
		memberMapper.updateMember(memberDto);
		
	}

	@Override
	public void updatePassword(MemberDto memberDto) throws Exception {
		memberDto.setMemberPassword(getEncrypt(memberDto.getMemberPassword()));
		memberMapper.updatePassword(memberDto);	
	}
	
	@Override
	public String findPassword(String memberId) throws Exception {
		return memberMapper.findPassword(memberId);
	}

	@Override
	public void deleteMember(String memberId) throws Exception {
		memberMapper.deleteMember(memberId);;
	}
}
