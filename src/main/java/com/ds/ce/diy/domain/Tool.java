package com.ds.ce.diy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tools")
@Indexed(index = "tools")
@JsonIgnoreProperties(value = "tags")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tool extends Rentable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "tool_id")
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    @Field
    @Analyzer(definition = "nGrams")
    private String name;

    @Size(min = 1)
    @Column(nullable = false, name = "rental_price")
    //TODO not the same than rentable#price ?
    private Double rentalPrice;

    @Size
    @Column
    private Double weight;

    @Size
    @Column(name = "max_size")
    private Double maxSize;

    @Column(name = "documentation_url")
    private String documentationUrl;

    @Column(nullable = false, updatable = false)
    @Getter
    private Nature nature;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "order_reference")
    private String orderReference;

    @ManyToMany
    @JoinTable(
            name = "tags_tools",
            joinColumns = @JoinColumn(name = "tool_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "tag_id", nullable = false))
    @IndexedEmbedded
    private List<Tag> tags;

    @ManyToOne
    private Tool parent;

    @OneToMany(mappedBy="parent")
    private Set<Tool> children;

    @Builder
    public Tool(String type, String brand, LocalDate purchaseDate, Double purchasePrice, String description,
                Double price, String name, Double rentalPrice, List<Tag> tags, Set<Tool> children, Tool parent,
                Double weight, Double maxSize, String documentationUrl, String orderNumber, String orderReference, Nature nature) {
        super(price, description, type, brand, purchaseDate, purchasePrice);
        this.name = name;
        this.rentalPrice = rentalPrice;
        this.tags = tags;
        this.children = children;
        this.parent = parent;
        this.weight = weight;
        this.maxSize = maxSize;
        this.documentationUrl = documentationUrl;
        this.orderNumber = orderNumber;
        this.orderReference = orderReference;
        this.nature = nature;
    }
}
