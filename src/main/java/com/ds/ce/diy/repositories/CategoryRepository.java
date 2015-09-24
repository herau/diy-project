package com.ds.ce.diy.repositories;

import com.ds.ce.diy.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
@PreAuthorize("hasAuthority('ADMIN')")
public interface CategoryRepository extends JpaRepository<Category, Integer> {}
