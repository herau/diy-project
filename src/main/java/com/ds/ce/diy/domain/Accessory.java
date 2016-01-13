package com.ds.ce.diy.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "accessories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Accessory extends AbstractRentable implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, name = "accessory_id")
    private Integer id;

    @Range
    @Column(nullable = false)
    @Setter @Getter
    private Integer quantity;

    @Embedded
    private Lifespan lifespan;

    public Accessory(String type, String brand, LocalDate purchaseDate, Double purchasePrice, String description,
                     Double price) {
        super(price, description, type, brand, purchaseDate, purchasePrice);
        this.lifespan = new Lifespan();
    }

}
