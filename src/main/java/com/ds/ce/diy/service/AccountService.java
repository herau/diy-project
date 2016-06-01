package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.Account;
import com.ds.ce.diy.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author Aurélien Leboulanger
 */
@Service
class AccountService {

    private final AccountRepository repository;

    @Inject
    public AccountService(AccountRepository repository) {

        this.repository = repository;
    }

    public Account create() {
        return repository.save(new Account());
    }

}
