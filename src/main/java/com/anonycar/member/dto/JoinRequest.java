package com.anonycar.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequest {

    private String email;
    private String name;
    private String password;
    private String nickname;
    private String brand;
}
