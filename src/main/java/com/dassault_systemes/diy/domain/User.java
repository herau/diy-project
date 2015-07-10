package com.dassault_systemes.diy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
// #TODO add creationDate, lastUpdatedDate
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    @Id
    @Column(updatable = false, nullable = false, unique = true, length = 11, name = "username")
    private String personalNumber;

    @Column(nullable = false, length = 25)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false, length = 55)
    private String firstname;

    @Column(nullable = false, length = 55)
    private String lastname;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(name = "personal_email")
    @Email
    private String personalEmail;

    @Column(columnDefinition = "NUMBER(1) DEFAULT 0 NOT NULL")
    @Enumerated(EnumType.ORDINAL)
    private State state;

    @Column(columnDefinition = "NUMBER(1) DEFAULT 0 NOT NULL")
    @Enumerated(EnumType.ORDINAL)
    private Company company;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Authorities> authorities;

    protected User() {
        // no-args constructor required by JPA spec and Jackson
    }

    public User(String personalNumber, String firstname, String lastname, String password, String email,
                Company company, State state) {
        this.personalNumber = personalNumber;
        this.firstname = firstname;
        this.password = password;
        this.lastname = lastname;
        this.email = email;
        this.company = company;
        this.state = state;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return new EqualsBuilder().append(personalNumber, user.personalNumber).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(personalNumber).toHashCode();
    }
}
