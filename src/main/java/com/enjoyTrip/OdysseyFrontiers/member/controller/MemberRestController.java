package com.enjoyTrip.OdysseyFrontiers.member.controller;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardListDto;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.HotPlaceDto;
import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;
import com.enjoyTrip.OdysseyFrontiers.member.model.service.MemberService;
import com.enjoyTrip.OdysseyFrontiers.util.constant.JwtConst;
import com.enjoyTrip.OdysseyFrontiers.util.jwt.JWTUtil;
import com.enjoyTrip.OdysseyFrontiers.util.jwt.JWToken;
//import com.enjoyTrip.OdysseyFrontiers.util.jwt.JwtGenerator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.Authentication;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.enjoyTrip.OdysseyFrontiers.util.constant.JwtConst.GRANT_TYPE;
import static com.enjoyTrip.OdysseyFrontiers.util.constant.JwtConst.HEADER_AUTH;
import static com.enjoyTrip.OdysseyFrontiers.util.constant.SessionConst.SESSION_MEMBER_INFO;


@Slf4j
@RestController
@RequestMapping("/member")
public class MemberRestController {

    private final MemberService memberService;

    // jwt
//    private final JwtGenerator jwtGenerator;
//    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    
	private final JWTUtil jwtUtil;
    
    
//    public MemberRestController(MemberService memberService, JwtGenerator jwtGenerator, AuthenticationManagerBuilder authenticationManagerBuilder) {
//        super();
//        this.memberService = memberService;
//        this.jwtGenerator = jwtGenerator;
//        this.authenticationManagerBuilder = authenticationManagerBuilder;
//    }

	public MemberRestController(MemberService memberService, JWTUtil jwtUtil) {
		super();
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}


//	
//    @GetMapping("/list")
//    public ResponseEntity<?> getAllMembers() throws Exception {
//        List<MemberDto> allMembers = memberService.findAllMembers();
//        return new ResponseEntity<>(allMembers, HttpStatus.OK);
//    }
	
	@GetMapping("/idcheck")
	public ResponseEntity<?> idCheck(@RequestParam Map<String, String> map) {
		try {
            int count = memberService.idCheck(map);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberDto memberDto) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            memberService.joinMember(memberDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("message", e.getMessage());
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto /*,
                                   HttpServletResponse response*/) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.ACCEPTED;
        
        System.out.println("login");

//        MemberDto memberDto = memberService.loginMember(map);

        System.out.println(memberDto);
        try {
        	MemberDto loginMember = memberService.loginMember(memberDto);
            if (loginMember != null) {
            	String accessToken = jwtUtil.createAccessToken(loginMember.getMemberId());
				String refreshToken = jwtUtil.createRefreshToken(loginMember.getMemberId());
				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);
				
//				발급받은 refresh token 을 DB에 저장.
				memberService.saveRefreshToken(loginMember.getMemberId(), refreshToken);
				
//				JSON 으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				
				status = HttpStatus.CREATED;

//				cookie 설정
//                Cookie cookie = new Cookie("memberId", map.get("memberId"));
//                Cookie cookie2 = new Cookie("memberPassword", map.get("memberPassword"));

                // @RequestParam으로 rememeber 못 가져옴.
//                if ("true".equals(remember)) { // 아이디 저장을 체크 했다면.
//
//                    cookie.setMaxAge(60 * 60 * 24 * 365 * 40); // 40년간 저장.
//                    response.addCookie(cookie);
//
//                    cookie2.setMaxAge(60 * 60 * 24 * 365 * 40); // 40년간 저장.
//                } else { // 아이디 저장을 해제 했다면.
//                    cookie.setMaxAge(0);
//                    cookie2.setMaxAge(0);
//                }
//                response.addCookie(cookie);
//                response.addCookie(cookie2);


//				jwt 설정
//                JWToken jwToken = signIn(memberDto.getMemberId(), memberDto.getName(), memberDto.getPassword());
//
//                HttpHeaders headers = new HttpHeaders();
//                "Bearer"는 HTTP 요청의 Authorization 헤더에 추가되는 값으로,
//                클라이언트가 서버에게 해당 요청이 보안 토큰(Bearer token)을 포함하고 있음을 알려줍니다.
//                "Bearer"는 표준화된 인증 체계인 OAuth 2.0에서 사용되며,
//                이를 통해 클라이언트가 서버에게 자신을 인증하고, 보호된 리소스에 접근할 수 있음을 알립니다.
//                headers.set(HEADER_AUTH, GRANT_TYPE + " " + jwToken);
//                System.out.println(jwToken);
//                return ResponseEntity.ok().headers(headers).build();
            } else {
                resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
                status = HttpStatus.UNAUTHORIZED;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
//            return "error/error";
        }
        return new ResponseEntity<>(resultMap, status);
    }


