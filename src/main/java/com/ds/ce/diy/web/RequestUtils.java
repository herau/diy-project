package com.ds.ce.diy.web;

import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.net.URI;

/**
 * @author Aurélien Leboulanger
 */
public final class RequestUtils {

    private RequestUtils() {}

    private static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Assert.state(requestAttributes != null, "Could not find current request via RequestContextHolder");
        Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    /**
     * @return url from the servlet request url with resolving the path parameter value.
     * @param path path to resolve with the servlet request url.
     */
    public static String getRequestPath(final String path) {
        Assert.notNull(path);

        final HttpServletRequest servletRequest = getRequest();
        if (servletRequest == null) {
            return path;
        }

        return URI.create(servletRequest.getRequestURL().toString()).resolve(path).normalize().toString();
    }
}
