package com.dassault_systemes.diy.domain;

import com.dassault_systemes.diy.config.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "users", indexes = {@Index(columnList = "personal_number", name = "user_personal_number_hidx")})
//TODO test creationDate, lastUpdatedDate
//TODO see if possible to add this configuration for the all application (via Spring auto-configuration)
@JsonInclude(Include.NON_NULL)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(updatable = false, nullable = false, unique = true, length = 11, name = "personal_number")
    private String personalNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 55)
    private String firstname;

    @Column(nullable = false, length = 55)
    private String lastname;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column(name = "personal_email")
    @Email
    private String personalEmail;

    @Column(columnDefinition = "NUMBER(1) DEFAULT 0 NOT NULL")
    @Enumerated(EnumType.ORDINAL)
    private State state;

    @Column(columnDefinition = "NUMBER(1) DEFAULT 0 NOT NULL")
    @Enumerated(EnumType.ORDINAL)
    private Company company;

    //    @Column(name = "created_date", nullable = false)
    //    @CreatedDate
    //    private LocalDateTime createdDate;
    //
    //    @Column(name = "modified_date")
    //    @LastModifiedDate
    //    private LocalDateTime modifiedDate;

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
        //this.createdDate = LocalDateTime.now();
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

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public Company getCompany() {
        return company;
    }

    //    @JsonProperty("created_date")
    //    @JsonIgnore
    //    public LocalDateTime getCreatedDate() {
    //        return createdDate;
    //    }
    //
    //    @JsonIgnore
    //    public LocalDateTime getModifiedDate() {
    //        return modifiedDate;
    //    }

    public State getState() {
        return state;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public Role getRole() {
        return role;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("personalNumber", personalNumber).append("role", role)
                                        .append("state", state).append("company", company).append("email", email)
                                        .append("lastname", lastname).append("firstname", firstname).toString();
    }
}
