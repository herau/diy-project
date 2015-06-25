package com.dassault_systemes.diy.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
// #TODO add creationDate, lastUpdatedDate
public class User implements Serializable {

    @Id
    @Column(updatable = false, nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(name = "personal_email")
    @Email
    private String personalEmail;

    @Column(columnDefinition = "NUMBER(1) DEFAULT 0 NOT NULL")
    @Enumerated(EnumType.ORDINAL)
    private STATUS status;

    @OneToMany(mappedBy = "user")
    private List<Authorities> authorities = Collections.emptyList();

    protected User() {
        // no-args constructor required by JPA spec
    }

    public String getMatricule() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<Authorities> getAuthorities() {
        return authorities;
    }
}
