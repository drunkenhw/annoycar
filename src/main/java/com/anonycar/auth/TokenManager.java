package com.anonycar.auth;

import com.anonycar.member.dto.AuthMember;

public interface TokenManager {
    String createAccessToken(AuthMember member);

    AuthMember getClaims(String token);

    boolean isValid(String token);
}
