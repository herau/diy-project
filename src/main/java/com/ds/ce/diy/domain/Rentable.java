package com.ds.ce.diy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
public class Rentable extends AbstractAuditableEntity implements Serializable {

    @Size(min = 1)
    @Column(nullable = false)
    @Getter
    @Setter
    private Double price;

    @Column(nullable = false)
    @Getter
    @Setter
    private String description;

    @Column(nullable = false, updatable = false)
    private String type;

    @Column(nullable = false, updatable = false)
    private String brand;

    @Column(nullable = false, updatable = false, name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(nullable = false, updatable = false, name = "purchase_price")
    private Double purchasePrice;

    protected Rentable(){}

    public Rentable(String type, String brand, LocalDate purchaseDate, Double purchasePrice, String description, Double price) {
        this.type = type;
        this.brand = brand;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.description = description;
        this.price = price;
    }
}
