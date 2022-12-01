package com.anonycar.member.service;

import static org.assertj.core.api.Assertions.*;

import com.anonycar.member.domain.Member;
import com.anonycar.member.domain.Password;
import com.anonycar.member.dto.AuthMember;
import com.anonycar.member.dto.JoinRequest;
import com.anonycar.member.dto.LoginRequest;
import com.anonycar.member.dto.UniqueResponse;
import com.anonycar.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberServiceTest {
    private static final String EMAIL_TEXT = "han@gmail.com";
    private static final String NICKNAME_TEXT = "han";

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private Encryptor encryptor;


    @BeforeEach
    void before() {
        Password password = Password.of(encryptor, "Abcd123!@");
        memberRepository.save(Member.builder()
                .email(EMAIL_TEXT)
                .nickname(NICKNAME_TEXT)
                .password(password)
                .build());

    }

    @DisplayName("회원가입을 한다")
    @Test
    void joinTest() {
        String email = "email@gmail.com";
        JoinRequest joinRequest = new JoinRequest(email, "han", "Abc45678!", "drunken", "benz");
        memberService.join(joinRequest);

        assertThat(memberRepository.findByEmail(email)).isPresent();
    }

    @DisplayName("로그인 기능")
    @Test
    void login() {
        AuthMember authInfo = memberService.login(new LoginRequest(EMAIL_TEXT, "Abcd123!@"));

        assertThat(authInfo.getId()).isNotNull();
    }

    @DisplayName("이메일 중복확인")
    @Test
    void validateUniqueEmail() {
        UniqueResponse uniqueResponse = memberService.validateUniqueEmail(EMAIL_TEXT);

        assertThat(uniqueResponse.isUnique()).isFalse();
    }

    @DisplayName("닉네임 중복확인")
    @Test
    void validateUniqueNickname() {
        UniqueResponse uniqueResponse = memberService.validateUniqueNickname(NICKNAME_TEXT);

        assertThat(uniqueResponse.isUnique()).isFalse();
    }
}