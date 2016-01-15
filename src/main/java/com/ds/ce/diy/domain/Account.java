package com.ds.ce.diy.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "accounts")
public class Account extends AbstractAuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Integer id;

    @Column
    @Getter
    //TODO maximum negative value ?
    private Double balance = 0.0;

    public Account() {}

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
