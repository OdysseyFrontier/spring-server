package com.enjoyTrip.OdysseyFrontiers.util.jwt.user;

import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;
import com.enjoyTrip.OdysseyFrontiers.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            // memberName이 중복이 없다고 가정.
            Optional<MemberDto> byMemberName = memberMapper.findByMemberName(username);

            return byMemberName
                    .map(this::createUserDetails)
                    .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 return
    private UserDetails createUserDetails(MemberDto member) {
        return User.builder()
                .username(member.getMemberName())
                .password(passwordEncoder.encode(member.getMemberPassword()))
                .roles(member.getStatus().getEnglishName())
                .build();
    }

}