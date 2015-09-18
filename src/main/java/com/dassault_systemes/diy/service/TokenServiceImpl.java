package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.domain.VerificationToken;
import com.dassault_systemes.diy.domain.VerificationTokenType;
import com.dassault_systemes.diy.repositories.UserRepository;
import com.dassault_systemes.diy.settings.AppSettings;

import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class TokenServiceImpl implements TokenService {

    private final AppSettings settings;

    private final UserRepository userRepository;

    private final MailService mailService;

    @Inject
    public TokenServiceImpl(AppSettings settings, UserRepository userRepository, MailService mailService) {
        this.settings = settings;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @Override
    public VerificationToken sendEmailRegistrationToken(User user) {
        VerificationToken token = new VerificationToken(user, VerificationTokenType.EMAIL_REGISTRATION,
                                                        settings.email.registration.getTokenExpiryTime());
        user.addVerificationToken(token);
        userRepository.save(user);
        //        TODO send verification token by mail
        //        emailServicesGateway.sendVerificationToken(new EmailServiceTokenModel(user, token, getConfig()
        // .getHostNameUrl()));

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Assert.state(requestAttributes != null, "Could not find current request via RequestContextHolder");
        Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();

        String content = token.getToken() + "\n " + servletRequest.getRequestURI();

        mailService.sendMail(user, content);

        return token;
    }
}
