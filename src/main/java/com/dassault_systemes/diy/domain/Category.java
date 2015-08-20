package com.dassault_systemes.diy.domain;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "categories")
@Indexed(index = "categories")
public class Category extends AbstractAuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "category_id")
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotNull
    @Field
    private String name;

    protected Category() {
        // default constructor for entity
    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
