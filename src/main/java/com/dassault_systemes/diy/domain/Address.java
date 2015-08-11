package com.dassault_systemes.diy.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;

    private String city;

    private String zip;
}