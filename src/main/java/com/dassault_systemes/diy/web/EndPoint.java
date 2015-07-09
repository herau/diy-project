package com.dassault_systemes.diy.web;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Named
@Path("hello")
public class EndPoint {

    @GET
    public String message() {
        return "Hello";
    }

}
