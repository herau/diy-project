package com.ds.ce.diy.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "supplies")
public class Supply extends Rentable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "supply_id")
    private Integer id;

    @Range
    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "alert_threshold", nullable = false)
    private Integer alertThreshold;

    @Column(name = "max_booking", nullable = false)
    private Integer maxBooking;

    protected Supply(){}

    //TODO check that purchaseDate is wanted
    public Supply(String type, String brand, LocalDate purchaseDate, Double purchasePrice, String description, Double price) {
        super(type, brand, purchaseDate, purchasePrice, description, price);
    }

}
