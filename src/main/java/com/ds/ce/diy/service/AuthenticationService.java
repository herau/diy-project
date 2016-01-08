package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.domain.security.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * override the default Spring security UserDetailsService to find the user by it's personalNumber
 *
 * @see UserDetailsService
 */
@Service
public class AuthenticationService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final UserService userService;

    @Inject
    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Authenticating user with personal ID number = {}", username);

        User user = userService.getByPersonalNumber(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with personal number =%s was not found", username)));

        return new CurrentUser(user);
    }
}
