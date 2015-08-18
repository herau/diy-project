package com.dassault_systemes.diy.repositories.search;

import com.dassault_systemes.diy.domain.Tool;

import java.util.List;

/**
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
