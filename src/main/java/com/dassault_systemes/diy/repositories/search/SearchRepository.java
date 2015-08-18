package com.dassault_systemes.diy.repositories.search;

import java.util.List;

/**
 * Search methods for the entities using Hibernate search
 *
 * @author herau
 */
public interface SearchRepository<T> {

    /**
     * search in all @Field annotated fields of the entity
     *
     * @param query string query
     *
     * @return list of matching entities
     *
     * @see org.hibernate.search.annotations.Field
     */
    List<T> search(String query);
}
