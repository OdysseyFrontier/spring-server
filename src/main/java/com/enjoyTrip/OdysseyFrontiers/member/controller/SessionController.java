package com.enjoyTrip.OdysseyFrontiers.member.controller;

import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.enjoyTrip.OdysseyFrontiers.util.constant.SessionConst.SESSION_MEMBER_INFO;

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/memberInfo")
    public ResponseEntity<?> mebmerinfo(HttpSession session) {
        MemberDto memberInfo = (MemberDto) session.getAttribute(SESSION_MEMBER_INFO);
        return ResponseEntity.ok(memberInfo);
    }
}
