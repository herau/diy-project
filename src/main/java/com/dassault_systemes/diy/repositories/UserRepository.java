package com.dassault_systemes.diy.repositories;

import com.dassault_systemes.diy.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPersonalNumber(String personalNumber);

    void deleteByPersonalNumber(String personalNumber);

    //    List<User> findByFirstnameOrLastname(String firstnameOrLastname);

}
