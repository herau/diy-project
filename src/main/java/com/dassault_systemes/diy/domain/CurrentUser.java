package com.dassault_systemes.diy.domain;

import com.dassault_systemes.diy.config.Role;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * extend the provided implementation of UserDetails to add custom information
 * <p>UserDetails.username is populated from User.getPersonalNumber</p>
 *
 * @see UserDetails
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public CurrentUser(final User user) {
        super(user.getPersonalNumber(), user.getPassword(),
              AuthorityUtils.createAuthorityList(user.getRole().toString()));

        this.user = user;
    }

    public String getId() {
        return user.getPersonalNumber();
    }

    public Role getRole() {
        return user.getRole();
    }

    public State getState() {
        return user.getState();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
               "user=" + user +
               "} " + super.toString();
    }
}
