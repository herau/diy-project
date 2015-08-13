package com.dassault_systemes.diy.repositories;

import com.dassault_systemes.diy.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
//@Secured("ADMIN")
@PreAuthorize("hasRole('ADMIN')")
public interface UserRepository extends CrudRepository<User, Integer> {

    @PreAuthorize("isAnonymous()")
    //    @Secured({"permitAll"})
    @RestResource(exported = false)
    Optional<User> findByPersonalNumber(String personalNumber);

    @PreAuthorize("hasRole('ADMIN') or principal.user.id == #user.id")
    @Override
    User save(User user);

    @PreAuthorize("hasRole('ADMIN') or principal.user.id == #user.id")
    @Override
    User findOne(Integer id);
}
