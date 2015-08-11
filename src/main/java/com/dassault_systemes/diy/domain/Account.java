package com.dassault_systemes.diy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "account_id")
    private Integer id;

    @Column
    private Double balance = 0.0;

    protected Account() {}

    /**
     * credit the account
     *
     * @param value
     *
     * @return
     */
    public Double credit(Double value) {
        balance += value;
        return balance;
    }

    /**
     * debit the account
     *
     * @param value
     *
     * @return
     */
    public Double debit(Double value) {
        balance -= value;
        return balance;
    }

}
