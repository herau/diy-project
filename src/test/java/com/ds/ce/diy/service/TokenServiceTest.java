package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.Company;
import com.ds.ce.diy.domain.State;
import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.repositories.UserRepository;
import com.ds.ce.diy.repositories.VerificationTokenRepository;
import com.ds.ce.diy.settings.AppSettings;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
        service = new TokenServiceImpl(this.appSettings, userRepository, tokenRepository, mailService, null);

        // Mock settings
        AppSettings.Email email = mock(AppSettings.Email.class);
        appSettings.setEmail(email);
        when(appSettings.getEmail()).thenReturn(email);
        when(email.getRegistration()).thenReturn(new AppSettings.Email.Registration());
    }

    @Test(expected = IllegalStateException.class)
    public void tokenService_sendRegistrationToken_OutsideRequestContext_ko()  {
        service.sendEmailRegistrationToken(new User());
    }

    @Test
    @Ignore
    public void tokenService_sendRegistrationToken_ok() {
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
