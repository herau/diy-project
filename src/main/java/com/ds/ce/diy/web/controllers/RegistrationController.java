package com.ds.ce.diy.web.controllers;

import com.ds.ce.diy.domain.Registration;
import com.ds.ce.diy.service.RegistrationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("api/registration")
@PreAuthorize("hasAnyAuthority('MEMBER, ADMIN')")
public class RegistrationController {

    private final RegistrationService service;

    @Inject
    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @RequestMapping(method = GET)
    public List<Registration> getAll() {
        service.
    }


}
