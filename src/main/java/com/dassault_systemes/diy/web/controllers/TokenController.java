package com.dassault_systemes.diy.web.controllers;

import com.dassault_systemes.diy.domain.VerificationToken;
import com.dassault_systemes.diy.service.TokenService;
import com.dassault_systemes.diy.web.EntryPoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@RequestMapping(value = EntryPoint.TOKENS)
public class TokenController {

    private final TokenService tokenService;

    @Inject
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "{token}")
    public String checkToken(@PathVariable("token") String token) {
        VerificationToken verificationToken = tokenService.verifyToken(token);

        if (verificationToken == null) {
            //TODO redirect to 404 page
            throw new IllegalArgumentException("");
        }

        //        switch (verificationToken.getType()) {
//            case EMAIL_REGISTRATION:
//                page = "/";
//                break;
//            default:
//                // no default job
//                break;
//        }

        return "app";
    }

}
