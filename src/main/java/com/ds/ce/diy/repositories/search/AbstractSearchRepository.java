package com.ds.ce.diy.repositories.search;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author herau
 * @see Transactional ensure transactions will be opened and closed at the beginning and at the end of each method.
 */
public abstract class AbstractSearchRepository<T> implements SearchRepository {

    private final Class<T> typeParameterClass;

    private final String[] fields;

    @PersistenceContext
    private EntityManager entityManager;

    public AbstractSearchRepository(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
        this.fields = Arrays.stream(typeParameterClass.getDeclaredFields())
                            .filter(field -> field.getAnnotation(org.hibernate.search.annotations.Field.class) != null)
                            .map(Field::getName).toArray(String[]::new);
    }

    protected List<T> search(String query, String... fields) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        // create native Lucene query using the query DSL
        // alternatively you can write the Lucene query using the Lucene query parser
        // or the Lucene programmatic API. The Hibernate Search DSL is recommended though
        // @
        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(typeParameterClass).get();
        Query luceneQuery = queryBuilder.keyword().onFields(fields).matching(query)
                            .createQuery();

        // wrap Lucene query in a javax.persistence.Query and execute the search
        return fullTextEntityManager.createFullTextQuery(luceneQuery, typeParameterClass).getResultList();
    }

    @Override
    public List<T> search(String query) {
        return search(query, fields);
    }
}
