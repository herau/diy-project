package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserService {

    private final UserRepository repository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public User findById(String Id) {
        return repository.findByPersonalNumber(Id);
    }

}
