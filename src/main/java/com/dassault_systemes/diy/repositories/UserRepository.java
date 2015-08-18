package com.dassault_systemes.diy.repositories;

import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.repositories.search.SearchRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, SearchRepository<User> {

    Optional<User> findByPersonalNumber(String personalNumber);

}
