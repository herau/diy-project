package com.dassault_systemes.diy.repositories.search;

import com.dassault_systemes.diy.domain.User;

/**
 * @author herau
 */
public class UserRepositoryImpl extends AbstractSearchRepository<User> {

    public UserRepositoryImpl() {
        super(User.class);
    }
}
