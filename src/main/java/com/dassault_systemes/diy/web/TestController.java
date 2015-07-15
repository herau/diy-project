package com.dassault_systemes.diy.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
//    @RolesAllowed(Role.ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    public String test() {
        return "ok";
    }
}


