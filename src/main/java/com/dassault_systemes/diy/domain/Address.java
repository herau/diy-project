package com.dassault_systemes.diy.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Address {

    @NotNull
    private String street;

    @NotNull
    private String city;

    @Size(min = 5, max = 5)
    private Integer zip;
}