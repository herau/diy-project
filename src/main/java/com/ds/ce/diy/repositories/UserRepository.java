package com.ds.ce.diy.repositories;

import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.repositories.search.SearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, SearchRepository<User> {

    Optional<User> findByPersonalNumber(String personalNumber);

}
