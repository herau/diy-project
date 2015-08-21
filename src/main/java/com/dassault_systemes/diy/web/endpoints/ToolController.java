package com.dassault_systemes.diy.web.endpoints;

import com.dassault_systemes.diy.domain.Tool;
import com.dassault_systemes.diy.repositories.ToolRepository;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
    public List<Tool> search(@RequestParam("query") String searchQuery) {
        return repository.search(searchQuery);
    }

}
