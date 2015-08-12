package com.dassault_systemes.diy.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.List;

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

    @Column
    @Pattern(regexp = "[0-9]{10}")
    private String phone;

    @Column
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String fax;

    @Embedded
    @NotNull
    private Address address;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "supplier_id")
    private List<Contact> contacts;

    protected Supplier() {}

}
