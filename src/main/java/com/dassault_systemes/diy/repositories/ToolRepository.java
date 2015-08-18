package com.dassault_systemes.diy.repositories;

import com.dassault_systemes.diy.domain.Tool;
import com.dassault_systemes.diy.repositories.search.SearchRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author herau
 */
@RepositoryRestResource(collectionResourceRel = "tools", path = "tools")
@PreAuthorize("hasAuthority('ADMIN')")
public interface ToolRepository extends CrudRepository<Tool, Integer>, SearchRepository<Tool> {

    @PreAuthorize("hasAuthority('USER')")
    @Override
    Tool findOne(Integer integer);
}