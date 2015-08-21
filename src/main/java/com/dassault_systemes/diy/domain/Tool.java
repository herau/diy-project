package com.dassault_systemes.diy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "tools")
@Indexed(index = "tools")
public class Tool extends AbstractAuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "tool_id")
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    @Field
    @Analyzer(definition = "nGrams")
    private String name;

    @Size(min = 1)
    @Column(nullable = false)
    private Double price;

    @Size(min = 1)
    @Column(nullable = false, name = "rental_price")
    private Double rentalPrice;

    @Column(nullable = false, length = 500)
    @Field
    @Analyzer(definition = "nGrams")
    private String description;

    //TODO should be not nullable
    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Enumerated(EnumType.ORDINAL)
    //TODO should be not nullable
    @Column
    //TODO fix java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Enum for search behavior
    //    @Field
    //    @Analyzer(definition = "nGrams")
    private Energy energy;

    @Size
    @Column
    //TODO due to existing data, can be null
    private Double weight;

    @Size
    @Column(name = "max_size")
    //TODO due to existing data, can be null
    private Double maxSize;

    @Column(name = "documentation_url")
    private String documentationUrl;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private Localization localization;

    @ManyToMany
    @JoinTable(
            name = "categories_tools",
            joinColumns = @JoinColumn(name = "tool_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false))
    @IndexedEmbedded
    private List<Category> categories;

    protected Tool(){}

    public String getName() {
        return name;
    }

    public Double getRentalPrice() {
        return rentalPrice;
    }

    public String getDescription() {
        return description;
    }

    public Energy getEnergy() {
        return energy;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getMaxSize() {
        return maxSize;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }

    @JsonIgnore
    public List<Category> getCategories() {
        return categories;
    }
}
