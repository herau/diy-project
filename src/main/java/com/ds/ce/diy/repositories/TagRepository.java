package com.ds.ce.diy.repositories;

import com.ds.ce.diy.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "tags", path = "tags")
@PreAuthorize("hasAuthority('ADMIN')")
public interface TagRepository extends JpaRepository<Tag, Integer> {}
