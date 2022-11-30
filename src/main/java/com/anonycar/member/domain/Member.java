package com.anonycar.member.domain;


import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    @Embedded
    private Password password;

    @Column(unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Builder
    public Member(Long id, String email, String name, Password password, String nickname, Brand brand) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.brand = brand;
    }
}
