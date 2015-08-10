package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.CurrentUser;

public interface CurrentUserService {

    /**
     * return true if the currentUser equals to the user from the userId or if the currentUser is an Admin
     *
     * @param currentUser
     * @param userId      Personal User ID
     *
     * @return
     */
    boolean canAccessUser(CurrentUser currentUser, String userId);

}
