package com.ds.ce.diy.web;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.net.URISyntaxException;

/**
 * Created by n27 on 10/9/15.
 */
public final class RequestUtils {

    private RequestUtils() {}

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Assert.state(requestAttributes != null, "Could not find current request via RequestContextHolder");
        Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    /**
     * create a new string url from the request url and by adding the path parameter value
     * @param path
     * @return
     */
    public static String getRequestPath(final String path) {
        HttpServletRequest servletRequest = getRequest();

        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(servletRequest != null ? servletRequest.getRequestURL().toString() : "");
        } catch (URISyntaxException e) {
            // no job here because it's always a correct URI
            //TODO generate an exception handle and return code error 500
        }

        uriBuilder.setPath(path);

        return uriBuilder.toString();
    }

}
