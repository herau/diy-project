package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.domain.VerificationToken;
import com.ds.ce.diy.web.exceptions.EntityNotFoundException;

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
     * @param base64EncodedToken String token (Base64 encoded)
     *
     * @return verified token or null if the token isn't valid
     *
     * @throws EntityNotFoundException if the token hasn't been found
     */
    VerificationToken verifyToken(String base64EncodedToken);
}
