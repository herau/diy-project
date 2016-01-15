package com.ds.ce.diy.repositories;

import com.ds.ce.diy.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "accounts", path = "accounts")
@PreAuthorize("hasAuthority('ADMIN')")
public interface AccountRepository extends CrudRepository<Account, Integer> {}
