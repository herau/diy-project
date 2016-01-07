package com.ds.ce.diy.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "accessories")
public class Accessory extends Rentable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "accessory_id")
    private Integer id;

    @Range
    @Column(nullable = false)
    @Setter @Getter
    private Integer quantity;

    @Embedded
    private Lifespan lifespan;

    protected Accessory() {}

    public Accessory(String serialNumber, String type, String brand, LocalDate purchaseDate, Double purchasePrice, String description, Double price) {
        super(type, brand, purchaseDate, purchasePrice,description,price);
        this.lifespan = new Lifespan(serialNumber);
    }

}
