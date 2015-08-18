package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.Account;
import com.dassault_systemes.diy.domain.Company;
import com.dassault_systemes.diy.domain.State;
import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.dto.UserDTO;
import com.dassault_systemes.diy.repositories.AccountRepository;
import com.dassault_systemes.diy.repositories.UserRepository;
import com.dassault_systemes.diy.web.exceptions.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordService passwordService;
    private final AccountRepository accountRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository,
                           PasswordService passwordService,
                           AccountRepository accountRepository) {
        this.repository = userRepository;
        this.passwordService = passwordService;
        this.accountRepository = accountRepository;
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
    public void delete(Integer id) {
        repository.delete(id);
    }

    public User get(Integer id) {
        User user = repository.findOne(id);
        if (user == null) {
            throw new EntityNotFoundException("User with the id [" + id + "] not found");
        }
        return user;
    }

    @Override
    public User create(UserDTO userDTO) {
        String personalNumber = userDTO.getPersonalNumber();
        Assert.notNull(personalNumber, "personalNumber should be not null");

        //TODO how to retrieve information for the user ?
        String securedPassword = passwordService.generateRandom();
        User user = new User(personalNumber, "TODO", "TODO", passwordService.encode(securedPassword), "todo@3ds.com", Company.DS, State.INVALID);

        Account userAccount = new Account();
        accountRepository.save(userAccount);
        user.setAccount(userAccount);

        return repository.save(user);
    }

    @Override
    public void update(Integer id, UserDTO userDTO) {
        User user = repository.getOne(id);

        String password = userDTO.getPassword();
        if (password != null) {
            if (!passwordService.match(userDTO.getOldPassword(), user.getPassword())) {
                throw new IllegalArgumentException("the old password doesn't match with the existing password");
            }
            user.setPassword(passwordService.encode(password));
        }

        String email = userDTO.getPersonalEmail();
        if (email != null) {
            user.setPersonalEmail(email);
        }

        repository.save(user);
    }

    @Override
    public List<User> search(String searchQuery) {
        return repository.search(searchQuery);
    }

}
