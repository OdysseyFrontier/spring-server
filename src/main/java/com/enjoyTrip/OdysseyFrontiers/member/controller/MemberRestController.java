package com.enjoyTrip.OdysseyFrontiers.member.controller;

import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;
import com.enjoyTrip.OdysseyFrontiers.member.model.service.MemberService;
import com.enjoyTrip.OdysseyFrontiers.util.constant.JwtConst;
import com.enjoyTrip.OdysseyFrontiers.util.jwt.JWToken;
import com.enjoyTrip.OdysseyFrontiers.util.jwt.JwtGenerator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public MemberRestController(MemberService memberService, JwtGenerator jwtGenerator, AuthenticationManagerBuilder authenticationManagerBuilder) {
        super();
        this.memberService = memberService;
        this.jwtGenerator = jwtGenerator;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }


    @GetMapping("/list")
    public ResponseEntity<?> getAllMembers() throws Exception {
        List<MemberDto> allMembers = memberService.findAllMembers();
        return new ResponseEntity<>(allMembers, HttpStatus.OK);
    }

    @PostMapping("/aa")
    public ResponseEntity<?> login(@RequestBody MemberDto memberDto,
                                   HttpServletResponse response) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.ACCEPTED;

        memberService.joinMember(memberDto);
        System.out.println(memberDto);
        try {
            if (memberDto != null) {

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
                JWToken jwToken = signIn(memberDto.getMemberName(), memberDto.getMemberId(), memberDto.getMemberPassword());

                HttpHeaders headers = new HttpHeaders();
//                "Bearer"는 HTTP 요청의 Authorization 헤더에 추가되는 값으로,
//                클라이언트가 서버에게 해당 요청이 보안 토큰(Bearer token)을 포함하고 있음을 알려줍니다.
//                "Bearer"는 표준화된 인증 체계인 OAuth 2.0에서 사용되며,
//                이를 통해 클라이언트가 서버에게 자신을 인증하고, 보호된 리소스에 접근할 수 있음을 알립니다.
                headers.set(HEADER_AUTH, GRANT_TYPE + " " + jwToken);
                System.out.println("create");
                System.out.println(GRANT_TYPE + " " + jwToken);
                return ResponseEntity.ok().headers(headers).build();
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
    public JWToken signIn(String userName, String userId, String password) throws Exception {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);

        System.out.println(11);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        System.out.println(22);
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JWToken jwtToken = jwtGenerator.generateToken(userId, authentication);

        System.out.println(33);
        return jwtToken;
    }
}