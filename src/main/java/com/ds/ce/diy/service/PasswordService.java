package com.ds.ce.diy.service;

/**
 * @author herau
 * @see org.springframework.security.crypto.password.PasswordEncoder
 */
interface PasswordService {

    String generateRandom();

    String encode(String password);

    boolean match(String password, String encodedPassword);
}