    // service에 추가?
//    @Transactional
//    public JWToken signIn(Long userId, String userName, String password) throws Exception {
//        // 1. username + password 를 기반으로 Authentication 객체 생성
//        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
//
//        System.out.println(11);
//
//        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
//        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//
//        System.out.println(22);
//        // 3. 인증 정보를 기반으로 JWT 토큰 생성
//        JWToken jwtToken = jwtGenerator.generateToken(userId, authentication);
//
//        System.out.println(33);
//        return jwtToken;
//    }
    
    
    @GetMapping("/info/{memberId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("memberId") long memberId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.memberInfo(memberId);
				resultMap.put("memberInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
    
    @GetMapping("/logout/{memberId}")
	public ResponseEntity<?> removeToken(@PathVariable ("memberId") long memberId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(memberId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

    
    @PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getMemberId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getMemberId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 access token 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("refresh token 도 사용 불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	// 팔로우 기능 추가
	@PostMapping("/{followerId}/follow/{followingId}")
	public ResponseEntity<?> followMember(@PathVariable long followerId, @PathVariable long followingId) {
		try {
			memberService.followMember(followerId, followingId);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{followerId}/unfollow/{followingId}")
	public ResponseEntity<?> unfollowMember(@PathVariable long followerId, @PathVariable long followingId) {
		try {
			memberService.unfollowMember(followerId, followingId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{memberId}/followers")
	public ResponseEntity<List<MemberDto>> getFollowers(@PathVariable long memberId) {
		try {
			List<MemberDto> followers = memberService.findFollowers(memberId);
			return new ResponseEntity<>(followers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{memberId}/following")
	public ResponseEntity<List<MemberDto>> getFollowing(@PathVariable long memberId) {
		try {
			List<MemberDto> following = memberService.findFollowing(memberId);
			return new ResponseEntity<>(following, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/search/all")
	public ResponseEntity<List<MemberDto>> searchAllMembers() throws Exception {
		List<MemberDto> members = memberService.searchMembers(" ");
		return ResponseEntity.ok(members);
	}

	@GetMapping("/search/{search}")
	public ResponseEntity<List<MemberDto>> searchMembers(@PathVariable(required = false) String search) throws Exception {
		List<MemberDto> members = memberService.searchMembers(search);
		return ResponseEntity.ok(members);
	}

	@GetMapping("/search/all/{loginMemberId}")
	public ResponseEntity<List<MemberDto>> searchAllMembersByLoginMemberId(@PathVariable(required = false) long loginMemberId) throws Exception {
//		System.out.println(loginMemberId);
//		long loginMemberId = Long.parseLong(loginMemberId);
		List<MemberDto> members = memberService.searchMembersByLoginMemberId(" ", loginMemberId);
		return ResponseEntity.ok(members);
	}

	@GetMapping("/search/{search}/{loginMemberId}")
	public ResponseEntity<List<MemberDto>> searchMembersByLoginMemberId(@PathVariable(required = false) String search,
																		@PathVariable(required = false) long loginMemberId) throws Exception {

		System.out.println(search);
		System.out.println(loginMemberId);
		List<MemberDto> members = memberService.searchMembersByLoginMemberId(search,loginMemberId);
		System.out.println(members);
		return ResponseEntity.ok(members);
	}
	
	
	// 특정 멤버에 대한 정보를 준다
	@GetMapping("/memberInfo")
	public ResponseEntity<?> getMemberInfo(@RequestParam Map<String, String> map) {
		
		log.info("getMemberInfo map - {}", map);
		try {
			MemberDto MemberDto = memberService.getMemberInfo(map);
			System.out.println(MemberDto);
			return new ResponseEntity<>(MemberDto, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// 특정 멤버의 hotplaceList를 준다
	@GetMapping("/hotplace")
	public ResponseEntity<?> getMemberHotPlace(@RequestParam long memberId) {
		
		log.info("getMemberHotPlace memberId - {}", memberId);
		try {
			List<HotPlaceDto> list = memberService.getMemberHotPlace(memberId);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 회원 정보 수정
    @PutMapping
    public ResponseEntity<?> modify(@RequestBody MemberDto memberDto) throws Exception {
    	System.out.println(memberDto);
        
        memberService.modifyMemberInfo(memberDto);
        

        return new ResponseEntity<>(HttpStatus.OK);
    }
}