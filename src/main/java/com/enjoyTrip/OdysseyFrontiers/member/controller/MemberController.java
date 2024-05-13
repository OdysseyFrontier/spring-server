package com.enjoyTrip.OdysseyFrontiers.member.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;
import com.enjoyTrip.OdysseyFrontiers.member.model.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static com.enjoyTrip.OdysseyFrontiers.util.constant.SessionConst.SESSION_MEMBER_INFO;

//@Controller
//@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	@GetMapping("/join")
	public String join() {
		return "member/join";
	}
	
	@GetMapping("/{memberId}")
	@ResponseBody
	public String idCheck(@PathVariable("memberId") Long memberId) throws Exception {
		System.out.println("idCheck userid : { " + memberId + "}");
		int cnt = memberService.idCheck(memberId);
		return cnt + "";
	}
	
	@PostMapping("/join")
	public String join(MemberDto memberDto, Model model) {
		System.out.println("memberDto info : { " + memberDto + "}");
		try {
			memberService.joinMember(memberDto);
			return "redirect:/member/login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			return "error/error";
		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, @RequestParam(name = "remember", required = false) String remember, Model model, HttpSession session, HttpServletResponse response) {
		System.out.println("login map : { " + map + "}");
		try {
			MemberDto memberDto = memberService.loginMember(map);
			if(memberDto != null) {
//				session 설정
				session.setAttribute(SESSION_MEMBER_INFO, memberDto);

//				cookie 설정
				Cookie cookie = new Cookie("memberId", map.get("memberId"));
				Cookie cookie2 = new Cookie("memberPassword", map.get("memberPassword"));
//				cookie.setPath("/board");
				if ("true".equals(remember)) { // 아이디 저장을 체크 했다면.
					
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40); // 40년간 저장.
					response.addCookie(cookie);
					
					cookie2.setMaxAge(60 * 60 * 24 * 365 * 40); // 40년간 저장.
					
				} else { // 아이디 저장을 해제 했다면.
					cookie.setMaxAge(0);
					cookie2.setMaxAge(0);
				}
				response.addCookie(cookie);
				response.addCookie(cookie2);
				
				return "redirect:/";
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "member/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return "error/error";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/update")
	public String update(MemberDto memberDto, Model model) {
		System.out.println("memberDto info : { " + memberDto + "}");
		try {
			memberService.updateMember(memberDto);
			model.addAttribute(SESSION_MEMBER_INFO, memberDto);
			return "/member/mypage";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 수정 중 문제 발생!!!");
			return "error/error";
		}
	}
	
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam Map<String, String> map,
								 Model model) {
		System.out.println("memberDto info : { " + map + "}");
		try {
			String pwd = memberService.findPassword(Long.valueOf(map.get("memberId")));
			if(pwd.equals(map.get("password"))) {
				if(map.get("renewPassword").equals(map.get("newPassword"))) {
					System.out.println("비번 두개 맞음");
					MemberDto memberDto = new MemberDto();
					// db에서 멤버 구분하는 id값
					memberDto.setMemberId(Long.parseLong(map.get("memberId")));
					// memberLoginId
					memberDto.setMemberId(Long.parseLong(map.get("memberId")));
					memberDto.setPassword(map.get("newPassword"));
					memberService.updatePassword(memberDto);
				}else {
					model.addAttribute("notEqual", "false");
					model.addAttribute("notEqualMag", "새로운 비밀번호와 다릅니다.");
					return "/member/mypage";
				}
				
			}else {
				System.out.println("현재비번 안맞음");
				model.addAttribute("notEqual", "false");
				model.addAttribute("notPwdMag", "현재 비밀번호와 다릅니다.");
				return "/member/mypage";
			}
			
			return "/member/mypage";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "비밀번호 변경 중 문제 발생!!!");
			return "error/error";
		}
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "member/mypage";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute(SESSION_MEMBER_INFO);
		try {
			memberService.deleteMember(memberDto.getMemberId());
			return "member/logout";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 삭제 중 문제 발생!!!");
			return "error/error";
		}
		
	}
	



}
