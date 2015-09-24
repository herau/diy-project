package com.ds.ce.diy.repositories;

import com.ds.ce.diy.domain.Tool;
import com.ds.ce.diy.repositories.search.SearchRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author herau
 */
@RepositoryRestResource(collectionResourceRel = "tools", path = "tools")
@PreAuthorize("hasAuthority('ADMIN')")
public interface ToolRepository extends CrudRepository<Tool, Integer>, SearchRepository<Tool> {

    @PreAuthorize("hasAnyAuthority('USER, ADMIN')")
    @Override
    Tool findOne(Integer integer);

}
