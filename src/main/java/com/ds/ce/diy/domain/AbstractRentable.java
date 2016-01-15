package com.ds.ce.diy.domain;

import com.ds.ce.diy.domain.audit.AbstractAuditableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"type", "brand"}, callSuper = false)
public abstract class AbstractRentable extends AbstractAuditableEntity implements Serializable {

    @Size(min = 1)
    @Column(nullable = false, name = "rental_price")
    @Getter
    @Setter
    @NonNull
    @NotNull
    private Double rentalPrice;

    @Column(nullable = false, length = 510)
    @Getter
    @Setter
    @NonNull
    @NotBlank
    private String description;

    @Column(nullable = false, updatable = false)
    @NonNull
    @Getter
    @NotBlank
    private String type;

    @Column(nullable = false, updatable = false)
    @NonNull
    @Getter
    @NotBlank
    private String brand;

    @Column(nullable = false, updatable = false, name = "purchase_date")
    @NonNull
    @NotNull
    private LocalDate purchaseDate;

    @Column(nullable = false, updatable = false, name = "purchase_price")
    @NonNull
    private Double purchasePrice;

}
