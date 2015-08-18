package com.dassault_systemes.diy.service;

/**
 * @author herau
 * @see org.springframework.security.crypto.password.PasswordEncoder
 */
public interface PasswordService {

    String generateRandom();

    String encode(String password);

    boolean match(String password, String encodedPassword);
}
