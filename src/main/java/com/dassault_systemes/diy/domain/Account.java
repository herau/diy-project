package com.dassault_systemes.diy.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account extends AbstractEntity {

    private Double balance;

}
