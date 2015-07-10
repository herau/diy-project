package com.dassault_systemes.diy.web;

import com.dassault_systemes.diy.config.Roles;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class TestController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @RolesAllowed(Roles.ADMIN)
    public String test() {
        return "ok";
    }
}


