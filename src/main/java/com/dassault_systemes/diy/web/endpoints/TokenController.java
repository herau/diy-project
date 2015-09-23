package com.dassault_systemes.diy.web.endpoints;

import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.domain.VerificationToken;
import com.dassault_systemes.diy.service.TokenService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/tokens")
public class TokenController {

    private final TokenService tokenService;

    @Inject
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @RequestMapping(method = GET, value = "/{token}")
    void checkToken(@RequestParam("token") String token) {
        VerificationToken verificationToken = tokenService.verifyToken(token);

        if (verificationToken == null) {
            //TODO redirect to 404 page
            throw new IllegalArgumentException("");
        }

        switch (verificationToken.getType()) {
            case EMAIL_REGISTRATION:

                break;
            default:
                // no default job
                break;
        }

        User user = verificationToken.getUser();

    }

}
