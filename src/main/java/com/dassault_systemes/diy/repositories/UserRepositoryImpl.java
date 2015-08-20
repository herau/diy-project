package com.dassault_systemes.diy.repositories;

import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.repositories.search.AbstractSearchRepository;

/**
 * custom Spring Data JPA implementation for the UserRepository.
 * @link http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.single-repository-behaviour
 * @author herau
 */
public class UserRepositoryImpl extends AbstractSearchRepository<User> {

    public UserRepositoryImpl() {
        super(User.class);
    }
}
