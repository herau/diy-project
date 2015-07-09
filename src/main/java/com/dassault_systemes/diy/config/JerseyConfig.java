package com.dassault_systemes.diy.config;

import com.dassault_systemes.diy.web.EndPoint;

import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Named;
import javax.ws.rs.ApplicationPath;

import java.io.IOException;

/**
 * need one bean of type ResourceConfig in which you register all Jersey Endpoints
 */
@Named
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() throws IOException {
        register(EndPoint.class);
    }
}
