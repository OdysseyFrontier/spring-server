//package com.enjoyTrip.OdysseyFrontiers.plan.controller;
//
//import com.enjoyTrip.OdysseyFrontiers.member.model.service.MemberService;
//import com.enjoyTrip.OdysseyFrontiers.plan.model.dto.PlanDto;
//import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;
//import com.enjoyTrip.OdysseyFrontiers.plan.model.service.PlanService;
//import com.enjoyTrip.OdysseyFrontiers.util.constant.JwtConst;
//import com.enjoyTrip.OdysseyFrontiers.util.jwt.JwtInterpreter;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import static com.enjoyTrip.OdysseyFrontiers.util.constant.JwtConst.HEADER_AUTH;
//import static com.enjoyTrip.OdysseyFrontiers.util.constant.SessionConst.SESSION_MEMBER_INFO;
//
//@RestController
//@RequestMapping("/plan")
//public class PlanRestController {
//    private final PlanService planService;
//    private final MemberService memberService;
//    private final JwtInterpreter jwtInterpreter;
//
//    public PlanRestController(PlanService planService, MemberService memberService, JwtInterpreter jwtInterpreter) {
//        super();
//        this.planService = planService;
//        this.memberService = memberService;
//        this.jwtInterpreter = jwtInterpreter;
//    }
//
//    @GetMapping("/aa")
//    public void aa(@CookieValue(name = HEADER_AUTH, required = false) String jwtToken) {
//        System.out.println("11");
//        System.out.println(jwtToken);
//    }
//
//
////    @CookieValue(name = "your-cookie-name", required = false) String jwtToken) {
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createPlan(
//            @RequestHeader(name = HEADER_AUTH) String jwtToken,
//            @RequestBody PlanDto planDto,
//            HttpServletRequest request) throws Exception {
//        System.out.println("createPlan");
//
//        Long userId = jwtInterpreter.getUserId(jwtToken);
//        planDto.setMemberId(userId);
//
//        Optional<MemberDto> byMemberId = memberService.findByMemberId(userId);
//
//
//        System.out.println(userId);
//        System.out.println(planDto);
//
//        if (byMemberId.isPresent()) {
//            planService.createPlan(planDto);
//        }
//
//        // 쿠키 가져오기
////        Cookie[] cookies = request.getCookies();
////        if (cookies != null) {
////            for (Cookie cookie : cookies) {
//////                if (cookie.getName().equals("your-cookie-name")) {
////                if (cookie.getName().equals(HEADER_AUTH)) {
////                    String jwtToken = cookie.getValue();
////
////                    Long userId = jwtInterpreter.getUserId(jwtToken);
////                    System.out.println(userId);
////
////                    // JWT 토큰 처리
////                    // ...
////                }
////            }
////        }
//        // 처리 로직
//
//
////        planDto.setMemberId(memberDto.getMemberId());
//
////        planService.createPlan(planDto);
//
////        return new ResponseEntity<>(planDto, HttpStatus.CREATED);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity<?> listPlan(@RequestBody Map<String, Object> map) throws Exception {
//        List<PlanDto> list = planService.listPlan(map);
//        System.out.println(list);
//
//        return new ResponseEntity<>(list, HttpStatus.OK);
//
//    }
//
//    @GetMapping("/view/{PlanNo}")
//    public ResponseEntity<?> viewPlan(@PathVariable int PlanNo, @RequestBody Map<String, String> map)
//            throws Exception {
//        PlanDto planDto = planService.getPlan(PlanNo);
//        System.out.println(planDto);
//
//        return new ResponseEntity<>(planDto, HttpStatus.OK);
//    }
//
//    @PutMapping("/{PlanNo}")
//    public ResponseEntity<?> updatePlan(@PathVariable int planId,
//                                        @RequestBody PlanDto planDto) throws Exception {
//        planService.modifyPlan(planId, planDto);
//
//        return new ResponseEntity<>(planDto, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{PlanNo}")
//    public ResponseEntity<?> deletePlan(@PathVariable int PlanNo) throws Exception {
//        planService.deletePlan(PlanNo);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
//}
//
