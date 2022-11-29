package com.anonycar.member.service;

import static org.assertj.core.api.Assertions.*;

import com.anonycar.member.dto.JoinRequest;
import com.anonycar.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입을 한다")
    void joinTest() {
        String email = "email@gmail.com";
        JoinRequest joinRequest = new JoinRequest(email, "han", "Abc45678!", "drunken", "benz");
        memberService.join(joinRequest);

        assertThat(memberRepository.findByEmail(email)).isPresent();
    }
}