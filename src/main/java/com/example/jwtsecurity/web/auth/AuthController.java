package com.example.jwtsecurity.web.auth;

import com.example.jwtsecurity.service.auth.AuthService;
import com.example.jwtsecurity.web.member.MemberRequestDto;
import com.example.jwtsecurity.web.member.MemberResponseDto;
import com.example.jwtsecurity.web.token.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto requestDto) {
    return ResponseEntity.ok(authService.signup(requestDto));
  }

  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto) {
    return ResponseEntity.ok(authService.login(requestDto));
  }
}