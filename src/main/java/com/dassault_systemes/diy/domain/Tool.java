package com.dassault_systemes.diy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "tools")
public class Tool implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "tool_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Size(min = 1)
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String description;

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private Energy energy;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private Localization localization;

    @Size
    @Column
    private Double weight;

    @Column(name = "documentation_url")
    private String documentationUrl;

    protected Tool(){}

}