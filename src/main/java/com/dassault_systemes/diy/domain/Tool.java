package com.dassault_systemes.diy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tools")
public class Tool extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Size(min = 1)
    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Energy energy;

    @Size
    private int weight;

    @Column(name = "documentation_url")
    private String documentationUrl;

}
