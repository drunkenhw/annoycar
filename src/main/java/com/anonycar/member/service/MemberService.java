package com.anonycar.member.service;

import com.anonycar.member.dto.AuthMember;
import com.anonycar.member.dto.JoinRequest;
import com.anonycar.member.dto.LoginRequest;
import com.anonycar.member.dto.UniqueResponse;
import com.anonycar.member.repository.MemberRepository;
import com.anonycar.member.domain.Brand;
import com.anonycar.member.domain.Member;
import com.anonycar.member.domain.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final Encryptor encryptor;

    @Transactional
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

    public AuthMember login(LoginRequest loginRequest) {
        String password = encryptor.encrypt(loginRequest.getPassword());
        Member member = memberRepository.findByEmailAndPasswordValue(loginRequest.getEmail(), password)
                .orElseThrow(IllegalArgumentException::new);
        return new AuthMember(member.getId(), member.getNickname());
    }

    public UniqueResponse validateUniqueEmail(String email) {
        boolean present = !memberRepository.existsByEmail(email);
        return new UniqueResponse(present);
    }

    public UniqueResponse validateUniqueNickname(String nickname) {
        boolean present = !memberRepository.existsByNickname(nickname);
        return new UniqueResponse(present);
    }
}
