package com.dassault_systemes.diy.repositories.search;

import com.dassault_systemes.diy.domain.Tool;

import java.util.List;

/**
 * Spring Data JPA implementation for the SearchRepository Interface.
 * <p>the name of the class should be post fixed with "Impl" token </p>
 * @link http://docs.spring.io/spring-data/jpa/docs/1.8.2.RELEASE/reference/html/#repositories.custom-implementations
 * @author herau
 */
public final class ToolRepositoryImpl extends AbstractSearchRepository<Tool> {

    public ToolRepositoryImpl() {
        super(Tool.class);
    }

    @Override
    public List search(String query) {
        // add categories.name field for the search query
        return search(query, "name", "description", "energy", "categories.name");
    }

}
