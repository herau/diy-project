package com.dassault_systemes.diy.repositories;

import com.dassault_systemes.diy.domain.Account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by n27 on 8/18/15.
 */
@RepositoryRestResource(collectionResourceRel = "accounts", path = "accounts")
@PreAuthorize("hasAuthority('ADMIN')")
public interface AccountRepository extends CrudRepository<Account, Integer> {}
