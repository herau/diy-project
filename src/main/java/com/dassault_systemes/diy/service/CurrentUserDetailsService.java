package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Inject
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByPersonalNumber(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with personal number =%s was not found", username));
        }
        return new CurrentUser(user);
    }
}
