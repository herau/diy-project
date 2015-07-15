package com.dassault_systemes.diy.web;

import org.springframework.security.access.prepost.PreAuthorize;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Named
@Path("test")
public class EndPoint {

    @GET
//    @RolesAllowed(Role.ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    public String message() {
        return "Hello";
    }

}
