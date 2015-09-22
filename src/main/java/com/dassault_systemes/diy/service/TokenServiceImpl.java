package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.State;
import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.domain.VerificationToken;
import com.dassault_systemes.diy.domain.VerificationTokenType;
import com.dassault_systemes.diy.repositories.UserRepository;
import com.dassault_systemes.diy.repositories.VerificationTokenRepository;
import com.dassault_systemes.diy.settings.AppSettings;
import com.dassault_systemes.diy.web.exceptions.EntityNotFoundException;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.net.URISyntaxException;
import java.util.Base64;

@Service
public class TokenServiceImpl implements TokenService {

    private final AppSettings settings;

    private final UserRepository userRepository;

    private final VerificationTokenRepository tokenRepository;

    private final MailService mailService;

    @Inject
    public TokenServiceImpl(AppSettings settings, UserRepository userRepository,
                            VerificationTokenRepository tokenRepository, MailService mailService) {
        this.settings = settings;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    @Override
    public VerificationToken sendEmailRegistrationToken(User user) throws URISyntaxException {
        //TODO create a service which remove tokens when expired
        VerificationToken token = new VerificationToken(user, VerificationTokenType.EMAIL_REGISTRATION,
                                                        settings.getEmail().getRegistration().getTokenExpiryTime());
        user.addVerificationToken(token);
        userRepository.save(user);

        HttpServletRequest servletRequest = getRequest();
        URIBuilder uriBuilder = new URIBuilder(servletRequest.getRequestURI());

        uriBuilder.setPath("/api/tokens/" + token.getToken());

        //TODO use template system
        mailService.sendMail(user, uriBuilder.toString());

        return token;
    }

    @Override
    public VerificationToken verifyToken(String tokenString) {
        VerificationToken token = tokenRepository.findByToken(new String(Base64.getDecoder().decode(tokenString)))
                                                 .orElseThrow(() -> new EntityNotFoundException(
                                                         "tokenString doesn't exist"));

        switch (token.getType()) {
            case EMAIL_REGISTRATION:
                if (!token.isValid() || token.getUser().getState() == State.VALID) {
                    return null;
                }
                break;
            default:
                // no default behavior
                break;
        }

        token.setVerified();

        tokenRepository.save(token);

        return token;
    }

    private HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Assert.state(requestAttributes != null, "Could not find current request via RequestContextHolder");
        Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }
}
