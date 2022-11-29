package com.anonycar.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinRequest {

    private String email;
    private String name;
    private String password;
    private String nickname;
    private String brand;
}
