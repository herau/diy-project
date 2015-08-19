package com.dassault_systemes.diy.search;

import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Build the Lucene index at application startup.
 * This is needed because the database is filled before and each time the web application is started.
 * In a normal web application probably you don't need to do this.
 *
 * @author herau
 */
@Component
@ConditionalOnWebApplication
public class BuildSearchIndex implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(BuildSearchIndex.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            Search.getFullTextEntityManager(entityManager).createIndexer().startAndWait();
        } catch (InterruptedException e) {
            logger.warn("An error occurred during the index building", e);
        }
    }
}
