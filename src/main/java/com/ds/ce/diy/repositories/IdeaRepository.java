package com.ds.ce.diy.repositories;

import com.ds.ce.diy.domain.Idea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ideas", path = "ideas")
public interface IdeaRepository extends CrudRepository<Idea, Integer> {}
