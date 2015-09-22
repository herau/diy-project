package com.dassault_systemes.diy.repositories;

import com.dassault_systemes.diy.domain.VerificationToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    Optional<VerificationToken> findByToken(String token);

}
