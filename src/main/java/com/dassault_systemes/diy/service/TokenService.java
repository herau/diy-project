package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.domain.VerificationToken;

/**
 * @author herau
 *         Responsible for generating tokens, persisting them, communicating with the email services gateway and
 *         verifying returned tokens
 */
public interface TokenService {

    VerificationToken sendEmailRegistrationToken(User user);

    //    TODO implement it

    //    VerificationToken sendEmailLostPasswordToken(User user);

    //    VerificationToken sendEmailVerificationToken(User user);
}
