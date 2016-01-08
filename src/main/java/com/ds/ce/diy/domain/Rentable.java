package com.ds.ce.diy.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"type", "brand"}, callSuper = false)
public abstract class Rentable extends AbstractAuditableEntity implements Serializable {

    @Size(min = 1)
    @Column(nullable = false)
    @Getter
    @Setter
    @NonNull
    private Double price;

    @Column(nullable = false)
    @Getter
    @Setter
    @NonNull
    private String description;

    @Column(nullable = false, updatable = false)
    @NonNull
    private String type;

    @Column(nullable = false, updatable = false)
    @NonNull
    private String brand;

    @Column(nullable = false, updatable = false, name = "purchase_date")
    @NonNull
    private LocalDate purchaseDate;

    @Column(nullable = false, updatable = false, name = "purchase_price")
    @NonNull
    private Double purchasePrice;

}
