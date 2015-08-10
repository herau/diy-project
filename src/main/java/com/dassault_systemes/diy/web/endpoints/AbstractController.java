package com.dassault_systemes.diy.web.endpoints;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class AbstractController {

    /**
     * build a HTTP location header from the current request url with the entity id
     *
     * @param entityId value of the id to add on the location url
     *
     * @return location uri
     */
    protected String getLocationHeader(String entityId) {
        return ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").buildAndExpand(entityId)
                                          .toUriString();
    }

}
