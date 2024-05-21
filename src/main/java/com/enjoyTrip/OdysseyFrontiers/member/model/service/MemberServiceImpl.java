package com.enjoyTrip.OdysseyFrontiers.member.model.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;
import com.enjoyTrip.OdysseyFrontiers.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private String salt;

	private MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
		salt = getSalt();
	}

	@Override
	public int idCheck(Map<String, String> map) throws Exception {
		return memberMapper.idCheck(map);
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
	
	public String getEncrypt(String password) {
		
		String result = "";
		try {
			//1. SHA256 알고리즘 객체 생성
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//2. pwd와 salt 합친 문자열에 SHA 256 적용
			System.out.println("pwd + salt 적용 전 : " + password+salt);
			md.update((password+salt).getBytes());
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
//		memberDto.setPassword(getEncrypt(memberDto.getPassword()));
		return memberMapper.joinMember(memberDto);
	}

	@Override
	public List<MemberDto> findAllMembers() throws Exception {
		return memberMapper.findAllMembers();
	}

	@Override
	public MemberDto loginMember(MemberDto memberDto) throws Exception {
//		memberDto.setPassword(getEncrypt(memberDto.getPassword()));
		return memberMapper.loginMember(memberDto);
	}

	@Override
	public void updateMember(MemberDto memberDto) throws Exception {
//		memberDto.setUserPwd(getEncrypt(memberDto.getUserPwd()));
		memberMapper.updateMember(memberDto);
		
	}

	@Override
	public void updatePassword(MemberDto memberDto) throws Exception {
//		memberDto.setPassword(getEncrypt(memberDto.getPassword()));
		memberMapper.updatePassword(memberDto);	
	}
	
	@Override
	public String findPassword(Long memberId) throws Exception {
		return memberMapper.findPassword(memberId);
	}

	@Override
	public void deleteMember(Long memberId) throws Exception {
		memberMapper.deleteMember(memberId);;
	}

	@Override
	public Optional<MemberDto> findByMemberId(Long memberId) throws Exception {
		return memberMapper.findByMemberId(memberId);
	}

	@Override
	public Optional<MemberDto> findByName(String name) throws Exception {
		return memberMapper.findByName(name);
	}
	
	
	
	@Override
	public MemberDto memberInfo(long memberId) throws Exception {
		return memberMapper.memberInfo(memberId);
	}

	@Override
	public void saveRefreshToken(long memberId, String refreshToken) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(long memberId) throws Exception {
		return memberMapper.getRefreshToken(memberId);
	}

	@Override
	public void deleRefreshToken(long memberId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}

	@Override
	public void followMember(long followerId, long followingId) throws Exception {
		memberMapper.followMember(Map.of("followerId", followerId, "followingId", followingId));
	}

	@Override
	public void unfollowMember(long followerId, long followingId) throws Exception {
		memberMapper.unfollowMember(Map.of("followerId", followerId, "followingId", followingId));
	}

	@Override
	public List<MemberDto> findFollowers(long memberId) throws Exception {
		return memberMapper.findFollowers(memberId);
	}

	@Override
	public List<MemberDto> findFollowing(long memberId) throws Exception {
		return memberMapper.findFollowing(memberId);
	}

	public List<MemberDto> searchMembers(String search) {
		if (search == null || search.trim().isEmpty()) {
			return memberMapper.findAll();
		} else {
			return memberMapper.findByEmailIdOrNameLike(search.trim());
		}
	}
}
