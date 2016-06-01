package com.ds.ce.diy.domain;

import com.ds.ce.diy.domain.audit.AbstractAuditableEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@EqualsAndHashCode(of = {"type", "brand"}, callSuper = false)
abstract class AbstractRentable extends AbstractAuditableEntity implements Serializable {

    @NotNull
    @Column(nullable = false, name = "rental_price")
    @NonNull
    private Double rentalPrice;

    @NotBlank
    @Column(nullable = false, length = 510)
    @NonNull
    private String description;

    @NotBlank
    @Column(nullable = false, updatable = false)
    @NonNull
    private String type;

    @NotBlank
    @Column(nullable = false, updatable = false)
    @NonNull
    private String brand;

    @NotNull
    @Column(nullable = false, updatable = false, name = "purchase_date")
    @NonNull
    private LocalDate purchaseDate;

    @Column(nullable = false, updatable = false, name = "purchase_price")
    @NonNull
    private Double purchasePrice;
}
