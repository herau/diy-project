package com.ds.ce.diy.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final Random secureRandom = new SecureRandom();

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String generateRandom() {
        return new BigInteger(60, secureRandom).toString(32);
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean match(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }

}
