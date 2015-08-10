package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.config.Role;
import com.dassault_systemes.diy.domain.CurrentUser;

import org.springframework.stereotype.Service;

@Service("currentUserService")
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public boolean canAccessUser(CurrentUser currentUser, String userId) {
        return currentUser != null && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }
}
