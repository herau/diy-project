package com.dassault_systemes.diy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(indexes = {@Index(name = "ix_auth_userId", columnList = "username,authority")})
public class Authorities implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(nullable = false)
    private String authority;

    protected Authorities() {
        // no-args constructor required by JPA spec
    }

    public User getUser() {
        return user;
    }

    public String getAuthority() {
        return authority;
    }
}
