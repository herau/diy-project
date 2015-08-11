package com.dassault_systemes.diy.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.io.Serializable;

@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "supplier_id")
    private Integer id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    @Email
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "[0-9]{10}")
    private String phone;

    @Column
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String fax;

    @Column
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String mobile;

    @Embedded
    private Address address;

    protected Supplier() {}

}
