package com.anonycar.member.controller;

import com.anonycar.auth.TokenManager;
import com.anonycar.member.dto.AuthMember;
import com.anonycar.member.dto.JoinRequest;
import com.anonycar.member.dto.LoginRequest;
import com.anonycar.member.dto.UniqueResponse;
import com.anonycar.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final TokenManager tokenManager;

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody JoinRequest joinRequest) {
        memberService.join(joinRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        AuthMember authMember = memberService.login(loginRequest);
        String accessToken = tokenManager.createAccessToken(authMember);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .build();
    }

    @GetMapping(value = "/exists", params = "email")
    public ResponseEntity<UniqueResponse> validateUniqueEmail(@RequestParam String email) {
        UniqueResponse uniqueResponse = memberService.validateUniqueEmail(email);
        return ResponseEntity.ok(uniqueResponse);
    }

    @GetMapping(value = "/exists", params = "nickname")
    public ResponseEntity<UniqueResponse> validateUniqueNickname(@RequestParam String nickname) {
        UniqueResponse uniqueResponse = memberService.validateUniqueNickname(nickname);
        return ResponseEntity.ok(uniqueResponse);
    }

}
