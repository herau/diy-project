package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.State;
import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.domain.VerificationToken;
import com.ds.ce.diy.domain.VerificationTokenType;
import com.ds.ce.diy.repositories.UserRepository;
import com.ds.ce.diy.repositories.VerificationTokenRepository;
import com.ds.ce.diy.settings.AppSettings;
import com.ds.ce.diy.web.EntryPoint;
import com.ds.ce.diy.web.RequestUtils;
import com.ds.ce.diy.web.exceptions.EntityNotFoundException;
import com.ds.ce.diy.web.exceptions.TokenHasExpiredException;
import com.ds.ce.diy.web.exceptions.UserAlreadyRegisteredException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static com.ds.ce.diy.domain.VerificationTokenType.EMAIL_REGISTRATION;

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
        Assert.notNull(user);

        if (!userRepository.exists(user.getId())) {
            throw new EntityNotFoundException("unknown user [" + user + "]");
        }

        //TODO create a service which remove tokens when expired
        AppSettings.Email.Registration registration = settings.getEmail().getRegistration();
        VerificationToken token = new VerificationToken(user, EMAIL_REGISTRATION,
                                                        registration.getTokenExpiration());
        user.addVerificationToken(token);
        userRepository.save(user);

        HttpServletRequest servletRequest = RequestUtils.getRequest();

        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(servletRequest != null ? servletRequest.getRequestURL().toString() : "");
        } catch (URISyntaxException e) {
            // no job here because it's always a correct URI
            //TODO generate an exception handle and return code error 500
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
    public VerificationToken verifyToken(String base64EncodedToken) {
        VerificationToken token =
                tokenRepository.findByToken(new String(Base64.getDecoder().decode(base64EncodedToken)))
                               .orElseThrow(() -> new EntityNotFoundException("token doesn't exist"));

        if (token.isValid()) {
            throw new TokenHasExpiredException();
        }

        VerificationTokenType type = token.getType();

        if (EMAIL_REGISTRATION == type && State.VALID == token.getUser().getState()) {
            throw new UserAlreadyRegisteredException();
        }

        return token;
    }
}
