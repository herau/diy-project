package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.Company;
import com.dassault_systemes.diy.domain.State;
import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.dto.UserDTO;
import com.dassault_systemes.diy.repositories.UserRepository;
import com.dassault_systemes.diy.repositories.UserSearchRepository;
import com.dassault_systemes.diy.web.exceptions.EntityNotFoundException;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserSearchRepository searchRepository;

    private final PasswordService passwordService;

    @Inject
    public UserServiceImpl(UserRepository userRepository, UserSearchRepository searchRepository,
                           PasswordService passwordService) {
        this.repository = userRepository;
        this.searchRepository = searchRepository;
        this.passwordService = passwordService;
    }

    @Override
    public Optional<User> getByPersonalNumber(String personalNumber) {
        return repository.findByPersonalNumber(personalNumber);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String personalNumber) {
        User user =
                getByPersonalNumber(personalNumber).orElseThrow(() -> new EntityNotFoundException("User not found"));

        repository.delete(user);
    }

    @Override
    public User create(UserDTO userDTO) {
        //FIXME how to retrieve information for the user ?
        User user =
                new User(userDTO.getPersonalNumber(), "TODO", "TODO", passwordService.generateRandom(), "todo@3ds.com",
                         Company.DS, State.INVALID);

        return repository.save(user);
    }

    @Override
    public void update(String personalNumber, UserDTO userDTO) {
        User user =
                getByPersonalNumber(personalNumber).orElseThrow(() -> new EntityNotFoundException("User not found"));

        String password = userDTO.getPassword();
        if (password != null) {
            user.setPassword(password);
        }

        String email = userDTO.getPersonalEmail();
        if (email != null) {
            user.setPersonalEmail(email);
        }

        repository.save(user);
    }

    @Override
    public List<User> search(String searchQuery) {
        return searchRepository.search(searchQuery);
    }

}
