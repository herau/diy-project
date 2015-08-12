package com.dassault_systemes.diy.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.io.Serializable;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "contact_id")
    private Integer id;

    @Column(nullable = false, length = 55)
    private String firstname;

    @Column(nullable = false, length = 55)
    private String lastname;

    @Column(nullable = false)
    @NotNull
    @Email
    private String email;

    @Column
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String mobile;

    @Column
    @Pattern(regexp = "[0-9]{10}")
    private String phone;

    protected Contact() {}

}
