package com.example.jwtsecurity.service.auth;

import com.example.jwtsecurity.domain.auth.member.Member;
import com.example.jwtsecurity.domain.auth.member.MemberRepository;
import com.example.jwtsecurity.jwt.TokenProvider;
import com.example.jwtsecurity.web.member.MemberRequestDto;
import com.example.jwtsecurity.web.member.MemberResponseDto;
import com.example.jwtsecurity.web.token.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
  private final AuthenticationManagerBuilder managerBuilder;
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;

  public MemberResponseDto signup(MemberRequestDto requestDto) {
    if (memberRepository.existsByEmail(requestDto.getEmail())) {
      throw new RuntimeException("이미 가입되어 있는 유저입니다");
    }

    Member member = requestDto.toMember(passwordEncoder);
    return MemberResponseDto.of(memberRepository.save(member));
  }

  public TokenDto login(MemberRequestDto requestDto) {
    UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

    Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

    return tokenProvider.generateTokenDto(authentication);
  }

}