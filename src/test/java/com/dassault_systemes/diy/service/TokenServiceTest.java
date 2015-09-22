package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.Company;
import com.dassault_systemes.diy.domain.State;
import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.repositories.UserRepository;
import com.dassault_systemes.diy.repositories.VerificationTokenRepository;
import com.dassault_systemes.diy.settings.AppSettings;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.net.URISyntaxException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TokenServiceTest {

    TokenService service;

    @Mock
    AppSettings appSettings;

    @Mock
    UserRepository userRepository;

    @Mock
    VerificationTokenRepository tokenRepository;

    @Mock
    MailService mailService;

    @Mock
    ServletRequestAttributes attrs;

    @Before
    public void setUp() {
        service = new TokenServiceImpl(this.appSettings, userRepository, tokenRepository, mailService);

        // Mock settings
        AppSettings.Email email = mock(AppSettings.Email.class);
        appSettings.setEmail(email);
        when(appSettings.getEmail()).thenReturn(email);
        when(email.getRegistration()).thenReturn(new AppSettings.Email.Registration());
    }

    @Test(expected = IllegalStateException.class)
    public void tokenService_sendRegistrationToken_OutsideRequestContext_ko() throws URISyntaxException {
        service.sendEmailRegistrationToken(new User());
    }

    @Test
    @Ignore
    public void tokenService_sendRegistrationToken_ok() throws URISyntaxException {
        // Mock RequestContextHolder
        RequestContextHolder.setRequestAttributes(attrs);
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        when(attrs.getRequest()).thenReturn(mockedRequest);
        when(mockedRequest.getRequestURL()).thenReturn(any(StringBuffer.class));

        User user = new User("toto", "foo", "bar", "pass", "test@test.com", Company.DS, State.INVALID);

        //        TODO check that save method is called once
        //        when(userRepository.save(any(User.class))).

        // Test
        service.sendEmailRegistrationToken(user);
    }

}
