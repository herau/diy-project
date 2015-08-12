package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.CurrentUser;
import com.dassault_systemes.diy.domain.Role;

import org.springframework.stereotype.Service;

@Service("currentUserService")
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public boolean canAccessUser(CurrentUser currentUser, String userId) {
        return currentUser != null && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }
}
