package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.Company;
import com.dassault_systemes.diy.domain.State;
import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.repositories.UserRepository;
import com.dassault_systemes.diy.settings.AppSettings;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TokenServiceTest {

    TokenService service;

    @Mock
    AppSettings appSettings;

    @Mock
    UserRepository userRepository;

    @Mock
    MailService mailService;

    @Before
    public void setUp() {
        service = new TokenServiceImpl(appSettings, userRepository, mailService);
        //TODO fix mockito for static fields
        //        when(appSettings.email).thenReturn(new AppSettings.Email());
        //when(appSettings.email.registration).thenReturn(new AppSettings.Email.Registration());
        //        when(appSettings.email.registration.getTokenExpiryTime()).thenReturn(20);
    }

    @Test
    @Ignore
    public void tokenService_sendRegistrationToken_ok() {
        User user = new User("toto", "foo", "bar", "pass", "test@test.com", Company.DS, State.INVALID);

        // Mocking
        when(userRepository.findByPersonalNumber(any(String.class))).thenReturn(Optional.of(user));

        // Test
        service.sendEmailRegistrationToken(user);
    }

}
