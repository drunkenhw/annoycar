package com.anonycar.auth;

import com.anonycar.member.dto.AuthMember;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenFactory implements TokenManager {

    private final Key signingKey;
    private final long validityInMilliseconds;

    public TokenFactory(@Value("${jwt.token.secret-key}") String secretKey,
                        @Value("${jwt.token.expire-date.access-token}") long validityInMilliseconds) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
        this.validityInMilliseconds = validityInMilliseconds;
    }

    @Override
    public String createAccessToken(AuthMember member) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .claim("id", member.getId())
                .claim("nickname", member.getNickname())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(signingKey)
                .compact();
    }

    @Override
    public AuthMember getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            return getAuthMember(ex.getClaims());
        }

        return getAuthMember(claims);
    }

    private AuthMember getAuthMember(Claims claims) {
        Long id = claims.get("id", Long.class);
        String nickname = claims.get("nickname", String.class);
        return new AuthMember(id, nickname);
    }

    @Override
    public boolean isValid(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
