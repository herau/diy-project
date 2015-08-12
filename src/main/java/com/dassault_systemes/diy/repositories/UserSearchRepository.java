package com.dassault_systemes.diy.repositories;

import com.dassault_systemes.diy.domain.User;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

/**
 * Search methods for the entity User using Hibernate search.
 *
 * @author herau
 * @see Transactional ensure transactions will be opened and closed at the beginning and at the end of each method.
 */
@Repository
@Transactional
public class UserSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<User> search(String searchQuery) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        // create native Lucene query using the query DSL
        // alternatively you can write the Lucene query using the Lucene query parser
        // or the Lucene programmatic API. The Hibernate Search DSL is recommended though
        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();
        Query luceneQuery =
                queryBuilder.keyword().onFields("firstname", "lastname", "personalNumber").matching(searchQuery)
                            .createQuery();

        // wrap Lucene query in a javax.persistence.Query and execute the search
        return fullTextEntityManager.createFullTextQuery(luceneQuery, User.class).getResultList();
    }

}
