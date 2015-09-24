package com.ds.ce.diy.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractAuditableEntity {

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @JsonProperty("created_date")
    @PreAuthorize("hasAuthority('ADMIN')")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @JsonProperty("last_modified_date")
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

}
