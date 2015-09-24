package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.State;
import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.domain.VerificationToken;
import com.ds.ce.diy.domain.VerificationTokenType;
import com.ds.ce.diy.repositories.UserRepository;
import com.ds.ce.diy.repositories.VerificationTokenRepository;
import com.ds.ce.diy.settings.AppSettings;
import com.ds.ce.diy.web.EntryPoint;
import com.ds.ce.diy.web.exceptions.EntityNotFoundException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    private final AppSettings settings;

    private final UserRepository userRepository;

    private final VerificationTokenRepository tokenRepository;

    private final MailService mailService;

    private final Configuration freeMarkerConfig;

    @Inject
    public TokenServiceImpl(AppSettings settings, UserRepository userRepository,
                            VerificationTokenRepository tokenRepository, MailService mailService, Configuration freeMarkerConfiguration) {
        this.settings = settings;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
        this.freeMarkerConfig = freeMarkerConfiguration;
    }

    @Override
    public VerificationToken sendEmailRegistrationToken(User user) {
        //TODO create a service which remove tokens when expired
        AppSettings.Email.Registration registration = settings.getEmail().getRegistration();
        VerificationToken token = new VerificationToken(user, VerificationTokenType.EMAIL_REGISTRATION,
                                                        registration.getTokenExpiryTime());
        user.addVerificationToken(token);
        userRepository.save(user);

        HttpServletRequest servletRequest = getRequest();

        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(servletRequest.getRequestURL().toString());
        } catch (URISyntaxException e) {
            // no job here because it's always a correct URI
        }

        uriBuilder.setPath(EntryPoint.TOKENS + "/" + token.getToken());

        Map<String, String> rootMap = new HashMap<>();
        rootMap.put("username", user.getFirstname());
        rootMap.put("url", uriBuilder.toString());

        Writer stringTemplate = new StringWriter();
        try {
            Template template = freeMarkerConfig.getTemplate("mail_registration.ftl");
            template.process(rootMap, stringTemplate);
        } catch (IOException | TemplateException e) {
            logger.error("unable to load the template file: {}", e.getMessage());
        }

        mailService.sendMail(user, registration.getSubject(), stringTemplate.toString());

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
