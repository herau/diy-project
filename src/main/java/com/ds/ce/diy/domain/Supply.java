package com.ds.ce.diy.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "supplies")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Supply extends AbstractRentable implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, name = "supply_id")
    private Integer id;

    @Range
    @Column(nullable = false)
    @Getter @Setter
    private Integer quantity;

    @Column(name = "alert_threshold", nullable = false, length = 5)
    @Getter @Setter
    private Integer alertThreshold;

    @Column(name = "max_booking", nullable = false, length = 5)
    @Getter @Setter
    private Integer maxBooking;

    //TODO check that purchaseDate is wanted
    public Supply(String type, String brand, LocalDate purchaseDate, Double purchasePrice, String description, Double price) {
        super(price, description, type, brand, purchaseDate, purchasePrice);
    }

}
