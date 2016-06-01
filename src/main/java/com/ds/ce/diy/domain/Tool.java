package com.ds.ce.diy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tools")
@Indexed(index = "tools")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tool extends AbstractRentable implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "tool_id")
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    @Field
    @Analyzer(definition = "nGrams")
    @Getter
    private String name;

    @Column
    private Double weight;

    @Column(name = "max_size")
    private Double maxSize;

    @Column(name = "documentation_url")
    private String documentationUrl;

    //TODO currently no data in DB
//    @Column(nullable = false, updatable = false)
//    @Getter
//    private Nature nature;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "order_reference")
    private String orderReference;

    @Embedded
    private Lifespan lifespan;

    @ManyToMany
    @JoinTable(
            name = "tags_tools",
            joinColumns = @JoinColumn(name = "tool_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "tag_id", nullable = false))
    @IndexedEmbedded
    @JsonIgnore
    private List<Tag> tags;

    @ManyToOne
    @JsonIgnore
    private Tool parent;

    @OneToMany(mappedBy="parent")
    @JsonIgnore
    private Set<Tool> children;

    @Builder
    public Tool(String type, String brand, LocalDate purchaseDate, Double purchasePrice, String description,
                String name, Double rentalPrice, List<Tag> tags, Set<Tool> children, Tool parent, Double weight, Double maxSize, String documentationUrl, String orderNumber, String orderReference) {
        super(rentalPrice, description, type, brand, purchaseDate, purchasePrice);
        this.name = name;
        this.tags = tags;
        this.children = children;
        this.parent = parent;
        this.weight = weight;
        this.maxSize = maxSize;
        this.documentationUrl = documentationUrl;
        this.orderNumber = orderNumber;
        this.orderReference = orderReference;
//        this.nature = nature;
        this.lifespan = new Lifespan();
    }
}
