package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.domain.VerificationToken;

/**
 * @author herau
 *         Responsible for generating tokens, persisting them, communicating with the email services gateway and
 *         verifying returned tokens
 */
public interface TokenService {

    /**
     * @param user
     *
     * @return
     *
     */
    VerificationToken sendEmailRegistrationToken(User user);

    //    TODO implement it

    //    VerificationToken sendEmailLostPasswordToken(User user);

    //    VerificationToken sendEmailVerificationToken(User user);

    /**
     * verify and return the token, according to the type of the token
     *
     * @param token String token (Base64 encoded)
     *
     * @return verified token or null if the token isn't valid
     *
     * @throws com.dassault_systemes.diy.web.exceptions.EntityNotFoundException if the token hasn't been found
     */
    VerificationToken verifyToken(String token);
}
