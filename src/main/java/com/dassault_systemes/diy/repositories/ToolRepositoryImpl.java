package com.dassault_systemes.diy.repositories;

import com.dassault_systemes.diy.domain.Tool;
import com.dassault_systemes.diy.repositories.search.AbstractSearchRepository;

import java.util.List;

/**
 * custom Spring Data JPA implementation for the ToolRepository.
 * @link http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.single-repository-behaviour
 * @author herau
 */
public final class ToolRepositoryImpl extends AbstractSearchRepository<Tool> {

    public ToolRepositoryImpl() {
        super(Tool.class);
    }

    @Override
    public List search(String query) {
        // add categories.name field for the search query
        return search(query, "name", "description", /*"energy",*/ "categories.name");
    }

}
