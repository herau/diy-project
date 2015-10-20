package com.ds.ce.diy.web.controllers;

import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.domain.VerificationToken;
import com.ds.ce.diy.service.TokenService;
import com.ds.ce.diy.web.EntryPoint;
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
        VerificationToken verifiedToken = tokenService.verifyToken(token);

        User user = verifiedToken.getUser();

        return "redirect:" + String.format(EntryPoint.PROFILE_PWD, user.getPersonalNumber());
    }

}
