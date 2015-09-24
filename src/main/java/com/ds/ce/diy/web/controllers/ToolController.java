package com.ds.ce.diy.web.controllers;

import com.ds.ce.diy.domain.Tool;
import com.ds.ce.diy.repositories.ToolRepository;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * custom Spring Data Rest method, exposed with ALPS/HATEOAS metadata
 */
@RepositoryRestController
@RequestMapping(value = "/tools")
public class ToolController {

    private final ToolRepository repository;

    @Inject
    ToolController(ToolRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/search", method = GET)
    @ResponseBody
    public Resources<Resource<Tool>> search(@RequestParam("query") String searchQuery,
                                            PersistentEntityResourceAssembler persistentEntityResourceAssembler) {
        List<Tool> tools = repository.search(searchQuery);
        Resources<Resource<Tool>> resources = Resources.wrap(tools);
        resources.forEach(toolResource -> toolResource
                .add(persistentEntityResourceAssembler.getSelfLinkFor(toolResource.getContent())));
        //TODO add links section to the current path
        return resources;
    }

}
