package com.dassault_systemes.diy.web.resources;

import com.dassault_systemes.diy.config.Role;
import com.dassault_systemes.diy.domain.User;
import com.dassault_systemes.diy.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {

    @Inject
    UserService service;

    @Inject
    ObjectMapper objectMapper;

    @GET
    @Path("{id}")
    @RolesAllowed(Role.ADMIN)
    public User getById(@PathParam("id") String id) {
        return service.findById(id);
    }

}
