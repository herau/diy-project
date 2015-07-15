package com.dassault_systemes.diy.repositories;

import com.dassault_systemes.diy.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByPersonalNumber(String personalNumber);

//    User findByFirstnameStartWithOrLastnameStartWithIgnorecase(String firstnameOrLastname);

}
