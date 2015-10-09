package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.Company;
import com.ds.ce.diy.domain.State;
import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.repositories.UserRepository;
import com.ds.ce.diy.repositories.VerificationTokenRepository;
import com.ds.ce.diy.settings.AppSettings;
import com.ds.ce.diy.web.exceptions.EntityNotFoundException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

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

    @Mock
    Configuration freeMarkerConfig;

    @Before
    public void setUp() throws IOException {
        service =
                new TokenServiceImpl(this.appSettings, userRepository, tokenRepository, mailService, freeMarkerConfig);

        // Mock settings
        AppSettings.Email email = mock(AppSettings.Email.class);
        appSettings.setEmail(email);
        when(appSettings.getEmail()).thenReturn(email);
        when(email.getRegistration()).thenReturn(new AppSettings.Email.Registration());
        when(freeMarkerConfig.getTemplate(anyString())).thenReturn(mock(Template.class));
    }

    @Test(expected = EntityNotFoundException.class)
    public void tokenService_sendRegistrationToken_noExistingUser_ko() {
        service.sendEmailRegistrationToken(new User());
    }

    @Test
    public void tokenService_sendRegistrationToken_ok() {
        RequestContextHolder.setRequestAttributes(attrs);

        User user = new User("toto", "foo", "bar", "pass", "test@test.com", Company.DS, State.INVALID);

        // simulate existing user
        when(userRepository.exists(anyInt())).thenReturn(true);

        service.sendEmailRegistrationToken(user);

        verify(userRepository).save(user);
        ArgumentCaptor<User> userMailCapture = ArgumentCaptor.forClass(User.class);
        verify(mailService).sendMail(userMailCapture.capture(), anyString(), anyString());

        assertEquals(user, userMailCapture.getValue());

        assertNotNull(user.getToken());
    }

}
