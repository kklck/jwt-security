package com.example.jwtsecurity.web.member;

import com.example.jwtsecurity.domain.auth.Authority;
import com.example.jwtsecurity.domain.auth.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {
  private String email;
  private String password;
  private String nickname;

  public Member toMember(PasswordEncoder passwordEncoder) {
    return Member.builder()
        .email(email)
        .password(passwordEncoder.encode(password))
        .nickname(nickname)
        .authority(Authority.ROLE_USER)
        .build();
  }

  public UsernamePasswordAuthenticationToken toAuthentication() {
    return new UsernamePasswordAuthenticationToken(email, password);
  }
}