package com.ds.ce.diy.repositories;

import com.ds.ce.diy.domain.Tool;
import com.ds.ce.diy.repositories.search.AbstractSearchRepository;

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
