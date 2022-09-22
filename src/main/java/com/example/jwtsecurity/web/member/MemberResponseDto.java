package com.example.jwtsecurity.web.member;

import com.example.jwtsecurity.domain.auth.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {
  private String email;
  private String nickname;

  public static MemberResponseDto of(Member member) {
    return MemberResponseDto.builder()
        .email(member.getEmail())
        .nickname(member.getNickname())
        .build();
  }
}