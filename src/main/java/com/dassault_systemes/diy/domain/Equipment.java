package com.dassault_systemes.diy.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "equipments")
public class Equipment extends AbstractAuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "equipment_id")
    private Integer id;

    @Range
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "alert_threshold", nullable = false)
    private Integer alertThreshold;

    @Column(name = "max_booking", nullable = false)
    private Integer maxBooking;

    @Column(nullable = false)
    private String description;

    public Equipment() {}

}
