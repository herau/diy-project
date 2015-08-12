package com.dassault_systemes.diy.repositories;

import com.dassault_systemes.diy.domain.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
@PreAuthorize("hasAuthority('ADMIN')")
public interface CategoryRepository extends JpaRepository<Category, Integer> {}
