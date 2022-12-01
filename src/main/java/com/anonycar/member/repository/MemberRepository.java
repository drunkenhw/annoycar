package com.anonycar.member.repository;

import com.anonycar.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndPasswordValue(String email, String password);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
