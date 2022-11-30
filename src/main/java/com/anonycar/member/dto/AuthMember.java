package com.anonycar.member.dto;

import lombok.Getter;

@Getter
public class AuthMember {

    private Long id;
    private String nickname;

    public AuthMember(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}