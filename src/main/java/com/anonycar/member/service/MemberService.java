package com.anonycar.member.service;

import com.anonycar.member.dto.JoinRequest;
import com.anonycar.member.repository.MemberRepository;
import com.anonycar.member.domain.Brand;
import com.anonycar.member.domain.Member;
import com.anonycar.member.domain.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final Encryptor encryptor;

    public void join(JoinRequest joinRequest) {
        Member member = Member.builder()
                .email(joinRequest.getEmail())
                .password(Password.of(encryptor, joinRequest.getPassword()))
                .name(joinRequest.getName())
                .nickname(joinRequest.getNickname())
                .brand(Brand.of(joinRequest.getBrand()))
                .build();
        memberRepository.save(member);
    }
}
