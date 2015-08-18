package com.dassault_systemes.diy.web.endpoints;

import org.hibernate.search.jpa.Search;
import org.hibernate.search.stat.Statistics;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("api/stats")
@PreAuthorize("hasAuthority('ADMIN')")
public class StatisticsController {

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "search")
    public Statistics searchStats() {
        return Search.getFullTextEntityManager(entityManager).getSearchFactory().getStatistics();
    }

}
